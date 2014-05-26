package com.umeca.infrastructure.jqgrid.operation;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.jqgrid.mapper.JqgridObjectMapper;
import com.umeca.infrastructure.jqgrid.model.*;
import com.umeca.model.shared.EntityGrid;
import com.umeca.repository.shared.SelectFilterFields;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 4:59 PM
 */

@Repository("genericJqGridPageSortFilter")
public class GenericJqGridPageSortFilter<T, V extends EntityGrid> {

    @PersistenceContext(unitName = "punit")
    private javax.persistence.EntityManager entityManager;

    public JqGridResultModel find(JqGridFilterModel opts, SelectFilterFields selFil, Class<T> tClass, Class<V> vClass){
        JqGridResultModel result = new JqGridResultModel();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<V> cq = cb.createQuery(vClass);

        Root<T> r = cq.from(tClass);
        cq.multiselect(selFil.getFields(r));

        if(StringExt.isNullOrWhiteSpace(opts.getSord()) == false && StringExt.isNullOrWhiteSpace(opts.getSidx())== false){
            if(opts.getSord().trim().toLowerCase().equals(EntitySpecification.JQGRID_ASC)){
                cq.orderBy(cb.asc(selectExpression(r, opts.getSidx(), selFil)));
            }
            else{
                cq.orderBy(cb.desc(selectExpression(r, opts.getSidx(), selFil)));
            }
        }

        Long totalRecords = criteriaCount(cb, opts, tClass, selFil);

        buildQuery(cb, cq, r, opts, selFil);

        Long numRows = opts.getRows() > 0 ? opts.getRows() : 1l;
        TypedQuery<V> tqData = entityManager.createQuery(cq);
        List<V> lstEnt = tqData.getResultList();

        List<JqGridRowsModel> rows = new ArrayList<>();

        for(EntityGrid entity : lstEnt){
            JqGridRowsModel row = new JqGridRowsModel();
            row.setId(entity.getId());
            row.setCell(entity);
            rows.add(row);
        }

        result.setTotal((int)(totalRecords / numRows) + 1);
        result.setPage(opts.getPage());
        result.setRecords(totalRecords);
        result.setRows(rows);

        return result;
    }

    private Expression<?> selectExpression(Root<T> r, String field, SelectFilterFields selFil) {
        Expression<String> exp = selFil.setFilterField(r, field);
        if(exp == null)
            exp = r.get(field);
        return exp;
    }

    private Long criteriaCount(CriteriaBuilder cb, JqGridFilterModel opts, Class<T> tClass, SelectFilterFields selFil) {
        CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
        Root<T> r = cqCount.from(tClass);
        CriteriaQuery<Long> cq = cqCount.select(cb.count(r));
        buildQuery(cb, cq, r, opts, selFil);
        TypedQuery<Long> tqCount = entityManager.createQuery(cq);
        return tqCount.getSingleResult();
    }

    private void buildQuery(CriteriaBuilder cb, CriteriaQuery<?> cq, Root<T> r, JqGridFilterModel opts, SelectFilterFields selFil) {
        JqGridMultipleFilterModel filter = null;

        if(opts == null || (StringExt.isNullOrWhiteSpace(opts.filters) && opts.extraFilters == null))
            return;

        try{
            Predicate p = cb.conjunction();

            if(StringExt.isNullOrWhiteSpace(opts.filters) == false){
                filter = JqgridObjectMapper.map(opts.filters);
            }

            if(opts.extraFilters != null){
                if(filter == null){
                    filter = new JqGridMultipleFilterModel();
                    filter.setRules(new ArrayList<JqGridRulesModel>());
                }
                filter.getRules().addAll(opts.extraFilters);
            }

            for(JqGridRulesModel rule : filter.getRules()){
                //Por ahora sólo se aplican AND (conjuntion) entre las reglas y el operador LIKE (Búsqueda de comienza con...)
                Expression<String> exp = selFil.setFilterField(r, rule.field);

                if(exp == null)
                    exp = r.get(rule.field).as(String.class);

                switch (rule.op){
                    case JqGridFilterModel.COMPARE_EQUAL:
                        p.getExpressions().add(cb.equal(exp, rule.data));
                        break;
                    default:
                        p.getExpressions().add(cb.like(cb.lower(exp), rule.data.trim().toLowerCase() + "%"));
                        break;
                }
            }

            cq.where(p);
        }catch (Exception ex){
            return;
        }
    }

}

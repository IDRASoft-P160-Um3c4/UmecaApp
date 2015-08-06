package com.umeca.infrastructure.jqgrid.operation;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.jqgrid.mapper.JqgridObjectMapper;
import com.umeca.infrastructure.jqgrid.model.*;
import com.umeca.infrastructure.model.managerEval.ManagerevalView;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Calendar;
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
        tqData.setFirstResult((opts.getPage()-1) * opts.getRows());
        tqData.setMaxResults(opts.getRows());

        List<V> lstEnt = tqData.getResultList();

        List<JqGridRowsModel> rows = new ArrayList<>();

        for(EntityGrid entity : lstEnt){
            boolean cn = false;
            JqGridRowsModel row = new JqGridRowsModel();
            row.setId(entity.getId());
            row.setCell(entity);
            for (JqGridRowsModel rs : rows){
                if (rs.getId() == row.getId()){
                    cn = true;
                    if (vClass.getName() == ManagerevalView.class.getName()){
                        ManagerevalView mev = (ManagerevalView)entity;
                        ManagerevalView rsv = (ManagerevalView)rs.getCell();
                        rsv.setCrime(rsv.getCrime() + ", " + mev.getCrime());
                    }
                    break;
                }
            }

            if (!cn)
                rows.add(row);
        }

        result.setTotal((int)(totalRecords / numRows) + 1);
        result.setPage(opts.getPage());
        result.setRecords(totalRecords);
        result.setRows(rows);

        return result;
    }

    public JqGridResultModel find(JqGridFilterModel opts, SelectFilterFields selFil, boolean distinct,  Class<T> tClass, Class<V> vClass){
        try{
        JqGridResultModel result = new JqGridResultModel();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<V> cq = cb.createQuery(vClass);

        Root<T> r = cq.from(tClass);
        cq.multiselect(selFil.getFields(r));
        cq.distinct(distinct);

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
        tqData.setFirstResult((opts.getPage()-1) * opts.getRows());
        tqData.setMaxResults(opts.getRows());

        List<V> lstEnt = tqData.getResultList();

        List<JqGridRowsModel> rows = new ArrayList<>();

        for(EntityGrid entity : lstEnt){
            boolean cn = false;
            JqGridRowsModel row = new JqGridRowsModel();
            row.setId(entity.getId());
            row.setCell(entity);
            for (JqGridRowsModel rs : rows){
                if (rs.getId() == row.getId()){
                    cn = true;
                    if (vClass.getName() == ManagerevalView.class.getName()){
                        ManagerevalView mev = (ManagerevalView)entity;
                        ManagerevalView rsv = (ManagerevalView)rs.getCell();
                        rsv.setCrime(rsv.getCrime() + ", " + mev.getCrime());
                    }
                    break;
                }
            }

            if (!cn)
                rows.add(row);
        }

        result.setTotal((int)(totalRecords / numRows) + 1);
        result.setPage(opts.getPage());
        result.setRecords(totalRecords);
        result.setRows(rows);

        return result;
        }catch (Exception e){
            return  null;
        }
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
        //tqCount.setFirstResult(opts.getPage() * opts.getRows());
        //tqCount.setMaxResults(opts.getRows());
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
                        if(rule.bData!=null)//se agrega para comparar valores booleanos
                            p.getExpressions().add(cb.equal(exp, rule.bData));
                        else
                            p.getExpressions().add(cb.equal(exp, rule.data));
                        break;
                    case JqGridFilterModel.COMPARE_NOT_EQUAL:
                            p.getExpressions().add(cb.notEqual(exp, rule.data));
                        break;
                    case JqGridFilterModel.COMPARE_IN:
                        Predicate pIn = exp.in(rule.lstInOp);
                        p.getExpressions().add(pIn);
                        break;
                    case JqGridFilterModel.COMPARE_NOT_IN:
                        Predicate pNotIn = cb.not(exp.in(rule.lstInOp));
                        p.getExpressions().add(pNotIn);
                        break;
                    case JqGridFilterModel.COMPARE_LEFT_JOIN_EQUAL:
                        Predicate pOr = cb.or(cb.equal(exp,rule.data),exp.isNull());
                        p.getExpressions().add(pOr);
                        break;
                    case JqGridFilterModel.BETWEEN:
                        Expression<Calendar> expDt = r.get(rule.field);
                        p.getExpressions().add(cb.between(expDt, rule.dtIni, rule.dtEnd));
                        break;
                    case JqGridFilterModel.IS_NULL:
                        p.getExpressions().add(cb.isNull(exp));
                        break;
                    default:
                        p.getExpressions().add(cb.like(cb.lower(exp), rule.data.trim().toLowerCase() + "%"));
                        break;
                }

                //List<String> lst = new ArrayList<String>();
                //cb.in(exp.in(lst));
            }

            cq.where(p);
        }catch (Exception ex){
            return;
        }
    }

}

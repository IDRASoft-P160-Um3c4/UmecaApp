package com.umeca.infrastructure.jqgrid.operation;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridMultipleFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.mapper.JqgridObjectMapper;
import com.umeca.model.entities.account.Role;
import com.umeca.model.entities.account.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 5:24 PM
 */
public class EntitySpecification<T> {

    //public final String JQGRID_DESC = "desc";
    public static final String JQGRID_ASC = "asc";

    public Specification<T> byFilter(final String filters){
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> ent, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate = builder.conjunction();
                if(!StringExt.isNullOrWhiteSpace(filters)){
                    buildQuery(predicate, builder, ent, filters);
                }
                return predicate;
            }
        };
    }

    public Specification<T> byFilter(final String filters, final List<String> lstFields ){
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> ent, CriteriaQuery<?> query, CriteriaBuilder builder) {
                //builder.construct(yClass,  selections);
                if(lstFields != null && lstFields.size() > 0){
                    List<Selection<?>> lstSelections = new ArrayList<>();

                    for (String field : lstFields){
                        lstSelections.add(ent.get(field));
                    }

                    query.multiselect(lstSelections);
                }

                Predicate predicate = builder.conjunction();
                if(!StringExt.isNullOrWhiteSpace(filters)){
                    buildQuery(predicate, builder, ent, filters);
                }
                return predicate;
            }
        };
    }


    public Specification<User> byFilter(final String filters, final int i ){
        return new Specification<User>() {
            public Predicate toPredicate(Root<User> ent, CriteriaQuery<?> query, CriteriaBuilder builder) {
                //builder.construct(yClass,  selections);

                CriteriaQuery<User> q = builder.createQuery(User.class);
                Root<User> r = query.from(User.class);
                q.select(builder.construct(User.class, r.get("id"), r.get("username"), r.get("enabled")));

                Predicate predicate = builder.conjunction();
                if(!StringExt.isNullOrWhiteSpace(filters)){
                    buildQuery(predicate, builder, ent, filters, i);
                }

                q.where(predicate);


                return predicate;
            }
        };
    }

    private void buildQuery(Predicate predicate, CriteriaBuilder builder, Root<User> ent, String filters, int i) {
        JqGridMultipleFilterModel filter;
        try{
            filter = JqgridObjectMapper.map(filters);
            for(JqGridRulesModel rule : filter.getRules()){
                //Por ahora sólo se aplican AND (conjuntion) entre las reglas y el operador LIKE (Búsqueda de comienza con...)
                predicate.getExpressions().add(builder.like(builder.lower(ent.get(rule.field).as(String.class)), rule.data.trim().toLowerCase() + "%"));
            }
        }catch (Exception ex){
            return;
        }

    }


    private void buildQuery(Predicate predicate, CriteriaBuilder builder, Root<T> ent, String filters) {
        JqGridMultipleFilterModel filter;
        try{
            filter = JqgridObjectMapper.map(filters);
            for(JqGridRulesModel rule : filter.getRules()){
                //Por ahora sólo se aplican AND (conjuntion) entre las reglas y el operador LIKE (Búsqueda de comienza con...)
                predicate.getExpressions().add(builder.like(builder.lower(ent.get(rule.field).as(String.class)), rule.data.trim().toLowerCase() + "%"));
            }
        }catch (Exception ex){
            return;
        }

    }

    public Sort orderBy(JqGridFilterModel opts) {
        Sort sort;
        if(opts.getSord().trim().toLowerCase().equals(JQGRID_ASC))
            sort = new Sort(new Sort.Order(Sort.Direction.ASC, opts.getSidx()));
        else
            sort = new Sort(new Sort.Order(Sort.Direction.DESC, opts.getSidx()));
        return sort;  //To change body of created methods use File | Settings | File Templates.
    }
}

package com.umeca.repository.account;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRowsModel;
import com.umeca.infrastructure.jqgrid.operation.EntitySpecification;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.account.UserView;
import com.umeca.model.shared.EntityGrid;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/1/14
 * Time: 10:16 PM
 */

@Repository("userviewRepository")
public class UserViewRepository {
//sdfsdfsdf
    @PersistenceContext(unitName = "punit")
    private javax.persistence.EntityManager entityManager;

    public JqGridResultModel findAll(JqGridFilterModel opts){
        JqGridResultModel result = new JqGridResultModel();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserView> cq = cb.createQuery(UserView.class);
        Root<User> r = cq.from(User.class);
        cq.multiselect(r.get("id"), r.get("username"), r.get("enabled"), r.join("roles").get("role"));

        if(StringExt.isNullOrWhiteSpace(opts.getSord()) == false && StringExt.isNullOrWhiteSpace(opts.getSidx())== false){
            if(opts.getSord().trim().toLowerCase().equals(EntitySpecification.JQGRID_ASC)){
                cq.orderBy(cb.asc(r.get(opts.getSidx())));
            }
            else{
                cq.orderBy(cb.desc(r.get(opts.getSidx())));
            }
        }

        CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
        Root<User> from = cqCount.from(User.class);
        CriteriaQuery<Long> selCount = cqCount.select(cb.count(from));

        Predicate p = cb.conjunction();
        p.getExpressions().add(cb.like(cb.lower(r.get("username").as(String.class)), "user r%"));
        cq.where(p);

        Predicate p1 = cb.conjunction();
        p1.getExpressions().add(cb.like(cb.lower(from.get("username").as(String.class)), "user r%"));
        selCount.where(p1);

        TypedQuery<Long> tqCount = entityManager.createQuery(selCount);
        TypedQuery<UserView> tqData = entityManager.createQuery(cq);

        tqData.setFirstResult((opts.getPage()-1) * opts.getRows());
        tqData.setMaxResults(opts.getRows());

        List<UserView> lstEnt = tqData.getResultList();
        Long count = tqCount.getSingleResult();

        result.setTotal((int)(count / opts.getRows()) + 1);
        result.setPage(opts.getPage());
        result.setRecords(count);

        List<JqGridRowsModel> rows = new ArrayList<>();

        for(EntityGrid entity : lstEnt){
            JqGridRowsModel row = new JqGridRowsModel();
            row.setId(entity.getId());
            row.setCell(entity);
            rows.add(row);
        }
        result.setRows(rows);

        return result;
    }

}

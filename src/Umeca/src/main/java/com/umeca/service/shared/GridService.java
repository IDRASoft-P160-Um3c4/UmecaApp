package com.umeca.service.shared;


import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.jqgrid.mapper.JqgridObjectMapper;
import com.umeca.infrastructure.jqgrid.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GridService<T extends EntityGrid> {

    @PersistenceContext(unitName = "punit")
    private EntityManager entityManager;

    private Map<String, Object> filters;

    public JqGridResultModel toGrid(Class<T> type, String fieldName, Object value, JqGridFilterModel opts) {
        filters = new HashMap<>();
        filters.put(fieldName, value);
        return toGrid(type, opts);
    }

    public JqGridResultModel toGrid(Class<T> type, Map<String, Object> filters, JqGridFilterModel opts) {
        this.filters = filters;
        return toGrid(type, opts);
    }

    public JqGridResultModel toGrid(Class<T> type, JqGridFilterModel opts) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(type);
        CriteriaQuery<Long> count = cb.createQuery(Long.class);
        Root<T> r = q.from(type);
        Root<T> rCount = count.from(type);

        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ra.getRequest();
        Map<String, String[]> params = request.getParameterMap();

        if (params.containsKey("sord") && params.containsKey("sidx")) { //tipo de ordenacion sord y campo para ordenar sidx
            String sort = params.get("sord")[0];
            String orderField = params.get("sidx")[0];
            try {
                Path<String> field = r.get(orderField);
                if (sort.equals("asc"))
                    q.orderBy(cb.asc(field));
                else
                    q.orderBy(cb.desc(field));
            } catch (Exception ex) {
            }
        }

        List<Predicate> predicatesFilter = new ArrayList<>();
        List<Predicate> predicatesFilterCount = new ArrayList<>();
        List<Predicate> predicatesSearch = new ArrayList<>();
        List<Predicate> predicatesSearchCount = new ArrayList<>();

        JqGridMultipleFilterModel filter = null;

        if (opts == null)
            return null;


        if (StringExt.isNullOrWhiteSpace(opts.filters) == false) {//se obtienen los filtros que manda el jqgrid
            filter = JqgridObjectMapper.map(opts.filters);
        }


        if (filter != null) {//se agregan las reglas encontradas como filtros para construir la consulta sobre la vista
            for (JqGridRulesModel rule : filter.getRules()) {
                if (filters == null)
                    filters = new HashMap<>();
                filters.put(rule.getField(), rule.getData());
            }
        }

        if (filters != null) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) { //reviso los filtros si no son de tipo String se aplica el operador equals en caso contrario like
                Path<String> param = r.get(entry.getKey());

                if(entry.getValue().getClass().equals(String.class)){
                    predicatesFilter.add(cb.like(cb.lower(param), "%" + ((String)entry.getValue()).toLowerCase() + "%"));
                    param = rCount.get(entry.getKey());
                    predicatesFilterCount.add(cb.like(cb.lower(param), "%" + ((String)entry.getValue()).toLowerCase() + "%"));

                }else{
                    predicatesFilter.add(cb.equal(param, entry.getValue()));
                    param = rCount.get(entry.getKey());
                    predicatesFilterCount.add(cb.equal(param, entry.getValue()));
                }
            }
        }

        if (!predicatesFilter.isEmpty() && !predicatesSearch.isEmpty()) {
            q.where(cb.and(cb.and(predicatesFilter.toArray(new Predicate[]{})), cb.or(predicatesSearch.toArray(new Predicate[]{}))));
            count.where(cb.and(cb.and(predicatesFilterCount.toArray(new Predicate[]{})), cb.or(predicatesSearchCount.toArray(new Predicate[]{}))));
        } else if (!predicatesFilter.isEmpty()) {
            q.where(cb.and(predicatesFilter.toArray(new Predicate[]{})));
            count.where(cb.and(predicatesFilterCount.toArray(new Predicate[]{})));
        } else if (!predicatesSearch.isEmpty()) {
            q.where(cb.or(predicatesSearch.toArray(new Predicate[]{})));
            count.where(cb.or(predicatesSearchCount.toArray(new Predicate[]{})));
        }

        count.select(cb.count(rCount));
        Long counter = entityManager.createQuery(count).getSingleResult();

//        int limit = params.containsKey("limit") ? isNumeric(params.get("limit")[0], Integer.MAX_VALUE) : Integer.MAX_VALUE;
//        int offset = params.containsKey("offset") ? isNumeric(params.get("of10et")[0], 0) : 0;

        int limit = Integer.parseInt(params.get("rows")[0]); //filas por pagina
        int page = Integer.parseInt(params.get("page")[0]);

        CriteriaQuery<T> select = q.select(r);
        TypedQuery<T> tq = entityManager.createQuery(select);
        tq.setFirstResult(limit * (page - 1));
        tq.setMaxResults(limit);

        JqGridResultModel result = new JqGridResultModel();
        int totalpages = ((int) Math.ceil(counter.intValue()/limit))+1; // se calcula el

        result.setTotal(totalpages); //numero de paginas
        result.setPage(page);//pagina actual
        result.setRecords(counter);//numero de registros

        List<T> lstEnt = tq.getResultList();

        List<JqGridRowsModel> rows = new ArrayList<>();

        for (EntityGrid entity : lstEnt) {
            JqGridRowsModel row = new JqGridRowsModel();
            row.setId(entity.getId());
            row.setCell(entity);
            rows.add(row);
        }

        result.setRows(rows);

        filters = null;

        return result;
    }

    private int isNumeric(String string, int defaultValue) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
package com.umeca.infrastructure.jqgrid.operation;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRowsModel;
import com.umeca.model.shared.EntityGrid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/1/14
 * Time: 12:34 PM
 */

public class JqGridPageSortFilter<T extends EntityGrid> {

    public JqGridResultModel DoQuery(JqGridFilterModel opts, JpaSpecificationExecutor<T> repository, final List<String> lstFields){
        JqGridResultModel result = new JqGridResultModel();
        EntitySpecification<T> specification = new EntitySpecification<>();
        Page<T> entities;

        if(StringExt.isNullOrWhiteSpace(opts.getSord()) == false && StringExt.isNullOrWhiteSpace(opts.getSidx())== false){
            Sort sort = specification.orderBy(opts);
            entities = repository.findAll(specification.byFilter(opts.filters, lstFields), new PageRequest(opts.getPage()-1, opts.getRows(), sort));
        }
        else{
            entities = repository.findAll(specification.byFilter(opts.filters, lstFields), new PageRequest(opts.getPage()-1, opts.getRows()));
        }

        result.setTotal(entities.getTotalPages());
        result.setPage(entities.getNumber()+1);
        result.setRecords(entities.getTotalElements());

        List<JqGridRowsModel> rows = new ArrayList<>();

        for(EntityGrid entity : entities){
            JqGridRowsModel row = new JqGridRowsModel();
            row.setId(entity.getId());
            row.setCell(entity);
            rows.add(row);
        }

        result.setRows(rows);
        return result;


    }

}

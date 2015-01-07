package com.umeca.infrastructure.jqgrid.mapper;

import com.umeca.infrastructure.jqgrid.model.JqGridMultipleFilterModel;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/1/14
 * Time: 8:46 AM
 */
public class JqgridObjectMapper {
    public static JqGridMultipleFilterModel map(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(jsonString, JqGridMultipleFilterModel.class);
        } catch (Exception e) {
            throw new RuntimeException (e);
        }
    }
}

package com.umeca.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {

    public static <T> Map<String,Object> getMap(List<T> datos){
        Map<String,Object> modelMap = new HashMap<String,Object>(3);
        modelMap.put("total", datos.size());
        modelMap.put("data", datos);
        modelMap.put("success", true);
        return modelMap;
    }
    
    
    public static Map<String,Object> getModelMapError(String msg){
 
        Map<String,Object> modelMap = new HashMap<String,Object>(2);
        modelMap.put("message", msg);
        modelMap.put("success", false);
 
        return modelMap;
    }


}

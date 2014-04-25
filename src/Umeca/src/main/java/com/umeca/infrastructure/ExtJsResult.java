package com.umeca.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtJsResult {

    public static <T> Map<String,Object> mapOk(List<T> datos){
        Map<String,Object> modelMap = new HashMap<String,Object>(3);
        modelMap.put("total", datos.size());
        modelMap.put("data", datos);
        modelMap.put("success", true);
        return modelMap;
    }
    
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param datos
	 * @return
	 */
    public static <T> Map<String,Object> mapOk(List<T> datos, int total){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", total);
		modelMap.put("data", datos);
		modelMap.put("success", true);		
		return modelMap;
	}
	
	public static <T> Map<String,Object> mapOk(){		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("success", true);
		return modelMap;
	}



    
    public static Map<String,Object> mapError(String msg){
 
        Map<String,Object> modelMap = new HashMap<String,Object>(2);
        modelMap.put("message", msg);
        modelMap.put("success", false);
 
        return modelMap;
    }
    
    


}

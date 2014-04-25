package com.umeca.infrastructure;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;

public class ReflexionPojo {

	public static <T> boolean HasAtLeastOneValue(T model){
		try {
			for (PropertyDescriptor pd : Introspector.getBeanInfo(model.getClass()).getPropertyDescriptors()) {
				  if (pd.getReadMethod() != null && !"class".equals(pd.getName()) && pd.getName().equals("id") == false
						  && pd.getName().equals("solucion") == false)
				  {
					  Object val = pd.getReadMethod().invoke(model);  
					  if(val != null){
						  if(val instanceof String == false)
							  return true;
						  String sValue = (String)val;
						  
						  if(sValue.trim().equals("") == false)
							  return true;
					  }
				  } 
			}
		} catch (Exception e) {
		}
		
		return false;
	}


    public static <T> boolean HasAtLeastOneValue(T model, List<String> listaExclusion){
        try {
            boolean isExcluded = false;
            for (PropertyDescriptor pd : Introspector.getBeanInfo(model.getClass()).getPropertyDescriptors()) {

                String nombre = pd.getName();

                    if(!listaExclusion.contains(nombre)){
                        if (pd.getReadMethod() != null && !"class".equals(pd.getName()) && pd.getName().equals("id") == false
                                && pd.getName().equals("solucion") == false){

                            Object val = pd.getReadMethod().invoke(model);

                            if(val != null){
                                if(val instanceof String == false)
                                    return true;
                                String sValue = (String)val;

                                if(sValue.trim().equals("") == false)
                                    return true;
                            }
                        }
                   }
            }
        } catch (Exception e) {
        }

        return false;
    }


}

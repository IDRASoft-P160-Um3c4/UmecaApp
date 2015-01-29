package com.umeca.infrastructure.security;

import org.apache.commons.lang.StringEscapeUtils;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class StringEscape {

    public static String escapeText(String text) {
        String value = text;
        if (text != null) {
            value= text.replace("\"","");
            value= value.replace("'","");
            value = StringEscapeUtils.escapeHtml(value);
            value = StringEscapeUtils.escapeJavaScript(value);
        } else {
            value = "";
        }
        return value;
    }

    public static Object escapeAttrs(Object obj, String[] propsArr) {

        Class classType = obj.getClass();
        Field[] fields = classType.getDeclaredFields();
        String propName;
        String[] arrNameProp, arrProps_aux;
        Field fieldProp;

        for (int i = 0; i < propsArr.length; i++) { //itero las propiedades
            for (int j = 0; j < fields.length; j++) {
                String currProp = propsArr[i];
                arrNameProp = currProp.split("\\.");
                if (arrNameProp.length > 1) { //si es una composicion
                    propName = arrNameProp[0]; //obtengo el nombre de la propiedad
                    try {
                        fieldProp = classType.getDeclaredField(propName); //obtengo el campo, si no existe lanza excepcion
                        fieldProp.setAccessible(true);
                        Object objComp = fieldProp.get(obj);//obtengo el objeto
                        String prop = "";
                        for (int k = 1; k < arrNameProp.length; k++) {//empieza en uno ya que la posicion 0 es el nombre de la propiedad composicion
                            if (prop != "")
                                prop += ".";
                            prop += arrNameProp[k];
                        }
                        arrProps_aux = new String[]{prop};
                        fieldProp.set(obj, escapeAttrs(objComp, arrProps_aux));
                    } catch (Exception e) {//si no existe el campo
                        e.printStackTrace();
                    } finally {
                        break;
                    }
                } else {//no es una composicion
                    //si el campo es de tipo string y se llama igual que la propiedad actual
                    fields[j].setAccessible(true);//para poder acceder al campo
                    propName = propsArr[i];
                    if (fields[j].getType().equals(String.class) && fields[j].getName().equals(propName)) {
                        try {
                            String fieldValue = (String) fields[j].get(obj);
                            fieldValue = escapeText(fieldValue);
                            fields[j].set(obj, fieldValue);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            break;
                        }
                    }
                }
            }
        }
        return obj;
    }

}


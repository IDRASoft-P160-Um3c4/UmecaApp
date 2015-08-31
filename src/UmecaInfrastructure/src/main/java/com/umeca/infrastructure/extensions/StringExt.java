package com.umeca.infrastructure.extensions;

import java.util.List;

public class StringExt {
    public static boolean isNullOrWhiteSpace(java.lang.String param) {
        return param == null || param.trim().length() == 0;
    }

    public static String naOnEmpty(String supUserDone) {

        if(isNullOrWhiteSpace(supUserDone))
            return "NA";

        return supUserDone;
    }

    public static String joinToString(List<Long> lstData) {
        if(lstData == null || lstData.size() == 0){
            return null;
        }

        String sRet = "";
        for (Long data : lstData){
            sRet = sRet + (sRet == "" ? "" : ", ") + data;
        }
        return sRet;
    }
}

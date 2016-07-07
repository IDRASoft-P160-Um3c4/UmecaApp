package com.umeca.model.entities.shared;

import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SharedSystemSetting;

import java.util.Calendar;

public class MonitoringPlanCommons {

    public static final boolean calculateHasActPreAuth(Calendar authorizationTime, Calendar posAuthorizationChangeTime) {
        return authorizationTime != null && posAuthorizationChangeTime != null;
    }

    public static boolean calculateIsMonPlanSuspended(Calendar generationTime, Calendar authorizationTime, Calendar posAuthorizationChangeTime) {
        if(typeIsMonPlanSuspended(generationTime, authorizationTime, posAuthorizationChangeTime) == MonitoringConstants.AUTHORIZATION_OK)
            return false;
        return true;
    }

    public static final int typeIsMonPlanSuspended(Calendar generationTime, Calendar authorizationTime, Calendar posAuthorizationChangeTime) {
        //Primero revisar si es por autorización del plan o por autorización de las nuevas actividades
        if(generationTime != null && authorizationTime == null){
            long timeDifDays = (Calendar.getInstance().getTimeInMillis() - generationTime.getTimeInMillis()) / (SharedSystemSetting.MILISECONDS_PER_HOUR);
            if(timeDifDays >= SharedSystemSetting.MonPlanHoursToAuthorize){
                return MonitoringConstants.AUTHORIZATION_MONPLAN;
            }

        }else if(calculateHasActPreAuth(authorizationTime, posAuthorizationChangeTime)){
            long timeDifDays = (Calendar.getInstance().getTimeInMillis() - posAuthorizationChangeTime.getTimeInMillis()) / (SharedSystemSetting.MILISECONDS_PER_HOUR);
            if(timeDifDays >= SharedSystemSetting.MonPlanHoursToAuthorize){
                return MonitoringConstants.AUTHORIZATION_ACTMONPLAN;
            }
        }
        return MonitoringConstants.AUTHORIZATION_OK;
    }
}

package com.umeca.model.shared;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/6/14
 * Time: 2:21 PM
 */
public class MonitoringConstants {
    public final static String STATUS_NEW ="NUEVO";
    public final static String STATUS_PENDING_CREATION ="EN PROCESO DE GENERAR";
    public final static String STATUS_PENDING_AUTHORIZATION ="EN PROCESO DE AUTORIZAR";
    public final static String STATUS_AUTHORIZED ="AUTORIZADO";
    public final static String STATUS_REJECTED_AUTHORIZED ="RECHAZADO AUTORIZAR";
    public final static String STATUS_MONITORING ="EN PROCESO DE EJECUCIÓN";
    public final static String STATUS_PENDING_END ="EN PROCESO DE TERMINAR";
    public final static String STATUS_REJECTED_END ="RECHAZADO TERMINAR";
    public final static String STATUS_END ="TERMINADO";

    public final static List<String> LST_STATUS_AUTHORIZE_READY =
            new ArrayList<String>(){{add(STATUS_AUTHORIZED);add(STATUS_MONITORING);add(STATUS_REJECTED_END);}};

    public final static String STATUS_ACTIVITY_NEW = "NUEVA";
    public final static String STATUS_ACTIVITY_MODIFIED = "MODIFICADA";
    public final static String STATUS_ACTIVITY_DONE = "REALIZADA";
    public final static String STATUS_ACTIVITY_FAILED = "NO REALIZADA";
    public final static String STATUS_ACTIVITY_DELETED = "ELIMINADA";

    public final static String TYPE_COMMENT_AUTHORIZED = "AUTORIZAR";
    public final static String TYPE_COMMENT_LOG_ACCOMPLISHMENT = "REPORTE INCUMPLIMIENTO";
    public final static String TYPE_COMMENT_MONITORING_PLAN_END = "TERMINAR PLAN";
    public final static String TYPE_COMMENT_CASE_END = "TERMINAR CASO";
    public final static String TYPE_COMMENT_ASSIGNED_CASE = "CASO ASIGNADO";

    public static final int ACTIVITY_ARRANGEMENT_UNDEFINED = -1;
    public static final int ACTIVITY_ARRANGEMENT_FAILED = 0;
    public static final int ACTIVITY_ARRANGEMENT_DONE = 1;

    public static final String LOG_PENDING_ACCOMPLISHMENT = "SOLICITUD AUTORIZAR REPORTE INCUMPLIMIENTO";
    public static final String LOG_ACCOMPLISHMENT_AUTHORIZED = "AUTORIZAR REPORTE INCUMPLIMIENTO";
    public static final String LOG_ACCOMPLISHMENT_REJECTED = "RECHAZADO REPORTE INCUMPLIMIENTO";

}

package com.umeca.model.shared;

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


    public final static String STATUS_ACTIVITY_NEW = "NUEVA";
    public final static String STATUS_ACTIVITY_MODIFIED = "MODIFICADA";
    public final static String STATUS_ACTIVITY_DONE = "REALIZADA";
    public final static String STATUS_ACTIVITY_FAILED = "NO REALIZADA";
    public final static String STATUS_ACTIVITY_DELETED = "ELIMINADA";

    public final static String TYPE_COMMENT_AUTHORIZED = "AUTORIZAR";
    public final static String TYPE_COMMENT_END = "TERMINAR";

    public static final int ACTIVITY_ARRANGEMENT_UNDEFINED = -1;
    public static final int ACTIVITY_ARRANGEMENT_FAILED = 0;
    public static final int ACTIVITY_ARRANGEMENT_DONE = 1;
}

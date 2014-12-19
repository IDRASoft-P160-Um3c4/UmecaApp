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
    public final static String STATUS_NEW = "NUEVO";
    public final static String STATUS_PENDING_CREATION = "EN PROCESO DE GENERAR";
    public final static String STATUS_PENDING_AUTHORIZATION = "EN PROCESO DE AUTORIZAR";
    public final static String STATUS_AUTHORIZED = "AUTORIZADO";
    public final static String STATUS_REJECTED_AUTHORIZED = "RECHAZADO AUTORIZAR";
    public final static String STATUS_MONITORING = "EN SEGUIMIENTO";
    public final static String STATUS_PENDING_END = "EN PROCESO DE TERMINAR";
    public final static String STATUS_REJECTED_END = "RECHAZADO TERMINAR";
    public final static String STATUS_END = "TERMINADO";

    public final static List<String> LST_STATUS_AUTHORIZE_READY =
            new ArrayList<String>() {{
                add(STATUS_AUTHORIZED);
                add(STATUS_MONITORING);
                add(STATUS_REJECTED_END);
            }};

    public final static String STATUS_ACTIVITY_NEW = "NUEVA";
    public final static String STATUS_ACTIVITY_PRE_NEW = "PRE_NUEVA";
    public final static String STATUS_ACTIVITY_MODIFIED = "MODIFICADA";
    public final static String STATUS_ACTIVITY_PRE_MODIFIED = "PRE_MODIFICADA";
    public final static String STATUS_ACTIVITY_DONE = "REALIZADA";
    public final static String STATUS_ACTIVITY_FAILED = "NO REALIZADA";
    public final static String STATUS_ACTIVITY_DELETED = "ELIMINADA";
    public final static String STATUS_ACTIVITY_PRE_DELETED = "PRE_ELIMINADA";

    public final static List<String> LST_STATUS_ACTIVITY_END =
            new ArrayList<String>() {{
                add(STATUS_ACTIVITY_DELETED);
                add(STATUS_ACTIVITY_DONE);
                add(STATUS_ACTIVITY_FAILED);
                add(STATUS_ACTIVITY_PRE_DELETED);
            }};

    public final static List<String> LST_STATUS_ACTIVITY_PRE_AUTH =
            new ArrayList<String>() {{
                add(STATUS_ACTIVITY_PRE_NEW);
                add(STATUS_ACTIVITY_PRE_MODIFIED);
                add(STATUS_ACTIVITY_PRE_DELETED);
            }};

    public final static String TYPE_COMMENT_AUTHORIZED = "AUTORIZAR";
    public final static String TYPE_COMMENT_LOG_ACCOMPLISHMENT = "REPORTE INCUMPLIMIENTO";
    public final static String TYPE_COMMENT_MONITORING_PLAN_END = "TERMINAR PLAN";
    public final static String TYPE_COMMENT_CASE_END = "TERMINAR CASO";
    public final static String TYPE_COMMENT_REOPEN_CASE = "REABRIR CASO";
    public final static String TYPE_COMMENT_ASSIGNED_CASE = "CASO ASIGNADO";
    public final static String TYPE_COMMENT_SCHEDULE_HEARING = "AUDIENCIA AGENDADA";

    public final static String TYPE_INFORMATION = "INFORMATION";

    public static final int ACTIVITY_ARRANGEMENT_UNDEFINED = -1;
    public static final int ACTIVITY_ARRANGEMENT_FAILED = 0;
    public static final int ACTIVITY_ARRANGEMENT_DONE = 1;

    public static final String ACTIVITY_ARRANGEMENT_UNDEFINED_STRING = "No definido";
    public static final String ACTIVITY_ARRANGEMENT_FAILED_STRING = "Incumplido";
    public static final String ACTIVITY_ARRANGEMENT_DONE_STRING = "Cumplido";

    public static final String LOG_PENDING_ACCOMPLISHMENT = "SOLICITUD AUTORIZAR REPORTE INCUMPLIMIENTO";
    public static final String LOG_ACCOMPLISHMENT_AUTHORIZED = "AUTORIZAR REPORTE INCUMPLIMIENTO";
    public static final String LOG_ACCOMPLISHMENT_REJECTED = "RECHAZADO REPORTE INCUMPLIMIENTO";

    public static final String LOG_MSG_INFO_PENDING_AUTHORIZATION = "En espera de la autorización del plan de seguimiento";
    public static final String LOG_MSG_INFO_PENDING_AUTHORIZATION_OBSOLETE = "En espera de respuesta a la solicitud de eliminar caso";
    public static final String LOG_MSG_INFO_PENDING_ACTIVITY_AUTHORIZATION =
            "Se crearon, modificaron o eliminaron actividades del plan de seguimiento, se espera autorización. Si no autoriza se suspenderá el plan.";

    public static final String LOG_MSG_INFO_OPEN_CASE_NOT_PROSECUTE = "Se abri&oacute; un caso no judicializado";
    public static final int AUTHORIZATION_MONPLAN = 1;
    public static final int AUTHORIZATION_ACTMONPLAN = 2;
    public static final int AUTHORIZATION_OK = 0;
    public static final Long FILTER_ACTIVE_CASE = -1L;
    public static final Long FILTER_SUSPENDED_CASE = -2L;
}

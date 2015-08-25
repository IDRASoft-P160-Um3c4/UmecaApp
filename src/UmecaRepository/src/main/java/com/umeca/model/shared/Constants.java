package com.umeca.model.shared;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 19/05/14
 * Time: 06:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Constants {
    public static final Long ELECTION_YES = 1L;
    public static final Long ELECTION_NO = 2L;
    public static final String VERIFICATION_STATUS_NEW_SOURCE = "NEW_SOURCE";
    public static final String VERIFICATION_STATUS_AUTHORIZED = "AUTHORIZED";
    public static final String VERIFICATION_STATUS_MEETING_COMPLETE = "MEETING_COMPLETE";

    public static final String VALUE_NOT_KNOW_SOURCE = "La fuente desconoce la información.";
    public static final String UNABLE_VERIF_TEXT = "No hay forma de verificar la información";
    public static final String UNABLE_VERIF_TEXT_DOC = "No fue posible verificar.";
    public static final String[] TABS_MEETING = {"imputed", "imputedHome", "socialNetwork", "reference", "job", "school", "drug", "leaveCountry"};
    public static final String[] ENTITIES_MEETING = {"imputed", "Domicilio", "Persona", "Referencia", "Trabajo", "school", "Sustancia", "leaveCountry"};
    public static final String[] NAMES_MEETING = {"imputed.", "imputedHomes.", "socialNetwork.", "references.", "jobs.", "school.", "drugs.", "leaveCountry."};
    public static final String ST_REQUEST_CHANGE_SOURCE = "CHANGE_STATUS_SOURCE";
    public static final String ST_REQUEST_CASE_OBSOLETE = "CASE_OBSOLETE";
    public static final String ST_REQUEST_EDIT_MEETING = "EDIT_MEETING";
    public static final String ST_REQUEST_EDIT_LEGAL_INFORMATION = "EDIT_LEGAL_INFORMATION";
    public static final String ST_REQUEST_EDIT_TECHNICAL_REVIEW = "EDIT_TECHNICAL_REVIEW";
    public static final String ST_REQUEST_GET_FREEDOM = "GET_FREEDOM";
    public static final String RESPONSE_TYPE_DRESSED = "DRESSED";
    public static final String ST_REQUEST_CASE_OBSOLETE_SUPERVISION = "CASE_OBSOLETE_SUPERVISION";
    public static final String CASE_STATUS_REQUEST_SUPERVISION = "ST_CASE_REQUEST_SUPERVISION";
    public static final String TYPE_COMMENT_OBSOLETE_CASE_SUPERVISION = "OBSOLETE_CASE_SUPERVISION";
    public static final String RESPONSE_OBSOLETE_CASE_SUPERVISION = "Respuesta a solicitud de eliminar un caso";
    public static final Long ENTITY_ID_NULL = -1l;
    public static final Integer ACT_REPORT_FOR_DIRECTOR = 1;
    public static final Integer ACT_REPORT_FOR_NOBODY = 0;
    public static final String FILE_PREFIX_USER = "USR_";
    public static final String FILE_PREFIX_PHOTO_EMPLOYEE = "PHOTO_EMPLOYEE_";
    public static final Long CHANNELING_ID_TYPE_FILE_CHANNELING_END_RECORD = 4l;
    public static final String CHANNELING_FULFILL_TITLE = "RESULTADO DE LA IMPOSICIÓN";

    public static String S_MEETING_INCOMPLETE = "INCOMPLETE";
    public static String S_MEETING_DECLINE = "DECLINE";
    public static String S_MEETING_INCOMPLETE_LEGAL = "INCOMPLETE_LEGAL";
    public static String S_MEETING_COMPLETE = "COMPLETE";
    public static String S_MEETING_COMPLETE_VERIFICATION = "COMPLETE_VERIFICATION";
    public static final DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
    public static Boolean GENDER_FEMALE = true;
    public static Boolean GENDER_MALE = false;

    public static final Long REGYSTER_TYPE_CURRENT = 1L;
    public static final Long REGYSTER_TYPE_SECONDARY = 2L;
    public static final Long REGYSTER_TYPE_PREVIOUS = 3L;

    public static final String TECHNICAL_REVIEW_QUESTIONARY_CODE = "TECHNICAL_REVIEW";
    public static final Integer CONDITIONAL_REPRIEVE_HEARING = 1;
    public static final Integer MEETING_HEARING = 2;

    public static final String KEY_CYPHER_APP = "1234567890123481";

    //status del caso
    public static final String CASE_STATUS_MEETING = "ST_CASE_MEETING";
    public static final String CASE_STATUS_SOURCE_VALIDATION = "ST_CASE_SOURCE_VALIDATION";
    public static final String ALPHA2_MEXICO = "MX";
    public static final String CASE_STATUS_HEARING_FORMAT_INCOMPLETE = "ST_CASE_HEARING_FORMAT_INCOMPLETE";
    public static final String CASE_STATUS_HEARING_FORMAT_END = "ST_CASE_HEARING_FORMAT_END";
    public static final String CASE_STATUS_VERIFICATION_COMPLETE = "ST_CASE_VERIFICATION_COMPLETE";
    public static final String CASE_STATUS_TECHNICAL_REVIEW = "ST_CASE_TECHNICAL_REVIEW_COMPLETE";
    public static final String CASE_STATUS_INCOMPLETE_TECHNICAL_REVIEW = "ST_CASE_TECHNICAL_REVIEW_INCOMPLETE";
    public static final String CASE_STATUS_VERIFICATION = "ST_CASE_VERIFICATION";

    public static final String CASE_STATUS_CONDITIONAL_REPRIEVE = "ST_CASE_CONDITIONAL_REPRIEVE";
    public static final String CASE_STATUS_FRAMING_INCOMPLETE = "ST_CASE_FRAMING_MEETING_INCOMPLETE";
    public static final String CASE_STATUS_FRAMING_COMPLETE = "ST_CASE_FRAMING_MEETING_COMPLETE";
    public static final String CASE_STATUS_REQUEST = "ST_CASE_REQUEST";
    public static final String CASE_STATUS_CLOSE_REQUEST = "ST_CASE_CLOSE_REQUEST";
    public static final String CASE_STATUS_EDIT_TEC_REV = "ST_CASE_EDIT_TEC_REV";
    public static final String CASE_STATUS_NOT_PROSECUTE = "ST_CASE_NOT_PROSECUTE";
    public static final String CASE_STATUS_NOT_PROSECUTE_OPEN = "ST_CASE_NOT_PROSECUTE_OPEN";
    public static final String CASE_STATUS_ONLY_MEETING = "ST_CASE_ONLY_MEETING";
//    public static final String CASE_STATUS_CLOSE_FORGIVENESS = "ST_CASE_CLOSE_FORGIVENESS";
//    public static final String CASE_STATUS_CLOSE_AGREEMENT = "ST_CASE_CLOSE_AGREEMENT";
//    public static final String CASE_STATUS_CLOSE_DESIST = "ST_CASE_CLOSE_DESIST";
//    public static final String CASE_STATUS_CLOSE_OTHER = "ST_CASE_CLOSE_OTHER";

    //sataus field verification
    public static final String ST_FIELD_VERIF_DONTKNOW = "DONT_KNOW";
    public static final String ST_FIELD_VERIF_EQUALS = "EQUALS";
    public static final String ST_FIELD_VERIF_NOEQUALS = "NO_EQUALS";
    public static final String ST_FIELD_VERIF_UNABLE = "UNABLE_VERIFICATION";

    public static final String ST_FIELD_VERIF_IMPUTED = "IS_IMPUTED";
    //    public static final String CASE_STATUS_PRE_CLOSED = "ST_CASE_PRE_CLOSED";
    public static final String CASE_STATUS_CLOSED = "ST_CASE_CLOSED";
//    public static final String CASE_STATUS_PRISON_CLOSED = "ST_CASE_PRISON_CLOSED";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_REVIEWER = "ROLE_REVIEWER";
    public static final String ROLE_EVALUATION_MANAGER = "ROLE_EVALUATION_MANAGER";
    public static final String ROLE_SUPERVISOR = "ROLE_SUPERVISOR";
    public static final String ROLE_SUPERVISOR_MANAGER = "ROLE_SUPERVISOR_MANAGER";
    public static final String ROLE_NOTUSE = "ROLE_NOTUSE";
    public static final String ROLE_ANONYMOUS = "ANONYMOUS";
    public static final String ROLE_HUMAN_RESOURCES = "ROLE_HUMAN_RESOURCES";
    public static final String ROLE_CHANNELING_MANAGER = "ROLE_CHANNELING_MANAGER";
    public static final String ROLE_DIRECTOR = "ROLE_DIRECTOR";
    public static final String ROLE_ATTORNEY_REGISTRATION_ = "ROLE_ATTORNEY_REGISTRATION";

    public static String FORMAT_CALENDAR_I = "dd/MM/yyyy HH:mm";
    public static String FORMAT_CALENDAR_II = "dd-MM-yyyy";
    public static String FORMAT_CALENDAR_III = "dd-MM-yyyy HH:mm";
    public static String FORMAT_VERIFICATION_DATE = "yyyy-MM-dd hh:mm:ss.S";
    public static String FORMAT_TIME_I = "HH:mm";

    public static final String SYSTEM_SETTINGS_ARCHIVE = "ARCHIVE";
    public static final String SYSTEM_SETTINGS_ARCHIVE_MAX_NUMBER_FILES = "MaxNumberFiles";
    public static final String SYSTEM_SETTINGS_ARCHIVE_MAX_SIZE_FILES = "MaxSizeFiles";
    public static final String SYSTEM_SETTINGS_ARCHIVE_PATH_TO_SAVE = "PathToSave";
    public static final String SYSTEM_SETTINGS_ARCHIVE_EMPLOYEE_PHOTO_PATH_TO_SAVE = "EmployeePhotoPathToSave";
    public static final String SYSTEM_SETTINGS_ARCHIVE_EMPLOYEE_PHOTO_TEMPORAL_PATH_TO_SAVE = "TemporalImgPathToSave";

    public static final String SYSTEM_SETTINGS_MONPLAN = "MONPLAN";
    public static final String SYSTEM_SETTINGS_MONPLAN_HOURS_TO_AUTHORIZE = "HoursToAuthorize";

    public static final String SYSTEM_SETTINGS_SCHEDULE_HEARING = "SCHEDULE_HEARING";
    public static final String SYSTEM_SETTINGS_SCHEDULE_LST_IDS_ARRANGEMENT = "LstIdsArrangement";
    public static final String SYSTEM_SETTINGS_SCHEDULE_LST_IDS_ARRANGEMENT_REMINDER = "LstIdsArrangementReminder";
    public static final String SYSTEM_SETTINGS_SCHEDULE_HEARING_SUPERVISION_ACTIVITY_ID = "SupervisionActivityId";
    public static final String SYSTEM_SETTINGS_SCHEDULE_HEARING_SUPERVISION_ACTIVITY_ID_REMINDER = "SupervisionActivityIdReminder";
    public static final String SYSTEM_SETTINGS_SCHEDULE_HEARING_GOAL_ACTIVITY_ID = "GoalActivityId";
    public static final String SYSTEM_SETTINGS_SCHEDULE_HEARING_GOAL_ACTIVITY_ID_REMINDER = "GoalActivityIdReminder";
    public static final String SYSTEM_SETTINGS_SCHEDULE_HEARING_DAYS_BEFORE_FOR_REMINDER = "DaysBeforeForReminder";


    public static String VERIFICATION_STATUS_COMPLETE = "VERIFICATION_COMPLETE";

    public static String STR_REVIEWER_NOTIF_NO_SOURCES = "NO_SOURCES";
    public static String STR_REVIEWER_NOTIF_SOURCES_NO_MEETING = "SOURCES_NO_MEETING";

    public static String CODE_S1_TEC_REV = "OT_S1";
    public static String CODE_S2_TEC_REV = "OT_S2";
    public static String CODE_S3_TEC_REV = "OT_S3";
    public static String CODE_S4_TEC_REV = "OT_S4";
    public static String CODE_S5_TEC_REV = "OT_S5";

    public static String TEC_REV_HIGH_RISK = "Riesgo alto!: Libertad muy difícil de cumplir.";
    public static String TEC_REV_MEDIUM_RISK = "Riesgo medio!: Se puede recomendar combinación de medidas cautelares en libertad bajo niveles de supervisión.";
    public static String TEC_REV_LOW_RISK = "Riesgo bajo!: Se puede recomendar combinación de medidas cautelares en libertad bajo niveles de supervisión.";
    public static String TEC_REV_MINIMUM_RISK = "Riesgo mínimo!: Se puede recomendar combinación de medidas cautelares en libertad bajo niveles de supervisión.";

    public static final Integer[] IDS_TABS_MEETING = {1, 2, 3, 4, 5, 6, 7, 8};

    public static final String RESPONSE_TYPE_PENDING = "PENDING";
    public static final String RESPONSE_TYPE_ACCEPTED = "ACCEPTED";
    public static final String RESPONSE_TYPE_REJECTED = "REJECTED";
    //public static final String CASE_STATUS_OBSOLETE_EVALUATION = "ST_CASE_OBSOLETE_EVALUATION";
//    public static final String CASE_STATUS_OBSOLETE_SUPERVISION = "ST_CASE_OBSOLETE_SUPERVISION";

    public static final Long MARITAL_SINGLE = 1L;
    public static final Long MARITAL_MARRIED = 2L;
    public static final Long MARITAL_DIVORCED = 3L;
    public static final Long MARITAL_UNION_FREE = 4L;
    public static final Long MARITAL_WIDOWER = 5L;

    public static final Long AC_LVL_ILLITERATE = 1L;
    public static final Long AC_LVL_PRIMARY = 2L;
    public static final Long AC_LVL_HIGH_SCH = 3L;
    public static final Long AC_LVL_BACHELOR = 4L;
    public static final Long AC_LVL_UNIVERSITY = 5L;
    public static final Long AC_LVL_GRADUATE = 6L;
    public static final Long AC_LVL_OTHER = 7L;

    public static final Long DRUG_ALCOHOL = 1L;
    public static final Long DRUG_MARIHUANA = 2L;
    public static final Long DRUG_COCAIN = 3L;
    public static final Long DRUG_HEROIN = 4L;
    public static final Long DRUG_OPIUM = 5l;
    public static final Long DRUG_PBC = 6L;
    public static final Long DRUG_SOLV = 7L;
    public static final Long DRUG_CEME = 8L;
    public static final Long DRUG_LSD = 9L;
    public static final Long DRUG_AMPH = 10L;
    public static final Long DRUG_META = 11L;
    public static final Long DRUG_EXTA = 12L;
    public static final Long DRUG_MUSH = 13L;
    public static final Long DRUG_OTHER = 14L;


    public static final String ST_REQUEST_AUTHORIZE_SOURCE = "AUTHORIZE_SOURCES";
    public static final String ST_REQUEST_NOT_PROSECUTE = "NOT_PROSECUTE";
    public static final String ST_REQUEST_MONPLAN_AUTH = "MONPLAN_AUTH";
    public static final String ST_REQUEST_CLOSE_CASE = "CLOSE_CASE_SUPERVISION";
    public static final String ST_REQUEST_UPDATE_MONPLAN_AUTH = "UPDATE_MONPLAN_AUTH";


    public static final String NAME_RELATIONSHIP_IMPUTED = "Imputado";
    public static final String NAME_RELATIONSHIP_OTHER = "Otro";
    public static String CODE_FILE_TECH_REVIEW = "FILE_TECHNICAL_REVIEW";
    public static String CODE_FILE_IMPUTED_PHOTO = "IMPUTED_PHOTO";

    public static final String NAME_RELATIONSHIP_NONE = "Ninguno";

    public static String S_MEETING_OBSOLETE = "OBSOLETE";

    public static final String ACTION_AUTHORIZE_LOG_COMMENT = "AUTORIZAR ELIMINAR CASO";

//    public static final Long ID_CLOSE_CAUSE_FORGIVENESS = 1L;
//    public static final Long ID_CLOSE_CAUSE_AGREEMENT = 2L;
//    public static final Long ID_CLOSE_CAUSE_DESIST = 3L;
//    public static final Long ID_CLOSE_CAUSE_OTHER = 4L;

    public static final String CODE_TOTAL_FULFILLMENT = "TOTAL_NON_FULFILLMENT";
    public static final String CODE_PARTIAL_FULFILLMENT = "PARTIAL_NON_FULFILLMENT";

    public static final String CODE_FILE_TYPE_IMG = "IMAGE_FILE";
    public static final String CODE_FILE_TYPE_WORD = "WORD_FILE";
    public static final String CODE_FILE_TYPE_EXCEL = "EXCEL_FILE";
    public static final String CODE_FILE_TYPE_PDF = "PDF_FILE";
    public static final String CODE_FILE_TYPE_COMPRESSED = "COMPRESSED_FILE";

    public static final String AGREEMENT_IS_DONE = "Realizado";
    public static final String AGREEMENT_IS_NOT_DONE = "No realizado";

    public static final String AGREEMENT_IS_FINISHED = "Concluido";
    public static final String AGREEMENT_IS_NOT_FINISHED = "No concluido";

    public static final String REQUEST_AGREEMENT_TYPE_FINISH = "FINISH_REQUEST_TYPE";
    public static final String RESPONSE_AGREEMENT_TYPE_FINISH_AUTH = "AUTH_FINISH_RESPONSE";
    public static final String RESPONSE_AGREEMENT_TYPE_FINISH_REJECT = "REJECT_FINISH_RESPONSE";

    public static final String ST_CODE_AGREEMENT_FINISH_REQUEST = "PENDENT_FINISH_REQUEST";
    public static final String ST_CODE_AGREEMENT_OPEN = "OPEN_AGREEMENT";
    public static final String ST_CODE_AGREEMENT_FINISH_REJECT = "FINISH_REJECT";
    public static final String ST_CODE_AGREEMENT_FINISHED = "FINISHED_AGREEMENT";

    public static final String ST_CODE_MINUTE_FINISH_REQUEST = "PENDENT_FINISH_REQUEST";
    public static final String ST_CODE_MINUTE_OPEN = "OPEN_AGREEMENT";
    public static final String ST_CODE_MINUTE_FINISH_REJECT = "FINISH_REJECT";
    public static final String ST_CODE_MINUTE_FINISHED = "FINISHED_AGREEMENT";

    public static final String REQUEST_MINUTE_TYPE_FINISH = "FINISH_REQUEST_TYPE";
    public static final String RESPONSE_MINUTE_TYPE_FINISH_AUTH = "AUTH_FINISH_RESPONSE";
    public static final String RESPONSE_MINUTE_TYPE_FINISH_REJECT = "REJECT_FINISH_RESPONSE";


    public static final String PROJECT_STATUS_ACTIVE = "Activo";
    public static final String PROJECT_STATUS_INACTIVE = "Inactivo";
    public static final String PROJECT_STATUS_FINISHED = "Finalizado";


    public static final String CHANNELING_TYPE_ECONOMIC_SUPPORT = "AE";
    public static final String CHANNELING_TYPE_TOXICOLOGICAL_TEST = "ET";
    public static final String CHANNELING_TYPE_PREVENTION_TYPE = "PTA";
    public static final String CHANNELING_TYPE_MEDICAL_TREATMENT = "TMP";
    public static final String CHANNELING_TYPE_EDUCATION = "ES";
    public static final String CHANNELING_TYPE_JOB = "EM";
    public static final String CHANNELING_NOTIFICATION_TITLE = "Registro de canalización";
    public static final String CHANNELING_FIRST_DATE_NOTIFICATION_TITLE = "Primer cita de canalización";
    public static final String CHANNELING_TRACK_NOTIFICATION_TITLE = "Seguimiento de la canalización";
    public static final String CHANNELING_CONCLUSION_NOTIFICATION_TITLE = "Conclusión de la canalización";
    public static final String CHANNELING_LOWER_NOTIFICATION_TITLE = "Baja de la canalización";
    public static final String CHANNELING_ABSENCE_TITLE = "Inasistencia a la canalización";
    public static final String CHANNELING_RESCHEDULE_TITLE = "Reagendar cita de canalización";
    public static final String CHANNELING_DROP_CHANNELING_TITLE = "SOLICITUD DE BAJA DE CANALIZACIÓN";
    public static final String CHANNELING_DROP_REJ_TITLE = "RECHAZÓ SOLICITUD DE BAJA DE CANALIZACIÓN";
    public static final String CHANNELING_DROP_AUTH_TITLE = "AUTORIZÓ SOLICITUD DE BAJA DE CANALIZACIÓN";
    public static final String CHANNELING_END_RECORD_TITLE = "CONCLUSIÓN DEL SERVICIO DE LA CANALIZACIÓN";
    public static final String CHANNELING_END_CODE = "CHANNELING_END";

    public static final String CHANNELING_SUPERVISION_ACTIVITY_CODE = "ACCA";
    public static final long CHANNELING_NOTIFICATION_GOAL_FIRST_DATE = 9l; //Identificador del catálogo activity_goals.txt
    public static final long CHANNELING_NOTIFICATION_GOAL_TRACK = 10l; //Identificador del catálogo activity_goals.txt
    public static final long CHANNELING_NOTIFICATION_GOAL_CONCLUSION = 11l; //Identificador del catálogo activity_goals.txt
    public static final long CHANNELING_NOTIFICATION_GOAL_LOWER = 12l; //Identificador del catálogo activity_goals.txt
    public static final String CHANNELING_IS_VOLUNTEER_TITLE = "(VOLUNTARIA)";

    public static final List<Long> LstChannelingNotificationGoal = new ArrayList<Long>(){{
        add(CHANNELING_NOTIFICATION_GOAL_FIRST_DATE);
        add(CHANNELING_NOTIFICATION_GOAL_TRACK);
        add(CHANNELING_NOTIFICATION_GOAL_CONCLUSION);
        add(CHANNELING_NOTIFICATION_GOAL_LOWER);
    }};

    /**                                         `
     * códigos para causas de cierre *
     */

    public static final String CLOSE_CAUSE_FORGIVENESS = "CAUSE_FORGIVENESS";
    public static final String CLOSE_CAUSE_AGREEMENT = "CAUSE_AGREEMENT";
    public static final String CLOSE_CAUSE_DESIST = "CAUSE_DESIST";
    public static final String CLOSE_CAUSE_OTHER = "CAUSE_OTHER";
    public static final String CLOSE_CAUSE_NO_ENTAILMENT = "CAUSE_NO_ENTAILMENT";
    public static final String CLOSE_CAUSE_OBSOLETE_EVALUATION = "CAUSE_OBSOLETE_EVALUATION";
    public static final String CLOSE_CAUSE_OBSOLETE_SUPERVISION = "CAUSE_OBSOLETE_SUPERVISION";
    public static final String CLOSE_CAUSE_PROMISE_PRISION = "CAUSE_PROMISE_PRISION";
    public static final String CLOSE_CAUSE_DISMISSAL = "CAUSE_DISMISSAL";

    /** códigos para causas de cierre **/

    public static final String COUNTRY_STATE_MUNICIPALITY_LOCATION_NOT_KNWOW = "No proporcionado";



    /** códigos para los eventos **/


    public static final String EVENT_INTERVIEW_DECLINED = "DECLINED";
    public static final String EVENT_CASE_REPORT = "REPORT";
    public static final String EVENT_CASE_OPINION = "OPINION";
    public static final String EVENT_ONLY_INTERVIEW = "INTERVIEW";

}


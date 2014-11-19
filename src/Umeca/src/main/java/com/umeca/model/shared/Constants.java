package com.umeca.model.shared;

import java.text.DateFormat;

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
    public static final String RESPONSE_TYPE_DRESSED = "DRESSED";
    public static String S_MEETING_INCOMPLETE = "INCOMPLETE";
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
    public static final String CASE_STATUS_EDIT_TEC_REV = "ST_CASE_EDIT_TEC_REV";
    public static final String CASE_STATUS_NOT_PROSECUTE = "ST_CASE_NOT_PROSECUTE";
    public static final String CASE_STATUS_NOT_PROSECUTE_OPEN = "ST_CASE_NOT_PROSECUTE_OPEN";
    //sataus field verification
    public static final String ST_FIELD_VERIF_DONTKNOW = "DONT_KNOW";
    public static final String ST_FIELD_VERIF_EQUALS = "EQUALS";
    public static final String ST_FIELD_VERIF_NOEQUALS = "NO_EQUALS";
    public static final String ST_FIELD_VERIF_UNABLE = "UNABLE_VERIFICATION";

    public static final String ST_FIELD_VERIF_IMPUTED = "IS_IMPUTED";
    public static final String CASE_STATUS_PRE_CLOSED = "ST_CASE_PRE_CLOSED";
    public static final String CASE_STATUS_CLOSED = "ST_CASE_CLOSED";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_REVIEWER = "ROLE_REVIEWER";
    public static final String ROLE_EVALUATION_MANAGER = "ROLE_EVALUATION_MANAGER";
    public static final String ROLE_SUPERVISOR = "ROLE_SUPERVISOR";
    public static final String ROLE_SUPERVISOR_MANAGER = "ROLE_SUPERVISOR_MANAGER";
    public static final String ROLE_NOTUSE = "ROLE_NOTUSE";
    public static final String ROLE_ANONYMOUS = "ANONYMOUS";

    public static String FORMAT_CALENDAR_I = "dd/MM/yyyy HH:mm";
    public static String FORMAT_VERIFICATION_DATE = "yyyy-MM-dd hh:mm:ss.S";

    public static final String SYSTEM_SETTINGS_ARCHIVE = "ARCHIVE";
    public static final String SYSTEM_SETTINGS_ARCHIVE_MAX_NUMBER_FILES = "MaxNumberFiles";
    public static final String SYSTEM_SETTINGS_ARCHIVE_MAX_SIZE_FILES = "MaxSizeFiles";
    public static final String SYSTEM_SETTINGS_ARCHIVE_PATH_TO_SAVE = "PathToSave";

    public static final String SYSTEM_SETTINGS_MONPLAN = "MONPLAN";
    public static final String SYSTEM_SETTINGS_MONPLAN_HOURS_TO_AUTHORIZE = "HoursToAuthorize";

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
    public static String CASE_STATUS_OBSOLETE = "ST_CASE_OBSOLETE";


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

    public static final String NAME_RELATIONSHIP_IMPUTED = "Imputado";
    public static String CODE_FILE_TECH_REVIEW = "FILE_TECHNICAL_REVIEW";
    public static String CODE_FILE_IMPUTED_PHOTO = "IMPUTED_PHOTO";

    public static final String NAME_RELATIONSHIP_NONE = "Ninguno";

}

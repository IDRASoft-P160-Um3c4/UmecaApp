package com.umeca.model.shared;

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
    public static final Long MARITAL_UNION_FREE = 4L;
    public static final Long MARITAL_MARRIED = 2L;
    public static final String VALUE_NOT_KNOW_SOURCE = "La fuente desconoce la información.";
    public static String S_MEETING_INCOMPLETE="INCOMPLETE";
    public static String S_MEETING_INCOMPLETE_LEGAL="INCOMPLETE_LEGAL";
    public static String S_MEETING_COMPLETE="COMPLETE";

    public static Boolean GENDER_FEMALE = true;
    public static Boolean GENDER_MALE = false;

    public static Long REGYSTER_TYPE_CURRENT = 1L;
    public static Long REGYSTER_TYPE_SECONDARY = 2L;
    public static Long REGYSTER_TYPE_PREVIOUS = 3L;

    public static final String TECHNICAL_REVIEW_QUESTIONARY_CODE="TECHNICAL_REVIEW";
    public static final Integer CONDITIONAL_REPRIEVE_HEARING= 1;
    public static final Integer MEETING_HEARING= 2;

    //status del caso
    public static String CASE_STATUS_MEETING = "ST_CASE_MEETING";
    public static String CASE_STATUS_SOURCE_VALIDATION = "ST_CASE_SOURCE_VALIDATION";
    public static final String ALPHA2_MEXICO = "MX";
    public static final String CASE_STATUS_HEARING_FORMAT_END="ST_CASE_HEARING_FORMAT_END";
    public static final String CASE_STATUS_VERIFICATION_COMPLETE="ST_CASE_VERIFICATION_COMPLETE";
    public static final String CASE_STATUS_TECHNICAL_REVIEW="ST_CASE_TECHNICAL_REVIEW_COMPLETE";
    public static final String CASE_STATUS_VERIFICATION = "ST_CASE_VERIFICATION";

    public static final String CASE_STATUS_FRAMING_MEETING_INCOMPLETE="ST_CASE_FRAMING_MEETING_INCOMPLETE";
    public static final String CASE_STATUS_FRAMING_MEETING_COMPLETE="ST_CASE_FRAMING_MEETING_COMPLETE";
    public static final String CASE_STATUS_CONDITIONAL_REPRIEVE="ST_CASE_CONDITIONAL_REPRIEVE";

    //sataus field verification
    public static final String ST_FIELD_VERIF_DONTKNOW="DONT_KNOW";
    public static final String ST_FIELD_VERIF_EQUALS="EQUALS";
    public static final String ST_FIELD_VERIF_NOEQUALS="NO_EQUALS";
    public static final String ST_FIELD_VERIF_UNABLE="UNABLE_VERIFICATION";

    public static String ST_FIELD_VERIF_IMPUTED = "IS_IMPUTED";
    public static final String CASE_STATUS_PRE_CLOSED="ST_CASE_PRE_CLOSED";

    public static String ROLE_ADMIN = "ROLE_ADMIN";
    public static String ROLE_REVIEWER = "ROLE_REVIEWER";
    public static String ROLE_SUPERVISOR = "ROLE_SUPERVISOR";
    public static String ROLE_SUPERVISOR_MANAGER = "ROLE_SUPERVISOR_MANAGER";
    public static String ROLE_NOTUSE = "ROLE_NOTUSE";

    public static String FORMAT_CALENDAR_I = "dd/MM/yyyy HH:mm";

}

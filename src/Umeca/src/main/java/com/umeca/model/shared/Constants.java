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
    public static String S_MEETING_INCOMPLETE="INCOMPLETE";
    public static String S_MEETING_INCOMPLETE_LEGAL="INCOMPLETE_LEGAL";
    public static String S_MEETING_COMPLETE="COMPLETE";

    public static Boolean GENDER_FEMALE = true;
    public static Boolean GENDER_MALE = false;

    public static String getS_MEETING_INCOMPLETE() {
        return S_MEETING_INCOMPLETE;
    }

    public static Long REGYSTER_TYPE_CURRENT = 1L;
    public static Long REGYSTER_TYPE_SECONDARY = 2L;
    public static Long REGYSTER_TYPE_PREVIOUS = 3L;

    public static final String CASE_STATUS_VERIFICATION="VERIFICATION_COMPLETE_CASE";
    public static final String CASE_STATUS_TECHNICAL_REVIEW="TECHNICAL_REVIEW_COMPLETE_CASE";

    public static final String TECHNICAL_REVIEW_QUESTIONARY_CODE="TECHNICAL_REVIEW";

    public static final Integer CONDITIONAL_REPRIEVE_HEARING= 1;
    public static final Integer MEETING_HEARING= 2;

    public static String CASE_STATUS_MEETING = "ST_CASE_MEETING";
    public static String CASE_STATUS_SOURCE_VALIDATION = "ST_CASE_SOURCE_VALIDATION";
    public static final String ALPHA2_MEXICO = "MX";
}

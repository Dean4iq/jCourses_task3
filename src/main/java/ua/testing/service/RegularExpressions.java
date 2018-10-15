package ua.testing.service;

public class RegularExpressions {
    public static final String NAMES_REGULAR_EXPRESSION = "^[A-Z]{1}[a-z]+";
    public static final String NICKNAME_REGULAR_EXPRESSION = "[A-z0-9._]{6,18}";
    public static final String PHONE_REGULAR_EXPRESSION = "[0-9]{12}";
    public static final String EMAIL_REGULAR_EXPRESSION = "[A-z0-9_.]+@[a-z]+.[a-z]{2,4}";
    public static final String COMMENT_REGULAR_EXPRESSION = ".+";
    public static final String DATE_REGULAR_EXPRESSION =
            "^[0-3]{1}[0-9]{1}[.][0-1]{1}[0-9]{1}[.][0-9]{4}$";
    public static final String GROUP_REGULAR_EXPRESSION = "[A-Z]+|[a-z]+";

    /*public static final String UA_LAST_NAME_EXPRESSION =
    *        "^[АБВГҐДЕЄЖЗІЇЙКЛМНОПРСТУФХЦЧШЩЮЯ]{1}[абвгґдеєжзиіїйклмнопрстуфхцчшщьюя]+";
    */
}

package ua.testing.view;

/**
 *
 */
public class View {
    public static final String GREETING_MSG = "Hello there!";
    public static final String WARN_MSG = "Please follow the procedure";
    public static final String FST_NAME_INP_MSG = "Input person's first name";
    public static final String LAST_NAME_INP_MSG = "Input person's last name";
    public static final String NICKNAME_INP_MSG = "Input person's nickname";
    public static final String NICKNAME_WARN_MSG =
            "Should contain 6-18 characters (letters numbers . _)!";
    public static final String COMMENT_INP_MSG = "Input your comment";
    public static final String PHONE_INP_MSG = "Input person's phone number";
    public static final String EMAIL_INP_MSG = "Input person's email";
    public static final String DATE_INP_MSG = "Input some date";
    public static final String GROUP_INP_MSG = "Input contact group from list";

    public static final String DONE_MSG = "DONE!";
    public static final String SUCCESS_MSG = "String added!";
    public static final String FAIL_MSG = "Wrong input! Try again";

    public void printMessage(String msg) {
        System.out.println(msg);
    }

    public void printConstructedMessage(String... msg) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String str : msg) {
            stringBuilder.append(str).append(" ");
        }

        System.out.println(stringBuilder.toString());
    }
}

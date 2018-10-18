package ua.testing.view;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 */
public class View {
    private static String LOCAL_BUNDLE_NAME = "messages";

    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle(LOCAL_BUNDLE_NAME,
                                    new Locale("ua"));

    public static String getBundleMsg(String str){
        return resourceBundle.getString(str);
    }

    /**
     *
     * @param msg message to output
     */
    public void printMessage(String msg) {
        System.out.println(msg);
    }

    /**
     *
     * @param msg array of messages to output
     */
    public void printConstructedMessage(String... msg) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String str : msg) {
            stringBuilder.append(str).append(" ");
        }

        System.out.println(stringBuilder.toString());
    }
}

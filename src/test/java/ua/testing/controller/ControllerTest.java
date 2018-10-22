package ua.testing.controller;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.testing.model.Group;
import ua.testing.model.IllegalLoginException;
import ua.testing.model.Record;
import ua.testing.model.RecordBook;
import ua.testing.service.RegularExpressions;
import ua.testing.view.View;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import static org.junit.Assert.*;
import static ua.testing.service.RegularExpressions.*;
import static ua.testing.service.RegularExpressions.GROUP_REGULAR_EXPRESSION;
import static ua.testing.view.TextConstant.*;

public class ControllerTest {
    private static View view;
    private static RecordBook recordBook;

    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("regex_localisation",
                    new Locale(View.LOCALISATION));

    @BeforeClass
    public static void initializeVariables(){
        view = new View();
        recordBook = new RecordBook();
    }

    @Test
    public void checkFirstName() {
        System.setIn(new ByteArrayInputStream("John".getBytes()));
        String response = checkData(NAME_REGULAR_EXPRESSION,
                new Scanner(System.in));
        assertEquals("John", response);
    }

    @Test
    public void checkLastName() {
        System.setIn(new ByteArrayInputStream("Connor".getBytes()));
        String response = checkData(LASTNAME_REGULAR_EXPRESSION,
                new Scanner(System.in));
        assertEquals("Connor", response);
    }

    @Test
    public void checkNickname() {
        System.setIn(new ByteArrayInputStream("Wa_hg11a".getBytes()));
        String response = checkData(RegularExpressions.LOGIN_REGULAR_EXPRESSION,
                new Scanner(System.in));
        assertEquals("Wa_hg11a", response);
    }

    @Test
    public void checkPhoneNumber() {
        System.setIn(new ByteArrayInputStream("123456789012".getBytes()));
        String response = checkData(RegularExpressions.PHONE_REGULAR_EXPRESSION,
                new Scanner(System.in));
        assertEquals("123456789012", response);
    }

    @Test
    public void checkEmail() {
        System.setIn(new ByteArrayInputStream("john23@hotmail.com".getBytes()));
        String response = checkData(RegularExpressions.EMAIL_REGULAR_EXPRESSION,
                new Scanner(System.in));
        assertEquals("john23@hotmail.com", response);
    }

    @Test
    public void checkComment() {
        System.setIn(new ByteArrayInputStream("ajgf,563.43".getBytes()));
        String response = checkData(RegularExpressions.COMMENT_REGULAR_EXPRESSION,
                new Scanner(System.in));
        assertNotNull(response);
    }

    private void startToCollectData() {
        Scanner scanner = new Scanner(System.in);
        String login;

        String firstName = checkData(NAME_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(View.resourceBundle, FST_NAME_INP_MSG));
        String lastName = checkData(LASTNAME_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(View.resourceBundle, LAST_NAME_INP_MSG));

        while (true) {
            try {
                String userLoginInput = checkData(LOGIN_REGULAR_EXPRESSION,
                        scanner, View.getBundleMsg(View.resourceBundle, NICKNAME_INP_MSG),
                        View.getBundleMsg(View.resourceBundle, NICKNAME_WARN_MSG));
                login = RecordBook.checkLogin(userLoginInput);

                break;
            } catch (IllegalLoginException ex) {
                System.err.println(ex.toString());
            }
        }

        String phoneNumber = checkData(PHONE_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(View.resourceBundle, PHONE_INP_MSG));
        String email = checkData(EMAIL_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(View.resourceBundle, EMAIL_INP_MSG));
        String comment = checkData(COMMENT_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(View.resourceBundle, COMMENT_INP_MSG));
        String date = checkData(DATE_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(View.resourceBundle, DATE_INP_MSG));
        String group = checkData(GROUP_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(View.resourceBundle, GROUP_INP_MSG),
                Arrays.toString(Group.values()));

        recordBook.putNewRecord(firstName, lastName, login, comment,
                phoneNumber, email, date);
        recordBook.addGroupToRecord(Group.valueOf(group),
                recordBook.getRecords().getLast());

        view.printMessage(View.getBundleMsg(View.resourceBundle, DONE_MSG));

        for (Record record : recordBook.getRecords()) {
            view.printMessage(record.toString());
        }
    }

    /**
     * Method to validate user data
     *
     * @param regularExpression regular expression to validate user input
     * @param scanner           Scanner class object to collect user input
     * @param askingMessage     array of messages to make contact with user
     * @return returns user input checked by regular expression
     */
    private String checkData(String regularExpression, Scanner scanner,
                             String... askingMessage) {
        String userInput = "";

        while (!userInput.matches(resourceBundle.getString(regularExpression))) {
            view.printConstructedMessage(askingMessage);

            userInput = scanner.next();

            if (!userInput.matches(regularExpression)) {
                view.printMessage(View.getBundleMsg(View.resourceBundle, FAIL_MSG));
            }
        }

        view.printMessage(View.getBundleMsg(View.resourceBundle, SUCCESS_MSG));

        return userInput;
    }

    public void processUser() {
        view.printMessage(View.getBundleMsg(View.resourceBundle, GREETING_MSG));
        view.printMessage(View.getBundleMsg(View.resourceBundle, WARN_MSG));

        startToCollectData();
    }
}
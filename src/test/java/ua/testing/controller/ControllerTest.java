package ua.testing.controller;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.testing.model.Group;
import ua.testing.model.Record;
import ua.testing.model.RecordBook;
import ua.testing.service.RegularExpressions;
import ua.testing.view.View;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ControllerTest {
    private static View view;
    private static RecordBook recordBook;

    @BeforeClass
    public static void initializeVariables(){
        view = new View();
        recordBook = new RecordBook();
    }

    @Test
    public void checkFirstName() {
        System.setIn(new ByteArrayInputStream("John".getBytes()));
        String response = checkData(RegularExpressions.NAMES_REGULAR_EXPRESSION,
                new Scanner(System.in));
        assertEquals("John", response);
    }

    @Test
    public void checkLastName() {
        System.setIn(new ByteArrayInputStream("Connor".getBytes()));
        String response = checkData(RegularExpressions.NAMES_REGULAR_EXPRESSION,
                new Scanner(System.in));
        assertEquals("Connor", response);
    }

    @Test
    public void checkNickname() {
        System.setIn(new ByteArrayInputStream("Wa_hg11a".getBytes()));
        String response = checkData(RegularExpressions.NAMES_REGULAR_EXPRESSION,
                new Scanner(System.in));
        assertEquals("Wa_hg11a", response);
    }

    @Test
    public void checkPhoneNumber() {
        System.setIn(new ByteArrayInputStream("123456789012".getBytes()));
        String response = checkData(RegularExpressions.NAMES_REGULAR_EXPRESSION,
                new Scanner(System.in));
        assertEquals("123456789012", response);
    }

    @Test
    public void checkEmail() {
        System.setIn(new ByteArrayInputStream("john23@hotmail.com".getBytes()));
        String response = checkData(RegularExpressions.NAMES_REGULAR_EXPRESSION,
                new Scanner(System.in));
        assertEquals("john23@hotmail.com", response);
    }

    @Test
    public void checkComment() {
        System.setIn(new ByteArrayInputStream("ajgf,563.43".getBytes()));
        String response = checkData(RegularExpressions.NAMES_REGULAR_EXPRESSION,
                new Scanner(System.in));
        assertNotNull(response);
    }

    private void startToCollectData() {
        Scanner scanner = new Scanner(System.in);

        String firstName = checkData(RegularExpressions.NAMES_REGULAR_EXPRESSION,
                scanner, View.FST_NAME_INP_MSG);
        String lastName = checkData(RegularExpressions.NAMES_REGULAR_EXPRESSION,
                scanner, View.LAST_NAME_INP_MSG);
        String nickname = checkData(RegularExpressions.NICKNAME_REGULAR_EXPRESSION,
                scanner, View.NICKNAME_INP_MSG, View.NICKNAME_WARN_MSG);
        String phoneNumber = checkData(RegularExpressions.PHONE_REGULAR_EXPRESSION,
                scanner, View.PHONE_INP_MSG);
        String email = checkData(RegularExpressions.EMAIL_REGULAR_EXPRESSION,
                scanner, View.EMAIL_INP_MSG);
        String comment = checkData(RegularExpressions.COMMENT_REGULAR_EXPRESSION,
                scanner, View.COMMENT_INP_MSG);
        String date = checkData(RegularExpressions.DATE_REGULAR_EXPRESSION,
                scanner, View.DATE_INP_MSG);
        String group = checkData(RegularExpressions.GROUP_REGULAR_EXPRESSION,
                scanner, View.GROUP_INP_MSG, Arrays.toString(Group.values()));

        recordBook.putNewRecord(firstName, lastName, nickname, comment,
                phoneNumber, email, date);
        recordBook.addGroupToRecord(Group.valueOf(group),
                recordBook.getRecords().getLast());

        view.printMessage(View.DONE_MSG);

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

        while (!userInput.matches(regularExpression)) {
            view.printConstructedMessage(askingMessage);

            userInput = scanner.next();

            if (!userInput.matches(regularExpression)) {
                view.printMessage(View.FAIL_MSG);
            }
        }

        view.printMessage(View.SUCCESS_MSG);

        return userInput;
    }

    public void processUser() {
        view.printMessage(View.GREETING_MSG);
        view.printMessage(View.WARN_MSG);

        startToCollectData();
    }
}
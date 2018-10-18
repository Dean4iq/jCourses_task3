package ua.testing.controller;

import ua.testing.model.Group;
import ua.testing.model.Record;
import ua.testing.model.RecordBook;
import ua.testing.service.RegularExpressions;
import ua.testing.view.View;

import java.util.Arrays;
import java.util.Scanner;

import static ua.testing.view.TextConstant.*;

public class Controller {
    private View view;
    private RecordBook recordBook;

    public Controller(View view, RecordBook recordBook) {
        this.view = view;
        this.recordBook = recordBook;
    }

    private void startToCollectData() {
        Scanner scanner = new Scanner(System.in);

        String firstName = checkData(RegularExpressions.NAMES_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(FST_NAME_INP_MSG));
        String lastName = checkData(RegularExpressions.NAMES_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(LAST_NAME_INP_MSG));
        String nickname = checkData(RegularExpressions.NICKNAME_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(NICKNAME_INP_MSG),
                         View.getBundleMsg(NICKNAME_WARN_MSG));
        String phoneNumber = checkData(RegularExpressions.PHONE_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(PHONE_INP_MSG));
        String email = checkData(RegularExpressions.EMAIL_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(EMAIL_INP_MSG));
        String comment = checkData(RegularExpressions.COMMENT_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(COMMENT_INP_MSG));
        String date = checkData(RegularExpressions.DATE_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(DATE_INP_MSG));
        String group = checkData(RegularExpressions.GROUP_REGULAR_EXPRESSION,
                scanner, View.getBundleMsg(GROUP_INP_MSG),
                         Arrays.toString(Group.values()));

        recordBook.putNewRecord(firstName, lastName, nickname, comment,
                phoneNumber, email, date);
        recordBook.addGroupToRecord(Group.valueOf(group),
                recordBook.getRecords().getLast());

        view.printMessage(View.getBundleMsg(DONE_MSG));

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
                view.printMessage(View.getBundleMsg(FAIL_MSG));
            }
        }

        view.printMessage(View.getBundleMsg(SUCCESS_MSG));

        return userInput;
    }

    public void processUser() {
        view.printMessage(View.getBundleMsg(GREETING_MSG));
        view.printMessage(View.getBundleMsg(WARN_MSG));

        startToCollectData();
    }
}

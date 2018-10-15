package ua.testing.controller;

import ua.testing.model.Group;
import ua.testing.model.Record;
import ua.testing.model.RecordBook;
import ua.testing.service.RegularExpressions;
import ua.testing.view.View;

import java.util.Arrays;
import java.util.Scanner;

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

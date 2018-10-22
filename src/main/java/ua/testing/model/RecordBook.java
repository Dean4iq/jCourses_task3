package ua.testing.model;

import java.util.LinkedList;

public class RecordBook {
    private static LinkedList<Record> records;

    public RecordBook() {
        records = new LinkedList<>();
        records.add(new Record("Mark", "Hi",
                "mark123456", "","123456789012", "mark@gmail.com",
                "22.12.2014"));
    }

    public void putNewRecord(String firstName, String lastName,
                             String login, String comment,
                             String phoneNumber, String email, String date) {
        records.add(new Record(firstName, lastName, login, comment,
                phoneNumber, email, date));
    }

    public static String checkLogin(String login) throws IllegalLoginException {
        for (Record record : records) {
            if (record.getLogin().equals(login)) {
                throw new IllegalLoginException(login);
            }
        }
        return login;
    }

    public void addGroupToRecord(Group group, Record record) {
        record.setGroup(group);
    }

    public LinkedList<Record> getRecords() {
        return records;
    }
}

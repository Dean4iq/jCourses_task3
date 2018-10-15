package ua.testing.model;

import java.util.LinkedList;

public class RecordBook {
    private LinkedList<Record> records;

    public RecordBook() {
        records = new LinkedList<>();
    }

    public void putNewRecord(String firstName, String lastName,
                             String nickname, String comment,
                             String phoneNumber, String email, String date) {
        records.add(new Record(firstName, lastName, nickname, comment,
                                phoneNumber, email, date));
    }

    public void addGroupToRecord(Group group, Record record) {
        record.setGroup(group);
    }

    public LinkedList<Record> getRecords() {
        return records;
    }
}

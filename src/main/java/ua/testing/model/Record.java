package ua.testing.model;

public class Record {
    private String firstName;
    private String lastName;
    private String nickname;
    private String comment;
    private String phoneNumber;
    private String email;
    private String date;
    private Group group;

    public Record(String firstName, String lastName, String nickname,
                  String comment, String phoneNumber, String email,
                  String date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.comment = comment;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.date = date;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Person's data:").append("\n");
        stringBuilder.append("Group: ").append(group).append("\n");
        stringBuilder.append("First name: ").append(firstName).append("\n");
        stringBuilder.append("Last name: ").append(lastName).append("\n");
        stringBuilder.append("Nickname: ").append(nickname).append("\n");
        stringBuilder.append("Phone number: ").append(phoneNumber).
                                                append("\n");
        stringBuilder.append("Email: ").append(email).append("\n");
        stringBuilder.append("Date: ").append(date).append("\n");
        stringBuilder.append("Comment: ").append(comment).append("\n");

        return stringBuilder.toString();
    }
}

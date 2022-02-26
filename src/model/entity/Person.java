package model.entity;

public class Person {
    private long id;
    private String userName;
    private String password;

    public Person() {
    }

    public Person(long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Person(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public Person setId(long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Person setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Person setPassword(String password) {
        this.password = password;
        return this;
    }
}

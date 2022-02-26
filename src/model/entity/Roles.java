package model.entity;

public class Roles {
    private String userName;
    private String roleName;

    public Roles() {
    }

    public Roles(String roleName) {
        this.roleName = roleName;
    }

    public Roles(String userName, String roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public Roles setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public Roles setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }
}

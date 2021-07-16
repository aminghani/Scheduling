package models;

public class User {
    private String role;

    public User(){

    }

    public User(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

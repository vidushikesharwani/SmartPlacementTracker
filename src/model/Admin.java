package model;

public class Admin extends User {

    public Admin(String adminId, String name, String email) {
        super(adminId, name, email);
    }

    public void displayAdmin() {
        displayUser();
        System.out.println("Role: Placement Adminn");
    }
}
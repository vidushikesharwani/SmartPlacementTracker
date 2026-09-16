import model.Admin;

public class TestAdmin {
    public static void main(String[] args) {
        Admin admin = new Admin(
                "A101",
                "Placement Office",
                "placement@vitbhopal.ac.in"
        );

        admin.displayAdmin();
    }
}
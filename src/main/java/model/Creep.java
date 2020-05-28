package model;

public class Creep extends User{

    public Creep(String name, String login, String email, String password, String phoneNumber, int role) {
        super(name, login, email, password, phoneNumber, role);
    }

    public Creep(){
        super();

    }
}

package dev.thelabs.rds;

public class User{
    public String name;
    public Boolean sessionactive;

    User(String n, Boolean active){
        name = n;
        sessionactive = active;
    }
}
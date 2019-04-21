package com.cristiangoncas.nbateams.data.models;

public class Player {

    private String firstName;
    private String lastName;
    private String position;
    private String number;

    public Player(String firstName, String lastName, String position, int number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.number = String.valueOf(number);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getNumber() {
        return number;
    }
}
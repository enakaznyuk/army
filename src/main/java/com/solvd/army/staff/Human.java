package com.solvd.army.staff;

public abstract class Human {

    private String firstName;
    private String lastName;
    private int militaryBadge;

    public Human(String firstName, String lastName, int militaryBadge) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.militaryBadge = militaryBadge;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMilitaryBadge() {
        return militaryBadge;
    }

    public void setMilitaryBadge(int militaryBadge) {
        this.militaryBadge = militaryBadge;
    }
}

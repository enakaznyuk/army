package com.solvd.army.staff;

public abstract class Human {

    private String firstName;
    private String lastName;
    private int age;
    private int militaryBadge;

    public Human(String firstName, String lastName, int age, int militaryBadge) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMilitaryBadge() {
        return militaryBadge;
    }

    public void setMilitaryBadge(int militaryBadge) {
        this.militaryBadge = militaryBadge;
    }
}

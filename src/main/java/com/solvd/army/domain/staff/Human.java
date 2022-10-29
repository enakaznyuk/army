package com.solvd.army.domain.staff;

public abstract class Human  implements IServe{

    private String firstName;
    private String lastName;
    private int militaryBadge;

    public Human(String firstName, String lastName, int militaryBadge) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.militaryBadge = militaryBadge;
    }

    public Human(){}

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

    /*методы ниже нужны для реализации Builder паттерна
    * данный паттерн позволяет спрятать геттеры и сеттеры за внутренним классом,
    * и при создании объекта обращаться именно ко внутреннему классу.
    * Разбивает создание объекта на отдельные шаги.
    * Может создавать неизменяемый объект, но конкретно тут поля объекта менять можно благодаря методам toBuilder*/
    public static HumanBuilder builderGeneral(){
        return new HumanBuilder(new General());
    }

    public static HumanBuilder builderOfficer(){
        return new HumanBuilder(new Officer());
    }

    public static HumanBuilder builderSoldier(){
        return new HumanBuilder(new Soldier());
    }

    public HumanBuilder toBuilderGeneral(){
        return new HumanBuilder(this);
    }

    public HumanBuilder toBuilderOfficer(){
        return new HumanBuilder(this);
    }

    public HumanBuilder toBuilderSoldier(){
        return new HumanBuilder(this);
    }

    public static class HumanBuilder{

        private final Human human;

        public HumanBuilder(Human human) {
            this.human = human;
        }

        public HumanBuilder firstName(String firstName){
            this.human.firstName = firstName;
            return this;
        }

        public HumanBuilder lasttName(String lastName){
            this.human.lastName = lastName;
            return this;
        }

        public HumanBuilder militaryBadge(Integer militaryBadge){
            this.human.militaryBadge = militaryBadge;
            return this;
        }

        public Human build(){
            return human;
        }

    }

    @Override
    public void charger() {
        System.out.println("doing exercises");
    }

    @Override
    public void serve() {
        System.out.println("performs military tasks");
    }

    @Override
    public String toString() {
        return "Human{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", militaryBadge=" + militaryBadge +
                '}';
    }
}

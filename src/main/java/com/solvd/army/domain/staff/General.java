package com.solvd.army.domain.staff;

import com.solvd.army.domain.weapon.SmallArm;

import java.util.List;

public class General extends Human {

    private long id;
    private SmallArm pistol;
    private List<Officer> division;
    private Long armyId;

    public General(String firstName, String lastName, int militaryBadge) {
        super(firstName, lastName, militaryBadge);
    }

    public General(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SmallArm getPistol() {
        return pistol;
    }

    public void setPistol(SmallArm pistol) {
        this.pistol = pistol;
    }

    public List<Officer> getDivision() {
        return division;
    }

    public void setDivision(List<Officer> division) {
        this.division = division;
    }

    public Long getArmyId() {
        return armyId;
    }

    public void setArmyId(Long armyId) {
        this.armyId = armyId;
    }

    @Override
    public String toString() {
        return "General{" +
                "id=" + id +
                ", pistol=" + pistol +
                ", division=" + division +
                '}';
    }

    @Override
    public void charger() {
        //super.charger();
        System.out.println("makes standards for the general");
    }

    @Override
    public void serve() {
        //super.serve();
        System.out.println("performs tasks according to the rank of general");
    }
}

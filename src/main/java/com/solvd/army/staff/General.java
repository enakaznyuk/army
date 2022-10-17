package com.solvd.army.staff;

import com.solvd.army.weapon.SmallArm;

import java.util.List;

public class General extends Human {

    private long id;
    private SmallArm pistol;
    private List<Officer> division;

    public General(String firstName, String lastName, int militaryBadge) {
        super(firstName, lastName, militaryBadge);
    }

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
}

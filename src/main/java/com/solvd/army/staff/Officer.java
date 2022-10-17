package com.solvd.army.staff;

import com.solvd.army.weapon.SmallArm;

import java.util.List;

public class Officer extends Human {

    private long id;
    private SmallArm rifle;
    private List<Soldier> battalion;

    public Officer(String firstName, String lastName, int militaryBadge) {
        super(firstName, lastName, militaryBadge);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SmallArm getRifle() {
        return rifle;
    }

    public void setRifle(SmallArm rifle) {
        this.rifle = rifle;
    }

    public List<Soldier> getBattalion() {
        return battalion;
    }

    public void setBattalion(List<Soldier> battalion) {
        this.battalion = battalion;
    }
}

package com.solvd.army.domain.staff;

import com.solvd.army.domain.weapon.SmallArm;

import java.util.ArrayList;
import java.util.List;

public class Officer extends Human {

    private long id;
    private SmallArm rifle;
    private List<Soldier> battalion = new ArrayList<>();

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



    @Override
    public String toString() {
        return "Officer{" +
                "id=" + id +
                ", rifle=" + rifle +
                ", battalion=" + battalion +
                '}';
    }

    public void addSoldier(Soldier soldierProba) {
        battalion.add(soldierProba);
    }
}

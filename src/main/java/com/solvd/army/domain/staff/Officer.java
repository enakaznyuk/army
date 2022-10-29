package com.solvd.army.domain.staff;

import com.solvd.army.domain.equipment.Plane;
import com.solvd.army.domain.weapon.SmallArm;

import java.util.ArrayList;
import java.util.List;

public class Officer extends Human implements IEvent {

    private long id;
    private SmallArm rifle;
    private List<Soldier> battalion = new ArrayList<>();
    private Long generalId;

    public Officer(String firstName, String lastName, int militaryBadge) {
        super(firstName, lastName, militaryBadge);
    }

    public Officer(){}

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

    public Long getGeneralId() {
        return generalId;
    }

    public void setGeneralId(Long generalId) {
        this.generalId = generalId;
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

    @Override
    public void charger() {
        super.charger();
        System.out.println("makes standards for the officer");
    }

    @Override
    public void serve() {
        super.serve();
        System.out.println("performs tasks according to the rank of officer");
    }

    @Override
    public void newPlane(Plane plane) {
        System.out.println("you are assigned to a new plane id = " + plane.getId());
    }
}

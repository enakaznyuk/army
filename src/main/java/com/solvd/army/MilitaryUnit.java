package com.solvd.army;

import com.solvd.army.staff.General;
import com.solvd.army.staff.Officer;
import com.solvd.army.staff.Soldier;

import java.util.List;

public class MilitaryUnit {

    private long id;
    private Integer number;
    private General general;
    private List<Officer> officers;
    private List<Soldier> soldiers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public General getGeneral() {
        return general;
    }

    public void setGeneral(General general) {
        this.general = general;
    }

    public List<Officer> getOfficers() {
        return officers;
    }

    public void setOfficers(List<Officer> officers) {
        this.officers = officers;
    }

    public List<Soldier> getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(List<Soldier> soldiers) {
        this.soldiers = soldiers;
    }
}

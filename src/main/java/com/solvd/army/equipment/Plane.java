package com.solvd.army.equipment;

import com.solvd.army.staff.Officer;

public class Plane extends Equipment {

    private long id;
    private Officer officer;

    public Plane(String type, String name, long number) {
        super(type, name, number);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Officer getOfficer() {
        return officer;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }
}

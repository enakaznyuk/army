package com.solvd.army.domain.equipment;

import com.solvd.army.domain.staff.Officer;

public class HeavyArtillery extends Equipment {

    private long id;
    private Officer officer;

    public HeavyArtillery(String type, String name, long number) {
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

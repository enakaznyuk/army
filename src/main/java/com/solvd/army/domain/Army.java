package com.solvd.army.domain;

import com.solvd.army.domain.staff.General;

public class Army {

    private long id;
    private Integer number;
    private General general;

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

    @Override
    public String toString() {
        return "Army{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}

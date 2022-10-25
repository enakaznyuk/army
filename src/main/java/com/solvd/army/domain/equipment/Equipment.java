package com.solvd.army.domain.equipment;

public abstract class Equipment {

    private String type;
    private String name;
    private long number;

    public Equipment(String type, String name, long number) {
        this.type = type;
        this.name = name;
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}

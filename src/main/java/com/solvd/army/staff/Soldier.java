package com.solvd.army.staff;

import com.solvd.army.weapon.HeavyWeapon;
import com.solvd.army.weapon.SmallArm;

public class Soldier  extends Human{

    private long id;
    private SmallArm rifle;
    private HeavyWeapon heavyWeapon;

    public Soldier(String firstName, String lastName, int age, int militaryBadge){
        super(firstName, lastName, age, militaryBadge);
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

    public HeavyWeapon getHeavyWeapon() {
        return heavyWeapon;
    }

    public void setHeavyWeapon(HeavyWeapon heavyWeapon) {
        this.heavyWeapon = heavyWeapon;
    }
}

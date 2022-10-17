package com.solvd.army.staff;

import com.solvd.army.equipment.Reward;
import com.solvd.army.weapon.HeavyWeapon;
import com.solvd.army.weapon.SmallArm;

import java.time.LocalDate;
import java.util.List;

public class Soldier  extends Human{

    private long id;
    private SmallArm rifle;
    private HeavyWeapon heavyWeapon;
    private LocalDate demobilization;
    private List<Reward> rewards;

    public Soldier(String firstName, String lastName, int militaryBadge){
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

    public HeavyWeapon getHeavyWeapon() {
        return heavyWeapon;
    }

    public void setHeavyWeapon(HeavyWeapon heavyWeapon) {
        this.heavyWeapon = heavyWeapon;
    }

    public LocalDate getDemobilization() {
        return demobilization;
    }

    public void setDemobilization(LocalDate demobilization) {
        this.demobilization = demobilization;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }
}

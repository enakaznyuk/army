package com.solvd.army.domain.staff;

import com.solvd.army.domain.equipment.Reward;
import com.solvd.army.domain.weapon.HeavyWeapon;
import com.solvd.army.domain.weapon.SmallArm;

import java.time.LocalDate;
import java.util.List;

public class Soldier  extends Human{

    private Long id;
    private SmallArm rifle;
    private HeavyWeapon heavyWeapon;
    private LocalDate demobilization;
    private List<Reward> rewards;

    public Soldier(String firstName, String lastName, int militaryBadge){
        super(firstName, lastName, militaryBadge);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "Soldier{" +
                "id=" + id +
                ", rifle=" + rifle +
                ", heavyWeapon=" + heavyWeapon +
                ", demobilization=" + demobilization +
                ", rewards=" + rewards +
                '}';
    }
}

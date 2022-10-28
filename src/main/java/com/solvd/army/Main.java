package com.solvd.army;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.staff.General;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.staff.Soldier;
import com.solvd.army.domain.weapon.SmallArm;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {


        SmallArm smallArm = new SmallArm();
        smallArm.setName("luch smerty");
        smallArm.setNumber(2023);
        smallArm.setId(42L);

        Soldier soldier = new Soldier("987","654", 321);
        soldier.setDemobilization(LocalDate.now());
        soldier.setRifle(smallArm);
        soldier.setOfficerId(6L);
        soldier.setId(23L);

        Officer officer = new Officer("hallo","hello", 777);
        officer.setRifle(smallArm);
        officer.setGeneralId(1L);
        officer.setId(8L);

        General general = new General("conichiva","conichiva", 212);
        general.setPistol(smallArm);
        general.setArmyId(1L);
        general.setId(2L);

        Army army = new Army();
        army.setNumber(30);
        army.setId(7L);
    }
}
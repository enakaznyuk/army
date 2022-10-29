package com.solvd.army.domain.staff;

import com.solvd.army.domain.weapon.Flash;
import com.solvd.army.domain.weapon.Fragmentation;
import com.solvd.army.domain.weapon.Smoke;

public class SituationPattern {

    public static Soldier grenadeSelection(Soldier soldier){

        switch (soldier.getSituation()){
            case KILL:
                soldier.setGrenade(new Fragmentation());
                soldier.getGrenade().use();
                break;
            case BLIND:
                soldier.setGrenade(new Flash());
                soldier.getGrenade().use();
                break;
            case CLOSE:
                soldier.setGrenade(new Smoke());
                soldier.getGrenade().use();
                break;
            default:
                break;
        }
        return soldier;
    }
}

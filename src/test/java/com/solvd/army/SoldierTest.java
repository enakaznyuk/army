package com.solvd.army;

import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.staff.Soldier;
import com.solvd.army.service.SoldiersMapService;
import com.solvd.army.service.impl.SoldierMapServiceImpl;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SoldierTest {

    @Test(testName = "check all fields of the soldier for null")
    public void verifySoldierNotEmptyTest(){

        Soldier soldier = new Soldier();
        SoldiersMapService soldiersMapService = new SoldierMapServiceImpl();
        soldier = soldiersMapService.findById(1L);

        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(soldier.getFirstName(), "soldier first name is null");
        sa.assertNotNull(soldier.getLastName(), "soldier last name is null");

        if(soldier.getMilitaryBadge() <= 0){
            throw new RuntimeException("soldier Military Badge must be > 0");
        }

        sa.assertNotNull(soldier.getDemobilization(), "soldier demobilization date is null");
        sa.assertNotNull(soldier.getRifle(), "soldier rifle is null");
        sa.assertAll();
    }

    @Test(testName = "army must have soldiers")
    public void verifySoldiersListIsNotEmptyTest(){
        SoldiersMapService service = new SoldierMapServiceImpl();
        List<Soldier> soldiers = service.findAll();
        //soldiers = null;

        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(soldiers, "list mustn't be null");
        sa.assertAll();
    }

    @Test(testName = "soldiers must have weapon")
    public void verifySmallArmIsNotEmptyTest(){
        SoldiersMapService service = new SoldierMapServiceImpl();
        List<Soldier> soldiers = service.findAll();

        SoftAssert sa = new SoftAssert();
        for(Soldier soldier : soldiers){
            sa.assertNotNull(soldier.getRifle(), "General must have officers");
        }
        sa.assertAll();
    }
}

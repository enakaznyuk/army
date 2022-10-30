package com.solvd.army;

import com.solvd.army.domain.staff.General;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.service.GeneralMapService;
import com.solvd.army.service.SmallArmMapService;
import com.solvd.army.service.impl.GeneralMapServiceImpl;
import com.solvd.army.service.impl.SmallArmMapServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SmallArmTest {

    @BeforeClass
    public List<SmallArm> allSmallArms(){
        SmallArmMapService service = new SmallArmMapServiceImpl();
        return service.findAll();
    }

    @Test(testName = "SmallArms must have number and name")
    public void verifySmallArmIsNotEmptyTest(){

        List<SmallArm> smallArms = allSmallArms();

        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(smallArms, "list mustn't be null");

        for(SmallArm smallArm : smallArms){
            sa.assertNotNull(smallArm.getName(), "SmallArm must have name");
            if(smallArm.getNumber() < 0){
                sa.fail("SmallArm must have number > 0");
            }

        }

        sa.assertAll();
    }
}

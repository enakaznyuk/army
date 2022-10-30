package com.solvd.army;

import com.solvd.army.domain.staff.General;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.staff.Soldier;
import com.solvd.army.service.OfficerMapService;
import com.solvd.army.service.SoldiersMapService;
import com.solvd.army.service.impl.OfficerMapServiceImpl;
import com.solvd.army.service.impl.SoldierMapServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class OfficerTest {

    @BeforeClass
    public List<Officer> allOfficers(){
        OfficerMapService service = new OfficerMapServiceImpl();
        return service.findAll();
    }


    @DataProvider(name = "invalidFirstNames")
    public Object[][] invalidFirstNames(){
        return new Object[][]{
                    { allOfficers().get(1) },
                    { allOfficers().get(2) },
                    { allOfficers().get(3) }
        };
    }

    @Test(testName = "army must have officers")
    public void verifyOfficersListIsNotEmptyTest(){

        List<Officer> officers = allOfficers();

        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(officers, "list mustn't be null");
        sa.assertAll();
    }

    @Test(dataProvider = "invalidFirstNames", testName = "verify all officers name")
    public void verifyOfficerFirstNameErrorMassageTest(Officer officer){

        SoftAssert sa = new SoftAssert();
        if(officer.getFirstName().length() < 4){
            sa.fail("less than 4");
        } else if (officer.getFirstName().length() > 10) {
            sa.fail();
        } else if (!officer.getFirstName().matches("^\\D*$")) {
            sa.fail("mustn't have 0-9");
        }
        sa.assertAll();
    }

    @Test(testName = "Officer must have soldiers")
    public void verifySoldiersListIsNotEmptyInOfficerTest(){

        List<Officer> officers = allOfficers();

        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(officers, "list mustn't be null");

        for(Officer officer : officers){
            sa.assertNotNull(officer.getBattalion(), "Officer must have soldiers");
        }

        sa.assertAll();
    }
}

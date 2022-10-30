package com.solvd.army;

import com.solvd.army.domain.staff.General;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.service.GeneralMapService;
import com.solvd.army.service.OfficerMapService;
import com.solvd.army.service.impl.GeneralMapServiceImpl;
import com.solvd.army.service.impl.OfficerMapServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class GeneralTest {

    @BeforeClass
    public List<General> allGenerals() {
        GeneralMapService service = new GeneralMapServiceImpl();
        return service.findAll();
    }

    @Test(testName = "General must have officers")
    public void verifyOfficersListInGeneralIsNotEmptyTest() {

        List<General> generals = allGenerals();

        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(generals, "list mustn't be null");

        for (General general : generals) {
            sa.assertNotNull(general.getDivision(), "General must have officers");
        }

        sa.assertAll();
    }

    @Test(testName = "General must have soldiers")
    public void verifyOfficersListHaveSoldiersInGeneralIsNotEmptyTest() {

        List<General> generals = allGenerals();

        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(generals, "list mustn't be null");

        for (General general : generals) {
            List<Officer> officers = general.getDivision();
            for (Officer officer : officers) {
                sa.assertNotNull(officer.getBattalion(), "General must have soldiers");
            }

        }
        sa.assertAll();
    }

    @Test(testName = "General must have pistol")
    public void verifyGeneralsPistolTest(){

        List<General> generals = allGenerals();

        SoftAssert sa = new SoftAssert();

        for(General general : generals){
            sa.assertNotNull(general.getPistol(), "General must have pistol");
        }

        sa.assertAll();
    }
}

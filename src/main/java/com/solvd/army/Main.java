package com.solvd.army;


import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.staff.Soldier;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.service.OfficersService;
import com.solvd.army.service.SmallArmsService;
import com.solvd.army.service.SoldiersService;
import com.solvd.army.service.impl.OfficersServiceImpl;
import com.solvd.army.service.impl.SmallArmsServiceImpl;
import com.solvd.army.service.impl.SoldiersServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        

        OfficersService service = new OfficersServiceImpl();
        List<Officer> officer = service.findAll();
        System.out.println(officer);
    }
}
package com.solvd.army.domain.staff;

import com.solvd.army.domain.equipment.Plane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHolder {

    private static final Map<Long, Officer> HOLDER = new HashMap<>();

    public static void getPlane(Officer officer, Plane plane){
        HOLDER.put(plane.getId(), officer);
    }

    public static void notify(Plane plane){
        Officer officer = HOLDER.get(plane.getId());
        if(officer != null)
            officer.newPlane(plane);
    }
}

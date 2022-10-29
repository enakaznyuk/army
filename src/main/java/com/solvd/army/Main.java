package com.solvd.army;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.equipment.Plane;
import com.solvd.army.domain.staff.*;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.service.OfficerMapService;
import com.solvd.army.service.impl.OfficerMapServiceImpl;

import java.time.LocalDate;
import java.util.List;

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
        soldier.setSituation(Situation.BLIND);

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

        OfficerMapService service = new OfficerMapServiceImpl();
        List<Officer> officerList = service.findAll();


        Human human = Factory.getHuman(Rank.GENERAL);
        human.toBuilderGeneral()
                .firstName("Ivan");

        /*позволяет добавлять классам новые поведения
         * помещая их в классы обёртки. Благодаря паттерну Decorator
         * мы можем менять оборачивать объекты в бесчисленное количество обёрток.
         * так же, например, если у нас у генерала есть в подчинении офицеры и солдаты,
         * которые имеют какие то методы,
         * мы можем реализацию этих методов вынести в отдельный класс и через эту обёртку
         * удобно вызвать методы и солдат и офицеров.
        * */
        human.serve();
        human.charger();

        /*
        * Паттерн Strategy позволяет в зависимости от задачи использовать разные алгоритмы
        * конкретно тут солдат в зависимости от ситуации использует ту или иную гранату.
        * гранаты схожи друг с другом, но отличаются по реализации.
        * */
        SituationPattern.grenadeSelection(soldier);

        /*
        * Тут высосанный из пальца паттерн listener
        * При назначении офицера на какой-то самолёт срабатывает EventHolder и
        * офицер получает уведомление на какой именно самолёт он получил назначение(по айдишнику)
        * */
        Plane plane = new Plane("Hunter", "SU-34", 15);
        plane.setId(2L);
        plane.setOfficer(officer);

        EventHolder.getPlane(officer, plane);
        EventHolder.notify(plane);
    }
}
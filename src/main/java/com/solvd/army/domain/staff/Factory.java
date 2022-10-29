package com.solvd.army.domain.staff;

import com.solvd.army.domain.exception.RankException;

public class Factory {

    public static Human getHuman(Rank rank){

        Human human;
        /*
        * Factory паттерн.
        * позволяет при вызове метода getHuman понять программе, какой именно объект ей создавать.
        *В данном случае, можно создать человека который является либо солдатом, либо офицером,
        * либо генералом.
        * Так же я совместил данный паттерн с паттерном Builder, и теперь я могу создавать человек
        * по рангу + разбиваю создание этих объектов на различные шаги
        * */
        switch (rank){
            case GENERAL:
                human = General.builderGeneral()
                        .firstName("Egor")
                        .lasttName("Nakaznyuk")
                        .militaryBadge(15)
                        .build();
                break;
            case OFFICER:
                human = Officer.builderOfficer()
                        .firstName("Egor")
                        .lasttName("Nakaznyuk")
                        .militaryBadge(15)
                        .build();
                break;
            case SOLDIER:
                human = Soldier.builderSoldier()
                        .firstName("Egor")
                        .lasttName("Nakaznyuk")
                        .militaryBadge(15)
                        .build();
                break;
            default:
                throw new RankException("No other rank in army");
        }
        return human;
    }
}

package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.Champion;

import java.util.ArrayList;

public class ChampionList {
    public static Ahri ahri = new Ahri();
    public static Azir azir = new Azir();
    public static Ezreal ezreal = new Ezreal();
    public static Jhin jhin = new Jhin();
    public static Jinx jinx = new Jinx();
    public static Kaisa kaisa = new Kaisa();
    public static Karma karma = new Karma(1);
    public static Lucian lucian = new Lucian();
    public static Senna senna = new Senna(0);
    public static Syndra syndra = new Syndra(0);
    public static Varus varus = new Varus();
    public static Vayne vayne = new Vayne();
    public static Zed zed = new Zed();


    public static ArrayList<Champion> allChampions = new ArrayList<>() {{
        add(ahri);
        add(azir);
        add(ezreal);
        add(jhin);
        add(jinx);
        add(kaisa);
        add(karma);
        add(lucian);
        add(senna);
        add(syndra);
        add(varus);
        add(vayne);
        add(zed);
    }};
}

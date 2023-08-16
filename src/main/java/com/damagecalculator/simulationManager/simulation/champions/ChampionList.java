package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.Champion;

import java.util.ArrayList;

public class ChampionList {
    public static Ahri ahri = new Ahri();
    public static Kaisa kaisa = new Kaisa();
    public static Lucian lucian = new Lucian();
    public static Syndra syndra = new Syndra(0);
    public static Varus varus = new Varus();
    public static Vayne vayne = new Vayne();


    public static ArrayList<Champion> allChampions = new ArrayList<>() {{
        add(ahri);
        add(kaisa);
        add(lucian);
        add(syndra);
        add(varus);
        add(vayne);
    }};
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Cryptbloom extends Item {
    public static final String name = "Cryptbloom";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2850;

    public Cryptbloom() {
        super(name, type, cost);
        ap = 60;
        ah = 10;
        percent_magic_pen = 30;
    }

    //ignoring healing

    @Override
    public Item makeCopy() {
        return new Cryptbloom();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class KaenicRookern extends Item {
    public static final String name = "Kaenic Rookern";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2900;

    public KaenicRookern() {
        super(name, type, cost);
        hp = 400;
        mr = 80;
        hp_regen = 100;
    }

    //ignoring magic shield

    @Override
    public Item makeCopy() {
        return new KaenicRookern();
    }
}

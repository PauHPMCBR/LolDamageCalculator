package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Hubris extends Item {
    public static final String name = "Hubris";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    //int statues; ignoring statue thing for now
    public Hubris() {
        super(name, type, cost);
        ad = 60;
        lethality = 18;
        ah = 10;
    }

    @Override
    public Item makeCopy() {
        return new Hubris();
    }
}

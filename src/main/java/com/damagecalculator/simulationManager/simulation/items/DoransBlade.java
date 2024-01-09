package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class DoransBlade extends Item {
    public static final String name = "Doran's Blade";
    public static final ItemType type = ItemType.STARTER;
    public static final int cost = 450;

    public DoransBlade() {
        super(name, type, cost);
        ad = 10;
        hp = 100;
        lifesteal = 3; //L + ratio no 3.5 bruhh
    }

    @Override
    public Item makeCopy() {
        return new DoransBlade();
    }
}

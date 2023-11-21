package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class PlatedSteelcaps extends Item {
    public static final String name = "Plated Steelcaps";
    public static final ItemType type = ItemType.BOOTS;
    public static final int cost = 1100;

    public PlatedSteelcaps() {
        super(name, type, cost);
        ms = 45;
        armor = 20;
    }

    @Override
    public Item makeCopy() {
        return new PlatedSteelcaps();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class AegisOfTheLegion extends Item {
    public static final String name = "Aegis of the Legion";
    public static final ItemType type = ItemType.BASIC;
    public static final int cost = 1100;

    public AegisOfTheLegion() {
        super(name, type, cost);
        ah = 10;
        armor = 30;
        mr = 30;
    }

    @Override
    public Item makeCopy() {
        return new AegisOfTheLegion();
    }
}

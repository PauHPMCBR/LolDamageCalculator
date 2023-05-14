package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ExecutionersCalling extends Item {
    public static final String name = "Executioner's Calling";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 800;

    public ExecutionersCalling() {
        super(name, type, cost);
        ad = 15;
    }

    @Override
    public Item makeCopy() {
        return new ExecutionersCalling();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ForceOfNature extends Item {
    public static final String name = "Force of Nature";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2800;

    public ForceOfNature() {
        super(name, type, cost);
        hp = 400;
        mr = 55;
        percent_ms = 5;
    }

    //ignoring stacking

    @Override
    public Item makeCopy() {
        return new ForceOfNature();
    }
}

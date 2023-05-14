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
        mr = 60;
        ms = 5;
    }

    @Override
    public Item makeCopy() {
        return new ForceOfNature();
    }
}

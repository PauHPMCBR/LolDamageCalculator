package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ForceOfNature extends Item {
    public static final String name = "Force of Nature";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2900;

    public ForceOfNature() {
        super(name, type, cost);
        hp = 350;
        mr = 70;
        ms = 5;
    }

    @Override
    public Item makeCopy() {
        return new ForceOfNature();
    }
}

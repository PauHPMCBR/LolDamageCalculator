package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class HarrowingCrescent extends Item {
    public static final String name = "Harrowing Crescent";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 400;

    public HarrowingCrescent() {
        super(name, type, cost);
        ad = 10;
        hp = 60;
        manaRegen = 50;
    }

    @Override
    public Item makeCopy() {
        return new HarrowingCrescent();
    }
}

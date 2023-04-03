package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class KnightsVow extends Item {
    public static final String name = "Knight's Vow";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2300;

    public KnightsVow() {
        super(name, type, cost);
        ah = 20;
        hp = 400;
        hpRegen = 200;
    }

    @Override
    public Item makeCopy() {
        return new KnightsVow();
    }
}

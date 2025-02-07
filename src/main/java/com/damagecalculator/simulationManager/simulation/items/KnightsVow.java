package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class KnightsVow extends Item {
    public static final String name = "Knight's Vow";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2300;

    public KnightsVow() {
        super(name, type, cost);
        ah = 10;
        armor = 40;
        hp = 200;
        hp_regen = 100;
    }

    @Override
    public Item makeCopy() {
        return new KnightsVow();
    }
}

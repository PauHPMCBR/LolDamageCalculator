package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class LongSword extends Item {
    public static final String name = "Long Sword";
    public static final ItemType type = ItemType.BASIC;
    public static final int cost = 350;

    public LongSword() {
        super(name, type, cost);
        ad = 10;
    }

    @Override
    public Item makeCopy() {
        return new LongSword();
    }
}

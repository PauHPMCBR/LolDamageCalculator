package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class NullMagicMantle extends Item {
    public static final String name = "Null-Magic Mantle";
    public static final ItemType type = ItemType.BASIC;
    public static final int cost = 450;

    public NullMagicMantle() {
        super(name, type, cost);
        mr = 25;
    }

    @Override
    public Item makeCopy() {
        return new NullMagicMantle();
    }
}

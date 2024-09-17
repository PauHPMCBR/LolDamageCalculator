package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class NegatronCloak extends Item {
    public static final String name = "Negatron Cloak";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 850;

    public NegatronCloak() {
        super(name, type, cost);
        mr = 45;
    }

    @Override
    public Item makeCopy() {
        return new NegatronCloak();
    }
}

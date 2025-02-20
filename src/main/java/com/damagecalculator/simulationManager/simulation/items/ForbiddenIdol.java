package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ForbiddenIdol extends Item {
    public static final String name = "Forbidden Idol";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 600;

    public ForbiddenIdol() {
        super(name, type, cost);
        mana_regen = 50;
    }

    @Override
    public Item makeCopy() {
        return new ForbiddenIdol();
    }
}

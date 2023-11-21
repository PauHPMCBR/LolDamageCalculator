package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BlastingWand extends Item {
    public static final String name = "Blasting Wand";
    public static final ItemType type = ItemType.BASIC;
    public static final int cost = 850;

    public BlastingWand() {
        super(name, type, cost);
        ap = 45;
    }

    @Override
    public Item makeCopy() {
        return new BlastingWand();
    }
}

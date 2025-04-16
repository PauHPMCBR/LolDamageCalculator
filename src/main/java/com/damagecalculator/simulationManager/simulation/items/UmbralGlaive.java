package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class UmbralGlaive extends Item {
    public static final String name = "Umbral Glaive";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2500;

    public UmbralGlaive() {
        super(name, type, cost);
        ad = 55;
        ah = 10;
        lethality = 15;
    }

    @Override
    public Item makeCopy() {
        return new UmbralGlaive();
    }
}

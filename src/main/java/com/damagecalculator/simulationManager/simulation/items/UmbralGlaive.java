package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class UmbralGlaive extends Item {
    public static final String name = "Umbral Glaive";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2300;

    public UmbralGlaive() {
        super(name, type, cost);
        ad = 50;
        ah = 15;
        lethality = 13;
    }

    @Override
    public Item makeCopy() {
        return new UmbralGlaive();
    }
}

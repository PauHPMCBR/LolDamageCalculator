package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class CrystallineBracer extends Item {
    public static final String name = "Crystalline Bracer";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 800;

    public CrystallineBracer() {
        super(name, type, cost);
        hp = 200;
        hpRegen = 100;
    }

    @Override
    public Item makeCopy() {
        return new CrystallineBracer();
    }
}

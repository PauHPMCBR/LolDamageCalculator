package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class GuardiansOrb extends Item {
    public static final String name = "Guardian's Orb";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 950;

    public GuardiansOrb() {
        super(name, type, cost);
        ap = 50;
        hp = 150;
    }

    @Override
    public Item makeCopy() {
        return new GuardiansOrb();
    }
}

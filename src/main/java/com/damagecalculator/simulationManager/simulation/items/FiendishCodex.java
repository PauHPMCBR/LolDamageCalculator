package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class FiendishCodex extends Item {
    public static final String name = "Fiendish Codex";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 900;

    public FiendishCodex() {
        super(name, type, cost);
        ap = 35;
        ah = 10;
    }

    @Override
    public Item makeCopy() {
        return new FiendishCodex();
    }
}

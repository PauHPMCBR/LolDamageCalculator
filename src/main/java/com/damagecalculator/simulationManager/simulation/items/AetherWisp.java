package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class AetherWisp extends Item {
    public static final String name = "Aether Wisp";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 850;

    public AetherWisp() {
        super(name, type, cost);
        ap = 30;
        ms = 5;
    }

    @Override
    public Item makeCopy() {
        return new AetherWisp();
    }
}
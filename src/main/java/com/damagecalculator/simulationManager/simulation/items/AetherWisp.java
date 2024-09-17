package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class AetherWisp extends Item {
    public static final String name = "Aether Wisp";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 900;

    public AetherWisp() {
        super(name, type, cost);
        ap = 30;
        percent_ms = 5;
    }

    @Override
    public Item makeCopy() {
        return new AetherWisp();
    }
}

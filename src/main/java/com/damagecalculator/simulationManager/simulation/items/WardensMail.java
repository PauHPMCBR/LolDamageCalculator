package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class WardensMail extends Item {
    public static final String name = "Warden's Mail";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1000;

    public WardensMail() {
        super(name, type, cost);
        armor = 40;
    }

    @Override
    public Item makeCopy() {
        return new WardensMail();
    }
}

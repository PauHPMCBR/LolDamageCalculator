package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class QuicksilverSash extends Item {
    public static final String name = "Quicksilver Sash";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1300;

    public QuicksilverSash() {
        super(name, type, cost);
        mr = 30;
    }

    @Override
    public Item makeCopy() {
        return new QuicksilverSash();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class HorizonFocus extends Item {
    public static final String name = "Horizon Focus";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2800;

    public HorizonFocus() {
        super(name, type, cost);
        ap = 115;
        ah = 25;
    }

    @Override
    public Item makeCopy() {
        return new HorizonFocus();
    }
}

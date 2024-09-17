package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class WingedMoonplate extends Item {
    public static final String name = "Winged Moonplate";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 800;

    public WingedMoonplate() {
        super(name, type, cost);
        hp = 200;
        percent_ms = 4;
    }

    @Override
    public Item makeCopy() {
        return new WingedMoonplate();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RylaisCrystalScepter extends Item {
    public static final String name = "Rylai's Crystal Scepter";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2600;

    public RylaisCrystalScepter() {
        super(name, type, cost);
        ap = 75;
        hp = 400;
    }

    @Override
    public Item makeCopy() {
        return new RylaisCrystalScepter();
    }
}

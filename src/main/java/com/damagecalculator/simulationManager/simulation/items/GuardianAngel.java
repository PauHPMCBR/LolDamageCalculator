package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class GuardianAngel extends Item {
    public static final String name = "Guardian Angel";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public GuardianAngel() {
        super(name, type, cost);
        ad = 55;
        armor = 45;
    }

    @Override
    public Item makeCopy() {
        return new GuardianAngel();
    }
}

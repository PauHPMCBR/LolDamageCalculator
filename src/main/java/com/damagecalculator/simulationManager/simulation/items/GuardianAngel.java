package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class GuardianAngel extends Item {
    public static final String name = "Guardian Angel";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public GuardianAngel() {
        super(name, type, cost);
        ad = 45;
        armor = 40;
    }

    @Override
    public Item makeCopy() {
        return new GuardianAngel();
    }
}

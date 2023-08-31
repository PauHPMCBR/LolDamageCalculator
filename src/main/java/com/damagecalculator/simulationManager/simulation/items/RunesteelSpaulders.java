package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RunesteelSpaulders extends Item {
    public static final String name = "Runesteel Spaulders";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 400;

    public RunesteelSpaulders() {
        super(name, type, cost);
        ad = 6;
        hp = 100;
        hp_regen = 75;
    }

    @Override
    public Item makeCopy() {
        return new RunesteelSpaulders();
    }
}

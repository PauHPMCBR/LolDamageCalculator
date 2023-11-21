package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SeekersArmguard extends Item {
    public static final String name = "Seeker's Armguard";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1600;

    public SeekersArmguard() {
        super(name, type, cost);
        ap = 40;
        armor = 35;
    }

    @Override
    public Item makeCopy() {
        return new SeekersArmguard();
    }
}

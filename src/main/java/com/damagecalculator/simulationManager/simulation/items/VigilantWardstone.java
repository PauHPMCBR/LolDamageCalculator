package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class VigilantWardstone extends Item {
    public static final String name = "Vigilant Wardstone";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2300;

    public VigilantWardstone() {
        super(name, type, cost);
        hp = 250;
        armor = 25;
        mr = 30;
        ah = 20;
    }

    @Override
    public Item makeCopy() {
        return new VigilantWardstone();
    }
}

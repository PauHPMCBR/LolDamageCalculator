package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class CosmicDrive extends Item {
    public static final String name = "Cosmic Drive";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public CosmicDrive() {
        super(name, type, cost);
        ap = 80;
        hp = 350;
        ah = 25;
        percent_ms = 5;
    }

    @Override
    public Item makeCopy() {
        return new CosmicDrive();
    }
}

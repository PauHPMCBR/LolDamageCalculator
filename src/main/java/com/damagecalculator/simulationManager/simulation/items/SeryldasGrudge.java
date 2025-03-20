package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SeryldasGrudge extends Item {
    public static final String name = "Serylda's Grudge";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 300;

    public SeryldasGrudge() {
        super(name, type, cost);
        ad = 45;
        ah = 15;
        armor_pen = 35;
    }

    @Override
    public Item makeCopy() {
        return new SeryldasGrudge();
    }
}

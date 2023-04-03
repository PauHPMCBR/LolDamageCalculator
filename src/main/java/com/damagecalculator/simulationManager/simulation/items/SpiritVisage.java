package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SpiritVisage extends Item {
    public static final String name = "Spirit Visage";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2900;

    public SpiritVisage() {
        super(name, type, cost);
        ah = 10;
        hp = 450;
        mr = 50;
        hpRegen = 100;
    }

    @Override
    public Item makeCopy() {
        return new SpiritVisage();
    }
}

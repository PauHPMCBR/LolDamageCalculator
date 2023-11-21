package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SeryldasGrudge extends Item {
    public static final String name = "Serylda's Grudge";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3200;

    public SeryldasGrudge() {
        super(name, type, cost);
        ad = 45;
        lethality = 15;
        ah = 15;
    }

    public void specialStats() {
        owner.increaseArmorPen(22 + 0.12f * owner.LETHALITY);
    }

    @Override
    public Item makeCopy() {
        return new SeryldasGrudge();
    }
}

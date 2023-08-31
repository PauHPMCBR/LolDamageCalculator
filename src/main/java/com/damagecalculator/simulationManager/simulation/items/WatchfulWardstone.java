package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class WatchfulWardstone extends Item {
    public static final String name = "Watchful Wardstone";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1100;

    public WatchfulWardstone() {
        super(name, type, cost);
        ap = 10;
        hp = 150;
        mana_regen = 50;
    }

    public void specialStats() {
        owner.BONUS_AD *= 1.08;
        owner.AP *= 1.08;
        owner.AH *= 1.08;
        owner.BONUS_HP *= 1.08;
    }

    @Override
    public Item makeCopy() {
        return new WatchfulWardstone();
    }
}

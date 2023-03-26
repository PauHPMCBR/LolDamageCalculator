package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class VigilantWardstone extends Item {
    public static final String name = "Vigilant Wardstone";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 1100;

    public VigilantWardstone() {
        super(name, type, cost);
        ah = 15;
        hp = 150;
    }

    public void specialStats() {
        owner.BONUS_AD *= 1.12;
        owner.AP *= 1.12;
        owner.AH *= 1.12;
        owner.BONUS_HP *= 1.12;
    }

    @Override
    public Item makeCopy() {
        return new VigilantWardstone();
    }
}

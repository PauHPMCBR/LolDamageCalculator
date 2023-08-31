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
        ap = 10;
        mana_regen = 50;
    }

    public void specialStats() {
        owner.BONUS_AD *= 1.2;
        owner.AP *= 1.2;
        owner.AH *= 1.2;
        owner.BONUS_HP *= 1.2;
    }

    @Override
    public Item makeCopy() {
        return new VigilantWardstone();
    }
}

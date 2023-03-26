package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Fimbulwinter extends Item {
    public static final String name = "Fimbulwinter";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2600;

    public Fimbulwinter() {
        super(name, type, cost);
        ah = 15;
        hp = 400;
        mana = 860;
    }

    public void specialStats() {
        owner.BONUS_HP += 0.08 * owner.MANA;
    }

    @Override
    public Item makeCopy() {
        return new Fimbulwinter();
    }
}

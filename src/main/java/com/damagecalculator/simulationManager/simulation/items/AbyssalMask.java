package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class AbyssalMask extends Item {
    public static final String name = "Abyssal Mask";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2300;

    public AbyssalMask() {
        super(name, type, cost);
        ah = 10;
        hp = 300;
        mr = 50;
    }

    public void specialStats() {
        owner.MAGIC_RESIST += 10; //9 per enemy, supposing 1v1
        owner.getEnemy().MAGIC_RESIST *= 0.8f; //20% magic reduction
    }

    @Override
    public Item makeCopy() {
        return new AbyssalMask();
    }
}

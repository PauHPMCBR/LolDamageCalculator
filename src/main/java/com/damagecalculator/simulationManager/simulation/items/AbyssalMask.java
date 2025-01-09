package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class AbyssalMask extends Item {
    public static final String name = "Abyssal Mask";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2650;

    public AbyssalMask() {
        super(name, type, cost);
        ah = 15;
        hp = 350;
        mr = 50;
    }

    public void specialStats() {
        cs.abyssalMaskItem = this;
    }

    @Override
    public Item makeCopy() {
        return new AbyssalMask();
    }
}

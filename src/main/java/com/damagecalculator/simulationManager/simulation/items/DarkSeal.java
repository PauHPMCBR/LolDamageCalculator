package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class DarkSeal extends Item {
    public static final String name = "Dark Seal";
    public static final ItemType type = ItemType.STARTER;
    public static final int cost = 350;

    int stacks;

    public DarkSeal(int stacks) {
        super(name, type, cost);
        ap = 15;
        hp = 50;
        extraVariableName = "Dark Seal Stacks (0-10)";
        this.stacks = Math.min(10, stacks);
    }

    public void specialStats() {
        owner.AP += 4 * Math.min(stacks, 10);
    }

    @Override
    public Item makeCopy() {
        return new DarkSeal(stacks);
    }
}

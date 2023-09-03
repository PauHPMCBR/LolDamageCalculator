package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class VerdantBarrier extends Item {
    public static final String name = "Verdant Barrier";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1000;

    int stacks;

    public VerdantBarrier(int stacks) {
        super(name, type, cost);
        ap = 20;
        mr = 25;
        extraVariableName = "Verdant Stacks (0-30)";
        this.stacks = Math.min(stacks, 30);
    }

    public void specialStats() {
        owner.MAGIC_RESIST += 0.3f * stacks;
    }

    @Override
    public Item makeCopy() {
        return new VerdantBarrier(stacks);
    }
}

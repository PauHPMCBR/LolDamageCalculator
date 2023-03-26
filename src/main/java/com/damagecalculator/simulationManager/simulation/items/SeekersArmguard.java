package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SeekersArmguard extends Item {
    public static final String name = "Seeker's Armguard";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1000;

    int stacks;

    public SeekersArmguard(int stacks) {
        super(name, type, cost);
        ap = 30;
        armor = 15;
        extraVariableName = "Seeker's Stacks";
        this.stacks = Math.min(stacks, 30);
    }

    public void specialStats() {
        owner.ARMOR += 0.5f * stacks;
    }

    @Override
    public Item makeCopy() {
        return new SeekersArmguard(stacks);
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RodOfAges extends Item {
    public static final String name = "Rod of Ages";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2700;

    int stacks;

    public RodOfAges(int stacks) {
        super(name, type, cost);
        ap = 50;
        hp = 300;
        mana = 300;
        extraVariableName = "ROA Minutes (0-10)";
        this.stacks = Math.min(stacks, 10);
    }

    public void specialStats() {
        owner.BONUS_HP += 20 * stacks;
        owner.AP += 4 * stacks;
        owner.MANA += 20 * stacks;
    }


    @Override
    public Item makeCopy() {
        return new RodOfAges(stacks);
    }
}

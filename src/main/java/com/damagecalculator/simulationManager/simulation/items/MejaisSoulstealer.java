package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class MejaisSoulstealer extends Item {
    public static final String name = "Mejai's Soulstealer";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 1600;

    int stacks;

    public MejaisSoulstealer(int stacks) {
        super(name, type, cost);
        ap = 20;
        hp = 100;
        extraVariableName = "Mejai's Stacks";
        this.stacks = stacks;
    }

    public void specialStats() {
        owner.AP += 5 * stacks; //extraVariable are mejai's stacks
    }

    @Override
    public Item makeCopy() {
        return new MejaisSoulstealer(stacks);
    }
}

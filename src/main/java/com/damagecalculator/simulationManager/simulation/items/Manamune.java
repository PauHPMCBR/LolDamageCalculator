package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Manamune extends Item {
    public static final String name = "Manamune";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2900;

    int stacks;

    public Manamune(int stacks) {
        super(name, type, cost);
        ad = 35;
        ah = 15;
        mana = 500;

        this.stacks = Math.min(stacks, 360);
    }

    public void specialStats() {
        owner.MANA += stacks;
        owner.BONUS_AD += owner.MANA * 0.025;
    }

    @Override
    public Item makeCopy() {
        return new Manamune(stacks);
    }
}

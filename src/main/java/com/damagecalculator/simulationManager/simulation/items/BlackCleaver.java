package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BlackCleaver extends Item {
    public static final String name = "Black Cleaver";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    int carveStacks;

    public BlackCleaver() {
        super(name, type, cost);
        ad = 55;
        hp = 400;
        ah = 20;

        carveStacks = 0;
    }

    public void specialStats() {
        cs.cleaverItem = this;
    }

    public void increaseCarveStacks() { //will get called by "Damage" class. special function
        if (carveStacks >= 5) return;
        owner.getEnemy().ARMOR /= (1 - carveStacks*0.05f);
        ++carveStacks;
        owner.getEnemy().ARMOR *= (1 - carveStacks*0.05f);
    }

    @Override
    public Item makeCopy() {
        return new BlackCleaver();
    }
}

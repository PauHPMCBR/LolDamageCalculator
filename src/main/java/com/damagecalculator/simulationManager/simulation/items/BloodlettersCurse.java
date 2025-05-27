package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BloodlettersCurse extends Item {
    public static final String name = "Bloodletter's Curse";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2900;

    int decayStacks;

    public BloodlettersCurse() {
        super(name, type, cost);
        ap = 65;
        hp = 400;
        ah = 15;

        decayStacks = 0;
    }

    public void specialStats() {
        cs.bloodlettersCurseItem = this;
    }

    public void increaseDecayStacks() { //will get called by "Damage" class. special function
        if (decayStacks >= 4) return;
        owner.getEnemy().MAGIC_RESIST /= (1 - decayStacks*0.075f);
        ++decayStacks;
        owner.getEnemy().MAGIC_RESIST *= (1 - decayStacks*0.075f);
    }

    @Override
    public Item makeCopy() {
        return new BloodlettersCurse();
    }
}

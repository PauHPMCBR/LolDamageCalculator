package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SpearOfShojin extends Item {
    public static final String name = "Spear of Shojin";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3100;

    int currentStacks;

    public SpearOfShojin() {
        super(name, type, cost);
        ad = 45;
        hp = 450;
    }

    public void specialStats() {
        owner.AH += 25;
        owner.ULTIMATE_HASTE -= 25;
        currentStacks = 0;
    }

    public void extraDmg() { //check this works?
        if (currentStacks == 4) return;
        cs.abilityDamageMultiplier /= (1 + 0.03f * currentStacks);
        ++currentStacks;
        cs.abilityDamageMultiplier *= (1 + 0.03f * currentStacks);
    }

    @Override
    public Item makeCopy() {
        return new SpearOfShojin();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SpearOfShojin extends Item {
    public static final String name = "Spear of Shojin";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3100;

    int currentStacks;
    float increase;

    public SpearOfShojin() {
        super(name, type, cost);
        ad = 55;
        ah = 20;
        hp = 500;
    }

    public void specialStats() {
        owner.AH += 15;
        owner.ULTIMATE_HASTE -= 15;
        currentStacks = 0;

        increase = owner.is_ranged ? 0.02f : 0.03f;
    }

    public void extraDmg() { //check this works?
        if (currentStacks == 4) return;
        cs.abilityDamageMultiplier /= (1 + increase * currentStacks);
        ++currentStacks;
        cs.abilityDamageMultiplier *= (1 + increase * currentStacks);
    }

    @Override
    public Item makeCopy() {
        return new SpearOfShojin();
    }
}

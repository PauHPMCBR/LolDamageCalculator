package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class HauntingGuise extends Item {
    public static final String name = "Haunting Guise";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1300;

    float currentDmgMult;
    public HauntingGuise() {
        super(name, type, cost);
        ap = 35;
        hp = 200;
    }

    public void specialStats() {
        currentDmgMult = 1;
    }

    public void extraDmg() {
        cs.damageTrueMultiplier /= currentDmgMult;
        currentDmgMult = 1 + (int)(Math.min(cs.time, 3)) * 0.02f;
        cs.damageTrueMultiplier *= currentDmgMult;
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new HauntingGuise();
    }
}

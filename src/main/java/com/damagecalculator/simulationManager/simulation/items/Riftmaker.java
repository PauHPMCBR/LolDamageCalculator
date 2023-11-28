package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Riftmaker extends Item {
    public static final String name = "Riftmaker";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    float currentDmgMult;
    public Riftmaker() {
        super(name, type, cost);
        ap = 80;
        ah = 15;
        hp = 350;
    }

    public void specialStats() {
        owner.AP += owner.getMaxHP() * 0.02f;
        currentDmgMult = 1;
    }

    //ignoring omnivamp
    public void extraDmg() {
        cs.damageTrueMultiplier /= currentDmgMult;
        currentDmgMult = 1 + (int)(Math.min(cs.time, 5)) * 0.02f;
        cs.damageTrueMultiplier *= currentDmgMult;
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new Riftmaker();
    }
}

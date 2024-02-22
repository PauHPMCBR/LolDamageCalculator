package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class LudensCompanion extends Item {
    public static final String name = "Luden's Companion";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2900;

    int currentStacks;
    float lastStackGained;

    public LudensCompanion() {
        super(name, type, cost);
        ap = 95;
        ah = 25;
        mana = 600;
    }

    public void specialStats() {
        currentStacks = 6; //start with max stacks
        lastStackGained = -1;
    }

    public void extraDmg() {
        //calculate gained stacks
        while (currentStacks < 6 && lastStackGained + 3 <= cs.time) {
            lastStackGained += 3;
            ++currentStacks;
        }

        float dmg = 45 + 0.04f * owner.AP;
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 2);
        for (int i = 1; i < currentStacks; ++i) { //subsequent stacks apply 1 by 1, big one occurs once
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg * 0.35f, 2); //supposing all hit single-target
        }

        currentStacks = 0;
        lastStackGained = cs.time;
    }


    @Override
    public Item makeCopy() {
        return new LudensCompanion();
    }
}

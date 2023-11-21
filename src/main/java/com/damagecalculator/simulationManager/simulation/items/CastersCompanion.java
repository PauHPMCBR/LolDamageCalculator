package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class CastersCompanion extends Item {
    public static final String name = "Caster's Companion";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    int currentStacks;
    float lastStackGained;

    public CastersCompanion() {
        super(name, type, cost);
        ap = 90;
        ah = 20;
        mana = 600;
        item_cooldown = 10;
    }

    public void specialStats() {
        currentStacks = 6; //start with max stacks
        lastStackGained = -1000;
    }

    public void extraDmg() {
        //calculate gained stacks
        while (currentStacks < 6 && lastStackGained + 3 <= cs.time) {
            lastStackGained += 3;
            ++currentStacks;
        }

        float dmg = 40 + 0.08f * owner.AP;
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 2);
        for (int i = 0; i < currentStacks; ++i) { //subsequent stacks apply 1 by 1, big one occurs once
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg * 0.35f, 2); //supposing all hit single-target
        }

        currentStacks = 0;
        lastStackGained = cs.time;
    }


    @Override
    public Item makeCopy() {
        return new CastersCompanion();
    }
}

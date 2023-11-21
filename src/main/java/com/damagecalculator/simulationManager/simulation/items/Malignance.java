package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.AbilityType;
import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Malignance extends Item {
    public static final String name = "Malignance";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    boolean active;
    float timeActivated;
    float lastTick;

    float mrReduction;


    //TODO check cooldowns, karma, teemo, etc?
    public Malignance() {
        super(name, type, cost);
        ap = 80;
        ah = 20;
        mana = 600;
    }

    public void specialStats() {
        owner.ULTIMATE_HASTE += 15;
        active = false;
        mrReduction = 6 + owner.lvl * 0.3f; //TODO level scaling!
    }

    public void extraDmg() {
        if (active) {
            float secs = cs.time - lastTick; //extra variable is time
            if (cs.time - timeActivated >= 3) {
                active = false;
                owner.getEnemy().MAGIC_RESIST += mrReduction;
                secs = 3 - (lastTick - timeActivated);
            }
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                    (float) (secs * (60 + 0.06 * owner.AP)), 1);
            lastTick = cs.time;
        }
        else if (owner.lastAbilityUsed == AbilityType.R) {
            active = true;
            owner.getEnemy().MAGIC_RESIST -= mrReduction;
            timeActivated = cs.time;
            lastTick = cs.time;
        }
    }

    @Override
    public Item makeCopy() {
        return new Malignance();
    }
}
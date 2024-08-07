package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Stormsurge extends Item {
    public static final String name = "Stormsurge";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2900;

    boolean activated;
    float timeActivated;
    public Stormsurge() {
        super(name, type, cost);
        ap = 95;
        magic_pen = 10;
        ms = 5;
    }

    public void specialStats() {
        activated = false;
        timeActivated = 0;
    }

    public void extraDmg() {
        if (!activated && timeActivated != 0) return; //already used
        if (activated) {
            if (cs.time - timeActivated >= 2) {
                float dmg = 140 + 0.2f * owner.AP;
                if (owner.is_ranged) dmg *= 0.9f;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,dmg, 2);
                activated = false;
            }
        }
        else if (owner.getEnemy().getRelativeMissingHP() >= 0.35) {
            activated = true;
            timeActivated = cs.time;
        }
    }

    @Override
    public Item makeCopy() {
        return new Stormsurge();
    }
}

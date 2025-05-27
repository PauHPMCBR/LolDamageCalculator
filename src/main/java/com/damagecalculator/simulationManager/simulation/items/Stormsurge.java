package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Stormsurge extends Item {
    public static final String name = "Stormsurge";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2800;

    boolean activated;
    float timeActivated;
    public Stormsurge() {
        super(name, type, cost);
        ap = 90;
        magic_pen = 15;
        percent_ms = 6;
    }

    public void specialStats() {
        activated = false;
        timeActivated = 0;
    }

    public void extraDmg() {
        if (!activated && timeActivated != 0) return; //already used
        if (activated) {
            if (cs.time - timeActivated >= 2) {
                float dmg = 125 + 0.1f * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 2);
                activated = false;
            }
        }
        else if (owner.getEnemy().getRelativeMissingHP() >= 0.25) {
            activated = true;
            timeActivated = cs.time;
        }
    }

    @Override
    public Item makeCopy() {
        return new Stormsurge();
    }
}

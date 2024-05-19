package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.AbilityType;
import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ZekesConvergence extends Item {
    public static final String name = "Zeke's Convergence";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2200;

    boolean active;
    float timeActivated;
    float lastTick;
    public ZekesConvergence() {
        super(name, type, cost);
        ah = 10;
        hp = 300;
        armor = 25;
        mr = 25;
    }

    public void specialStats() {
        active = false;
    }

    public void onHit() {
        if (active) {
            float timeDiff = cs.time - lastTick;
            if (cs.time - timeActivated > 5) {
                timeDiff = 5 - (lastTick - timeDiff);
                active = false;
            }
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 50 * timeDiff, 2);
        }
    }
    public void extraDmg() {
        onHit();
        if (owner.lastAbilityUsed == AbilityType.R) {
            active = true;
            timeActivated = cs.time;
            lastTick = cs.time;
        }
    }

    @Override
    public Item makeCopy() {
        return new ZekesConvergence();
    }
}

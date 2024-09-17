
package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class LiandrysTorment extends Item {
    public static final String name = "Liandry's Torment";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    float currentDmgMult;
    float lastTick;

    public LiandrysTorment() {
        super(name, type, cost);
        ap = 70;
        hp = 300;

        lastTick = 0;
    }

    public void specialStats() {
        currentDmgMult = 1;
    }

    public void extraDmg() {
        cs.damageTrueMultiplier /= currentDmgMult;
        currentDmgMult = 1 + (int)(Math.min(cs.time, 3)) * 0.02f;
        cs.damageTrueMultiplier *= currentDmgMult;

        float secs = Math.min(cs.time - lastTick, 3); //extra variable is time
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                (float) (secs * 0.02 * owner.getEnemy().getMaxHP()), 1);
        lastTick = cs.time;
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new LiandrysTorment();
    }
}

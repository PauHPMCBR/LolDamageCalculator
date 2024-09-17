package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class HollowRadiance extends Item {
    public static final String name = "Hollow Radiance";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2800;

    float lastTick;

    public HollowRadiance() {
        super(name, type, cost);
        hp = 400;
        ah = 10;
        mr = 40;
        hp_regen = 100;
    }

    public void specialStats() {
        lastTick = 0;
    }

    //ignoring the killing enemy part

    public void extraDmg() {
        float ticks = cs.time - lastTick;
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                (float) (ticks * (15 + 0.01 * owner.BONUS_HP)), 1);
        lastTick = cs.time;
    }

    public void onHit() {
        extraDmg();
    }


    @Override
    public Item makeCopy() {
        return new HollowRadiance();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SunfireAegis extends Item {
    public static final String name = "Sunfire Aegis";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2700;

    float dmgMult;
    float lastTick;

    public SunfireAegis() {
        super(name, type, cost);
        hp = 450;
        armor = 50;
    }

    public void specialStats() {
        lastTick = 0;
        dmgMult = 1;
    }

    public void extraDmg() {
        dmgMult = 1 +  0.1f * Math.min(cs.time, 5);
        float ticks = cs.time - lastTick;
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                (float) (ticks * (12 + 0.0175 * owner.BONUS_HP) * dmgMult), 1);
        lastTick = cs.time;
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new SunfireAegis();
    }
}

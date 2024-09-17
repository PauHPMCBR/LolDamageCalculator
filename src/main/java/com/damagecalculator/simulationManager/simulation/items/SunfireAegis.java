package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SunfireAegis extends Item {
    public static final String name = "Sunfire Aegis";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2700;

    float lastTick;

    public SunfireAegis() {
        super(name, type, cost);
        hp = 350;
        ah = 10;
        armor = 50;
    }

    public void specialStats() {
        lastTick = 0;
    }

    public void extraDmg() {
        float ticks = cs.time - lastTick;
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (ticks * (20 + 0.01f * owner.BONUS_HP)), 1);
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

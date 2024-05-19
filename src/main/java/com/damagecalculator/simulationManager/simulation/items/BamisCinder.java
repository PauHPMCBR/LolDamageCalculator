package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BamisCinder extends Item {
    public static final String name = "Bami's Cinder";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 900;

    float lastTick;

    public BamisCinder() {
        super(name, type, cost);
        hp = 200;
        ah = 5;

        lastTick = 0;
    }

    public void extraDmg() {
        float ticks = cs.time - lastTick; //extra variable is time
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                (float) (ticks * (13 + 0.005 * owner.BONUS_HP)), 1);
        lastTick = cs.time;
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new BamisCinder();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RunaansHurricane extends Item {
    public static final String name = "Runaan's Hurricane";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2800;

    public RunaansHurricane() {
        super(name, type, cost);
        as = 40;
        crit = 20;
        percent_ms = 7;
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 15, 1);
    }

    @Override
    public Item makeCopy() {
        return new RunaansHurricane();
    }
}

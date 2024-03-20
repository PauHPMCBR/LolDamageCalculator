package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class KircheisShard extends Item {
    public static final String name = "Kircheis Shard";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 700;

    int energize;

    public KircheisShard() {
        super(name, type, cost);
        ad = 15;

        energize = 9;
    }

    public void onHit() {
        ++energize;
        if (energize == 10) { //kinda arbitrary
            energize = 0;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 50, 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new KircheisShard();
    }
}

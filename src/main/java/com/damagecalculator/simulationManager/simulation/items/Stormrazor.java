package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Stormrazor extends Item {
    public static final String name = "Stormrazor";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2700;

    int energize;

    public Stormrazor() {
        super(name, type, cost);
        ad = 45;
        crit = 20;
        as = 15;

        energize = 9;
    }

    public void onHit() {
        ++energize;
        if (energize == 10) { //kinda arbitrary
            energize = 0;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 120, 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new Stormrazor();
    }
}

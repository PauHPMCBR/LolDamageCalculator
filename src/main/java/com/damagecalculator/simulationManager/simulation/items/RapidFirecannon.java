package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RapidFirecannon extends Item {
    public static final String name = "Rapid Firecannon";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2650;

    int energize;

    public RapidFirecannon() {
        super(name, type, cost);
        as = 35;
        crit = 25;
        percent_ms = 4;
    }

    public void specialStats() {
        energize = 9;
    }

    public void onHit() {
        ++energize;
        if (energize == 10) { //kinda arbitrary
            energize = 0;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 40, 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new RapidFirecannon();
    }
}

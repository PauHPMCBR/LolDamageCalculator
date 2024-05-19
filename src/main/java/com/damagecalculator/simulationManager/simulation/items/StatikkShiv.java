package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class StatikkShiv extends Item {
    public static final String name = "Statikk Shiv";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2900;

    int energize;

    public StatikkShiv() {
        super(name, type, cost);
        ad = 50;
        crit = 20;
        as = 30;

        energize = 9;
    }

    public void onHit() {
        ++energize;
        if (energize == 10) { //kinda arbitrary
            energize = 0;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 90, 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new StatikkShiv();
    }
}

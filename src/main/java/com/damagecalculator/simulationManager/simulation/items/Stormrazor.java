package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Stormrazor extends Item {
    public static final String name = "Stormrazor";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    int energize;

    public Stormrazor() {
        super(name, type, cost);
        ad = 55;
        crit = 20;
        as = 15;

        energize = 9;
    }

    public void onHit() {
        ++energize;
        if (energize == 10) { //kinda arbitrary
            energize = 0;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 90 + owner.getAD()*0.25f, 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new Stormrazor();
    }
}

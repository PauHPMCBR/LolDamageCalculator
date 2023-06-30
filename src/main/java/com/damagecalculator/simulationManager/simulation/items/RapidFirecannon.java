package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RapidFirecannon extends Item {
    public static final String name = "Rapid Firecannon";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    int energyze;

    public RapidFirecannon() {
        super(name, type, cost);
        ad = 30;
        as = 15;
        crit = 20;
        ms = 7;

        energyze = 9;
    }

    public void onHit() {
        ++energyze;
        if (energyze == 10) { //kinda arbitrary
            energyze = 0;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 60, 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new RapidFirecannon();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RapidFirecannon extends Item {
    public static final String name = "Rapid Firecannon";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2500;

    int energyze;

    public RapidFirecannon() {
        super(name, type, cost);
        as = 35;
        crit = 20;
        ms = 7;

        energyze = 9;
    }

    public void onHit() {
        ++energyze;
        if (energyze == 10) { //kinda arbitrary
            energyze = 0;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 120, 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new RapidFirecannon();
    }
}

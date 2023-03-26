package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RecurveBow extends Item {
    public static final String name = "Recurve Bow";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1000;

    public RecurveBow() {
        super(name, type, cost);
        as = 25;
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 15, 1);
    }

    @Override
    public Item makeCopy() {
        return new RecurveBow();
    }
}

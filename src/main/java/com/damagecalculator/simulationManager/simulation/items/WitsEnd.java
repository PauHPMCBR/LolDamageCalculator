package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class WitsEnd extends Item {
    public static final String name = "Wit's End";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2800;

    public WitsEnd() {
        super(name, type, cost);
        as = 50;
        mr = 45;
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 45, 1);
    }

    @Override
    public Item makeCopy() {
        return new WitsEnd();
    }
}

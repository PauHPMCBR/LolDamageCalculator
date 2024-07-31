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
        as = 55;
        mr = 50;
    }

    public void onHit() {
        float dmg = 40 + 4*Math.max(0, owner.lvl-8);
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 1);
    }

    @Override
    public Item makeCopy() {
        return new WitsEnd();
    }
}

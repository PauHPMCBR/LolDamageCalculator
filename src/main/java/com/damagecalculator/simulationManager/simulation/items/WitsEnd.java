package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class WitsEnd extends Item {
    public static final String name = "Wit's End";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2900;

    float dmg;
    public WitsEnd() {
        super(name, type, cost);
        as = 55;
        mr = 50;
    }

    public void specialStats() {
        dmg = 15 + Math.max(0, owner.lvl - 8) * 10;
        dmg -= Math.max(0, owner.lvl - 14) * (1.25f - 10);
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 1);
    }

    @Override
    public Item makeCopy() {
        return new WitsEnd();
    }
}

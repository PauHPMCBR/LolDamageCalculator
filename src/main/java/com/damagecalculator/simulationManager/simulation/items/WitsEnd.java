package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class WitsEnd extends Item {
    public static final String name = "Wit's End";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3100;

    float dmg;

    public WitsEnd() {
        super(name, type, cost);
        ad = 40;
        as = 40;
        mr = 40;
    }

    public void specialStats() {
        dmg = 15 + Math.max(0, owner.lvl - 8) * 10;
        dmg -= Math.max(0, owner.lvl - 14) * (1.25 - 10);
    } //supposing champ wont lvl up

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 1);
    }

    @Override
    public Item makeCopy() {
        return new WitsEnd();
    }
}

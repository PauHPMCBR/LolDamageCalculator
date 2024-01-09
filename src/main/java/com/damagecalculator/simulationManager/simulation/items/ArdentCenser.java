package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ArdentCenser extends Item {
    public static final String name = "Ardent Censer";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2300;

    public ArdentCenser() {
        super(name, type, cost);
        ap = 50;
        mana_regen = 125;
        ah = 15; // TODO check ability haste
        hsp = 8;
        percent_ms = 8;
    }

    public void specialStats() {
        owner.BONUS_AS += 25;
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 20, 1);
    }

    @Override
    public Item makeCopy() {
        return new ArdentCenser();
    }
}

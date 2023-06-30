package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ArdentCenser extends Item {
    public static final String name = "Ardent Censer";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2100;

    public ArdentCenser() {
        super(name, type, cost);
        ap = 35;
        manaRegen = 75;
        healShieldPower = 8;
        ms = 5;
    }

    public void specialStats() {
        owner.BONUS_AS += 20;
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 15, 1);
    }

    @Override
    public Item makeCopy() {
        return new ArdentCenser();
    }
}

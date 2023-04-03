package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ArdentCenser extends Item {
    public static final String name = "Ardent Censer";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2300;

    float onhitDamage;

    public ArdentCenser() {
        super(name, type, cost);
        ap = 60;
        manaRegen = 100;
        healShieldPower = 8;
    }

    public void specialStats() {
        owner.BONUS_AS += 10 + 20f / 17f * (owner.lvl - 1);

        onhitDamage = 5 + 15f / 17f * (owner.lvl - 1);
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, onhitDamage);
    }

    @Override
    public Item makeCopy() {
        return new ArdentCenser();
    }
}

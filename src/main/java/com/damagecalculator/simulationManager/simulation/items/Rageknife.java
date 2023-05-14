package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Rageknife extends Item {
    public static final String name = "Rageknife";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1200;

    int stacks;

    public Rageknife() {
        super(name, type, cost);
        as = 25;

        stacks = 0;
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 20, 1);
        if (stacks < 3) {
            ++stacks;
            owner.BONUS_AS += 5;
        }
    }


    @Override
    public Item makeCopy() {
        return new Rageknife();
    }
}

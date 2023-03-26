package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Rageknife extends Item {
    public static final String name = "Rageknife";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 800;

    float critChance;

    public Rageknife() {
        super(name, type, cost);
        as = 25;
    }

    public void specialStats() {
        critChance = owner.CRIT_CHANCE;//item cooldown is amount of crit (from 0 to 100)
        owner.CRIT_CHANCE = 0;
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, critChance * 1.75f, 1);
    }


    @Override
    public Item makeCopy() {
        return new Rageknife();
    }
}

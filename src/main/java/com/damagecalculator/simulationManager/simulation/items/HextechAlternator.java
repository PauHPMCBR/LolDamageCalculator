package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class HextechAlternator extends Item {
    public static final String name = "Hextech Alternator";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1100;

    public HextechAlternator() {
        super(name, type, cost);
        ap = 50;
        item_cooldown = 40;
    }

    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 50f + 75f / 17f * (owner.lvl -1), 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new HextechAlternator();
    }
}

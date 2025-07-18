package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class HextechRocketbelt extends Item {
    public static final String name = "Hextech Rocketbelt";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2650;

    public HextechRocketbelt() {
        super(name, type, cost);
        ap = 70;
        ah = 20;
        hp = 300;
        item_cooldown = 40;
    }

    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (float) (100 + 0.1 * owner.AP), 2);
            owner.autoReset(); //how many people know this? this is so random xd
        }
    }

    @Override
    public Item makeCopy() {
        return new HextechRocketbelt();
    }
}

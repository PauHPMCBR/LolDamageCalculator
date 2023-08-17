package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class HextechRocketbelt extends Item {
    public static final String name = "Hextech Rocketbelt";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3200;

    public HextechRocketbelt() {
        super(name, type, cost);
        ap = 90;
        ah = 15;
        hp = 250;
        magic_pen = 6;
        item_cooldown = 40;
    }

    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (float) (125 + 0.15 * owner.AP), 2);
            owner.autoReset(); //how many people know this? this is so random xd
        }
    }

    public void applyMythicPassive() {
        owner.MAGIC_PEN += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new HextechRocketbelt();
    }
}

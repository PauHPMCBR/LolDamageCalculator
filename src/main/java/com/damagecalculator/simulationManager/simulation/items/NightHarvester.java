package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class NightHarvester extends Item {
    public static final String name = "Night Harvester";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3200;

    public NightHarvester() {
        super(name, type, cost);
        ap = 90;
        ah = 25;
        hp = 300;
        item_cooldown = 30;
    }

    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (float) (125 + 0.15 * owner.AP), 1);
        }
    }

    public void applyMythicPassive() {
        owner.AH += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new NightHarvester();
    }
}

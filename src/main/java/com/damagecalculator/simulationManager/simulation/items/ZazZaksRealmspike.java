package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ZazZaksRealmspike extends Item {
    public static final String name = "Zaz'Zak's Realmspike";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 400; //upgraded from bounty of worlds

    //ignoring bubble stuff

    public ZazZaksRealmspike() {
        super(name, type, cost);
        hp = 200;
        mana_regen = 75;
        hp_regen = 75;
        item_cooldown = 10;
    }

    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 10 + 0.2f*owner.AP + 0.03f * owner.getEnemy().getMaxHP(), 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new ZazZaksRealmspike();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ZazZaksRealmspike extends Item {
    public static final String name = "Zaz'Zak's Realmspike";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 40; //upgraded from bounty of worlds

    //ignoring bubble stuff

    public ZazZaksRealmspike() {
        super(name, type, cost);
        hp = 200;
        mana_regen = 75;
        hp_regen = 75;

        item_cooldown = 3; //TODO check cd (scales with lvl?)
    }

    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 50 + 0.03f * owner.getEnemy().getMaxHP(), 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new ZazZaksRealmspike();
    }
}

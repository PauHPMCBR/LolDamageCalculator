package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class UnendingDespair extends Item {
    public static final String name = "Unending Despair";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2800;

    float lastProcTime;

    public UnendingDespair() {
        super(name, type, cost);
        hp = 400;
        armor = 55;
        ah = 10;

        item_cooldown = 5;
    }

    public void specialStats() {
        lastProcTime = 0;
    }

    //ignoring healing
    public void extraDmg() {
        while (lastProcTime + item_cooldown <= cs.time) {
            lastProcTime += item_cooldown;
            float dmg = 30 + 20f/17 * (owner.lvl - 1) + owner.getMaxHP() * 0.03f;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 2);
        }
    }

    public void onHit() {
        extraDmg();
    }


    @Override
    public Item makeCopy() {
        return new UnendingDespair();
    }
}

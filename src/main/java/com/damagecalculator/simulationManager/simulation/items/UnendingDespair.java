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
    }

    public void specialStats() {
        lastProcTime = 0;
    }

    //ignoring healing
    public void extraDmg() {
        while (lastProcTime + 7 <= cs.time) {
            lastProcTime += 7;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 22 + owner.lvl + 0.03f * owner.getMaxHP(), 2);
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

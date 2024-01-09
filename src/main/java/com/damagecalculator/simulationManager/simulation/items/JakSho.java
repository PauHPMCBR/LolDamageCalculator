package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class JakSho extends Item {
    public static final String name = "Jak'Sho, The Protean";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3200;

    boolean stacked;
    
    public JakSho() {
        super(name, type, cost);
        hp = 300;
        armor = 50;
        mr = 50;
    }

    public void specialStats() {
        stacked = false;
    }

    public void extraDmg() {
        if (stacked) return;
        if (cs.time >= 5) {
            stacked = true;
            owner.ARMOR *= 1.3f;
            owner.MAGIC_RESIST *= 1.3f;
        }
    }
    public void onHit() {
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new JakSho();
    }
}

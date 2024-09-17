package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class NashorsTooth extends Item {
    public static final String name = "Nashor's Tooth";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public NashorsTooth() {
        super(name, type, cost);
        ap = 80;
        as = 50;
        ah = 15;
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 15 + 0.15f * owner.AP, 1);
    }

    @Override
    public Item makeCopy() {
        return new NashorsTooth();
    }
}

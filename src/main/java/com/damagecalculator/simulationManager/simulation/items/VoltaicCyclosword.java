package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class VoltaicCyclosword extends Item {
    public static final String name = "Voltaic Cyclosword";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2900;

    int energize;

    public VoltaicCyclosword() {
        super(name, type, cost);
        ad = 55;
        lethality = 18;
        ah = 15;
    }

    public void specialStats() {
        energize = 9;
    }

    public void onHit() {
        energize += 2; // for dashes?
        if (energize == 10) { //kinda arbitrary
            energize = 0;
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 100, 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new VoltaicCyclosword();
    }
}

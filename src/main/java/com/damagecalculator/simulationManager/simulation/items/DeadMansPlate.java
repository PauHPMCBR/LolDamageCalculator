package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class DeadMansPlate extends Item {
    public static final String name = "Dead Man's Plate";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2900;

    float lastHitTime;
    public DeadMansPlate() {
        super(name, type, cost);
        hp = 350;
        armor = 55;
        percent_ms = 4;
    }

    public void specialStats() {
        lastHitTime = 0;
    }

    //supposing always moving, never cc'd
    public void onHit() {
        int stacks = (int)Math.min(100, 7 * cs.time * 4);
        damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, stacks * (0.4f + owner.BASE_AD/100), 1);
    }

    @Override
    public Item makeCopy() {
        return new DeadMansPlate();
    }
}

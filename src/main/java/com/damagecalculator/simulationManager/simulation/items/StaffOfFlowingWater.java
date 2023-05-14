package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class StaffOfFlowingWater extends Item {
    public static final String name = "Staff of Flowing Water";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2100;

    public StaffOfFlowingWater() {
        super(name, type, cost);
        ap = 35;
        manaRegen = 75;
        healShieldPower = 8;
        ms = 5;
    }

    public void specialStats() { //supposing it's always active
        owner.AP += 30f + 15f / 17f * (owner.lvl - 1);
        owner.AH += 20;
    }

    @Override
    public Item makeCopy() {
        return new StaffOfFlowingWater();
    }
}

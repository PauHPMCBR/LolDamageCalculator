package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class StaffOfFlowingWater extends Item {
    public static final String name = "Staff of Flowing Water";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2300;

    public StaffOfFlowingWater() {
        super(name, type, cost);
        ap = 50;
        manaRegen = 100;
        healShieldPower = 8;
    }

    public void specialStats() { //supposing it's always active
        owner.AP += 25f + 20f / 17f * (owner.lvl - 1);
        owner.AH += 20;
    }

    @Override
    public Item makeCopy() {
        return new StaffOfFlowingWater();
    }
}

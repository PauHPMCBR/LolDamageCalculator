package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class StaffOfFlowingWater extends Item {
    public static final String name = "Staff of Flowing Water";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2300;

    public StaffOfFlowingWater() {
        super(name, type, cost);
        ap = 40;
        ah = 15;
        mana_regen = 125;
        hsp = 8;
    }

    public void specialStats() { //supposing it's always active, ignoring ms
        owner.AP += 30;
    }

    @Override
    public Item makeCopy() {
        return new StaffOfFlowingWater();
    }
}

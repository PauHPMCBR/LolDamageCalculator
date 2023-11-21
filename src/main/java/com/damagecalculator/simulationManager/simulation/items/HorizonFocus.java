package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class HorizonFocus extends Item {
    public static final String name = "Horizon Focus";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2700;

    //TODO check if first damage instance is increased as well

    public HorizonFocus() {
        super(name, type, cost);
        ap = 90;
        ah = 20;
    }

    public void specialStats() { //supposing 700+ range?
        cs.damageTrueMultiplier *= 1.1;
    }

    @Override
    public Item makeCopy() {
        return new HorizonFocus();
    }
}

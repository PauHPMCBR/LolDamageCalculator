package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class HorizonFocus extends Item {
    public static final String name = "Horizon Focus";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2700;

    public HorizonFocus() {
        super(name, type, cost);
        ap = 90;
        ah = 20;
    }

    //first damage instance is also amplified, supposing it's always triggered instantly
    public void specialStats() { //supposing 700+ range?
        cs.damageTrueMultiplier *= 1.1f;
    }

    @Override
    public Item makeCopy() {
        return new HorizonFocus();
    }
}

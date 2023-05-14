package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RejuvenationBead extends Item {
    public static final String name = "Rejuvenation Bead";
    public static final ItemType type = ItemType.BASIC;
    public static final int cost = 300;

    public RejuvenationBead() {
        super(name, type, cost);
        hpRegen = 100;
    }

    @Override
    public Item makeCopy() {
        return new RejuvenationBead();
    }
}

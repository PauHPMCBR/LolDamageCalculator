package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class OblivionOrb extends Item {
    public static final String name = "Oblivion Orb";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 800;

    public OblivionOrb() {
        super(name, type, cost);
        ap = 30;
    }

    @Override
    public Item makeCopy() {
        return new OblivionOrb();
    }
}

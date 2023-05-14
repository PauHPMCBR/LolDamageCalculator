package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BlightingJewel extends Item {
    public static final String name = "Blighting Jewel";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1250;

    public BlightingJewel() {
        super(name, type, cost);
        ap = 25;
        percent_magic_pen = 13;
    }

    @Override
    public Item makeCopy() {
        return new BlightingJewel();
    }
}

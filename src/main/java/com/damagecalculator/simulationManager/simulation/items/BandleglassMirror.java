package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BandleglassMirror extends Item {
    public static final String name = "Bandleglass Mirror";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 900;

    public BandleglassMirror() {
        super(name, type, cost);
        ap = 20;
        ah = 10;
        mana_regen = 100;
    }

    @Override
    public Item makeCopy() {
        return new BandleglassMirror();
    }
}

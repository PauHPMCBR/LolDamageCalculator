package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class CaulfieldsWarhammer extends Item {
    public static final String name = "Caulfield's Warhammer";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1050;

    public CaulfieldsWarhammer() {
        super(name, type, cost);
        ad = 20;
        ah = 10;
    }

    @Override
    public Item makeCopy() {
        return new CaulfieldsWarhammer();
    }
}

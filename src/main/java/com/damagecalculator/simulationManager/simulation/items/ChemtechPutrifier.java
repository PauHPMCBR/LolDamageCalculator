package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ChemtechPutrifier extends Item {
    public static final String name = "Chemtech Putrifier";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2100;

    public ChemtechPutrifier() {
        super(name, type, cost);
        ap = 35;
        ah = 15;
        mana_regen = 75;
        hsp = 10;
    }

    @Override
    public Item makeCopy() {
        return new ChemtechPutrifier();
    }
}

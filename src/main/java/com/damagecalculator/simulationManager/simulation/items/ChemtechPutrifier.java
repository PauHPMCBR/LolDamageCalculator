package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ChemtechPutrifier extends Item {
    public static final String name = "Chemtech Putrifier";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2300;

    public ChemtechPutrifier() {
        super(name, type, cost);
        ap = 40;
        ah = 15;
        manaRegen = 100;
        healShieldPower = 8;
    }

    @Override
    public Item makeCopy() {
        return new ChemtechPutrifier();
    }
}

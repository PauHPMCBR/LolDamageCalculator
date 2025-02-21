package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SymbioticSoles extends Item {
    public static final String name = "Symbiotic Soles";
    public static final ItemType type = ItemType.BOOTS;
    public static final int cost = 900;

    public SymbioticSoles() {
        super(name, type, cost);
        ms = 40;
    }

    @Override
    public Item makeCopy() {
        return new SymbioticSoles();
    }
}

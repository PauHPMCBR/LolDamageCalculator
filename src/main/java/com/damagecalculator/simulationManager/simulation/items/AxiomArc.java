package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class AxiomArc extends Item {
    public static final String name = "Axiom Arc";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public AxiomArc() {
        super(name, type, cost);
        ad = 55;
        lethality = 18;
        ah = 20;
    }

    @Override
    public Item makeCopy() {
        return new AxiomArc();
    }
}

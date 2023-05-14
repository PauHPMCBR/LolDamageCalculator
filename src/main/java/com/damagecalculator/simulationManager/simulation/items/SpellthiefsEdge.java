package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SpellthiefsEdge extends Item {
    public static final String name = "Spellthief's Edge";
    public static final ItemType type = ItemType.STARTER;
    public static final int cost = 400;

    public SpellthiefsEdge() {
        super(name, type, cost);
        ap = 8;
        hp = 10;
        manaRegen = 50;
    }

    @Override
    public Item makeCopy() {
        return new SpellthiefsEdge();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class GuardiansHammer extends Item {
    public static final String name = "Guardian's Hammer";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 950;

    public GuardiansHammer() {
        super(name, type, cost);
        ad = 25;
        hp = 150;
        lifesteal = 7;
    }

    @Override
    public Item makeCopy() {
        return new GuardiansHammer();
    }
}

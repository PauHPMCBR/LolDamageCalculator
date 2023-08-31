package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ChaliceOfBlessing extends Item {
    public static final String name = "Chalice of Blessing";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 900;

    public ChaliceOfBlessing() {
        super(name, type, cost);
        hp = 200;
        mana_regen = 50;
    }
    //ignoring hp regen for each mana regen


    @Override
    public Item makeCopy() {
        return new ChaliceOfBlessing();
    }
}

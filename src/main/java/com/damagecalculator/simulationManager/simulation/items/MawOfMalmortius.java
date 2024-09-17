package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class MawOfMalmortius extends Item {
    public static final String name = "Maw of Malmortius";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3100;

    public MawOfMalmortius() {
        super(name, type, cost);
        ad = 60;
        mr = 40;
        ah = 15;
    }

    @Override
    public Item makeCopy() {
        return new MawOfMalmortius();
    }
}

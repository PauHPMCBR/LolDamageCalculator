package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class LordDominiksRegards extends Item {
    public static final String name = "Lord Dominik's Regards";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public LordDominiksRegards() {
        super(name, type, cost);
        ad = 45;
        crit = 25;
        armor_pen = 40;
    }

    @Override
    public Item makeCopy() {
        return new LordDominiksRegards();
    }
}

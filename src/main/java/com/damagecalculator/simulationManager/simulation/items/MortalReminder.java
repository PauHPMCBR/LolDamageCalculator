package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class MortalReminder extends Item {
    public static final String name = "Mortal Reminder";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public MortalReminder() {
        super(name, type, cost);
        ad = 35;
        crit = 20;
        armor_pen = 30;
    }

    @Override
    public Item makeCopy() {
        return new MortalReminder();
    }
}

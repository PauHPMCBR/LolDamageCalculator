package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class MortalReminder extends Item {
    public static final String name = "Mortal Reminder";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public MortalReminder() {
        super(name, type, cost);
        ad = 40;
        crit = 25;
        armor_pen = 35;
    }

    @Override
    public Item makeCopy() {
        return new MortalReminder();
    }
}

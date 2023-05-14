package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class LastWhisper extends Item {
    public static final String name = "Last Whisper";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1450;

    public LastWhisper() {
        super(name, type, cost);
        ad = 20;
        armor_pen = 18;
    }

    @Override
    public Item makeCopy() {
        return new LastWhisper();
    }
}

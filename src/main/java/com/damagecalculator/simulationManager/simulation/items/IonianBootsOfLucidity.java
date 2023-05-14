package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class IonianBootsOfLucidity extends Item {
    public static final String name = "Ionian Boots of Lucidity";
    public static final ItemType type = ItemType.BOOTS;
    public static final int cost = 950;

    public IonianBootsOfLucidity() {
        super(name, type, cost);
        ah = 20;
    }

    @Override
    public Item makeCopy() {
        return new IonianBootsOfLucidity();
    }
}

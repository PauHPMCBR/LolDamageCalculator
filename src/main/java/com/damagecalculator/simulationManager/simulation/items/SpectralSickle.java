package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SpectralSickle extends Item {
    public static final String name = "Spectral Sickle";
    public static final ItemType type = ItemType.STARTER;
    public static final int cost = 400;

    public SpectralSickle() {
        super(name, type, cost);
        ad = 5;
        hp = 10;
        manaRegen = 50;
    }

    @Override
    public Item makeCopy() {
        return new SpectralSickle();
    }
}

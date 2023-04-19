package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SteraksGage extends Item {
    public static final String name = "Sterak's Gage";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3100;

    public SteraksGage() {
        super(name, type, cost);
        hp = 400;
    }

    public void specialStats() {
        owner.BONUS_AD += 0.5 * owner.BASE_AD;
    }

    @Override
    public Item makeCopy() {
        return new SteraksGage();
    }
}
package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class OverlordsBloodmail extends Item {
    public static final String name = "Overlord's Bloodmail";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3300;

    public OverlordsBloodmail() {
        super(name, type, cost);
        ad = 30;
        hp = 550;
    }

    public void specialStats() {
        owner.BONUS_AD += owner.BONUS_HP * 0.025f;
    }

    //ignoring %ad with missing hp

    @Override
    public Item makeCopy() {
        return new OverlordsBloodmail();
    }
}

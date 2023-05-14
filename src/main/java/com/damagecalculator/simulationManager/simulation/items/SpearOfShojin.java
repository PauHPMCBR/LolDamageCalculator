package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SpearOfShojin extends Item {
    public static final String name = "Spear of Shojin";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3400;

    float specialAH;

    public SpearOfShojin() {
        super(name, type, cost);
        ad = 65;
        ah = 20;
        hp = 300;
    }

    public void specialStats() {
        specialAH = (float) (8 + 0.08*owner.BONUS_AD);
        if (owner.is_ranged) specialAH *= 0.75;
        owner.AH += specialAH;
        owner.ULTIMATE_HASTE -= specialAH; //ignoring *0.5 penalty for cc abilities
    }

    @Override
    public Item makeCopy() {
        return new SpearOfShojin();
    }
}

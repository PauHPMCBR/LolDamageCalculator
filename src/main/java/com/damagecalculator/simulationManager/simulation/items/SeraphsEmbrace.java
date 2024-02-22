package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SeraphsEmbrace extends Item {
    public static final String name = "Seraph's Embrace";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public SeraphsEmbrace() {
        super(name, type, cost);
        ap = 80;
        ah = 25;
        mana = 1000;
    }

    public void specialStats() { //ignoring shield
        float baseMana = owner.base_mana + owner.mana_growth * (owner.lvl - 1);
        owner.AP += (owner.MANA - baseMana) * 0.02f;
    }

    @Override
    public Item makeCopy() {
        return new SeraphsEmbrace();
    }
}

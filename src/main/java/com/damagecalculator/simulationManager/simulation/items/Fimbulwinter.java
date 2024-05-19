package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Fimbulwinter extends Item {
    public static final String name = "Fimbulwinter";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2400;

    public Fimbulwinter() {
        super(name, type, cost);
        ah = 15;
        hp = 550;
        mana = 860;
    }

    public void specialStats() {
        float baseMana = owner.base_mana + owner.mana_growth * (owner.lvl - 1);
        owner.BONUS_HP += (owner.MANA - baseMana) * 0.15f;
    }

    @Override
    public Item makeCopy() {
        return new Fimbulwinter();
    }
}

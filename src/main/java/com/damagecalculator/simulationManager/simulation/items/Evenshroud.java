package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Evenshroud extends Item {
    public static final String name = "Evenshroud";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 2300;

    public Evenshroud() {
        super(name, type, cost);
        ah = 20;
        hp = 200;
        armor = 30;
        mr = 30;
    }

    public void specialStats() {
        cs.damageTrueMultiplier *= 1.07;
    }

    public void applyMythicPassive() {
        owner.ARMOR += 5 * owner.legendary_items_carried;
        owner.MAGIC_RESIST += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new Evenshroud();
    }
}

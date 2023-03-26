package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class CrownOfTheShatteredQueen extends Item {
    public static final String name = "Crown of the Shattered Queen";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 2800;

    public CrownOfTheShatteredQueen() {
        super(name, type, cost);
        ap = 70;
        ah = 20;
        hp = 250;
        mana = 600;
    }

    public void specialStats() {
        owner.AP += 10;
        if (owner.lvl >= 9) owner.AP += (owner.lvl-8)*3;
    }
    public void applyMythicPassive() {
        //ignoring +1% bonus ms
        owner.AP += 8 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new CrownOfTheShatteredQueen();
    }
}

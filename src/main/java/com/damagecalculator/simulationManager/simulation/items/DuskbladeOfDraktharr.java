package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class DuskbladeOfDraktharr extends Item {
    public static final String name = "Duskblade of Draktharr";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3100;

    float currentDmgMult;

    public DuskbladeOfDraktharr() {
        super(name, type, cost);
        ad = 60;
        lethality = 18;
        ah = 20;
        item_cooldown = 10;

        currentDmgMult = 1;
    }

    public void extraDmg() {
        cs.damageMultiplier /= currentDmgMult;
        currentDmgMult = 1 + Math.min(0.7f, owner.getEnemy().getRelativeMissingHP())/0.7f * 0.2f; //up to 20% increase, maxed at 30% remaining
        cs.damageMultiplier *= currentDmgMult;
    }

    public void onHit() {
        extraDmg();
    }

    public void applyMythicPassive() {
        owner.AH += 5 * owner.legendary_items_carried;
        //ignoring +5 bonus ms
    }

    @Override
    public Item makeCopy() {
        return new DuskbladeOfDraktharr();
    }
}

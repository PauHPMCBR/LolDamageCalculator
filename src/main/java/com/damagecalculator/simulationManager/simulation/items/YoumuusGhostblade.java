package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class YoumuusGhostblade extends Item {
    public static final String name = "Youmuu's Ghostblade";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3100;

    int lethalityGained;

    public YoumuusGhostblade() {
        super(name, type, cost);
        ad = 60;
        ah = 20;
        lethality = 18;
    } //supposing already max stacked

    public void specialStats() {
        lethalityGained = 8 + Math.max(0, owner.lvl - 6);
        owner.LETHALITY += lethalityGained;
    }
    public void extraDmg() {
        if (cs.time >= 3 && lethalityGained != 0) {
            owner.LETHALITY -= lethalityGained;
            lethalityGained = 0;
        }
    }
    public void onHit() {
        extraDmg();
    }
    public void applyMythicPassive() {
        owner.BONUS_AD += 7 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new YoumuusGhostblade();
    }
}

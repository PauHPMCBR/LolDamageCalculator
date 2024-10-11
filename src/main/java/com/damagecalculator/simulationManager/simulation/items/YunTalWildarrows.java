package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class YunTalWildarrows extends Item {
    public static final String name = "Yun Tal Wildarrows";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2950;

    public YunTalWildarrows() {
        super(name, type, cost);
        ad = 60;
        crit = 25;
    }

    public void onHit() {
        boolean wasCrit = ((owner.autosUsed-1)%4) < owner.CRIT_CHANCE/100 * 4; //works for every 25% crit chance, wouldn't work with 20% cloak/zeal, non-random cycle of 4
        if (wasCrit) { //apply burn damage all at once (cause it can stack)
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 60, 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new YunTalWildarrows();
    }
}

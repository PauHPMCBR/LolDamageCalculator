package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class IronspikeWhip extends Item {
    public static final String name = "Ironspike Whip";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1100;

    public IronspikeWhip() {
        super(name, type, cost);
        ad = 30;
    }

    public void extraDmg() {
        if (canUse()) {
            lastUsed = cs.time - item_cooldown*(1 - 100/(100+owner.ITEM_HASTE + owner.AH));
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, owner.BASE_AD);
        }
    }

    @Override
    public Item makeCopy() {
        return new IronspikeWhip();
    }
}

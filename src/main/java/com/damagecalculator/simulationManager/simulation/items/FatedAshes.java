
package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class FatedAshes extends Item {
    public static final String name = "Fated Ashes";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 900;

    float lastTick;

    public FatedAshes() {
        super(name, type, cost);
        ap = 40;

        lastTick = 0;
    }


    public void extraDmg() {
        float secs = Math.min(cs.time - lastTick, 3); //extra variable is time
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                secs*7, 1);
        lastTick = cs.time;
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new FatedAshes();
    }
}

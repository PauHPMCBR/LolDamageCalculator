
package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BlackfireTorch extends Item {
    public static final String name = "Blackfire Torch";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2800;

    float lastTick;
    int stacks;

    public BlackfireTorch(int stacks) {
        super(name, type, cost);
        ap = 90;
        mana = 600;
        ah = 25;

        extraVariableName = "Blackfire stacks (1-5)";
        this.stacks = Math.max(stacks, 1);

        lastTick = 0;
    }

    public void specialStats() {
        owner.AP *= (1 + 0.04f*stacks);
    }

    public void extraDmg() {
        float secs = Math.min(cs.time - lastTick, 3); //extra variable is time
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                (float) (secs * (20 + 0.02 * owner.AP)), 1);
        lastTick = cs.time;
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new BlackfireTorch(stacks);
    }
}

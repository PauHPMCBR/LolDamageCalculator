package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ProwlersClaw extends Item {
    public static final String name = "Prowler's Claw";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 3100;

    boolean useProwlersActive;
    float activeStart;

    public ProwlersClaw(boolean useProwlersActive) {
        super(name, type, cost);
        ad = 60;
        ah = 20;
        lethality = 18;
        item_cooldown = 90;

        this.useProwlersActive = useProwlersActive;
        activeStart = 0;
    }

    public void extraDmg() {
        if (useProwlersActive) {
            if (canUse()) {
                putOnCooldown();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (float) (70 + 0.3 * owner.BONUS_AD));
                activeStart = cs.time;
                cs.damageMultiplier *= 1.15;
            }
            else if (activeStart + 3 >= cs.time) {
                activeStart = 0;
                cs.damageMultiplier /= 1.15;
            }
        }
    }

    public void applyMythicPassive() {
        owner.LETHALITY += 5 * owner.legendary_items_carried;
        //ignoring +5 bonus ms
    }

    @Override
    public Item makeCopy() {
        return new ProwlersClaw(useProwlersActive);
    }
}

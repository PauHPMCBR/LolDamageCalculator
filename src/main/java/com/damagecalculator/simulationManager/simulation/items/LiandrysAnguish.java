
package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class LiandrysAnguish extends Item {
    public static final String name = "Liandry's Anguish";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3200;

    float lastTick;

    public LiandrysAnguish() {
        super(name, type, cost);
        ap = 80;
        ah = 20;
        mana = 600;

        lastTick = 0;
    }

    public void specialStats() {
        cs.liandryPercent = (float) (1 + 0.12 / 1250 * Math.min(1250, owner.getEnemy().BONUS_HP));
    }

    public void extraDmg() {
        float secs = Math.min(cs.time - lastTick, 4); //extra variable is time
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                (float) (secs * (50 + 0.06 * owner.AP + 0.04 * owner.getEnemy().getMaxHP())/4), 1);
        lastTick = cs.time;
    }

    public void onHit() {
        extraDmg();
    }

    public void applyMythicPassive() {
        owner.AH += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new LiandrysAnguish();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Everfrost extends Item {
    public static final String name = "Everfrost";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 2800;

    public Everfrost() {
        super(name, type, cost);
        ap = 70;
        ah = 20;
        hp = 250;
        mana = 600;
        item_cooldown = 30;
    }

    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (float) (100 + 0.3 * owner.AP));
        }
    }

    public void applyMythicPassive() {
        owner.AP += 10 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new Everfrost();
    }
}

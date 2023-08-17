package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class JakSho extends Item {
    public static final String name = "Jak'Sho, The Protean";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3200;

    int stacks;
    
    public JakSho() {
        super(name, type, cost);
        ah = 20;
        hp = 400;
        armor = 30;
        mr = 30;
        
        stacks = 0;
    }

    public void extraDmg() { //extra variable are stacks
        if (stacks == 8) return;
        int newStacks = Math.min(8, (int)cs.time);
        owner.ARMOR += 2 * (newStacks - stacks);
        owner.MAGIC_RESIST += 2 * (newStacks - stacks);
        stacks = newStacks;
        if (stacks == 8) {
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 80f + 0.07f * owner.BONUS_HP, 2);
            //ignoring healing
            owner.ARMOR += (owner.ARMOR - owner.base_armor - owner.armor_growth*(owner.lvl-1)) * 0.2;
            owner.MAGIC_RESIST += (owner.MAGIC_RESIST - owner.base_mr - owner.mr_growth*(owner.lvl-1)) * 0.2; //increase BONUS resistances
        }
    }
    public void onHit() {
        extraDmg();
    }
    public void applyMythicPassive() {
        owner.ARMOR += 5 * owner.legendary_items_carried;
        owner.MAGIC_RESIST += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new JakSho();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class EchoesOfHelia extends Item {
    public static final String name = "Echoes of Helia";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 2300;

    int stacks;

    public EchoesOfHelia() {
        super(name, type, cost);
        ap = 30;
        ah = 15;
        hp = 200;
        manaRegen = 125;

        stacks = 0;
    }

    public void specialStats() {
        owner.AP += owner.MANA_REGEN/25 * 3; // Gain 3 ability power for every additional 25% base mana regeneration
    }

    public void extraDmg() { //supposing all abilities are heal/shield AND deal dmg (prob overtuned)
        if (stacks < 2) ++stacks;
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (30 + 12.5f*Math.max(0, owner.lvl - 6)) * stacks); //its supposed to be ally lvl
        stacks = 0;
    }

    public void onHit() {
        if (stacks < 2) ++stacks;
    }

    public void applyMythicPassive() {
        owner.AH += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new EchoesOfHelia();
    }
}

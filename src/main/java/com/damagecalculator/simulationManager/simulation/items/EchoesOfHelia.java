package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class EchoesOfHelia extends Item {
    public static final String name = "Echoes of Helia";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2200;

    int stacks;

    public EchoesOfHelia() {
        super(name, type, cost);
        ap = 40;
        ah = 20;
        hp = 200;
        mana_regen = 125;

        stacks = 0;
    }

    public void extraDmg() { //supposing all abilities are heal/shield AND deal dmg (prob overtuned)
        if (stacks < 2) ++stacks;
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 60 * stacks, 2);
        stacks = 0;
    }

    public void onHit() {
        if (stacks < 3) ++stacks;
    }

    @Override
    public Item makeCopy() {
        return new EchoesOfHelia();
    }
}

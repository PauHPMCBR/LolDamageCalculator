package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Muramana extends Item {
    public static final String name = "Muramana";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2900;

    public Muramana() {
        super(name, type, cost);
        ad = 30;
        ah = 15;
        mana = 860;
    }

    public void specialStats() {
        owner.BONUS_AD += owner.MANA * 0.025;
    }

    public void extraDmg() {
        float percent = 0.04f;
        if (owner.is_ranged) percent = 0.02f;
        damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (owner.MANA * percent), 1);
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 0.01f * owner.MANA, 1);
    }

    @Override
    public Item makeCopy() {
        return new Muramana();
    }
}

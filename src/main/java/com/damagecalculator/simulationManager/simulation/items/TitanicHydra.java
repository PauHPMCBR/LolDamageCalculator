package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class TitanicHydra extends Item {
    public static final String name = "Titanic Hydra";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3300;


    public TitanicHydra() {
        super(name, type, cost);

        ad = 30;
        hp = 500;
    }

    public void specialStats() {
        owner.BONUS_AD += owner.BONUS_HP * 0.02;
    }

    public void onHit() {
        float dmg = (float) (4 + 0.015 * owner.getMaxHP());
        if (owner.is_ranged) dmg *= 0.75;
        damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg, 1);
    }

    @Override
    public Item makeCopy() {
        return new TitanicHydra();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class KrakenSlayer extends Item {
    public static final String name = "Kraken Slayer";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3100;

    int autos;
    public KrakenSlayer() {
        super(name, type, cost);
        ad = 50;
        as = 40;
        percent_ms = 5;
    }

    public void startingCalculations() {
        autos = 0;
    }

    public void onHit() {
        ++autos;
        if (autos % 3 == 0) {
            float baseDmg = 140 + 10 * Math.max(0, owner.lvl - 8);
            if (owner.is_ranged) baseDmg *= 0.8f;
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                    baseDmg * (1 + owner.getEnemy().getRelativeMissingHP()), 1);
            //damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, baseDmg * dmgMult, 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new KrakenSlayer();
    }
}

package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class KrakenSlayer extends Item {
    public static final String name = "Kraken Slayer";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    int autos;
    float dmgMult;
    public KrakenSlayer() {
        super(name, type, cost);
        ad = 40;
        as = 35;
        crit = 20;
    }

    public void startingCalculations() {
        dmgMult = 1;
        autos = 0;
    }

    public void onHit() {
        ++autos;
        if (autos % 3 == 0) {
            float baseDmg = 140 + 17 * Math.max(0, owner.lvl - 8);
            //damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
            //        (float) (baseDmg + owner.getAD() * 0.65 + owner.AP * 0.6) * (1 + owner.getEnemy().getRelativeMissingHP()), 1);
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, baseDmg * dmgMult, 1);
            if (dmgMult < 2) dmgMult += 0.5f;
        }
    }

    @Override
    public Item makeCopy() {
        return new KrakenSlayer();
    }
}

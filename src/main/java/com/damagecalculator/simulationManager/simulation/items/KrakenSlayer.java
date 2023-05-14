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
        as = 30;
        crit = 20;

        autos = 0;
        dmgMult = 1;
    }

    public void onHit() {
        ++autos;
        if (autos % 3 == 0) {
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                    (float) (20 + owner.getAD() * 0.6 + owner.AP * 0.45) * dmgMult, 1);
            if (dmgMult < 2) dmgMult += 0.5;
        }
    }

    @Override
    public Item makeCopy() {
        return new KrakenSlayer();
    }
}

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
            float baseDmg = 35 + 5 * Math.max(0, owner.lvl - 8);
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                    (float) (baseDmg + owner.getAD() * 0.65 + owner.AP * 0.6) * dmgMult, 1);
            if (dmgMult < 2) dmgMult += 0.5;
        }
    }

    @Override
    public Item makeCopy() {
        return new KrakenSlayer();
    }
}

package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Shards extends Rune {
    public static final String name = "Shards";
    public static final RunePath path = RunePath.Shards;
    public static final int column = 0;
    public static final int row = 0;

    public int s1, s2, s3;

    public Shards(int s1, int s2, int s3) {
        super(name, path, column, row);
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    public void specialStats() {
        int adaptives = 0;
        if (s1 == 0) ++adaptives;
        if (s2 == 0) ++adaptives;
        if (s1 == 1) owner.BONUS_AS += 10;
        if (s1 == 2) owner.AH += 8;
        if (owner.getAdaptive().equals(DamageType.physicalDmg))
            owner.BONUS_AD += adaptives * 5.4;
        else
            owner.AP += adaptives * 9;
        if (s3 == 0) owner.BONUS_HP += 15 + 125f / 17 * (owner.lvl - 1);
        //ignoring armor and mr runes
    }

    @Override
    public Rune makeCopy() {
        return new Shards(s1, s2, s3);
    }
}
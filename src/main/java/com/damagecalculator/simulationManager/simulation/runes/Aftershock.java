package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Aftershock extends Rune {
    public static final String name = "Aftershock";
    public static final RunePath path = RunePath.Resolve;
    public static final int column = 0;
    public static final int row = 1;

    boolean isActive;
    float activationTime;

    float armorIncrease;
    float mrIncrease;

    public Aftershock() {
        super(name, path, column, row);
        rune_cooldown = 20;

        isActive = false;
    }

    public void extraDmg() {
        if (canUse() && !isActive) {
            armorIncrease = 35 + (owner.ARMOR - (owner.base_armor + owner.armor_growth * (owner.lvl-1))) * 0.8f;
            mrIncrease = 35 + (owner.MAGIC_RESIST - (owner.base_mr + owner.mr_growth * (owner.lvl-1))) * 0.8f;

            owner.ARMOR += armorIncrease;
            owner.MAGIC_RESIST += mrIncrease;

            isActive = true;
            activationTime = cs.time;
        }
        else if (isActive && cs.time > activationTime + 2.5) {
            owner.ARMOR -= armorIncrease;
            owner.MAGIC_RESIST -= mrIncrease;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                    25 + 95f / 17f * (owner.lvl - 1) + owner.BONUS_HP * 0.08f);

            isActive = false;
            putOnCooldown();
        }
    }

    @Override
    public Rune makeCopy() {
        return new Aftershock();
    }
}
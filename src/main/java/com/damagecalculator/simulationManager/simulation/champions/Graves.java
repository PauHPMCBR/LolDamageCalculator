package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.Ability;
import com.damagecalculator.simulationManager.simulation.AbilityType;
import com.damagecalculator.simulationManager.simulation.Champion;
import com.damagecalculator.simulationManager.simulation.DamageType;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Graves extends Champion {
    public static final String name = "Graves";

    float remainingBullets;

    public void autoAttack() {
        boolean isCrit = (autosUsed % 5) < CRIT_CHANCE / 100 * 5; //works for every 20% crit chance, wouldn't work with 15% cloak/zeal, non-random cycle of 5

        float pelletAdRatio = 0.6895f + 0.01765f * lvl * (0.595f + 0.0225f * (lvl - 1));
        int pellets = (isCrit ? 6 : 4);

        float pelletDamage = pelletAdRatio * getAD();
        if (isCrit) pelletDamage *= (float) (1 + (crit_damage - 1)*0.45);

        autoDmg += cs.damage.applyDamage(DamageType.physicalDmg, pelletDamage, 0);

        autoCd = 1 / getAttackSpeed();

        ++autosUsed;
        lastAbilityUsed = AUTO;

        applyOnhit(1, null);

        for (int i = 1; i < pellets; ++i)
            autoDmg += cs.damage.applyDamage(DamageType.physicalDmg, pelletDamage/3, 0);

        --remainingBullets;
        if (remainingBullets == 0) {
            autoCd = 1 / (0.651f + 0.014f * (lvl-1));
            remainingBullets = 2;
            //that will have problems with auto resets
        }

        e.currentCooldown -= 0.5f * pellets;
    }

    public Graves() {
        super (name,
                625f,
                106f,
                325f,
                40f,
                33f,
                4.6f,
                68f,
                4f,
                32f,
                2.05f,
                1.75f,
                0.475f,
                3f,
                0f,
                0.49f,
                3f,
                true
        );

        passive = new Ability(PASSIVE) {
            public void startingCalculations() {
                remainingBullets = 2;
            }
        };

        q = new Ability(Q) {
            public void onUse() {
                float[] damage1 = new float[]{45, 65, 85, 105, 125};
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                        damage1[lvl] + 0.8f * owner.BONUS_AD, 6);

                currentCooldown = getCooldown();
            }
            public void onExpiring() {
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, damage[lvl] + ad_scale[lvl] * owner.BONUS_AD, 6);
            }
        };
        q.damageType = DamageType.physicalDmg;
        q.cooldown = new float[]{13,11.25f,9.5f,7.75f,6};
        q.damage = new float[]{85,120,155,190,225}; //detonation damage
        q.ad_scale = new float[]{0.4f,0.65f,0.9f,1.15f,1.4f};
        q.duration = new float[]{2,2,2,2,2};
        q.cast_time = 0.25f;

        w = new Ability(W) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, damage[lvl] + ap_scale[lvl] * owner.AP, 6);
                currentCooldown = getCooldown();
            }
        };
        w.damageType = DamageType.magicDmg;
        w.cooldown = new float[]{26,24,22,20,18};
        w.damage = new float[]{60,110,160,210,260};
        w.ap_scale = new float[]{0.6f,0.6f,0.6f,0.6f,0.6f};
        w.cast_time = 0.25f;

        e = new Ability(E) {
            public void startingCalculations() {
                extraVariable = 0; // grit stacks
            }
            public void onUse() {
                if (extraVariable < 8) {
                    extraVariable += 2;
                    ARMOR += 2 * damage[lvl];
                }
                remainingBullets = Math.min(2, remainingBullets + 1);
                autoReset();
                currentCooldown = getCooldown();
            }
        };
        e.damageType = null;
        e.damage = new float[]{4,7,10,13,16}; // bonus armor per stack
        e.cooldown = new float[]{16,15,14,13,12};
        e.cast_time = 0;
        r = new Ability(R) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,damage[lvl] + ad_scale[lvl] * owner.BONUS_AD, 6);
                currentCooldown = getCooldown();
            }
        };
        r.damageType = DamageType.physicalDmg;
        r.cooldown = new float[]{100,80,60};
        r.damage = new float[]{275,425,575};
        r.ad_scale = new float[]{1.5f,1.5f,1.5f};
        r.cast_time = 0.25f;

        upgradeOrder = new AbilityType[] {Q, E, W, Q, Q, R, Q, E, Q, E, R, E, E, W, W, R, W, W};
        abilityPriorities = new AbilityType[] {W, AUTO, Q, E};
    }

    @Override
    public Champion makeCopy() {
        return new Graves();
    }
}

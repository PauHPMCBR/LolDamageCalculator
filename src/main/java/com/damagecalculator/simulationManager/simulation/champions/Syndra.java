package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;
import static com.damagecalculator.simulationManager.simulation.AbilityType.*;


public class Syndra extends Champion {
    public static final String name = "Syndra";

    public int splinters;

    public int ballsPresent;

    public Syndra(int splinters) {
        super (name,
                563f,
                104f,
                480f,
                40f,
                25f,
                4.6f,
                54f,
                2.9f,
                30f,
                1.3f,
                1.75f,
                0.658f,
                3f,
                18.75f,
                0.625f,
                2f,
                true
        );
        
        this.splinters = splinters;

        passive = new Ability(PASSIVE) { //extraVariable are splinters
            public void startingCalculations() {
                extraVariable = splinters;
                if (extraVariable >= 120) owner.AP *= 1.15f;

                ballsPresent = 0;
            }
        };

        q = new Ability(Q) { //extraVariable = is q upgraded
            public void startingCalculations() {
                if (owner.passive.extraVariable >= 40)
                    extraVariable = 1;
            }
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (damage[lvl] +
                        ap_scale[lvl] * owner.AP), 6);
                ++ballsPresent;
                if (extraVariable == 1) {
                    extraVariable = 2; //first q 1.25s cooldown cause 2 stacked
                    currentCooldown = 1.25f;
                }
                else {
                    int extraAH = owner.r.lvl * 10;
                    owner.AH += extraAH;
                    currentCooldown = getCooldown();
                    owner.AH -= extraAH;
                }
            }
        };
        q.damageType = DamageType.magicDmg;
        q.cooldown = new float[]{7,7,7,7,7};
        q.damage = new float[]{75,110,145,180,215};
        q.ap_scale = new float[]{0.7f,0.7f,0.7f,0.7f,0.7f};
        q.cast_time = 0;

        w = new Ability(W) {//extraVariable = is w upgraded
            public void startingCalculations() {
                if (owner.passive.extraVariable >= 60)
                    extraVariable = 1;
            }
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 6);
                if (extraVariable == 1) {
                    float mult = (float) (12 + 0.02 * owner.AP);
                    damageDealt += cs.damage.applyDamage(DamageType.trueDmg, dmg * mult / 100, 3);
                }
                currentCooldown = getCooldown();
            }
        };
        w.damageType = DamageType.magicDmg;
        w.cooldown = new float[]{12,11,10,9,8};
        w.damage = new float[]{70,105,140,175,210};
        w.ap_scale = new float[]{0.65f,0.65f,0.65f,0.65f,0.65f};
        w.cast_time = 0;

        e = new Ability(E) { //ignoring e upgrade
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (damage[lvl] +
                        ap_scale[lvl] * owner.AP), 6);
                currentCooldown = getCooldown();
            }
        };
        e.damageType = DamageType.magicDmg;
        e.cooldown = new float[]{17,17,17,17,17};
        e.damage = new float[]{60,95,130,165,200};
        e.ap_scale = new float[]{0.6f,0.6f,0.6f,0.6f,0.6f};
        e.cast_time = 0.25f;

        r = new Ability(R) { //extraVariable = is r upgraded
            public void startingCalculations() {
                if (owner.passive.extraVariable >= 100)
                    extraVariable = 1;
            }
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                ballsPresent += 3;

                for (int i = 0; i < ballsPresent; ++i)
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 6);

                if (extraVariable == 1 && owner.getEnemy().getRelativeMissingHP() > 0.85) cs.damage.execute();

                currentCooldown = getCooldown();
            }
        };
        r.damageType = DamageType.magicDmg;
        r.cooldown = new float[]{120,100,80};
        r.damage = new float[]{90,130,170};
        r.ap_scale = new float[]{0.2f,0.2f,0.2f};
        r.cast_time = 0.264f;

        extraVariableName = "Splinters";
        upgradeOrder = new AbilityType[] {Q, W, E, Q, Q, R, Q, W, Q, W, R, W, W, E, E, R, E, E};
        abilityPriorities = new AbilityType[] {Q, E, W, R};
    }

    @Override
    public Champion makeCopy() {
        return new Syndra(splinters);
    }
}

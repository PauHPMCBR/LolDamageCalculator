package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;
import static com.damagecalculator.simulationManager.simulation.AbilityType.w;

public class Syndra extends Champion {
    public static final String name = "Syndra";

    public int splinters;

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
                0.625f,
                2.5f,
                18.75f,
                1f,
                2f,
                true
        );
        
        this.splinters = splinters;

        Passive = new Ability(AbilityType.passive) { //extraVariable are splinters
            public void startingCalculations() {
                extraVariable = splinters;
                if (extraVariable >= 120) owner.AP *= 1.15;
            }
        };

        Q = new Ability(AbilityType.q) { //extraVariable = is q upgraded
            public void startingCalculations() {
                if (owner.Passive.extraVariable >= 40)
                    extraVariable = 1;
            }
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (damage[lvl] +
                        ap_scale[lvl] * owner.AP));
                if (extraVariable == 1) extraVariable = 2; //first q no cooldown cause 2 stacked
                else {
                    int extraAH = owner.R.lvl * 10;
                    owner.AH += extraAH;
                    currentCooldown = getCooldown();
                    owner.AH -= extraAH;
                }
            }
        };
        Q.damageType = DamageType.magicDmg;
        Q.cooldown = new float[]{7,7,7,7,7};
        Q.damage = new float[]{70,105,140,175,210};
        Q.ap_scale = new float[]{0.7f,0.7f,0.7f,0.7f,0.7f};
        Q.cast_time = 0;

        W = new Ability(AbilityType.w) {//extraVariable = is w upgraded
            public void startingCalculations() {
                if (owner.Passive.extraVariable >= 60)
                    extraVariable = 1;
            }
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg);
                if (extraVariable == 1) {
                    float mult = (float) (12 + 0.02 * owner.AP);
                    damageDealt += cs.damage.applyDamage(DamageType.trueDmg, dmg * mult / 100);
                }
                currentCooldown = getCooldown();
            }
        };
        W.damageType = DamageType.magicDmg;
        W.cooldown = new float[]{12,11,10,9,8};
        W.damage = new float[]{70,110,150,190,230};
        W.ap_scale = new float[]{0.7f,0.7f,0.7f,0.7f,0.7f};
        W.cast_time = 0;

        E = new Ability(AbilityType.e) { //ignoring e upgrade
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (damage[lvl] +
                        ap_scale[lvl] * owner.AP));
                currentCooldown = getCooldown();
            }
        };
        E.damageType = DamageType.magicDmg;
        E.cooldown = new float[]{17,17,17,17,17};
        E.damage = new float[]{75,115,155,195,235};
        E.ap_scale = new float[]{0.45f,0.45f,0.45f,0.45f,0.45f};
        E.cast_time = 0.25f;

        R = new Ability(AbilityType.r) { //extraVariable = is r upgraded
            public void startingCalculations() {
                if (owner.Passive.extraVariable >= 100)
                    extraVariable = 1;
            }
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                int spheres = 5; //supposing 5 as average? min 3, max 7

                for (int i = 0; i < spheres; ++i)
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg);

                if (extraVariable == 1 && owner.getEnemy().getRelativeMissingHP() > 0.85) cs.damage.execute();

                currentCooldown = getCooldown();
            }
        };
        R.damageType = DamageType.magicDmg;
        R.cooldown = new float[]{120,100,80};
        R.damage = new float[]{90,130,170};
        R.ap_scale = new float[]{0.17f,0.17f,0.17f,0.17f,0.17f};
        R.cast_time = 0.264f;

        extraVariableName = "Splinters";
        upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,w,q,w,r,w,w,e,e,r,e,e};
        abilityPriorities = new AbilityType[] {q,e,w,r};
    }

    @Override
    public Champion makeCopy() {
        return new Syndra(splinters);
    }
}

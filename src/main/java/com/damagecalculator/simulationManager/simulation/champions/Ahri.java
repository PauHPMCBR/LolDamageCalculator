package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.Ability;
import com.damagecalculator.simulationManager.simulation.AbilityType;
import com.damagecalculator.simulationManager.simulation.Champion;
import com.damagecalculator.simulationManager.simulation.DamageType;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Ahri extends Champion {
    public static final String name = "Ahri";

    public Ahri() {
        super (name,
                590f,
                96f,
                418f,
                25f,
                21f,
                4.7f,
                53f,
                3f,
                30f,
                1.3f,
                1.75f,
                0.668f,
                2.5f,
                20.054f,
                1f,
                2f,
                true
        );

        Passive = new Ability(AbilityType.passive);

        Q = new Ability(AbilityType.q) {
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg);
                damageDealt += cs.damage.applyDamage(DamageType.trueDmg, dmg);

                currentCooldown = getCooldown();
            }
        };
        Q.damageType = DamageType.magicDmg;
        Q.cooldown = new float[]{7,7,7,7,7};
        Q.damage = new float[]{40,65,90,115,140};
        Q.ap_scale = new float[]{0.45f,0.45f,0.45f,0.45f,0.45f};
        Q.cast_time = 0.25f;

        W = new Ability(AbilityType.w) {
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg);
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg*0.3f, 1);
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg*0.3f, 1);

                currentCooldown = getCooldown();
            }
        };
        W.damageType = DamageType.magicDmg;
        W.cooldown = new float[]{9,8,7,6,5};
        W.damage = new float[]{50,75,100,125,150};
        W.ap_scale = new float[]{0.3f,0.3f,0.3f,0.3f,0.3f};
        W.cast_time = 0;

        E = new Ability(AbilityType.e) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (damage[lvl] +
                        ap_scale[lvl] * owner.AP));
                currentCooldown = getCooldown();
            }
        };
        E.damageType = DamageType.magicDmg;
        E.cooldown = new float[]{14,14,14,14,14};
        E.damage = new float[]{80,110,140,170,200};
        E.ap_scale = new float[]{0.6f,0.6f,0.6f,0.6f,0.6f};
        E.cast_time = 0.25f;

        R = new Ability(AbilityType.r) {
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                int dashes = 3;
                for (int i = 0; i < dashes; ++i)
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg);

                currentCooldown = getCooldown();
            }
        };
        R.damageType = DamageType.magicDmg;
        R.cooldown = new float[]{130,105,80};
        R.damage = new float[]{60,90,120};
        R.ap_scale = new float[]{0.35f,0.35f,0.35f,0.35f,0.35f};
        R.cast_time = 0;

        upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,w,q,w,r,w,w,e,e,r,e,e};
        abilityPriorities = new AbilityType[] {e,w,q,r};
    }

    @Override
    public Champion makeCopy() {
        return new Ahri();
    }
}

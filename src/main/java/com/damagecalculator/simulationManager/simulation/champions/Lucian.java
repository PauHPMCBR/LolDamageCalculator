package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;
import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Lucian extends Champion {
    public static final String name = "Lucian";
    
    public Lucian() {
        super(
                name,
                641f,
                100f,
                320f,
                43f,
                28f,
                4.2f,
                60f,
                2.9f,
                30f,
                1.3f,
                1.75f,
                0.638f,
                3f,
                15f,
                0.638f,
                3.3f,
                true
        );

        passive = new Ability(PASSIVE) { //onhit extra damage not implemented
            public void startingCalculations() {
                extraVariable = 0.5f;
                if (owner.lvl >= 7) extraVariable += 0.05f;
                if (owner.lvl >= 13) extraVariable += 0.05f;
            }
            public void onCall() {
                //supposing lucian is ALWAYS gonna be tested with "useAutosBetweenAbilities" set to true
                owner.BASE_AD *= extraVariable;
                owner.BONUS_AD *= extraVariable; //make lucian ad 50-60%, then auto (without changing auto cd)
                float prov = owner.autoCd;
                owner.autoAttack();
                owner.autoCd = prov;
                owner.BONUS_AD /= extraVariable;
                owner.BASE_AD /= extraVariable;

                owner.e.currentCooldown -= 4; //reduce e cd by 4 secs, need to test if before or after navori
            }
        };

        q = new Ability(Q) {
            public void startingCalculations() {
                cast_time = 0.4f - 0.15f / 17f * (owner.lvl -1);
            }
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (damage[lvl] +
                        ad_scale[lvl] * owner.BONUS_AD), 6);
                currentCooldown = getCooldown();
                owner.passive.onCall();
            }
        };
        q.damageType = DamageType.physicalDmg;
        q.cooldown = new float[]{9,8,7,6,5};
        q.damage = new float[]{85,115,145,175,205};
        q.ad_scale = new float[]{0.6f,0.75f,0.9f,1.05f,1.2f};
        //cast time in starting calculations

        w = new Ability(W) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (damage[lvl] +
                        ap_scale[lvl] * owner.AP), 6);
                currentCooldown = getCooldown();
                owner.passive.onCall();
            }
        };
        w.damageType = DamageType.magicDmg;
        w.cooldown = new float[]{14,13,12,11,10};
        w.damage = new float[]{75,110,145,180,215};
        w.ap_scale = new float[]{0.9f,0.9f,0.9f,0.9f,0.9f};
        w.cast_time = 0.25f;

        e = new Ability(E) {
            public void onUse() {

                owner.autoReset();
                currentCooldown = getCooldown();
                owner.passive.onCall();
            }
        };
        e.cooldown = new float[]{16,15.5f,15,14.5f,14};
        e.cast_time = 0;

        r = new Ability(R) {
            public void startingCalculations() {
                extraVariable = 22 + (int)(owner.CRIT_CHANCE / 4); //amount of shots
            }
            public void onUse() {
                float dmg = damage[lvl] + ad_scale[lvl]*owner.getAD() + ap_scale[lvl]*owner.AP;
                for (int i = 0; i < extraVariable; ++i)
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg, 6);

                currentCooldown = getCooldown();
                owner.passive.onCall();
            }
        };
        r.damageType = DamageType.physicalDmg;
        r.cooldown = new float[]{110,100,90};
        r.damage = new float[]{15,30,45};
        r.ad_scale = new float[]{0.25f,0.25f,0.25f};
        r.ap_scale = new float[]{0.15f,0.15f,0.15f};
        r.cast_time = 3f;

        upgradeOrder = new AbilityType[] {Q, W, E, Q, Q, R, Q, E, Q, E, R, E, E, W, W, R, W, W};
        abilityPriorities = new AbilityType[] {AUTO, E, Q, W};
    }
    
    @Override
    public Champion makeCopy() {
        return new Lucian();
    }
}

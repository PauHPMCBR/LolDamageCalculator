package simulationManager.simulation.champions;

import simulationManager.simulation.Ability;
import simulationManager.simulation.AbilityType;
import simulationManager.simulation.Champion;
import simulationManager.simulation.DamageType;

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
                2.5f,
                15f,
                1f,
                3.3f,
                true
        );

        Passive = new Ability(AbilityType.passive) { //onhit extra damage not implemented
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

                owner.E.currentCooldown -= 4; //reduce e cd by 4 secs, need to test if before or after navori
            }
        };

        Q = new Ability(AbilityType.q) {
            public void startingCalculations() {
                cast_time = 0.4f - 0.15f / 17f * (owner.lvl -1);
            }
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (damage[lvl] +
                        ad_scale[lvl] * owner.BONUS_AD));
                currentCooldown = getCooldown();
                owner.Passive.onCall();
            }
        };
        Q.damageType = DamageType.physicalDmg;
        Q.cooldown = new float[]{9,8,7,6,5};
        Q.damage = new float[]{95,125,155,185,215};
        Q.ad_scale = new float[]{0.6f,0.75f,0.9f,1.05f,1.2f};
        //cast time in starting calculations

        W = new Ability(AbilityType.w) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (damage[lvl] +
                        ap_scale[lvl] * owner.AP));
                currentCooldown = getCooldown();
                owner.Passive.onCall();
            }
        };
        W.damageType = DamageType.magicDmg;
        W.cooldown = new float[]{14,13,12,11,10};
        W.damage = new float[]{75,110,145,180,215};
        W.ap_scale = new float[]{0.9f,0.9f,0.9f,0.9f,0.9f};
        W.cast_time = 0.25f;

        E = new Ability(AbilityType.e) {
            public void onUse() {

                owner.autoReset();
                currentCooldown = getCooldown();
                owner.Passive.onCall();
            }
        };
        E.cooldown = new float[]{22,20,18,16,14};
        E.cast_time = 0;

        R = new Ability(AbilityType.r) {
            public void startingCalculations() {
                extraVariable = 22 + (int)(owner.CRIT_CHANCE / 4); //amount of shots
            }
            public void onUse() {
                float dmg = damage[lvl] + ad_scale[lvl]*owner.getAD() + ap_scale[lvl]*owner.AP;
                for (int i = 0; i < extraVariable; ++i)
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg);

                currentCooldown = getCooldown();
                owner.Passive.onCall();
            }
        };
        R.damageType = DamageType.physicalDmg;
        R.cooldown = new float[]{110,100,90};
        R.damage = new float[]{15,30,45};
        R.ad_scale = new float[]{0.25f,0.25f,0.25f};
        R.ap_scale = new float[]{0.15f,0.15f,0.15f};
        R.cast_time = 3f;
    }
    
    @Override
    public Champion makeCopy() {
        return new Lucian();
    }
}

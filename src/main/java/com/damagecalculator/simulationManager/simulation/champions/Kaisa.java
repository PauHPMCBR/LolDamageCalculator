package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;
import static com.damagecalculator.simulationManager.simulation.AbilityType.e;

public class Kaisa extends Champion {
    public static final String name = "Kai'Sa";
    
    public Kaisa() {
        super(
                name,
                670f,
                102f,
                344.88f,
                38f,
                28f,
                4.2f,
                59f,
                2f,
                30f,
                1.3f,
                1.75f,
                0.664f,
                2.5f,
                16.108f,
                1f,
                1.8f,
                true
        );

        Passive = new Ability(AbilityType.passive) { //extraVariable = plasma stacks
            public void startingCalculations() {
                extraVariable = 0;
            }
            public void onCall() {
                float dmg = 5 + ((float)(owner.lvl-1)*18)/17;
                dmg += (1+((float)(owner.lvl-1)*11)/17) * extraVariable;
                dmg += (0.15 + 0.025*extraVariable) * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 1);
                if (extraVariable == 4) {
                    float percentageMissing = (float) (15 + 0.06 * owner.AP);
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, percentageMissing/100 * owner.getEnemy().getMissingHP(), 1);
                    extraVariable = 0;
                }
                else ++extraVariable;
            }
        };

        Q = new Ability(AbilityType.q) { //extraVariable = is q evolved
            public void startingCalculations() {
                if (owner.BONUS_AD + owner.BASE_AD - 59 >= 100) //59 is base ad (base_ad in champion is private)
                    extraVariable = 1;
            }
            public void onUse() {
                //suppose all projectiles hit
                int extraMissiles = 5;
                if (extraVariable == 1) extraMissiles = 11;

                float dmg = damage[lvl] + ad_scale[lvl]*owner.BONUS_AD + ap_scale[lvl]*owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg); //supposing isolated target
                for (int i = 0; i < extraMissiles; ++i) damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg * 0.25f);

                currentCooldown = getCooldown();
            }
        };
        Q.damageType = DamageType.physicalDmg;
        Q.cooldown = new float[]{10, 9, 8, 7, 6};
        Q.damage = new float[]{40,55,70,85,100};
        Q.ad_scale = new float[]{0.5f,0.5f,0.5f,0.5f,0.5f};
        Q.ap_scale = new float[]{0.3f,0.3f,0.3f,0.3f,0.3f};
        Q.cast_time = 0;

        W = new Ability(AbilityType.w) { //extraVariable = is w evolved
            public void startingCalculations() {
                if (owner.AP >= 100) extraVariable = 1;
            }
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, damage[lvl] + ad_scale[lvl] * owner.getAD() +
                        ap_scale[lvl] * owner.AP);
                owner.Passive.onCall();
                owner.Passive.onCall();
                currentCooldown = getCooldown();
                if (extraVariable == 1) {
                    owner.Passive.onCall();
                    currentCooldown *= 0.27; //77% cd refund
                }
            }
        };
        W.damageType = DamageType.magicDmg;
        W.cooldown = new float[]{22,20,18,16,14};
        W.damage = new float[]{30,55,80,105,130};
        W.ad_scale = new float[]{1.3f,1.3f,1.3f,1.3f,1.3f};
        W.ap_scale = new float[]{0.45f,0.45f,0.45f,0.45f,0.45f};
        W.cast_time = 0.4f;

        E = new Ability(AbilityType.e) { //ignoring evolve invisibility
            public void startingCalculations() {
                cast_time = (float) (1.2 - Math.min(0.6, 0.006 * owner.BONUS_AS)); //not keeping into account attack speed changes
            }
            public void onUse() {
                owner.attack_windup = 6.44f;
                owner.BONUS_AS += damage[lvl];
                currentCooldown = getCooldown();
            }
            public void onExpiring() {
                owner.attack_windup = 16.108f;
                owner.BONUS_AS -= damage[lvl];
            }
            public void onCall() { //autos
                currentCooldown -= 0.5f;
                if (currentCooldown < 0) currentCooldown = 0;
            }
        };
        E.cooldown = new float[]{16,14.5f,13,11.5f,10};
        E.duration = new float[]{4,4,4,4,4};
        E.damage = new float[]{40,50,60,70,80}; //used as attack speed
        E.damageType = null;

        R = new Ability(AbilityType.r); //just an auto reset, ignoring slight time save

        //This special item will call the passive and E on-hit effects (damage and cooldown subtraction)

        Item passiveOnHit = new PassiveKaisaOnhit();
        addUniqueItem(passiveOnHit);

        upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,e,q,e,r,e,e,w,w,r,w,w};
        abilityPriorities = new AbilityType[] {q,auto,e,w};
    }

    @Override
    public Champion makeCopy() {
        return new Kaisa();
    }



    public static class PassiveKaisaOnhit extends Item {
        public static final String name = "_passive onhit";
        public static final ItemType type = ItemType.unique;
        public static final int cost = 0;

        public PassiveKaisaOnhit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void onHit() {
            owner.Passive.onCall();
            owner.E.onCall();
        }

        @Override
        public Item makeCopy() {
            return new PassiveKaisaOnhit();
        }
    }
}

package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;
import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Kaisa extends Champion {
    public static final String name = "Kai'Sa";
    
    public Kaisa() {
        super(
                name,
                640f,
                102f,
                344.88f,
                38f,
                28f,
                4.2f,
                59f,
                2.6f,
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

        passive = new Ability(PASSIVE) { //extraVariable = plasma stacks
            float lastApplied = 0;
            public void startingCalculations() {
                extraVariable = 0;
            }
            public void onCall() {
                if (cs.time > lastApplied + 4) extraVariable = 0;
                lastApplied = cs.time;

                float dmg = 5 + ((float)(owner.lvl-1)*18)/17;
                dmg += (1+((float)(owner.lvl-1)*11)/17) * extraVariable;
                dmg += (0.15 + 0.025*extraVariable) * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 3);
                if (extraVariable == 4) {
                    float percentageMissing = (float) (15 + 0.06 * owner.AP);
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, percentageMissing/100 * owner.getEnemy().getMissingHP(), 3);
                    extraVariable = 0;
                }
                else ++extraVariable;
            }
        };

        q = new Ability(Q) { //extraVariable = is q evolved
            public void startingCalculations() {
                if (owner.BONUS_AD + owner.BASE_AD - owner.base_ad >= 100) //59 is base ad (base_ad in champion is private)
                    extraVariable = 1;
            }
            public void onUse() {
                //suppose all projectiles hit
                int extraMissiles = 5;
                if (extraVariable == 1) extraMissiles = 11;

                float dmg = damage[lvl] + ad_scale[lvl]*owner.BONUS_AD + ap_scale[lvl]*owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg, 6); //supposing isolated target
                for (int i = 0; i < extraMissiles; ++i) damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg * 0.25f, 6);

                currentCooldown = getCooldown();
            }
        };
        q.damageType = DamageType.physicalDmg;
        q.cooldown = new float[]{10, 9, 8, 7, 6};
        q.damage = new float[]{40,55,70,85,100};
        q.ad_scale = new float[]{0.5f,0.5f,0.5f,0.5f,0.5f};
        q.ap_scale = new float[]{0.2f,0.2f,0.2f,0.2f,0.2f};
        q.cast_time = 0;

        w = new Ability(W) { //extraVariable = is w evolved
            public void startingCalculations() {
                if (owner.AP >= 100) extraVariable = 1;
            }
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, damage[lvl] + ad_scale[lvl] * owner.getAD() +
                        ap_scale[lvl] * owner.AP, 6);
                owner.passive.onCall();
                owner.passive.onCall();
                currentCooldown = getCooldown();
                if (extraVariable == 1) {
                    owner.passive.onCall();
                    currentCooldown *= 0.25; //75% cd refund
                }
            }
        };
        w.damageType = DamageType.magicDmg;
        w.cooldown = new float[]{22,20,18,16,14};
        w.damage = new float[]{30,55,80,105,130};
        w.ad_scale = new float[]{1.3f,1.3f,1.3f,1.3f,1.3f};
        w.ap_scale = new float[]{0.45f,0.45f,0.45f,0.45f,0.45f};
        w.cast_time = 0.4f;

        e = new Ability(E) { //ignoring evolve invisibility
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
        e.cooldown = new float[]{16,14.5f,13,11.5f,10};
        e.duration = new float[]{4,4,4,4,4};
        e.damage = new float[]{40,50,60,70,80}; //used as attack speed
        e.damageType = null;

        r = new Ability(R); //just an auto reset, ignoring slight time save

        //This special item will call the passive and E on-hit effects (damage and cooldown subtraction)

        Item passiveOnHit = new PassiveKaisaOnhit();
        addUniqueItem(passiveOnHit);

        //upgradeOrder = new AbilityType[] {Q, W, E, Q, Q, R, Q, E, Q, E, R, E, E, W, W, R, W, W};
        upgradeOrder = new AbilityType[] {Q, W, E, Q, Q, R, Q, W, Q, W, R, W, W, E, E, R, E, E};

        abilityPriorities = new AbilityType[] {Q, AUTO, E, W};
    }

    @Override
    public Champion makeCopy() {
        return new Kaisa();
    }



    public static class PassiveKaisaOnhit extends Item {
        public static final String name = "_passive onhit";
        public static final ItemType type = ItemType.UNIQUE;
        public static final int cost = 0;

        public PassiveKaisaOnhit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void onHit() {
            owner.passive.onCall();
            owner.e.onCall();
        }

        @Override
        public Item makeCopy() {
            return new PassiveKaisaOnhit();
        }
    }
}

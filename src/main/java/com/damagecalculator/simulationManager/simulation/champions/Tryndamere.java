package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Tryndamere extends Champion {
    public static final String name = "Tryndamere";

    int startingFury;
    public Tryndamere(int startingFury) {
        super(
                name,
                696f,
                108f,
                0f,
                0f,
                33f,
                4.8f,
                66f,
                4f,
                32f,
                2.05f,
                1.75f,
                0.67f,
                3f,
                19f,
                0.694f,
                3.4f,
                false
        );

        this.startingFury = startingFury;

        passive = new Ability(PASSIVE) {
            public void startingCalculations() {
                extraVariable = startingFury;
                owner.CRIT_CHANCE = Math.min(100, owner.CRIT_CHANCE + extraVariable*0.5f);
            }
            public void onCall() {
                if (extraVariable > 95) return; // no more fury increases
                if (owner.wasLastAutoCrit() && extraVariable <= 90) {
                    extraVariable += 10;
                    owner.CRIT_CHANCE = Math.min(100, owner.CRIT_CHANCE + 4);
                }
                else {
                    extraVariable += 5;
                    owner.CRIT_CHANCE = Math.min(100, owner.CRIT_CHANCE + 2);
                }
            }
        };

        q = new Ability(Q) {
            public void startingCalculations() {
                owner.BONUS_AD += ad_scale[lvl];
            }
        };
        q.damageType = null;
        //q.cooldown = new float[]{10, 9, 8, 7, 6};
        q.ad_scale = new float[]{5,10,15,20,25}; // = bonus ad
        //q.cast_time = 0;

        w = new Ability(W) {
            public void onUse() {
                currentCooldown = getCooldown();
            }
        };
        w.damageType = null;
        w.cooldown = new float[]{14,14,14,14,14};
        w.cast_time = 0.3f;

        e = new Ability(E) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                        damage[lvl] + ad_scale[lvl]*owner.BONUS_AD + ap_scale[lvl]*owner.AP, 6);
                currentCooldown = getCooldown();
            }
            public void onCall() { //autos
                if (owner.wasLastAutoCrit()) currentCooldown -= 1.5f;
            }
        };
        e.damageType = DamageType.physicalDmg;
        e.cooldown = new float[]{12,11,10,9,8};
        e.damage = new float[]{75,105,135,165,195};
        e.ad_scale = new float[]{1.3f,1.3f,1.3f,1.3f,1.3f};
        e.ap_scale = new float[]{0.8f,0.8f,0.8f,0.8f,0.8f};
        e.cast_time = 0;

        r = new Ability(R); //just gives fury, use startingFury lol

        Item furyOnhit = new FuryOnhit();
        addUniqueItem(furyOnhit);

        extraVariableName = "Starting Fury";
        upgradeOrder = new AbilityType[] {E, Q, Q, W, Q, R, Q, E, Q, E, E, E, W, W, W, W, R, R};
        abilityPriorities = new AbilityType[] {AUTO, E};
    }

    @Override
    public Champion makeCopy() {
        return new Tryndamere(startingFury);
    }

    public static class FuryOnhit extends Item {
        public static final String name = "_fury onhit";
        public static final ItemType type = ItemType.UNIQUE;
        public static final int cost = 0;

        public FuryOnhit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void onHit() {
            owner.passive.onCall();
            owner.e.onCall();
        }

        @Override
        public Item makeCopy() {
            return new FuryOnhit();
        }
    }
}

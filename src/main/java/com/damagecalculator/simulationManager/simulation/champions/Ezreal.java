package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Ezreal extends Champion {
    public static final String name = "Ezreal";

    public Ezreal() {
        super(
                name,
                600f,
                102f,
                375f,
                70f,
                24f,
                4.7f,
                62f,
                2.75f,
                30f,
                1.3f,
                1.75f,
                0.625f,
                2.5f,
                18.839f,
                0.625f,
                2.5f,
                true
        );

        passive = new Ability(PASSIVE) { // extra variable = passive stacks
            public void startingCalculations() {
                extraVariable = 0;
            }
            public void onCall() {
                if (extraVariable == 5) return;
                ++extraVariable;
                owner.BONUS_AS += 10;
            }
        };

        q = new Ability(Q) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                        damage[lvl] + ad_scale[lvl] * owner.getAD() + ap_scale[lvl] * owner.AP, 6);

                if (cs.time - lastSheenProc >= 1.5) can_use_sheen = true;
                owner.applyOnhit(1, null);

                currentCooldown = getCooldown();

                owner.q.currentCooldown -= 1.5f;
                owner.w.currentCooldown -= 1.5f;
                owner.e.currentCooldown -= 1.5f;
                owner.r.currentCooldown -= 1.5f;
            }
        };
        q.damageType = DamageType.physicalDmg;
        q.damage = new float[]{20,45,70,95,120};
        q.ad_scale = new float[]{1.4f,1.4f,1.4f,1.4f,1.4f};
        q.ap_scale = new float[]{0.15f,0.15f,0.15f,0.15f,0.15f};
        q.cast_time = 0.25f;
        q.cooldown = new float[]{5.5f,5.25f,5,4.75f,4.5f};

        w = new Ability(W) { // extraVariable = does enemy have w ring
            public void startingCalculations() {
                extraVariable = 0;
            }
            public void onUse() {
                extraVariable = 1;
                currentCooldown = getCooldown();
            }
            public void onCall() {
                if (extraVariable == 0 || owner.lastAbilityUsed == AbilityType.W) return;
                extraVariable = 0;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        damage[lvl] + ad_scale[lvl] * owner.BONUS_AD + ap_scale[lvl] * owner.AP, 6);
            }
        };
        w.damageType = DamageType.magicDmg;
        w.cast_time = 0.25f;
        w.cooldown = new float[]{8,8,8,8,8};
        w.damage = new float[]{80,125,190,245,300};
        w.ad_scale = new float[]{1,1,1,1,1};
        w.ap_scale = new float[]{0.7f,0.75f,0.8f,0.85f,0.9f};

        e = new Ability(E) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        damage[lvl] + ad_scale[lvl] * owner.BONUS_AD + ap_scale[lvl] * owner.AP, 6);
                currentCooldown = getCooldown();
            }
        };
        e.damageType = DamageType.magicDmg;
        e.cooldown = new float[]{26,23,20,17,14};
        e.damage = new float[]{80,130,180,230,280};
        e.ad_scale = new float[]{0.5f,0.5f,0.5f,0.5f,0.5f};
        e.ap_scale = new float[]{0.75f,0.75f,0.75f,0.75f,0.75f};
        e.cast_time = 0.25f;

        r = new Ability(R) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        damage[lvl] + ad_scale[lvl] * owner.BONUS_AD + ap_scale[lvl] * owner.AP, 6);
                currentCooldown = getCooldown();
            }
        };
        r.damageType = DamageType.magicDmg;
        r.cooldown = new float[]{120,105,90};
        r.damage = new float[]{350,550,750};
        r.ad_scale = new float[]{1f,1f,1f,1f,1f};
        r.ap_scale = new float[]{0.9f,0.9f,0.9f,0.9f,0.9f};
        r.cast_time = 1;


        //This special item will call the passive and W on-hit/ability-hit effects
        Item passiveAttackSpeed = new EzrealAbilityHit();
        addUniqueItem(passiveAttackSpeed);

        upgradeOrder = new AbilityType[] {Q, E, W, Q, Q, R, Q, E, Q, E, R, E, E, W, W, R, W, W};
        abilityPriorities = new AbilityType[] {R, Q, W, E, AUTO};
    }

    @Override
    public Champion makeCopy() {
        return new Ezreal();
    }



    public static class EzrealAbilityHit extends Item {
        public static final String name = "_passive attackspeed";
        public static final ItemType type = ItemType.UNIQUE;
        public static final int cost = 0;

        public EzrealAbilityHit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void onHit() {
            owner.w.onCall();
        }

        public void extraDmg() {
            owner.passive.onCall();
            owner.w.onCall();
        }

        @Override
        public Item makeCopy() {
            return new EzrealAbilityHit();
        }
    }
}

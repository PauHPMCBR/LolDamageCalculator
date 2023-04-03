package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Vayne extends Champion {
    public static final String name = "Vayne";

    public Vayne() {
        super(
                name,
                550f,
                103f,
                231f,
                35f,
                23f,
                4.6f,
                60f,
                2.35f,
                30f,
                1.3f,
                1.75f,
                0.658f,
                2.5f,
                17.554f,
                1f,
                3.3f,
                true
        );

        Passive = new Ability(AbilityType.passive);

        Q = new Ability(AbilityType.q) {
            public void startingCalculations(){
                extraVariable = 0;
            }
            public void onUse() {
                extraVariable = 1;
                owner.autoReset();
                currentCooldown = getCooldown();
                if (owner.R.isUnlocked() && owner.R.active) {
                    if (owner.R.lvl == 0) currentCooldown *= 0.7f;
                    else if (owner.R.lvl == 1) currentCooldown *= 0.6f;
                    else currentCooldown *= 0.5;
                }
            }
            public void onCall() {
                if (!isUnlocked()) return;
                if (extraVariable == 1) {
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, owner.getAD() * ad_scale[lvl], 1);
                    extraVariable = 0;
                }
            }
        };
        Q.damageType = DamageType.physicalDmg;
        Q.cooldown = new float[]{4,3.5f,3,2.5f,2};
        Q.ad_scale = new float[]{0.75f,0.85f,0.95f,1.05f,1.15f};
        Q.cast_time = 0;

        W = new Ability(AbilityType.w) {
            public void startingCalculations() {
                extraVariable = 0;
            }
            public void onCall() {
                if (!isUnlocked()) return;
                ++extraVariable;
                if (extraVariable == 3) {
                    extraVariable = 0;
                    float dmg = Math.max(damage[lvl],
                            owner.getEnemy().getMaxHP() * ad_scale[lvl]);
                    damageDealt += cs.damage.applyDamage(DamageType.trueDmg, dmg);
                }
            }
        };
        W.damageType = DamageType.trueDmg;
        W.damage = new float[]{50,65,80,95,110}; //minimum bonus damage
        W.ad_scale = new float[]{0.06f,0.07f,0.08f,0.09f,0.1f}; //%true dmg

        E = new Ability(AbilityType.e) {
            public void onUse() {
                float dmg = damage[lvl] + ad_scale[lvl] * owner.BONUS_AD;
                dmg *= 2.5; //supposing target always collides with terrain
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg);
                owner.W.onCall();
                currentCooldown = getCooldown();
            }
        };
        E.cooldown = new float[]{20,18,16,14,12};
        E.damage = new float[]{50,85,120,155,190};
        E.ad_scale = new float[]{0.5f,0.5f,0.5f,0.5f,0.5f};
        E.cast_time = 0.25f;
        E.damageType = null;

        R = new Ability(AbilityType.r) {
            public void onUse() {
                owner.BONUS_AD += damage[lvl];
            }
            public void onExpiring() {
                owner.BONUS_AD -= damage[lvl];
            }
        };
        R.damage = new float[]{25,40,55}; //bonus ad
        R.duration = new float[]{8,10,12};
        R.cooldown = new float[]{100,85,70};
        R.damageType = null;


        //This special item will call the Q and W on-hit effects
        Item passiveOnHit = new VayneOnhit();
        addUniqueItem(passiveOnHit);

        upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,w,q,w,r,w,w,e,e,r,e,e};
        abilityPriorities = new AbilityType[] {r,auto,q,e};
    }

    @Override
    public Champion makeCopy() {
        return new Vayne();
    }



    public static class VayneOnhit extends Item {
        public static final String name = "_passive onhit";
        public static final ItemType type = ItemType.unique;
        public static final int cost = 0;

        public VayneOnhit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void onHit() {
            owner.Q.onCall();
            owner.W.onCall();
        }

        @Override
        public Item makeCopy() {
            return new VayneOnhit();
        }
    }
}

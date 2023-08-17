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

        passive = new Ability(PASSIVE);

        q = new Ability(Q) {
            public void startingCalculations(){
                extraVariable = 0;
            }
            public void onUse() {
                extraVariable = 1;
                owner.autoReset();
                currentCooldown = getCooldown();
                if (owner.r.isUnlocked() && owner.r.active) {
                    if (owner.r.lvl == 0) currentCooldown *= 0.7f;
                    else if (owner.r.lvl == 1) currentCooldown *= 0.6f;
                    else currentCooldown *= 0.5;
                }
            }
            public void onCall() {
                if (!isUnlocked()) return;
                if (extraVariable == 1) {
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                            owner.getAD() * ad_scale[lvl] + owner.AP * ap_scale[lvl], 3);
                    extraVariable = 0;
                }
            }
        };
        q.damageType = DamageType.physicalDmg;
        q.cooldown = new float[]{4,3.5f,3,2.5f,2};
        q.ad_scale = new float[]{0.75f,0.85f,0.95f,1.05f,1.15f};
        q.ap_scale = new float[]{0.5f,0.5f,0.5f,0.5f,0.5f};
        q.cast_time = 0.4f;

        w = new Ability(W) {
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
                    damageDealt += cs.damage.applyDamage(DamageType.trueDmg, dmg, 3);
                }
            }
        };
        w.damageType = DamageType.trueDmg;
        w.damage = new float[]{50,65,80,95,110}; //minimum bonus damage
        w.ad_scale = new float[]{0.06f,0.07f,0.08f,0.09f,0.1f}; //%true dmg

        e = new Ability(E) {
            public void onUse() {
                float dmg = damage[lvl] + ad_scale[lvl] * owner.BONUS_AD;
                dmg *= 2.5; //supposing target always collides with terrain
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg, 6);
                owner.w.onCall();
                currentCooldown = getCooldown();
            }
        };
        e.damageType = DamageType.physicalDmg;
        e.cooldown = new float[]{20,18,16,14,12};
        e.damage = new float[]{50,85,120,155,190};
        e.ad_scale = new float[]{0.5f,0.5f,0.5f,0.5f,0.5f};
        e.cast_time = 0.25f;

        r = new Ability(R) {
            public void onUse() {
                owner.BONUS_AD += damage[lvl];
                currentCooldown = getCooldown();
            }
            public void onExpiring() {
                owner.BONUS_AD -= damage[lvl];
            }
        };
        r.damage = new float[]{25,40,55}; //bonus ad
        r.duration = new float[]{8,10,12};
        r.cooldown = new float[]{100,85,70};
        r.damageType = null;


        //This special item will call the Q and W on-hit effects
        Item passiveOnHit = new VayneOnhit();
        addUniqueItem(passiveOnHit);

        upgradeOrder = new AbilityType[] {Q, W, E, Q, Q, R, Q, W, Q, W, R, W, W, E, E, R, E, E};
        abilityPriorities = new AbilityType[] {R, AUTO, Q, E};
    }

    @Override
    public Champion makeCopy() {
        return new Vayne();
    }



    public static class VayneOnhit extends Item {
        public static final String name = "_passive onhit";
        public static final ItemType type = ItemType.UNIQUE;
        public static final int cost = 0;

        public VayneOnhit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void onHit() {
            owner.q.onCall();
            owner.w.onCall();
        }

        @Override
        public Item makeCopy() {
            return new VayneOnhit();
        }
    }
}

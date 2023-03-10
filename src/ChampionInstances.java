import simulationManager.simulation.*;

import java.util.*;

public class ChampionInstances {

    /**
     * Like the training mode dummy. Designed to be the "enemy" with a set amount of hp, armor and magic resist.
     * For commodity reasons, 2k hp is set as base hp and the rest as bonus hp (only affects liandry's extra damage)
     */
    public static Champion createDummy(int hp, int armor, int mr) {
        Champion dummy = new Champion(
                "Dummy",
                Math.min(2000, hp),
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f
        );
        dummy.lvl = 1;
        Item dummyStats = new Item("_dummy stats",0,0,0,0,Math.max(0, hp - 2000),armor,mr);
        dummy.addUniqueItem(dummyStats);
        return dummy;
    }


    public static Champion createKaisa() {
        Champion kaisa = new Champion( //CHANGE BASE AD IN ABILITY Q AND ATTACK WINDUP IN E IF MODIFIED HERE!
                "Kai'sa",
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
                1.8f
        );
        kaisa.is_ranged = true;

        kaisa.Passive = new Ability(AbilityType.passive) { //extraVariable = plasma stacks
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

        kaisa.Q = new Ability(AbilityType.q) { //extraVariable = is q evolved
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
        kaisa.Q.damageType = DamageType.physicalDmg;
        kaisa.Q.cooldown = new float[]{10, 9, 8, 7, 6};
        kaisa.Q.damage = new float[]{40,55,70,85,100};
        kaisa.Q.ad_scale = new float[]{0.5f,0.5f,0.5f,0.5f,0.5f};
        kaisa.Q.ap_scale = new float[]{0.3f,0.3f,0.3f,0.3f,0.3f};
        kaisa.Q.cast_time = 0;

        kaisa.W = new Ability(AbilityType.w) { //extraVariable = is w evolved
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
        kaisa.W.damageType = DamageType.magicDmg;
        kaisa.W.cooldown = new float[]{22,20,18,16,14};
        kaisa.W.damage = new float[]{30,55,80,105,130};
        kaisa.W.ad_scale = new float[]{1.3f,1.3f,1.3f,1.3f,1.3f};
        kaisa.W.ap_scale = new float[]{0.45f,0.45f,0.45f,0.45f,0.45f};
        kaisa.W.cast_time = 0.4f;

        kaisa.E = new Ability(AbilityType.e) { //ignoring evolve invisibility
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
        kaisa.E.cooldown = new float[]{16,14.5f,13,11.5f,10};
        kaisa.E.duration = new float[]{4,4,4,4,4};
        kaisa.E.damage = new float[]{40,50,60,70,80}; //used as attack speed
        kaisa.E.damageType = null;

        kaisa.R = new Ability(AbilityType.r); //just an auto reset, ignoring slight time save

        //This special item will call the passive and E on-hit effects (damage and cooldown subtraction)

        kaisa.addUniqueItem(OtherItems.kaisaPassiveOnhitCall);

        return kaisa;
    }




    public static Champion createSyndra(int splinters) { //splinters are her passive stacks, up to 120
        Champion syndra = new Champion(
                "Syndra",
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
                2f
        );
        syndra.is_ranged = true;

        syndra.Passive = new Ability(AbilityType.passive) { //extraVariable are splinters
            public void startingCalculations() {
                extraVariable = splinters;
                if (extraVariable >= 120) owner.AP *= 1.15;
            }
        };

        syndra.Q = new Ability(AbilityType.q) { //extraVariable = is q upgraded
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
        syndra.Q.damageType = DamageType.magicDmg;
        syndra.Q.cooldown = new float[]{7,7,7,7,7};
        syndra.Q.damage = new float[]{70,105,140,175,210};
        syndra.Q.ap_scale = new float[]{0.7f,0.7f,0.7f,0.7f,0.7f};
        syndra.Q.cast_time = 0;

        syndra.W = new Ability(AbilityType.w) {//extraVariable = is w upgraded
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
        syndra.W.damageType = DamageType.magicDmg;
        syndra.W.cooldown = new float[]{12,11,10,9,8};
        syndra.W.damage = new float[]{70,110,150,190,230};
        syndra.W.ap_scale = new float[]{0.7f,0.7f,0.7f,0.7f,0.7f};
        syndra.W.cast_time = 0;

        syndra.E = new Ability(AbilityType.e) { //ignoring e upgrade
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (damage[lvl] +
                        ap_scale[lvl] * owner.AP));
                currentCooldown = getCooldown();
            }
        };
        syndra.E.damageType = DamageType.magicDmg;
        syndra.E.cooldown = new float[]{17,17,17,17,17};
        syndra.E.damage = new float[]{75,115,155,195,235};
        syndra.E.ap_scale = new float[]{0.45f,0.45f,0.45f,0.45f,0.45f};
        syndra.E.cast_time = 0.25f;

        syndra.R = new Ability(AbilityType.r) { //extraVariable = is r upgraded
            public void startingCalculations() {
                if (owner.Passive.extraVariable >= 100)
                    extraVariable = 1;
            }
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                int spheres = 5; //supposing 5 as average? min 3, max 7

                for (int i = 0; i < spheres; ++i)
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg);

                if (extraVariable == 1 && owner.getEnemy().getRelativeMissingHP() < 0.15) cs.damage.execute();

                currentCooldown = getCooldown();
            }
        };
        syndra.R.damageType = DamageType.magicDmg;
        syndra.R.cooldown = new float[]{120,100,80};
        syndra.R.damage = new float[]{90,130,170};
        syndra.R.ap_scale = new float[]{0.17f,0.17f,0.17f,0.17f,0.17f};
        syndra.R.cast_time = 0.264f;

        return syndra;
    }






    public static Champion createLucian() { //splinters are her passive stacks, up to 120
        Champion lucian = new Champion(
                "Lucian",
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
                3.3f
        );
        lucian.is_ranged = true;

        lucian.Passive = new Ability(AbilityType.passive) { //onhit extra damage not implemented
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

        lucian.Q = new Ability(AbilityType.q) {
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
        lucian.Q.damageType = DamageType.physicalDmg;
        lucian.Q.cooldown = new float[]{9,8,7,6,5};
        lucian.Q.damage = new float[]{95,125,155,185,215};
        lucian.Q.ad_scale = new float[]{0.6f,0.75f,0.9f,1.05f,1.2f};
        //cast time in starting calculations

        lucian.W = new Ability(AbilityType.w) {
            public void onUse() {                
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (damage[lvl] +
                        ap_scale[lvl] * owner.AP));
                currentCooldown = getCooldown();
                owner.Passive.onCall();
            }
        };
        lucian.W.damageType = DamageType.magicDmg;
        lucian.W.cooldown = new float[]{14,13,12,11,10};
        lucian.W.damage = new float[]{75,110,145,180,215};
        lucian.W.ap_scale = new float[]{0.9f,0.9f,0.9f,0.9f,0.9f};
        lucian.W.cast_time = 0.25f;

        lucian.E = new Ability(AbilityType.e) {
            public void onUse() {
                
                owner.autoReset();
                currentCooldown = getCooldown();
                owner.Passive.onCall();
            }
        };
        lucian.E.cooldown = new float[]{22,20,18,16,14};
        lucian.E.cast_time = 0;

        lucian.R = new Ability(AbilityType.r) {
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
        lucian.R.damageType = DamageType.physicalDmg;
        lucian.R.cooldown = new float[]{110,100,90};
        lucian.R.damage = new float[]{15,30,45};
        lucian.R.ad_scale = new float[]{0.25f,0.25f,0.25f};
        lucian.R.ap_scale = new float[]{0.15f,0.15f,0.15f};
        lucian.R.cast_time = 3f;

        return lucian;
    }
}

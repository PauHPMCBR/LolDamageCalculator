package simulationManager.simulation;

/**
 * Implementation of runes (they are treated as items)
 * Some runes have variable values like alactriy stacks, eyeball stacks, shard options... that can be accessed
 *      from outside and be modyfied. make sure to modify them BEFORE the start of a simulationManager.simulation
 */
public class Runes extends Items {

    public static int alacrityStacks;
    public static Item legendAlacrity = new Item("Alacrity",0,0,0,0,0,0,0) {
        void specialStats() {
            cs.champion.BONUS_AS += 3 + 1.5*Math.min(alacrityStacks, 10);
        }
    };

    public static Item coupDeGrace = new Item("Coup de Grace",0,0,0,0,0,0,0) {
        void specialStats() {
                cs.hasCoupDeGrace = true;
            }
    };

    public static Item cutDown = new Item("Cut Down",0,0,0,0,0,0,0) {
        void specialStats() {
            cs.hasCutDown = true;
        }
    };

    public static Item lastStand = new Item("Last Stand",0,0,0,0,0,0,0) {
        void specialStats() {
            cs.hasLastStand = true;
        }
    };

    public static int eyeballStacks;
    public static Item eyeballCollection = new Item("Eyeball Collection",0,0,0,0,0,0,0) {
        void specialStats() {
            extraVariable = Math.min(eyeballStacks, 10);
            if (cs.champion.getAdaptive().equals(DamageType.physicalDmg)) {
                cs.champion.BONUS_AD += 1.2 * extraVariable;
                if (extraVariable == 10) cs.champion.BONUS_AD += 6;
            } else {
                cs.champion.AP += 2 * extraVariable;
                if (extraVariable == 10) cs.champion.AP += 10;
            }
        }
    };

    public static Item manaflowBand = new Item("Manaflow Band",0,0,0,0,0,0,0);

    public static Item transcendence = new Item("Transcendence",0,0,0,0,0,0,0) {
        void specialStats() {
            if (cs.champion.lvl > 4) cs.champion.AH += 5;
            if (cs.champion.lvl > 7) cs.champion.AH += 5; //ignoring takedown
        }
    };

    public static Item absoluteFocus = new Item("Absolute Focus",0,0,0,0,0,0,0) {
        void specialStats() {
            //supposing always above 70% hp
            if (cs.champion.getAdaptive().equals(DamageType.physicalDmg))
                cs.champion.BONUS_AD += 1.8 + 16.2 / 17 * (cs.champion.lvl - 1);
            else
                cs.champion.AP += 3 + 27f / 17 * (cs.champion.lvl - 1);
        }
    };

    public static int gatheringStacks;
    public static Item gatheringStorm = new Item("Gathering Storm", 0,0,0,0,0,0,0) {
        void specialStats() {
            extraVariable = gatheringStacks+1;
            if (cs.champion.getAdaptive().equals(DamageType.physicalDmg))
                cs.champion.BONUS_AD += 2.4 * extraVariable * (extraVariable - 1);
            else
                cs.champion.AP += 4 * extraVariable * (extraVariable - 1);
        }
    };

    public static Item biscuitDelivery = new Item("Biscuit Delivery",0,0,0,0,0,0,0);

    public static int shard1, shard2, shard3;
    public static Item shards = new Item("Rune Shards",0,0,0,0,0,0,0) {
        void specialStats() {
            int adaptives = 0;
            if (shard1 == 0) ++adaptives;
            if (shard2 == 0) ++adaptives;
            if (shard1 == 1) cs.champion.BONUS_AS += 10;
            if (shard1 == 2) cs.champion.AH += 8;
            if (cs.champion.getAdaptive().equals(DamageType.physicalDmg))
                cs.champion.BONUS_AD += adaptives * 5.4;
            else
                cs.champion.AP += adaptives * 9;
            if (shard3 == 0) cs.champion.BONUS_HP += 15 + 125f / 17 * (cs.champion.lvl - 1);
            //ignoring armor and mr runes
        }
    };

    public static Item pressTheAttack = new Item("Press the Attack",0,0,0,0,0,0,0) {
         void onHit() {
            if (extraVariable > 0) {
                if (cs.time >= extraVariable + 6) {
                    //time has expired, ignoring dmg from abilities and THIS instance of damage, should be minor impact
                    extraVariable = 0;
                    float increasedDmg = (float) (1.08 + 0.04 * (cs.champion.lvl-1)/17);
                    cs.damageMultiplier /= increasedDmg;
                }
            }
            if (extraVariable <= 0) {
                --extraVariable; //amount of hits, negative
                if (extraVariable == -3) {
                    damageDealt += cs.damage.applyDamage(cs.champion.getAdaptive(),
                            40 + ((float)(140*cs.champion.lvl-1))/17);
                    float increasedDmg = (float) (1.08 + 0.04 * (cs.champion.lvl-1)/17);
                    cs.damageMultiplier *= increasedDmg;
                    extraVariable = cs.time;
                }
            }
        }
    };

    public static Item lethalTempo = new Item("Lethal Tempo",0,0,0,0,0,0,0) {
        void onHit() {
            if (extraVariable == 6) return;
            ++extraVariable;
            int bonus_as = Math.min(15, cs.champion.lvl)/3;
            if (!cs.champion.is_ranged) bonus_as *= 2;
            else if (cs.champion.lvl >= 15) --bonus_as;
            bonus_as += 5;
            cs.champion.BONUS_AS += bonus_as;
            if (extraVariable == 6) cs.champion.max_as = 10;
        }
    };

    public static Item conqueror = new Item("Conqueror",0,0,0,0,0,0,0) {
        void addStack() {
            if (extraVariable == 12) return;
            ++extraVariable; //ignoring healing
            if (cs.champion.getAdaptive().equals(DamageType.physicalDmg))
                cs.champion.BONUS_AD += 1.2 + 1.5/17*(cs.champion.lvl-1);
            else
                cs.champion.AP += 2 + 2.5/17*(cs.champion.lvl-1);
        }
        void onHit() {
            addStack();
            if (!cs.champion.is_ranged) addStack();
        }
        void extraDmg() {
            addStack();
            addStack();
        }
    };

    public static Item electrocute = new Item("Electrocute",0,0,0,0,0,0,0) {
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                item_cooldown = 25 - (float)5/17*(cs.champion.lvl-1); //not exactly correct, but close enough
                ++extraVariable;
                if (extraVariable == 3) {
                    lastUsed = cs.time - item_cooldown * (1 - cs.champion.getItemCooldownModification());
                    damageDealt += cs.damage.applyDamage(cs.champion.getAdaptive(), (float) (30 + 150/17*(cs.champion.lvl-1)
                            + 0.4*cs.champion.BONUS_AD + 0.25*cs.champion.AP));
                    extraVariable = 0;
                }
            }
        }
        void onHit() {
            extraDmg();
        }
    };

    public static Item hailOfBlades = new Item("Hail of Blades",0,0,0,0,0,0,0) {
        void onHit() {
            if (cs.time - lastUsed > item_cooldown) {
                if (extraVariable == 0) {
                    cs.champion.BONUS_AS += 110;
                    extraVariable = cs.champion.max_as;
                    cs.champion.max_as = 90;
                }
                ++extraVariable;
                if (extraVariable == 3) {
                    lastUsed = cs.time - item_cooldown * (1 - cs.champion.getItemCooldownModification());
                    cs.champion.max_as = extraVariable;
                    cs.champion.BONUS_AS -= 110;
                }
            }
        }
    };

    public static int darkHarvestStacks = 0;
    public static Item darkHarvest = new Item("Dark Harvest",0,0,0,0,0,0,0) {
        void specialStats() {
            extraVariable = darkHarvestStacks;
        }
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                if (owner.getEnemy().getRelativeMissingHP() >= 0.5) {
                    float dmg = 20 + 40f/17f*(owner.lvl -1) + 5*extraVariable;
                    dmg += 0.25f * owner.BONUS_AD + 0.15f * owner.AP;
                    damageDealt += cs.damage.applyDamage(owner.getAdaptive(), dmg);
                    ++extraVariable;
                    lastUsed = cs.time - item_cooldown * (1 - cs.champion.getItemCooldownModification());
                }
            }
        }
        void onHit() {
            extraDmg();
        }
    };

    public static Item summonAery = new Item("Summon Aery",0,0,0,0,0,0,0) {
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                float dmg = 10 + 30f / 17f * (owner.lvl - 1);
                dmg += 0.15f * owner.BONUS_AD + 0.10f * owner.AP;
                damageDealt += cs.damage.applyDamage(owner.getAdaptive(), dmg);
                lastUsed = cs.time - item_cooldown * (1 - cs.champion.getItemCooldownModification());
            }
        }
        void onHit() {
            extraDmg();
        }
    };

    public static Item arcaneComet = new Item("Arcane Comet",0,0,0,0,0,0,0) {
        void specialStats() {
            item_cooldown = 20 - 12f / 17f * (owner.lvl - 1);
        }
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                float dmg = 30 + 70f / 17f * (owner.lvl - 1);
                dmg += 0.35f * owner.BONUS_AD + 0.20f * owner.AP;
                damageDealt += cs.damage.applyDamage(owner.getAdaptive(), dmg);
                lastUsed = cs.time - item_cooldown * (1 - cs.champion.getItemCooldownModification());
            }
            else {
                float remainingCD = lastUsed + item_cooldown - cs.time;
                lastUsed -= remainingCD * 0.2f; //reduce cd when other abilities are used
            }
        }
    };

    public static Item graspOfTheUndying = new Item("Grasp of the Undying", 0,0,0,0,0,0,0) {
        void onHit() {
            if (cs.time >= extraVariable + 3) {
                extraVariable = cs.time;
                float mult = 0.035f;
                if (owner.is_ranged) mult = 0.021f;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, owner.getMaxHP() * mult);
                //ignoring heal part
                owner.BONUS_HP += 4;
                if (!owner.is_ranged) owner.BONUS_HP += 3;
            }
        }
    };

    public static Item firstStrike = new Item("First Strike",0,0,0,0,0,0,0) {
        void specialStats() {
            item_cooldown = 25 - 10f/17f*(cs.champion.lvl-1);

            extraVariable = cs.time; //activated before first dmg instance
            cs.hasFirstStrike = true;
        }
        void extraDmg() { //doesnt work?
            if (extraVariable + 3 < cs.time) {
                cs.hasFirstStrike = false;
                lastUsed = cs.time;
                extraVariable = cs.time + 10000;
            }
            else if (lastUsed + item_cooldown >= cs.time && !cs.hasFirstStrike) {
                extraVariable = cs.time;
                cs.hasFirstStrike = true;
            }
        }
        void onHit() {
            extraDmg();
        }
    };

    public static Item cheapShot = new Item("Cheap Shot",0,0,0,0,0,0,0) {
        void extraDmg() { //extremely inaccurate, would be MUCH lower in a real game. this supposes perma CC
            if (cs.time - lastUsed > item_cooldown) {
                damageDealt += cs.damage.applyDamage(DamageType.trueDmg, 10 + 35f / 17f * (owner.lvl - 1));
                lastUsed = cs.time - item_cooldown * (1 - cs.champion.getItemCooldownModification());
            }
        }
    };

    public static Item suddenImpact = new Item("Sudden Impact",0,0,0,0,0,0,0) {
        void extraDmg() { //extremely inaccurate, supposing every ability is a dash
            if (cs.time - lastUsed > item_cooldown && extraVariable <= 0) { //the extra comparator is to see that the rune is NOT active atm
                extraVariable = cs.time + 0.01f;
                owner.LETHALITY += 9;
                owner.MAGIC_PEN += 7;
            }
            else if (extraVariable + 5 > cs.time) {
                lastUsed = cs.time - item_cooldown * (1 - cs.champion.getItemCooldownModification());
                extraVariable = -6;
                owner.LETHALITY -= 9;
                owner.MAGIC_PEN -= 7;
            }
        }
    };

    public static int ingeniousStacks;
    public static Item ingeniousHunter = new Item("Ingenious Hunter",0,0,0,0,0,0,0) {
        void specialStats() {
            if (Runes.ingeniousStacks < 6) extraVariable = Runes.ingeniousStacks;
            else extraVariable = 5;
            cs.champion.ITEM_HASTE += 20 + 6*Math.min(extraVariable, 5);
        }
    };

    public static int ultimateHunterStacks;
    public static Item ultimateHunter = new Item("Ultimate Hunter",0,0,0,0,0,0,0) {
        void specialStats() {
            if (Runes.ultimateHunterStacks < 6) extraVariable = Runes.ultimateHunterStacks;
            else extraVariable = 5;
            cs.champion.ULTIMATE_HASTE += 6 + 5*Math.min(extraVariable, 5);
        }
    };

    public static Item scorch = new Item("Scorch",0,0,0,0,0,0,0) {
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown * (1 - cs.champion.getItemCooldownModification());
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 20 + 20f/17*(cs.champion.lvl-1));
            }
        }
    };

    //Shield bash (?)
    //Overgrowth (?)

    public static Item cosmicInsight = new Item("Cosmic Insight",0,0,0,0,0,0,0) {
        void specialStats() {
            cs.champion.ITEM_HASTE += 10; //ignoring sum haste
        }
    };

    static void initializeRunes() {

        runes.add(legendAlacrity);

        runes.add(coupDeGrace);

        runes.add(cutDown);

        runes.add(lastStand);

        runes.add(eyeballCollection);

        manaflowBand.mana = 250;
        runes.add(manaflowBand);

        runes.add(transcendence);

        runes.add(absoluteFocus);

        runes.add(gatheringStorm);

        biscuitDelivery.mana = 40*3; //supposing 3 cookies
        runes.add(biscuitDelivery);

        runes.add(shards);

        runes.add(pressTheAttack);

        runes.add(lethalTempo);

        runes.add(conqueror);

        runes.add(electrocute);

        hailOfBlades.item_cooldown = 12;
        runes.add(hailOfBlades);

        darkHarvest.item_cooldown = 45;
        runes.add(darkHarvest);

        summonAery.item_cooldown = 3; //aprox. 0.45s to arrive to target + 2s of cooldown + ~0.5secs coming back?
        runes.add(summonAery);

        runes.add(arcaneComet);

        runes.add(graspOfTheUndying);

        runes.add(firstStrike);

        cheapShot.item_cooldown = 4;
        runes.add(cheapShot);

        suddenImpact.item_cooldown = 4;
        runes.add(suddenImpact);

        ingeniousHunter.is_hidden = true;
        runes.add(ingeniousHunter);

        ultimateHunter.is_hidden = true;
        runes.add(ultimateHunter);

        scorch.item_cooldown = 10;
        runes.add(scorch);

        cosmicInsight.is_hidden = true;
        runes.add(cosmicInsight);


        for (Item i : runes) i.is_rune = true;
    }
}

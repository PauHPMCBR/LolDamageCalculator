package simulationManager.simulation;

/**
 * Implementation of mythic items
 */
public class MythicItems extends Items {

    public static Item crownOfTheShatteredQueen = new Item("Crown of the Shattered Queen", 0, 70, 0, 20, 250, 0, 0) {
        void specialStats() {
            owner.AP += 10;
            if (owner.lvl >= 9) owner.AP += (owner.lvl-8)*3;
        }
        void applyMythicPassive() {
            //ignoring +1% bonus ms
            owner.AP += 8 * owner.legendary_items_carried;
        }
    };

    public static Item divineSunderer = new Item("Divine Sunderer", 40, 0, 0, 20, 300, 0, 0) {
        public void onHit() {
            if (cs.time - lastUsed > item_cooldown) {
                if (owner.can_use_sheen) {
                    owner.can_use_sheen = false;
                    lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                    float amount = (float) (1.25 * owner.BASE_AD);
                    amount += owner.getEnemy().getMaxHP() * 0.03;
                    if (!owner.is_ranged) amount += owner.getEnemy().getMaxHP() * 0.03;
                    amount = (float) Math.max(amount, 1.5 * owner.BASE_AD);
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, amount, 1);
                    //ignoring healing part
                }
            }
        }
        void applyMythicPassive() {
            owner.increaseArmorPen(3 * owner.legendary_items_carried);
        }
    };

    public static Item duskbladeOfDraktharr = new Item("Duskblade of Draktharr", 60, 0, 0, 20, 0, 0, 0) {
        public void onHit() {
            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                if (owner.is_ranged)
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 55 + 25 * owner.BONUS_AD, 1);
                else
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 75 + 30 * owner.BONUS_AD, 1);
            }
        }
        void applyMythicPassive() {
            owner.AH += 5 * owner.legendary_items_carried;
            //ignoring +5 bonus ms
        }
    };

    public static Item eclipse = new Item("Eclipse", 60, 0, 0, 15, 0, 0, 0) {
        void specialStats() {
            item_cooldown = 6;
            if (owner.is_ranged) item_cooldown = 12;
        }
        void extraDmg() {
            //ignoring needs 2 hits to proc, ms and shield granted
            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                if (owner.is_ranged)
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (float) (0.03 * owner.getEnemy().getMaxHP()));
                else
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (float) (0.06 * owner.getEnemy().getMaxHP()));
            }
        }
        void applyMythicPassive() {
            owner.increaseArmorPen(4 * owner.legendary_items_carried);
            //ignoring +5 bonus ms
        }
    };

    public static Item evenshroud = new Item("Evenshroud",0,0,0,20,200,30,30) {
        void specialStats() {
            cs.damageMultiplier *= 1.1;
        }
        void applyMythicPassive() {
            owner.ARMOR += 5 * owner.legendary_items_carried;
            owner.MAGIC_RESIST += 5 * owner.legendary_items_carried;
        }
    };

    public static Item everfrost = new Item("Everfrost", 0, 70, 0, 20, 250, 0, 0) {
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (float) (100 + 0.3 * owner.AP));
            }
        }
        void applyMythicPassive() {
            owner.AP += 10 * owner.legendary_items_carried;
        }
    };

    public static Item galeforce = new Item("Galeforce", 60, 0, 20, 0, 0, 0, 0) {
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                float missing = (float) Math.min(0.7, owner.getEnemy().getRelativeMissingHP());
                if (missing < 0.7) return; //this way, the execute multiplier is maxed

                lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                float amount = 180;
                if (owner.lvl >= 10) amount += 15 * (owner.lvl - 9);
                amount += 0.45 * owner.BONUS_AD;
                amount += amount * missing * 5 / 7;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, amount);
            }
        }
        //ignoring +2% ms mythic passive
    };

    public static Item goredrinker = new Item("Goredrinker",55,0,0,20,300,0,0) {
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown*(1 - 100/(100+owner.ITEM_HASTE + owner.AH));
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (float) (1.75 * owner.BASE_AD));
            }
        }
        void applyMythicPassive() {
            owner.BONUS_HP += 50 * owner.legendary_items_carried;
            owner.AH += 3 * owner.legendary_items_carried;
        }
    };

    public static Item heartsteel = new Item("Heartsteel",0,0,0,20,800,0,0) {
        void onHit() {
            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                float dmg = 125f + 0.06f * owner.getMaxHP();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg, 1);
                owner.BONUS_HP += dmg * 0.1;
            }
        }
        void applyMythicPassive() {
            owner.BONUS_HP *= (1 + 0.01 * owner.legendary_items_carried); //+1% hp for every legendary
        }
    };

    public static Item hextechRocketbelt = new Item("Hextech Rocketbelt",0,90,0,15,250,0,0) {
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (float) (125 + 0.15 * owner.AP));
                owner.autoReset(); //how many people know this? this is so random xd
            }
        }
        void applyMythicPassive() {
            owner.MAGIC_PEN += 5 * owner.legendary_items_carried;
        }
    };

    public static Item icebornGauntlet = new Item("Iceborn Gauntlet",0,0,0,20,400,50,0) {
        public void onHit() { //ignoring damage reduction part!
            if (cs.time - lastUsed > item_cooldown) {
                if (owner.can_use_sheen) {
                    owner.can_use_sheen = false;
                    lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, owner.BASE_AD, 1);
                }
            }
        }
        void applyMythicPassive() {
            owner.BONUS_HP += 50 * owner.legendary_items_carried; //ignoring tenacity and slow resist
        }
    };

    public static Item immortalShieldbow = new Item("Immortal Shieldbow", 50, 0, 20, 0, 0, 0, 0) {
        //ignoring bonus ad from shield
        void applyMythicPassive() {
            owner.BONUS_AD += 5 * owner.legendary_items_carried;
            owner.BONUS_HP += 70 * owner.legendary_items_carried;
        }
    };

    public static Item imperialMandate = new Item("Imperial Mandate",0,40,0,20,200,0,0) {
        void specialStats() {
            extraVariable = 45 + 30f / 17f * (owner.lvl - 1); //extraVariable = damage
        }
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, extraVariable); //supposing every ability slows or cc
            }
        }
        void applyMythicPassive() {
            owner.AP += 15 * owner.legendary_items_carried;
        }
    };

    public static Item jakSho = new Item("Jak'Sho",0,0,0,20,400,30,30) {
        void extraDmg() { //extra variable are stacks
            if (extraVariable == 8) return;
            int newStacks = Math.min(8, (int)cs.time);
            owner.ARMOR += 2 * (newStacks - extraVariable);
            owner.MAGIC_RESIST += 2 * (newStacks - extraVariable);
            extraVariable = newStacks;
            if (extraVariable == 8) {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 80f + 0.07f * owner.BONUS_HP);
                //ignoring healing
                owner.ARMOR += (owner.ARMOR - owner.base_armor - owner.armor_growth*(owner.lvl-1)) * 0.2;
                owner.MAGIC_RESIST += (owner.MAGIC_RESIST - owner.base_mr - owner.mr_growth*(owner.lvl-1)) * 0.2; //increase BONUS resistances
            }
        }
        public void onHit() {
            extraDmg();
        }
        public void applyMythicPassive() {
            owner.ARMOR += 5 * owner.legendary_items_carried;
            owner.MAGIC_RESIST += 5 * owner.legendary_items_carried;
        }
    };

    public static Item krakenSlayer = new Item("Kraken Slayer",65,0,25,0,0,0,0) {
        public void onHit() {
            if (owner.autosUsed % 3 == 0)
                damageDealt += cs.damage.applyDamage(DamageType.trueDmg, (float) (owner.getAD() * 0.4 + 50), 1);
        }
        void applyMythicPassive() {
            owner.BONUS_AS += 10 * owner.legendary_items_carried;
        }
    };

    public static Item liandrysAnguish = new Item("Liandry's Anguish",0,80,0,20,0,0,0) {
        void specialStats() {
            cs.liandryPercent = (float) (1 + 0.12 / 1250 * Math.min(1250, owner.getEnemy().BONUS_HP));
        }
        void extraDmg() {
            float ticks = cs.time - extraVariable; //extra variable is time
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                    (float) (ticks * (50 + 0.06 * owner.AP + 0.04 * owner.getEnemy().getMaxHP())));
            extraVariable = cs.time;
        }
        public void onHit() {
            extraDmg();
        }
        void applyMythicPassive() {
            owner.AH += 5 * owner.legendary_items_carried;
        }
    };

    public static Item locketOfTheIronSolari = new Item("Locket Of The Iron Solari",0,0,0,20,200,30,30) {
        //ignoring shield
        //ignoring mythic passive
    };

    public static Item ludensTempest = new Item("Luden's Tempest", 0, 80, 0, 20, 0, 0, 0) {
        void specialStats() {
            //!!! ludens works with damage instances, so instead of ability calling for extraDmg, the class
            // Damage will call this function for every instance of magic damage. the extra variable is to not do anything the first time it's called
            if (extraVariable == 0) {extraVariable = 1; return;}

            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (float) (100 + 0.1 * owner.AP));
            }
            lastUsed -= 0.3; //decrease cd by 0.3 secs
        }

        void applyMythicPassive() {
            owner.MAGIC_PEN += 5 * owner.legendary_items_carried;
        }
    };

    public static Item moonstoneRenewer = new Item("Moonstone Renewer",0,40,0,20,200,0,0) {
        //ignoring healing passive and mythic passive
    };

    public static Item nightHarvester = new Item("Night Harvester",0,90,0,25,300,0,0) {
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (float) (125 + 0.15 * owner.AP));
            }
        }
        void applyMythicPassive() {
            owner.AH += 5 * owner.legendary_items_carried;
        }
    };

    public static boolean useProwlersActive = true;
    public static Item prowlersClaw = new Item("Prowler's Claw", 60, 0, 0, 20, 0, 0, 0) {
        void extraDmg() {
            if (useProwlersActive) {
                if (cs.time - lastUsed > item_cooldown) {
                    lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (float) (70 + 0.3 * owner.BONUS_AD));
                    extraVariable = cs.time;
                    cs.damageMultiplier *= 1.15;
                }
                else if (extraVariable + 3 >= cs.time) {
                    extraVariable = 0;
                    cs.damageMultiplier /= 1.15;
                }
            }
        }
        void applyMythicPassive() {
            owner.LETHALITY += 5 * owner.legendary_items_carried;
            //ignoring +5 bonus ms
        }
    };

    public static Item radiantVirtue = new Item("Radiant Virtue",0,0,0,20,400,30,30) {
        void extraDmg() { //supposing insta ult, ignoring healing part
            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown*(1 - 100/(100+owner.ITEM_HASTE + owner.AH));
                extraVariable = 0.15f * owner.getMaxHP();
                owner.BONUS_HP += extraVariable;
            }
            if (lastUsed < cs.time + 9 && extraVariable != 0) { //9s duration, then remove extra hp
                owner.BONUS_HP -= extraVariable;
                extraVariable = 0;
            }
        }
        void applyMythicPassive() {
            owner.BONUS_HP += 100 * owner.legendary_items_carried;
        }
    };

    public static Item riftmaker = new Item("Riftmaker", 0, 70, 0, 15, 300, 0, 0) {
        void specialStats() {
            cs.riftmakerItem = this;
        }
        void applyMythicPassive() {
            owner.AP += 8 * owner.legendary_items_carried;
            //ignoring +2 omnivamp
        }
    };

    public static Item rodOfAges = new Item("Rod of Ages",0,110,0,0,700,0,0) { //ignoring mana-hp healing, supposing max stacks
        void applyMythicPassive() {
            owner.AH += 5 * owner.legendary_items_carried;
        }
    };

    public static Item shureliasBattlesong = new Item("Shurelia's Battlesong",0,40,0,20,200,0,0) {
        void applyMythicPassive() {
            owner.AH += 5 * owner.legendary_items_carried;
        }
    };

    public static Item stridebreaker = new Item("Stridebreaker", 50, 0, 20, 20, 300, 0, 0) {
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown*(1 - 100/(100+owner.ITEM_HASTE + owner.AH));
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (float) (1.75 * owner.BASE_AD));
            }
        }
        //ignoring mythic passive +2% ms
    };

    public static Item trinityForce = new Item("Trinity Force", 35, 0, 30, 20, 300, 0 ,0) {
        void specialStats() {
            owner.BASE_AD *= 1.2;
        }
        public void onHit() {
            if (cs.time - lastUsed > item_cooldown) {
                if (owner.can_use_sheen) {
                    owner.can_use_sheen = false;
                    owner.lastSheenProc = cs.time;
                    lastUsed = cs.time - item_cooldown * (1 - owner.getItemCooldownModification());
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 2 * owner.BASE_AD, 1);
                }
            }
        }
        void applyMythicPassive() {
            owner.BONUS_AD += 3 * owner.legendary_items_carried;
            owner.AH += 3 * owner.legendary_items_carried;
            //ignoring +3 bonus ms
        }
    };


    static void initializeMythics() {
        mythics.add(crownOfTheShatteredQueen);

        divineSunderer.item_cooldown = 1.5F;
        mythics.add(divineSunderer);

        duskbladeOfDraktharr.item_cooldown = 15F;
        duskbladeOfDraktharr.lethality = 18;
        mythics.add(duskbladeOfDraktharr);

        eclipse.lethality = 12;
        mythics.add(eclipse);

        mythics.add(evenshroud);

        everfrost.item_cooldown = 30;
        everfrost.mana = 600;
        mythics.add(everfrost);

        galeforce.item_cooldown = 110;
        galeforce.crit = 20;
        mythics.add(galeforce);

        liandrysAnguish.mana = 600;
        mythics.add(liandrysAnguish);

        galeforce.crit = 20;
        mythics.add(galeforce);

        goredrinker.item_cooldown = 15;
        mythics.add(goredrinker);

        heartsteel.item_cooldown = 30;
        mythics.add(heartsteel);

        hextechRocketbelt.magic_pen = 6;
        hextechRocketbelt.item_cooldown = 40;

        icebornGauntlet.item_cooldown = 1.5f;
        mythics.add(icebornGauntlet);

        immortalShieldbow.crit = 20;
        mythics.add(immortalShieldbow);

        imperialMandate.item_cooldown = 6;
        mythics.add(imperialMandate);

        mythics.add(jakSho);

        krakenSlayer.crit = 20;
        mythics.add(krakenSlayer);

        mythics.add(locketOfTheIronSolari);

        ludensTempest.mana = 600;
        ludensTempest.magic_pen = 6;
        ludensTempest.item_cooldown = 10;
        mythics.add(ludensTempest);

        mythics.add(moonstoneRenewer);

        nightHarvester.item_cooldown = 30;
        mythics.add(nightHarvester);

        rodOfAges.mana = 700;
        mythics.add(rodOfAges);

        prowlersClaw.lethality = 18;
        mythics.add(prowlersClaw);

        radiantVirtue.item_cooldown = 90;
        mythics.add(radiantVirtue);

        mythics.add(riftmaker);

        mythics.add(shureliasBattlesong);

        stridebreaker.item_cooldown = 15; //reduced by ability haste!
        mythics.add(stridebreaker);

        trinityForce.item_cooldown = 1.5F;
        mythics.add(trinityForce);

        for (Item mythic : mythics) mythic.is_mythic = true;
    }
}

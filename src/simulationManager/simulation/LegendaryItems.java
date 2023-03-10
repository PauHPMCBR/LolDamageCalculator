package simulationManager.simulation;

/**
 * Implementation of legendary items
 */
public class LegendaryItems extends Items {

    public static Item abyssalMask = new Item("Abyssal Mask", 0, 0, 0, 10,500,0,40) {
        void specialStats() {
            float mpen = (float) (5 + 0.0012 * (owner.BONUS_HP));
            owner.getEnemy().MAGIC_RESIST = Math.min(0, owner.getEnemy().MAGIC_RESIST - mpen);
        } //ignoring mana and healing
    };

    public static Item anathemasChains = new Item("Anathema's Chains",0,0,0,20,650,0,0); //ignoring vendetta
    //archangels -> seraphs
    //ardent

    public static Item axiomArc = new Item("Axiom Arc", 55, 0, 0, 25, 0, 0, 0); //ignoring ult cd

    public static Item bansheesVeil = new Item("Banshee's Veil", 0, 80, 0, 10, 0, 0, 45); //ignoring shield

    public static Item blackCleaver = new Item("Black Cleaver", 50, 0, 0, 30, 400, 0, 0) {
        void specialStats() {
            //!!! cleaver works with damage instances, so instead of ability/auto calling for extraDmg/onHit, the class
            // Damage will call this function for every instance of physical damage. the extra variable is to not do anything the first time it's called
            if (extraVariable == 0) {extraVariable = 1; return;}
            if (extraVariable > 6) return; //the next code will get executed 6 times in total

            owner.getEnemy().ARMOR /= (1 - extraVariable*0.05);
            ++extraVariable;
            owner.getEnemy().ARMOR *= (1 - extraVariable*0.05);
        }
    };

    public static Item blackMistScythe = new Item("Black Mist Scythe",20,0,0,0,75,0,0); //ignoring mana regen

    public static Item bladeOfTheRuinedKing = new Item("Blade of the Ruined King", 40, 0, 25, 0, 0, 0,0) {
        void onHit() {
            float mult = 0.12f;
            if (owner.is_ranged) mult = 0.09f;
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, Math.max(15, owner.getEnemy().HP * mult));

            if (cs.time > lastUsed + item_cooldown) {
                ++extraVariable;
                if (extraVariable == 3) {
                    lastUsed = cs.time - item_cooldown * (1 - owner.getItemCooldownModification());
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 40 + 63f / 9 * Math.max(0, owner.lvl - 9), 1);
                    extraVariable = 0;
                }
            }
        }
    };

    public static Item bloodthister = new Item("Bloodthister",55,0,0,0,0,0,0); //ignoring shield and lifesteal

    public static Item bulwackOfTheMountain = new Item("Bulwack of the Mountain",0,20,0,0,250,0,0); //ignoring hp regen

    public static Item chempunkChainsword = new Item("Chempunk Chainsword",55,0,0,25,250,0,0); //ignoring grievous
    //putrifier

    public static Item cosmicDrive = new Item("Cosmic Drive",0,90,0,30,0,0,0) {
        void extraDmg() {
            ++extraVariable;
            if (extraVariable == 3) {
                owner.AP += 40;
            }
        }
        public void onHit() {
            extraDmg();
        }
    };
    //dead mans

    public static Item deathDance = new Item("Death's Dance", 65,0,0,0,0,50,0);//ignoring passives

    public static Item demonicEmbrace = new Item("Demonic Embrace",0,75,0,0,350,0,0) {
        void specialStats() {
           owner.AP += owner.BONUS_HP * 0.02;
        }
        void extraDmg() {
            float ticks = cs.time - extraVariable; //extra variable is time
            float mult = 0.064f;
            if (owner.is_ranged) mult = 0.04f;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                    (float) (ticks * (owner.getEnemy().getMaxHP() * mult)));
            extraVariable = cs.time;
        }
        public void onHit() {
            extraDmg();
        }
    };

    public static Item edgeOfNight = new Item("Edge of Night",50,0,0,0,325,0,0);//ignoring shield

    public static Item essenceReaver = new Item("Essence Reaver", 55,0,0,20,0,0,0) {
        public void onHit() {
            if (cs.time - lastUsed > item_cooldown) {
                if (owner.can_use_sheen) {
                    owner.can_use_sheen = false;
                    owner.lastSheenProc = cs.time;
                    lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                            (float) (owner.BASE_AD + 0.4*owner.BONUS_AD), 1);
                }
            }
        }
    };

    public static Item fimbulwinter = new Item("Fimbulwinter",0,0,0,15,400,0,0) {
        void specialStats() {
            owner.BONUS_HP += 0.08 * owner.MANA;
        } //ignoring shields
    };

    //force of nature

    public static Item frozenHeart = new Item("Frozen Heart",0,0,0,20,0,90,0); //ignoring rock solid and cripple

    //gargoyle

    public static Item guardianAngel = new Item("Guardian Angel",45,0,0,0,0,40,0);

    public static Item guinsoosRageblade = new Item("Guinsoo's Rageblade",0,0,45,0,0,0,0) {
        void specialStats() {
            item_cooldown = owner.CRIT_CHANCE;//item cooldown is amount of crit (from 0 to 100)
            owner.CRIT_CHANCE = 0;
        }
        public void onHit() {
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, item_cooldown * 2, 1);

            ++extraVariable;
            if (extraVariable == 3) {
                for (Item i : owner.allItems) {
                    if (i.getName().equals("Kraken Slayer") || i.getName().equals("Guinsoo's Rageblade")) continue;
                    i.onHit();
                }
                extraVariable = 0;
            }
        }
    };

    public static Item horizonFocus = new Item("Horizon Focus",0,100,0,15,150,0,0) {
        void specialStats() { //supposing 700+ range?
            cs.damageMultiplier *= 1.1;
        }
    };

    public static Item hullbreaker = new Item("Hullbreaker",50,0,0,0,400,0,0);//ignoring bonus resists

    public static Item infinityEdge = new Item("Infinity Edge",70,0,0,0,0,0,0) {
        void specialStats() {
            if (owner.CRIT_CHANCE >= 40) //not tested
                owner.crit_damage += 0.35f;
        }
    };
    //knights vow

    public static Item lichBane = new Item("Lich Bane",0,75,0,15,0,0,0) {
        public void onHit() {
            if (cs.time - lastUsed > item_cooldown) {
                if (owner.can_use_sheen) {
                    owner.can_use_sheen = false;
                    owner.lastSheenProc = cs.time;
                    lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                            (float) (0.75*owner.BASE_AD + 0.5*owner.AP), 1);
                }
            }
        }
    };

    public static Item lordDominiksRegards = new Item("Lord Dominik's Regards",35,0,0,0,0,0,0) {
        void specialStats() {
            float diff = owner.getEnemy().getMaxHP() - owner.getMaxHP();
            diff = Math.min(2500, Math.max(0, diff));
            cs.ldrPercent = 1 + diff/10000; //should be correct
        }
    };
    //manamune -> muramana

    public static Item mawOfMalmortius = new Item("Maw of Malmortius",65,0,0,0,0,0,50); //ignoring shield + lifesteal

    public static int mejaisStacks;
    public static Item mejaisSoulstealer = new Item("Mejai's Soulstealer",0,20,0,0,100,0,0) {
        void specialStats() {
            extraVariable = mejaisStacks;
            owner.AP += 5 * extraVariable; //extraVariable are mejai's stacks
        }
    };

    public static Item mercurialScimitar = new Item("Mercurial Scimitar",40,0,0,0,0,0,30); //ignoring qss
    //mikaels

    public static Item morellonomicon = new Item("Morellonomicon",0,90,0,0,200,0,0); //ignoring grievous

    public static Item mortalReminder = new Item("Mortal Reminder",35,0,0,0,0,0,0); //ignoring grievous

    public static Item muramana = new Item("Muramana",35,0,0,15,0,0,0) {
        void specialStats() {
            owner.BONUS_AD += owner.MANA * 0.025;
        }
        void extraDmg() {
            float percent = 0.035f;
            if (owner.is_ranged) percent = 0.027f;
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                    (float) (owner.MANA * percent + owner.BONUS_AD * 0.06));
        }
        public void onHit() {
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 0.015f * owner.MANA, 1);
        }
    };

    public static Item nashorsTooth = new Item("Nashor's Tooth",0,100,50,0,0,0,0) {
        public void onHit() {
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 15 + 0.2f * owner.AP, 1);
        }
    };

    public static Item navoriQuickblades = new Item("Navori Quickblades",60,0,0,20,0,0,0) {
        void specialStats() {
            if (owner.CRIT_CHANCE >= 40) extraVariable = 1;
            cs.navoriPercent = 1 + owner.CRIT_CHANCE/500; //untested
        }
        public void onHit() {
            if (extraVariable == 1) {
                owner.Q.currentCooldown *= (1 - 0.15);
                owner.W.currentCooldown *= (1 - 0.15);
                owner.E.currentCooldown *= (1 - 0.15);
            }
        }
    };

    public static Item pauldronsOfWhiterock = new Item("Pauldrons of Whiterock",15,0,0,0,250,0,0); //ignoring hp regen

    public static Item phantomDancer = new Item("Phantom Dancer", 20,0,25,0,0,0,0) {
        public void onHit() {
            ++extraVariable;
            if (extraVariable == 4) {
                owner.BONUS_AS += 30;
            }
        }
    };

    public static Item rabbadonsDeathcap = new Item("Rabbadon's Deathcap",0,120,0,0,0,0,0) {
        void specialStats() {
            owner.AP *= 1.35; //have to check if some extra ap is skipped
        }
    };

    public static Item randuinsOmen = new Item("Randuin's Omen",0,0,0,10,400,60,0); //ignoring rock solid, slow and less crit

    public static Item rapidFirecannon = new Item("Rapid Firecannon",0,0,35,0,0,0,0) {
        void specialStats() {
            extraVariable = 9;
        }
        void onHit() {
            ++extraVariable;
            if (extraVariable == 10) { //kinda arbitrary
                extraVariable = 0;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 120);
            }
        }
    };

    public static int ravenousStacks = 20;
    public static Item ravenousHydra = new Item("Ravenous Hydra", 65,0,0,25,0,0,0) {
        void specialStats() {
            extraVariable = ravenousStacks;
            owner.BONUS_AD += extraVariable*0.5;
        }
    };
    //redemption

    public static Item runaansHurrycane = new Item("Runaan's Hurricane",0,0,45,0,0,0,0);

    public static Item rylaisCrystalScepter = new Item("Rylai's Crystal Scepter",0,75,0,0,400,0,0);

    public static Item seraphsEmbrace = new Item("Seraph's Embrace",0,70,0,10,200,0,0) {
        void specialStats() { //ignoring shield
            owner.AP += 0.025 * owner.MANA;
        }
    };

    public static Item serpentsFang = new Item("Serpent's Fang",55,0,0,0,0,0,0); //ignoring shield break

    public static Item seryldasGrudge = new Item("Serylda's Grudge", 45,0,0,20,0,0,0);

    public static Item shadowflame = new Item("Shadowflame",0,100,0,0,200,0,0) {
        void specialStats() {
            extraVariable = 10;
            owner.MAGIC_PEN += extraVariable;
        }
        void extraDmg() {
            owner.MAGIC_PEN -= extraVariable;
            float normalizedHP = Math.max(1000, Math.min(2500, owner.getEnemy().HP));
            extraVariable = 10 + (2500 - normalizedHP)/150;
            owner.MAGIC_PEN += extraVariable; //should be like magic pen in theory, ignoring shield part
        }
        public void onHit() {
            extraDmg();
        }
    };

    public static Item shardOfTrueIce = new Item("Shard of True Ice",0,40,0,0,75,0,0); //ignoring mana regen

    public static Item silvermereDawn = new Item("Silvermere Dawn",40,0,0,0,300,0,0);

    public static Item spearOfShojin = new Item("Spear of Shojin", 65,0,0,20,300,0,0) {
        void specialStats() {
            extraVariable = (float) (8 + 0.08*owner.BONUS_AD);
            if (owner.is_ranged) extraVariable *= 0.75;
            owner.AH += extraVariable;
            owner.ULTIMATE_HASTE -= extraVariable; //ignoring *0.5 penalty for cc abilities
        }
    };
    //spirit visage
    //staff

    public static Item steraksCage = new Item("Sterak's Cage",0,0,0,0,400,0,0) {
        void specialStats() {
            owner.BONUS_AD += 0.5 * owner.BASE_AD;
        } //ignoring shield
    };

    public static Item stormrazor = new Item("Stormrazor",45,0,15,0,0,0,0) {
        public void onHit() {
            ++extraVariable;
            if (extraVariable == 10) { //kinda arbitrary
                extraVariable = 0;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 120, 1);
            }
        }
    };

    public static Item sunfireAegis = new Item("Sunfire Aegis",0,0,0,0,500,50,0) {
        void extraDmg() {
            float ticks = cs.time - extraVariable; //extra variable is time
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                    (float) (ticks * (15 + 0.0175 * owner.BONUS_HP)));
            extraVariable = cs.time;
        }
        public void onHit() {
            extraDmg();
        }
    };

    public static Item collector = new Item("The Collector",55,0,0,0,0,0,0) {
        void extraDmg() {
            if (owner.getEnemy().getRelativeMissingHP() > 0.95)
                cs.damage.execute();
        }
    };
    //thornmail

    public static Item titanicHydra = new Item("Titanic Hydra",30,0,0,0,500,0,0) {
        void specialStats() {
            owner.BONUS_AD += owner.BONUS_HP * 0.02;
        }
        public void onHit() {
            extraVariable = (float) (4 + 0.015 * owner.getMaxHP());
            if (owner.is_ranged) extraVariable *= 0.75;
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, extraVariable, 1);
        }
    };
    //chemtank

    public static Item umbralGlaive = new Item("Umbral Glaive",50,0,0,15,0,0,0);

    public static Item vigilantWardstone = new Item("Vigilant Wardstone",0,0,0,15,150,0,0) {
        void specialStats() {
            owner.BONUS_AD *= 1.12;
            owner.AP *= 1.12;
            owner.AH *= 1.12;
            owner.BONUS_HP *= 1.12;
        }
    };

    public static Item voidStaff = new Item("Void Staff",0,65,0,0,0,0,0);

    public static Item warmongsArmor = new Item("Warmong's Armor",0,0,0,10,800,0,0); //ignoring hp regens
    //winter's -> fimbulwinter

    public static Item witsEnd = new Item("Wit's End",40,0,40,0,0,0,40) {
        void specialStats() {
            extraVariable = 15 + Math.max(0, owner.lvl - 8) * 10;
            extraVariable -= Math.max(0, owner.lvl - 14) * (1.25 - 10);
        } //supposing champ wont lvl up
        public void onHit() {

            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, extraVariable, 1);
        }
    };

    public static Item yoummuusGhostblade = new Item("Yoummuu's Ghostblade",55,0,0,15,0,0,0);
    //zekes

    public static Item zhonyasHourglass = new Item("Zhonya's Hourglass",0,80,0,15,0,45,0); //ignoring stasis

    static void initializeLegendaries() {

        abyssalMask.mana = 300;
        legendaries.add(abyssalMask);

        legendaries.add(anathemasChains);

        axiomArc.lethality = 18;
        legendaries.add(axiomArc);

        legendaries.add(bansheesVeil);

        legendaries.add(blackCleaver);

        legendaries.add(blackMistScythe);

        bladeOfTheRuinedKing.item_cooldown = 20;
        legendaries.add(bladeOfTheRuinedKing);

        bloodthister.crit = 20;
        legendaries.add(bloodthister);

        legendaries.add(bulwackOfTheMountain);

        legendaries.add(chempunkChainsword);

        legendaries.add(cosmicDrive);

        legendaries.add(deathDance);

        legendaries.add(demonicEmbrace);

        edgeOfNight.lethality = 10;
        legendaries.add(edgeOfNight);

        essenceReaver.item_cooldown = 1.5f;
        legendaries.add(essenceReaver);

        fimbulwinter.mana = 860;
        legendaries.add(fimbulwinter);

        frozenHeart.mana = 400;
        legendaries.add(frozenHeart);

        legendaries.add(guardianAngel);

        guinsoosRageblade.crit = 20;
        legendaries.add(guinsoosRageblade);

        legendaries.add(horizonFocus);

        legendaries.add(hullbreaker);

        infinityEdge.crit = 20;
        legendaries.add(infinityEdge);

        legendaries.add(lichBane);

        lordDominiksRegards.armor_pen = 30;
        lordDominiksRegards.crit = 20;
        legendaries.add(lordDominiksRegards);

        legendaries.add(mawOfMalmortius);

        legendaries.add(mejaisSoulstealer);

        mercurialScimitar.crit = 20;
        legendaries.add(mercurialScimitar);

        morellonomicon.magic_pen = 10;
        legendaries.add(morellonomicon);

        mortalReminder.armor_pen = 30;
        mortalReminder.crit = 20;
        legendaries.add(mortalReminder);

        muramana.mana = 860;
        legendaries.add(muramana);

        legendaries.add(nashorsTooth);

        navoriQuickblades.crit = 20;
        legendaries.add(navoriQuickblades);

        legendaries.add(pauldronsOfWhiterock);

        phantomDancer.crit = 20;
        legendaries.add(phantomDancer);

        legendaries.add(rabbadonsDeathcap);

        legendaries.add(randuinsOmen);

        rapidFirecannon.crit = 20;
        legendaries.add(rapidFirecannon);

        legendaries.add(ravenousHydra);

        runaansHurrycane.crit = 20; //not adding ranged exclussiveness
        legendaries.add(runaansHurrycane);

        legendaries.add(rylaisCrystalScepter);

        seraphsEmbrace.mana = 860;
        legendaries.add(seraphsEmbrace);

        serpentsFang.lethality = 12;
        legendaries.add(serpentsFang);

        seryldasGrudge.armor_pen = 30;
        legendaries.add(seryldasGrudge);

        legendaries.add(shadowflame);

        legendaries.add(shardOfTrueIce);

        legendaries.add(silvermereDawn);

        legendaries.add(spearOfShojin);

        legendaries.add(steraksCage);

        stormrazor.crit = 20;
        legendaries.add(stormrazor);

        legendaries.add(sunfireAegis);

        collector.lethality = 12;
        collector.crit = 20;
        legendaries.add(collector);

        legendaries.add(titanicHydra);

        umbralGlaive.lethality = 10;
        legendaries.add(umbralGlaive);

        legendaries.add(vigilantWardstone);

        voidStaff.percent_magic_pen = 40;
        legendaries.add(voidStaff);

        legendaries.add(warmongsArmor);

        legendaries.add(witsEnd);

        yoummuusGhostblade.lethality = 18;
        legendaries.add(yoummuusGhostblade);

        legendaries.add(zhonyasHourglass);

        for (Item i : legendaries) i.is_legendary = true;
    }
}

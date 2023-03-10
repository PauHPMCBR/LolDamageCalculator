package simulationManager.simulation;

public class ComponentItems {
    public static Item cull = new Item("Cull",7,0,0,0,0,0,0); //ignoring healing

    public static int darkSealStacks = 0;
    public static Item darkSeal = new Item("Dark Seal",0,15,0,0,40,0,0) {
        void specialStats() {
            owner.AP += 4 * Math.min(darkSealStacks, 10);
        }
    };

    public static Item doransBlade = new Item("Doran's Blade",8,0,0,0,80,0,0); //ignoring healing

    public static Item doransRing = new Item("Doran's Ring",0,15,0,0,70,0,0); //ignoring mana, healing and minion onhit

    public static Item doransShield = new Item("Doran's Shield",0,0,0,0,80,0,0); //ignoring healing and onhit

    public static Item guardiansBlade = new Item("Guardian's Blade",30,0,0,15,150,0,0);

    public static Item guardiansHammer = new Item("Guardian's Hammer",25,0,0,0,150,0,0); //ignoring lifesteal

    public static Item guardiansHorn = new Item("Guardian's Horn",0,0,0,0,150,0,0); //ignoring healing

    public static Item guardiansOrb = new Item("Guardian's Orb",0,50,0,0,150,0,0); //ignoring mana and healing

    public static Item relicShield = new Item("Relic Shield",0,5,0,0,30,0,0); //ignoring hp regen

    public static Item spectralSickle = new Item("Spectral Sickle",5,0,0,0,10,0,0); //ignoring mana regen

    public static Item spellthiefsEdge = new Item("Spellthief's Edge",0,8,0,0,10,0,0); //ignoring mana regen

    public static Item steelShoulderguards = new Item("Steel Shoulderguards",3,0,0,0,30,0,0); //ignoring hp regen

    public static int tearBonusMana = 0;
    public static Item tearOfTheGoddess = new Item("Tear of the Goddess",0,0,0,0,0,0,0) {
        void specialStats() {
            owner.MANA += tearBonusMana;
        }
    };

    public static Item amplifyingTome = new Item("Amplifying Tome",0,20,0,0,0,0,0);

    public static Item BFSword = new Item("B. F. Sword",40,0,0,0,0,0,0);

    public static Item blastingWand = new Item("Blasting Wand",0,40,0,0,0,0,0);

    public static Item cloakOfAgility = new Item("Cloak of Agility",0,0,0,0,0,0,0);

    public static Item clothArmor = new Item("Cloth Armor",0,0,0,0,0,15,0);

    public static Item dagger = new Item("Dagger",0,0,12,0,0,0,0);

    //farie charm

    public static Item longSword = new Item("Long Sword",10,0,0,0,0,0,0);

    public static Item needlesslyLargeRod = new Item("Needlessly Large Rod",0,60,0,0,0,0,0);

    public static Item nullMagicMantle = new Item("Null-Magic Mantle",0,0,0,0,0,0,25);

    public static Item pickaxe = new Item("Pickaxe",25,0,0,0,0,0,0);

    //rejuvenation bead

    public static Item rubyCrystal = new Item("Ruby Crystal",0,0,0,0,150,0,0);

    public static Item sapphireCrystal = new Item("Sapphire Crystal",0,0,0,0,0,0,0);

    public static Item sheen = new Item("Sheen",0,0,0,0,0,0,0) {
        public void onHit() {
            if (cs.time - lastUsed > item_cooldown) {
                if (owner.can_use_sheen) {
                    owner.can_use_sheen = false;
                    owner.lastSheenProc = cs.time;
                    lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, owner.BASE_AD, 1);
                }
            }
        }
    };

    //stopwatch


    public static Item aegisOfTheLegion = new Item("AegisOfTheLegion",0,0,0,10,0,30,30);

    public static Item aetherWisp = new Item("Aether Wisp",0,30,0,0,0,0,0); //ignoring ms

    public static Item bamisCinder = new Item("Bami's Cinder",0,0,0,0,300,0,0) {
        void extraDmg() {
            float ticks = cs.time - extraVariable; //extra variable is time
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                    (float) (ticks * (12 + 0.01 * owner.BONUS_HP)));
            extraVariable = cs.time;
        }
        public void onHit() {
            extraDmg();
        }
    };

    public static Item bandleglassMirror = new Item("Bandleglass Mirror",0,20,0,10,0,0,0); //ignoring mana regen

    public static Item blightingJewel = new Item("Blighting Jewel",0,25,0,0,0,0,0);

    public static Item brambleVest = new Item("Bramble Vest",0,0,0,0,0,30,0); //ignoring thorns and grievous

    public static Item catalystOfAeons = new Item("Catalyst of Aeons",0,0,0,0,225,0,0); //ignoring mana and hp regen

    public static Item caulfieldsWarhammer = new Item("Caulfield's Warhammer",25,0,0,10,0,0,0);

    public static Item chainVest = new Item("Chain Vest",0,0,0,0,0,40,0);

    public static Item crystallineBracer = new Item("Crystalline Bracer",0,0,0,0,200,0,0); //ignoring hp regen

    public static Item executionersCalling = new Item("Executioner's Calling",15,0,0,0,0,0,0); //ignoring grievous

    public static Item fiendishCodex = new Item("Fiendish Codex",0,35,0,10,0,0,0);

    //forbidden idol

    public static Item frostfang = new Item("Frostfang",0,15,0,0,70,0,0); //ignoring mana regen

    public static Item giantsBelt = new Item("Giant's Belt",0,0,0,0,350,0,0);

    public static Item glacialBuckler = new Item("Glacial Buckler",0,0,0,10,250,20,0);

    public static Item harrowingCrescent = new Item("Harrowing Crescent",10,0,0,0,60,0,0); //ignoring mana regen

    public static Item hearthboundAxe = new Item("Hearthbound Axe",15,0,15,0,0,0,0); //ignoring ms

    public static Item hexdrinker = new Item("Hexdrinker",25,0,0,0,0,0,35); //ignoring shield

    public static Item hextechAlternator = new Item("Hextech Alternator",0,25,0,0,150,0,0) {
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown*(1-owner.getItemCooldownModification());
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 50f + 75f / 17f * (owner.lvl -1));
            }
        }
    };

    public static Item ironspkieWhip = new Item("Ironspike Whip",30,0,0,0,0,0,0) {
        void extraDmg() {
            if (cs.time - lastUsed > item_cooldown) {
                lastUsed = cs.time - item_cooldown*(1 - 100/(100+owner.ITEM_HASTE + owner.AH));
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, owner.BASE_AD);
            }
        }
    };

    public static Item kindlegem = new Item("Kindlegem",0,0,0,10,200,0,0);

    public static Item kircheisShard = new Item("Kircheis Shard",0,0,15,0,0,0,0) {
        void specialStats() {
            extraVariable = 9;
        }
        void onHit() {
            ++extraVariable;
            if (extraVariable == 10) { //kinda arbitrary
                extraVariable = 0;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 80);
            }
        }
    };

    public static Item lastWhisper = new Item("Last Whisper",20,0,0,0,0,0,0);

    public static Item leechingLeer = new Item("Leeching Leer",0,20,0,0,250,0,0); //ignoring omnivamp

    public static Item lostChapter = new Item("Lost Chapter",0,40,0,10,0,0,0);

    public static Item negatronCloak = new Item("Negatron Cloak",0,0,0,0,0,0,50);

    public static Item noonquiver = new Item("Noonquiver",30,0,15,0,0,0,0); //ignoring minion onhit

    public static Item oblivionOrb = new Item("Oblivion Orb",0,30,0,0,0,0,0); //ignoring grievous

    public static Item phage = new Item("Phage",15,0,0,0,200,0,0); //ignoring healing

    public static Item quicksilverSash = new Item("Quicksilver Sash",0,0,0,0,0,0,30);

    public static Item rageknife = new Item("Rageknife",0,0,25,0,0,0,0) {
        void specialStats() {
            extraVariable = owner.CRIT_CHANCE;//item cooldown is amount of crit (from 0 to 100)
            owner.CRIT_CHANCE = 0;
        }

        public void onHit() {
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, extraVariable * 1.75f, 1);
        }
    };

    public static Item recurveBow = new Item("Recurve Bow",0,0,25,0,0,0,0) {
        void onHit() {
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 15, 1);
        }
    };

    public static Item runesteelSpaulders = new Item("Runesteel Spaulders",6,0,0,0,100,0,0); //ignoring hp regen

    public static int seekersArmguardStacks = 0; //every stack is .5 armor!
    public static Item seekersArmguard = new Item("Seeker's Armguard",0,30,0,0,0,15,0) {
        void specialStats() {
            owner.ARMOR += 0.5f * Math.min(seekersArmguardStacks, 30);
        }
    };

    public static Item serratedDirk = new Item("Serrated Dirk",30,0,0,0,0,0,0);

    public static Item spectresCowl = new Item("Spectre's Cowl",0,0,0,0,250,0,25); //ignoring hp regen

    public static Item targonsBuckler = new Item("Targon's Buckler",0,10,0,0,100,0,0); //ignoring hp regen

    public static Item tiamat = new Item("Tiamat",25,0,0,0,0,0,0); //ignoring cleave

    public static Item vampiricScepter = new Item("Vampiric Scepter",15,0,0,0,0,0,0); //ignoring lifesteal

    public static int verdantBarrierStacks = 0; //every stack is .3 mr!
    public static Item verantBarrier = new Item("Verdant Barrier",0,20,0,0,0,0,25) {
        void specialStats() {
            owner.MAGIC_RESIST += 0.3f * Math.min(verdantBarrierStacks, 30);
        }
    };

    public static Item wardensMail = new Item("Warden's Mail",0,0,0,0,0,40,0); //ignoring rock solid

    public static Item watchfulWardstone = new Item("Watchful Wardstone",0,0,0,10,150,0,0);

    public static Item wingedMoonplate = new Item("Winged Moonplate",0,0,0,0,150,0,0); //ignoring ms

    public static Item zeal = new Item("Zeal",0,0,18,0,0,0,0);


    public static void initializeComponents() {
        guardiansBlade.is_legendary = true;
        guardiansHammer.is_legendary = true;
        guardiansHorn.is_legendary = true;
        guardiansOrb.is_legendary = true;

        tearOfTheGoddess.mana = 240;
        cloakOfAgility.crit = 15;
        sapphireCrystal.mana = 250;

        sheen.item_cooldown = 1.5f;

        blightingJewel.percent_magic_pen = 13;
        catalystOfAeons.mana = 300;
        hextechAlternator.item_cooldown = 40;
        ironspkieWhip.item_cooldown = 20;
        lastWhisper.armor_pen = 18;
        lostChapter.mana = 300;
        serratedDirk.lethality = 10;
        zeal.crit = 15;
    }
}

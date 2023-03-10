package simulationManager.simulation;

import java.util.*;

/**
 * This class makes sure that an inventory fulfills all rules, which are:
 *  - Max item slots (hardcoded as 6, currently)
 *  - Only one copy of an item (as of now, only legendaries, mythics and boots implemented)
 *  - Only one mythic
 *  - Only one of each group of exclusive items (lifeline, sheen/spellblade, boots...)
 *      (in theory you can have more than one sheen item, but only the one with highest priority will work)
 * (Currently, runaans for melee champions is not checked)
 */
public class Inventory {
    private final int maxSize = 6;

    private final List<Item> inventory;

    private static final Item[][] exclusiveItemsArray = new Item[][] {
            new Item[] {MythicItems.immortalShieldbow, LegendaryItems.steraksCage, LegendaryItems.mawOfMalmortius, LegendaryItems.seraphsEmbrace, ComponentItems.hexdrinker}, //lifeline
            new Item[] {LegendaryItems.infinityEdge, LegendaryItems.navoriQuickblades, LegendaryItems.guinsoosRageblade, ComponentItems.rageknife}, //crit mod
            new Item[] {LegendaryItems.navoriQuickblades, LegendaryItems.spearOfShojin}, //cd mod
            new Item[] {MythicItems.trinityForce, MythicItems.divineSunderer, MythicItems.icebornGauntlet, LegendaryItems.essenceReaver, LegendaryItems.lichBane, ComponentItems.sheen}, //spellblade
            new Item[] {MythicItems.rodOfAges, LegendaryItems.abyssalMask, ComponentItems.catalystOfAeons}, //eternity
            new Item[] {LegendaryItems.fimbulwinter, LegendaryItems.muramana, LegendaryItems.seraphsEmbrace, ComponentItems.tearOfTheGoddess}, //mana charge
            new Item[] {LegendaryItems.lordDominiksRegards, LegendaryItems.seryldasGrudge, ComponentItems.lastWhisper}, //last whisper
            new Item[] {LegendaryItems.titanicHydra, LegendaryItems.ravenousHydra, ComponentItems.tiamat}, //hydra
            new Item[] {LegendaryItems.mercurialScimitar, LegendaryItems.silvermereDawn, ComponentItems.quicksilverSash}, //qicksilver
            new Item[] {Items.berserkers, Items.sorcerers, Items.lucidities}, //boots
            new Item[] {LegendaryItems.mejaisSoulstealer, ComponentItems.darkSeal}, //glory
            new Item[] {ComponentItems.guardiansOrb, ComponentItems.guardiansHorn, ComponentItems.guardiansBlade, ComponentItems.guardiansHammer}, //guardian
            new Item[] {LegendaryItems.sunfireAegis, ComponentItems.bamisCinder}, //immolate
            new Item[] {LegendaryItems.shardOfTrueIce, LegendaryItems.pauldronsOfWhiterock, LegendaryItems.blackMistScythe, LegendaryItems.bulwackOfTheMountain,
                        ComponentItems.frostfang, ComponentItems.runesteelSpaulders, ComponentItems.harrowingCrescent, ComponentItems.targonsBuckler,
                        ComponentItems.spellthiefsEdge, ComponentItems.steelShoulderguards, ComponentItems.spectralSickle, ComponentItems.relicShield}, //support
            new Item[] {ComponentItems.bandleglassMirror, ComponentItems.ironspkieWhip, ComponentItems.leechingLeer, ComponentItems.lostChapter, ComponentItems.noonquiver}, //mythic component
            new Item[] {LegendaryItems.vigilantWardstone, ComponentItems.watchfulWardstone}, //sightstone
            new Item[] {LegendaryItems.voidStaff, ComponentItems.blightingJewel} //void pen
    };

    /**
     * Constructor for the ArrayList, since it's more comfortable to declare it first as an array
     */
    private static ArrayList<Item>[] exclusiveItems;
    private static void fillExclusiveItems() {
        exclusiveItems = new ArrayList[exclusiveItemsArray.length];
        for (int i = 0; i < exclusiveItemsArray.length; i++) {
            exclusiveItems[i] = new ArrayList<>();
            for (int j = 0; j < exclusiveItemsArray[i].length; j++) {
                exclusiveItems[i].add(exclusiveItemsArray[i][j]);
            }
        }
    }
    private boolean[] exclusiveItemsUsed;
    private boolean hasMythic;

    private boolean canAdd(Item item) {
        if (item.is_rune) return false;
        if (inventory.size() == maxSize) return false;
        if (item.is_legendary && inventory.contains(item)) return false;
        if (item.is_mythic && hasMythic) return false;
        for (int i = 0; i < exclusiveItems.length; ++i)
            if (exclusiveItems[i].contains(item) && exclusiveItemsUsed[i]) return false;
        return true;
    }

    /**
     * Will return true if item was successfully added, false otherwise
     */
    public boolean add(Item item) {
        if (!canAdd(item)) return false;

        inventory.add(item);
        for (int i = 0; i < exclusiveItems.length; ++i)
            if (exclusiveItems[i].contains(item)) exclusiveItemsUsed[i] = true;
        if (item.is_mythic) hasMythic = true;
        return true;
    }

    /**
     * Will return true if ALL items were successfully added, false otherwise (and no item will be added)
     */
    public boolean addAll(List<Item> items) {
        if (items.size() + inventory.size() > maxSize) return false;
        for (int i = 0; i < items.size(); ++i) {
            if (!add(items.get(i))) {
                for (int j = 0; j < i; ++j) remove(items.get(j));
                return false;
            }
        }
        return true;
    }
    public boolean addAll(Item[] items) {
        return addAll(List.of(items));
    }

    /**
     * Will return true if it managed to remove the item, false if the item wasn't in the inventory
     */
    public boolean remove(Item item) {
        if (!inventory.contains(item)) return false;

        inventory.remove(item);
        for (int i = 0; i < exclusiveItems.length; ++i)
            if (exclusiveItems[i].contains(item)) exclusiveItemsUsed[i] = false;
        if (item.is_mythic) hasMythic = false;
        return true;
    }

    public void clear() {
        inventory.clear();
        exclusiveItemsUsed = new boolean[exclusiveItems.length];
        hasMythic = false;
    }


    public ArrayList<Item> getItems() {
        return new ArrayList<>(inventory);
    }


    public Inventory() {
        Items.initializeItems();
        inventory = new ArrayList<>();
        if (exclusiveItems == null) fillExclusiveItems();
        exclusiveItemsUsed = new boolean[exclusiveItems.length];
        hasMythic = false;
    }

    /**
     * To create a copy of an inventory so it can be changed leaving the original intact
     */
    public Inventory(Inventory i) {
        inventory = new ArrayList<>(i.inventory);

        exclusiveItemsUsed = Arrays.copyOf(i.exclusiveItemsUsed, i.exclusiveItemsUsed.length);
        hasMythic = i.hasMythic;
    }
}

package com.damagecalculator.simulationManager.simulation;

import java.util.*;
import static com.damagecalculator.simulationManager.simulation.items.ItemList.*;

/**
 * This class makes sure that an inventory fulfills all rules, which are:
 *  - Max item slots (hardcoded as 6, currently)
 *  - Only one copy of an item (as of now, only legendaries and boots implemented)
 *  - Only one of each group of exclusive items (lifeline, sheen/spellblade, boots...)
 *      (in theory you can have more than one sheen item, but only the one with highest priority will work)
 * (Currently, runaans for melee champions is not checked)
 */
public class Inventory {
    public static final int maxSize = 6;

    private final List<Item> inventory;
    private int cost;

    private static final Item[][] exclusiveItemsArray = new Item[][] {
            new Item[] {seekersArmguard, zhonyasHourglass}, //stasis component
            new Item[] {verdantBarrier, bansheesVeil}, //annul
            new Item[] {immortalShieldbow, steraksGage, mawOfMalmortius, seraphsEmbrace, hexdrinker}, //lifeline
            new Item[] {trinityForce, icebornGauntlet, essenceReaver, lichBane, sheen}, //spellblade
            new Item[] {fimbulwinter, muramana, seraphsEmbrace, tearOfTheGoddess}, //mana charge
            new Item[] {lordDominiksRegards, seryldasGrudge, lastWhisper, mortalReminder, blackCleaver, terminus}, //armor pen
            new Item[] {titanicHydra, ravenousHydra, profaneHydra, tiamat}, //hydra
            new Item[] {mercurialScimitar, quicksilverSash}, //qicksilver
            new Item[] {berserkersGreaves, sorcerersShoes, ionianBootsOfLucidity, platedSteelcaps, mercurysTreads,
                            symbioticSoles, synchronizedSouls, bootsOfSwiftness, boots}, //boots
            new Item[] {mejaisSoulstealer, darkSeal}, //glory
            new Item[] {guardiansOrb, guardiansHorn, guardiansBlade, guardiansHammer}, //guardian
            new Item[] {sunfireAegis, bamisCinder, hollowRadiance}, //immolate
            new Item[] {worldAtlas, runicCompass, bountyOfWorlds, celestialOpposition, solsticeSleigh,
                            bloodsong, dreamMaker, zazZaksRealmspike}, //support
            new Item[] {vigilantWardstone, watchfulWardstone}, //sightstone
            new Item[] {voidStaff, blightingJewel, terminus, cryptbloom, bloodlettersCurse}, //void pen
            new Item[] {doransBlade, doransRing, doransShield}, //doran's items
            new Item[] {serratedDirk}, //unique
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
    public boolean canAdd(Item item) {
        if (inventory.size() == maxSize) return false;
        if (item.type == ItemType.LEGENDARY && inventory.contains(item)) return false;
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
        cost += item.cost;
        for (int i = 0; i < exclusiveItems.length; ++i)
            if (exclusiveItems[i].contains(item)) exclusiveItemsUsed[i] = true;
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
                //System.out.println("Invalid build!");
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
        cost -= item.cost;
        for (int i = 0; i < exclusiveItems.length; ++i)
            if (exclusiveItems[i].contains(item)) exclusiveItemsUsed[i] = false;
        return true;
    }

    public void clear() {
        inventory.clear();
        cost = 0;
        exclusiveItemsUsed = new boolean[exclusiveItems.length];
    }


    public ArrayList<Item> getItems() {
        return new ArrayList<>(inventory);
    }
    public int getSize() {
        return inventory.size();
    }
    public int getCost() {
        return cost;
    }

    public void sort() {
        inventory.sort((o1, o2) -> o1.cost < o2.cost ? 1 : -1);
    }


    @Override
    public int hashCode() {
        int res = 0;
        for (Item i : inventory) res += i.name.hashCode();
        return res;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Inventory)) return false;
        return o.hashCode() == this.hashCode();
    }


    public Inventory() {
        inventory = new ArrayList<>();
        cost = 0;
        if (exclusiveItems == null) fillExclusiveItems();
        exclusiveItemsUsed = new boolean[exclusiveItems.length];
    }

    /**
     * To create a copy of an inventory so it can be changed leaving the original intact
     */
    public Inventory(Inventory i) {
        inventory = new ArrayList<>();
        for (Item item : i.inventory) inventory.add(item.makeCopy());
        cost = i.cost;

        exclusiveItemsUsed = Arrays.copyOf(i.exclusiveItemsUsed, i.exclusiveItemsUsed.length);
    }
}

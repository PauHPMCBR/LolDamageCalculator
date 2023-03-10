package simulationManager.simulation;

import java.util.Vector;

/**
 * List of all implemented items and runes and implementation of boots
 */
public class Items {
    public static Vector<Item> boots = new Vector<>();
    public static Vector<Item> mythics = new Vector<>();
    public static Vector<Item> legendaries = new Vector<>();
    public static Vector<Item> runes = new Vector<>();


    public static Item berserkers = new Item("Berserker's Greaves", 0, 0, 35, 0, 0, 0, 0);
    public static Item lucidities = new Item("Ionian Boots of Lucidity", 0, 0, 0, 20, 0, 0, 0);
    public static Item sorcerers = new Item("Sorcerer's Shoes", 0, 0, 0, 0, 0, 0, 0);


    static void initializeBoots() {
        boots.add(berserkers);

        boots.add(lucidities);

        sorcerers.magic_pen = 18;
        boots.add(sorcerers);
    }

    private static boolean initialized = false;
    public static void initializeItems() {
        if (initialized) return;
        initialized = true;

        Items.initializeBoots();
        MythicItems.initializeMythics();
        LegendaryItems.initializeLegendaries();
        ComponentItems.initializeComponents();
        OtherItems.initializeOthers();
        Runes.initializeRunes();
    }
}

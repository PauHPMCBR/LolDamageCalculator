package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class ShureliasBattlesong extends Item {
    public static final String name = "Shurelia's Battlesong";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 2500;

    public ShureliasBattlesong() {
        super(name, type, cost);
        ap = 40;
        ah = 20;
        hp = 200;
        manaRegen = 100;
    }

    public void applyMythicPassive() {
        owner.AH += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new ShureliasBattlesong();
    }
}

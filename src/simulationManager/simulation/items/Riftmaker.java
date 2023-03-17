package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Riftmaker extends Item {
    public static final String name = "Riftmaker";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 3200;

    public Riftmaker() {
        super(name, type, cost);
        ap = 70;
        ah = 15;
        hp = 300;
        omnivamp = 7;
    }

    public void specialStats() {
        cs.riftmakerItem = this;
    }

    public void applyMythicPassive() {
        owner.AP += 8 * owner.legendary_items_carried;
        //ignoring +2 omnivamp
    }

    @Override
    public Item makeCopy() {
        return new Riftmaker();
    }
}

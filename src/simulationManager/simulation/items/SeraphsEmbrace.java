package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class SeraphsEmbrace extends Item {
    public static final String name = "Seraph's Embrace";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public SeraphsEmbrace() {
        super(name, type, cost);
        ap = 70;
        ah = 10;
        hp = 200;
        mana = 860;
    }

    public void specialStats() { //ignoring shield
        owner.AP += 0.025 * owner.MANA;
    }

    @Override
    public Item makeCopy() {
        return new SeraphsEmbrace();
    }
}

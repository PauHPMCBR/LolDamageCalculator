
package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class LostChapter extends Item {
    public static final String name = "Lost Chapter";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1300;

    public LostChapter() {
        super(name, type, cost);
        ap = 40;
        ah = 10;
        mana = 300;
    }

    @Override
    public Item makeCopy() {
        return new LostChapter();
    }
}

package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class DarkSeal extends Item {
    public static final String name = "Dark Seal";
    public static final ItemType type = ItemType.starter;
    public static final int cost = 350;

    int stacks;

    public DarkSeal(int stacks) {
        super(name, type, cost);
        ap = 15;
        hp = 40;

        this.stacks = stacks;
    }

    public void specialStats() {
        owner.AP += 4 * Math.min(stacks, 10);
    }

    @Override
    public Item makeCopy() {
        return new DarkSeal(stacks);
    }
}

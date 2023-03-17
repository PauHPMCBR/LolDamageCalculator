package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class VerdantBarrier extends Item {
    public static final String name = "Verdant Barrier";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1000;

    int stacks;

    public VerdantBarrier(int stacks) {
        super(name, type, cost);
        ap = 20;
        mr = 25;

        this.stacks = stacks;
    }

    public void specialStats() {
        owner.MAGIC_RESIST += 0.3f * Math.min(stacks, 30);
    }

    @Override
    public Item makeCopy() {
        return new VerdantBarrier(stacks);
    }
}

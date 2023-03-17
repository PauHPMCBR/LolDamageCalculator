package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class SeekersArmguard extends Item {
    public static final String name = "Seeker's Armguard";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1000;

    int stacks;

    public SeekersArmguard(int stacks) {
        super(name, type, cost);
        ap = 30;
        armor = 15;

        this.stacks = stacks;
    }

    public void specialStats() {
        owner.ARMOR += 0.5f * Math.min(stacks, 30);
    }

    @Override
    public Item makeCopy() {
        return new SeekersArmguard(stacks);
    }
}

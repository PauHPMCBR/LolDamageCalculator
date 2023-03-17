package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class MejaisSoulstealer extends Item {
    public static final String name = "Mejai's Soulstealer";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 1600;

    int stacks;

    public MejaisSoulstealer(int stacks) {
        super(name, type, cost);
        ap = 20;
        hp = 100;

        this.stacks = stacks;
    }

    public void specialStats() {
        owner.AP += 5 * stacks; //extraVariable are mejai's stacks
    }

    @Override
    public Item makeCopy() {
        return new MejaisSoulstealer(stacks);
    }
}

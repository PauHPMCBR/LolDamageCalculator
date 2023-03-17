package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class TearOfTheGoddess extends Item {
    public static final String name = "Tear of the Goddess";
    public static final ItemType type = ItemType.starter;
    public static final int cost = 400;

    int manaStacks;

    public TearOfTheGoddess(int manaStacks) {
        super(name, type, cost);
        this.manaStacks = manaStacks;
        mana = 240 + manaStacks;
    }

    @Override
    public Item makeCopy() {
        return new TearOfTheGoddess(manaStacks);
    }
}

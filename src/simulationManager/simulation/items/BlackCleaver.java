package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class BlackCleaver extends Item {
    public static final String name = "Black Cleaver";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3100;

    int carveStacks;

    public BlackCleaver() {
        super(name, type, cost);
        ad = 50;
        hp = 400;
        ah = 30;

        carveStacks = 0;
    }

    public void increaseCarveStacks() { //will get called by "Damage" class. special function
        if (carveStacks >= 6) return;
        owner.getEnemy().ARMOR /= (1 - carveStacks*0.05);
        ++carveStacks;
        owner.getEnemy().ARMOR *= (1 - carveStacks*0.05);
    }

    @Override
    public Item makeCopy() {
        return new BlackCleaver();
    }
}

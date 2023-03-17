package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class RavenousHydra extends Item {
    public static final String name = "Ravenous Hydra";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3400;

    int ravenousStacks;

    public RavenousHydra(int ravenousStacks) {
        super(name, type, cost);
        ad = 65;
        ah = 25;
        lifesteal = 10;

        this.ravenousStacks = ravenousStacks;
    }

    public void specialStats() {
        owner.BONUS_AD += ravenousStacks*0.5;
    }

    @Override
    public Item makeCopy() {
        return new RavenousHydra(ravenousStacks);
    }
}

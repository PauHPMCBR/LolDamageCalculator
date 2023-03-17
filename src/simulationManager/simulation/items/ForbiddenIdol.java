package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class ForbiddenIdol extends Item {
    public static final String name = "Forbidden Idol";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 800;

    public ForbiddenIdol() {
        super(name, type, cost);
        manaRegen = 50;
    }

    @Override
    public Item makeCopy() {
        return new ForbiddenIdol();
    }
}

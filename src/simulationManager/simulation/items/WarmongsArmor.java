package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class WarmongsArmor extends Item {
    public static final String name = "Warmong's Armor";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public WarmongsArmor() {
        super(name, type, cost);
        ah = 10;
        hp = 800;
        hpRegen = 200;
    }

    @Override
    public Item makeCopy() {
        return new WarmongsArmor();
    }
}

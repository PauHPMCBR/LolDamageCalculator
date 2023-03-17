package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class ClothArmor extends Item {
    public static final String name = "Cloth Armor";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 300;

    public ClothArmor() {
        super(name, type, cost);
        armor = 15;
    }

    @Override
    public Item makeCopy() {
        return new ClothArmor();
    }
}

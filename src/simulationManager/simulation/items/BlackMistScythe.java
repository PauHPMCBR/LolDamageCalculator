package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class BlackMistScythe extends Item {
    public static final String name = "Black Mist Scythe";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 400; //but it's upgrade

    public BlackMistScythe() {
        super(name, type, cost);
        ad = 20;
        hp = 75;
        manaRegen = 100;
    }

    @Override
    public Item makeCopy() {
        return new BlackMistScythe();
    }
}

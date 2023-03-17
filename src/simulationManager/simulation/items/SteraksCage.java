package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class SteraksCage extends Item {
    public static final String name = "Sterak's Cage";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3100;

    public SteraksCage() {
        super(name, type, cost);
        hp = 400;
    }

    public void specialStats() {
        owner.BONUS_AD += 0.5 * owner.BASE_AD;
    }

    @Override
    public Item makeCopy() {
        return new SteraksCage();
    }
}

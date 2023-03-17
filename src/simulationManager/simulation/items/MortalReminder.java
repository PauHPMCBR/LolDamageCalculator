package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class MortalReminder extends Item {
    public static final String name = "Mortal Reminder";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public MortalReminder() {
        super(name, type, cost);
        ad = 35;
        crit = 20;
        armor_pen = 30;
    }

    @Override
    public Item makeCopy() {
        return new MortalReminder();
    }
}

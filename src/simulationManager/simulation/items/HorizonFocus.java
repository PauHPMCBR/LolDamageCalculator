package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class HorizonFocus extends Item {
    public static final String name = "Horizon Focus";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public HorizonFocus() {
        super(name, type, cost);
        ap = 100;
        ah = 15;
        hp = 150;
    }

    public void specialStats() { //supposing 700+ range?
        cs.damageMultiplier *= 1.1;
    }

    @Override
    public Item makeCopy() {
        return new HorizonFocus();
    }
}

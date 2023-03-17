package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class LordDominiksRegards extends Item {
    public static final String name = "Lord Dominik's Regards";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public LordDominiksRegards() {
        super(name, type, cost);
        ad = 35;
        crit = 20;
        armor_pen = 30;
    }

    public void specialStats() {
        float diff = owner.getEnemy().getMaxHP() - owner.getMaxHP();
        diff = Math.min(2500, Math.max(0, diff));
        cs.ldrPercent = 1 + diff/10000; //should be correct
    }

    @Override
    public Item makeCopy() {
        return new LordDominiksRegards();
    }
}

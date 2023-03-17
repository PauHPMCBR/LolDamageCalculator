package simulationManager.simulation.items;
import simulationManager.simulation.*;

public class Empty extends Item {
    public static final String name = "";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 0;

    public Empty() {
        super(name, type, cost);
    }

    @Override
    public Item makeCopy() {
        return new Empty();
    }
}

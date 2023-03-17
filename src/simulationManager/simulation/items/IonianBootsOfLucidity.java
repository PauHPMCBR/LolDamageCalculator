package simulationManager.simulation.items;
import simulationManager.simulation.*;

public class IonianBootsOfLucidity extends Item {
    public static final String name = "Ionian Boots of Lucidity";
    public static final ItemType type = ItemType.boots;
    public static final int cost = 950;

    public IonianBootsOfLucidity() {
        super(name, type, cost);
        ah = 20;
    }

    @Override
    public Item makeCopy() {
        return new IonianBootsOfLucidity();
    }
}

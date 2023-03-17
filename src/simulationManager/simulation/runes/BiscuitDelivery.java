package simulationManager.simulation.runes;

import simulationManager.simulation.Rune;
import simulationManager.simulation.RunePath;

public class BiscuitDelivery extends Rune {
    public static final String name = "Biscuit Delivery";
    public static final RunePath path = RunePath.inspiration;
    public static final int column = 2;
    public static final int row = 2;

    int biscuits;

    public BiscuitDelivery(int biscuits) {
        super(name, path, column, row);

        this.biscuits = Math.min(biscuits, 3);
    }

    public void specialStats() {
        owner.MANA += 40 * biscuits;
    }

    @Override
    public Rune makeCopy() {
        return new BiscuitDelivery(biscuits);
    }
}

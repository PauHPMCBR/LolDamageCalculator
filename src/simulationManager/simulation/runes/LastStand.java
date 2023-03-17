package simulationManager.simulation.runes;

import simulationManager.simulation.Rune;
import simulationManager.simulation.RunePath;

public class LastStand extends Rune {
    public static final String name = "Last Stand";
    public static final RunePath path = RunePath.precision;
    public static final int column = 3;
    public static final int row = 2;

    public LastStand() {
        super(name, path, column, row);
    }

    public void specialStats() {
        cs.hasLastStand = true;
    }

    @Override
    public Rune makeCopy() {
        return new LastStand();
    }
}

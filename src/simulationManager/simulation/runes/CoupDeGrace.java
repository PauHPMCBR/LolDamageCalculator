package simulationManager.simulation.runes;

import simulationManager.simulation.Rune;
import simulationManager.simulation.RunePath;

public class CoupDeGrace extends Rune {
    public static final String name = "Coup de Grace";
    public static final RunePath path = RunePath.precision;
    public static final int column = 3;
    public static final int row = 0;

    public CoupDeGrace() {
        super(name, path, column, row);
    }

    public void specialStats() {
        cs.hasCoupDeGrace = true;
    }

    @Override
    public Rune makeCopy() {
        return new CoupDeGrace();
    }
}

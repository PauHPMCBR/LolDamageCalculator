package simulationManager.simulation.runes;

import simulationManager.simulation.*;

public class Empty extends Rune {
    public static final String name = "";
    public static final RunePath path = RunePath.precision;
    public static final int column = 0;
    public static final int row = 0;

    public Empty() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new Empty();
    }
}
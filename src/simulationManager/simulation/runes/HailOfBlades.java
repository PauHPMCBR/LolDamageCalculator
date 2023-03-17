package simulationManager.simulation.runes;

import simulationManager.simulation.Rune;
import simulationManager.simulation.RunePath;

public class HailOfBlades extends Rune {
    public static final String name = "Hail of Blades";
    public static final RunePath path = RunePath.domination;
    public static final int column = 0;
    public static final int row = 3;

    int autos;
    float prevMaxAs;

    public HailOfBlades() {
        super(name, path, column, row);
        rune_cooldown = 12;

        autos = 0;
    }

    public void onHit() { //gives as AFTER the 1st auto
        if (canUse()) {
            if (autos == 0) {
                cs.champion.BONUS_AS += 110;
                prevMaxAs = cs.champion.max_as;
                cs.champion.max_as = 90;
            }
            ++autos;
            if (autos == 3) {
                putOnCooldown();
                cs.champion.max_as = prevMaxAs;
                cs.champion.BONUS_AS -= 110;
            }
        }
    }

    @Override
    public Rune makeCopy() {
        return new HailOfBlades();
    }
}
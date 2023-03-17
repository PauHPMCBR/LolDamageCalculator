package simulationManager.simulation.runes;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Rune;
import simulationManager.simulation.RunePath;

public class Scorch extends Rune {
    public static final String name = "Scorch";
    public static final RunePath path = RunePath.sorcery;
    public static final int column = 3;
    public static final int row = 0;

    public Scorch() {
        super(name, path, column, row);
        rune_cooldown = 10;
    }

    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 20 + 20f/17*(cs.champion.lvl-1));
        }
    }

    @Override
    public Rune makeCopy() {
        return new Scorch();
    }
}
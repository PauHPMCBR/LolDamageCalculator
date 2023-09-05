package com.damagecalculator.simulationManager;

import com.damagecalculator.GlobalVariables;
import com.damagecalculator.simulationManager.simulation.*;

public class SimulationManager {
    CurrentState cs;
    DamageCalculator dmgCalc;

    public Printer printer = new Printer();

    /**
     * Set both of these before starting a simulation. Keep in mind a COPY is created, so any modifications on the original will make no effect
     */
    public void setChampion(Champion c) {
        cs.champion = c.makeCopyWithoutReset();
    }
    public void setEnemy(Champion c) {
        cs.enemy = c.makeCopyWithoutReset();
    }

    int startingHP, startingArmor, startingMR; //used for dummy stats printing, as some effects may alter its stats (like abyssal mask or black cleaver)
    private void startSimulation() {
        dmgCalc = new DamageCalculator(cs);

        cs.startSimulation();
        startingHP = (int) cs.enemy.getMaxHP();
        startingArmor = (int) cs.enemy.ARMOR;
        startingMR = (int) cs.enemy.MAGIC_RESIST;
    }

    /**
     * Function that will call the damage calculator and print the results if asked for. Default print option is true
     */
    public float simulateCombo(AbilityType[] combo, boolean print) {
        startSimulation();
        dmgCalc.applyCombo(combo);

        if (print) {
            printer.printChampionStats(cs.champion, true, false);
            printer.printDummyStats(cs.enemy.name, startingHP, startingArmor, startingMR);
            printer.printSeparator();
            if (dmgCalc.hasBurnItem && GlobalVariables.extraBurnTime > 0)
                printer.println("Took " + cs.time + " seconds to execute the combo, including "
                        + GlobalVariables.extraBurnTime + " seconds of item burn time.");
            else
                printer.println("Took " + cs.time + " seconds to execute the combo.");
            printer.printSeparator();
            printer.printDamages(cs.champion, cs.damage);

        }

        return cs.damage.getTotalDamage();
    }
    public float simulateCombo(AbilityType[] combo) {
        return simulateCombo(combo, true);
    }

    /**
     * Function that will call the damage calculator and print the results if asked for. Default print option is true
     */
    public boolean useAutosBetweenAbilities = false;
    public float simulateDps(AbilityType[] abilityPriorities, boolean print) {
        startSimulation();

        dmgCalc.useAutosBetweenAbilities = useAutosBetweenAbilities;
        float time = dmgCalc.timeToKill(abilityPriorities, false);

        if (print) {
            printer.printChampionStats(cs.champion, true, false);
            printer.printDummyStats(cs.enemy.name, startingHP, startingArmor, startingMR);
            printer.printSeparator();
            printer.println("Took " + time + " seconds to kill.");
            AbilityType[] combo = dmgCalc.comboDone.toArray(new AbilityType[0]);
            printer.printCombo(combo);
            printer.printSeparator();
            printer.printDamages(cs.champion, cs.damage);
        }

        if (GlobalVariables.DpsInsteadOfTime) return cs.damage.getTotalDamage() / time;
        else return time;
    }
    public float simulateDps(AbilityType[] abilityPriorities) {
        return simulateDps(abilityPriorities, true);
    }

    public SimulationManager() {
        cs = new CurrentState();
    }
}

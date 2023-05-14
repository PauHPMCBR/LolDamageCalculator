package com.damagecalculator.simulationManager;

import com.damagecalculator.simulationManager.simulation.*;

import java.util.*;

/**
 * Class to facilitate printing results and stats
 */
public class Printer {

    public void print(String s) {
        System.out.print(s);
    }
    public void println(String s) {
        print(s + '\n');
    }
    public void println() {
        print("\n");
    }

    public void printSeparator() {
        println("---------------------------------------------");
    }

    /**
     * Prints the damage dealt by everything (abilities, items, runes...)
     */
    public void printDamages(Champion champion, Damage damage) {
        println("Total damage: " + (int)damage.getTotalDamage() + "   (" + (int)damage.totalPhysical +
                " physical, " + (int)damage.totalMagic + " magic, " + (int)damage.totalTrue + " true)");
        printSeparator();
        if (champion.getAutoDmg() != 0) println(champion.getAutoDmg() + " from autos");
        if (champion.passive.getDamageDealt() != 0) println(champion.passive.getDamageDealt() + " from passive");
        if (champion.q.getDamageDealt() != 0) println(champion.q.getDamageDealt() + " from Q");
        if (champion.w.getDamageDealt() != 0) println(champion.w.getDamageDealt() + " from W");
        if (champion.e.getDamageDealt() != 0) println(champion.e.getDamageDealt() + " from E");
        if (champion.r.getDamageDealt() != 0) println(champion.r.getDamageDealt() + " from R");

        for (Item i : champion.getInventory().getItems()) {
            if (i.is_hidden || i.getDamageDealt()== 0) continue;
            println(i.getDamageDealt() + " from " + i.name);
        }
        for (Rune i : champion.getRunes()) {
            if (i.is_hidden || i.getDamageDealt() == 0) continue;
            println(i.getDamageDealt() + " from " + i.name);
        }
    }

    /**
     * Prints a sequence of abilities, and the total amount of each used in total
     */
    public void printCombo(AbilityType[] combo) {
        print("Combo used:");
        int p = 0, q = 0, w = 0, e = 0, r = 0, a = 0;
        for (AbilityType i : combo) {
            print(" ");
            switch (i) {
                case PASSIVE -> {print("P"); ++p;}
                case Q -> {print("Q"); ++q;}
                case W -> {print("W"); ++w;}
                case E -> {print("E"); ++e;}
                case R -> {print("R"); ++r;}
                case AUTO -> {print("A"); ++a;}
            }
        }
        println("  (" + a + "A " + q + "Q " + w + "W " + e + "E " + r + "R)");
    }

    /**
     * Will only print the 3 dummy stats (HP, Armor, MR)
     */
    public void printDummyStats(String name, int hp, int ar, int mr) {
        println("Enemy (" + name + ") stats: " + hp + " HP, " +
                ar + " armor and " + mr + " magic resist.");
    }

    /**
     * Prints all champion's basic stats and its build and runes, if specified
     */
    public void printChampionStats(Champion champion, boolean printBuild, boolean printRunes) {
        println("Champion (" + champion.name + ") stats: Level " + champion.lvl + ", "
                + (int)champion.getAD() + " tAD, " + (int)champion.AP + " AP, "
                + (int)champion.getMaxHP() + " HP, " + (int)champion.ARMOR + " armor, "
                + (int)champion.MAGIC_RESIST + " MR, " + (int)champion.BONUS_AS + " bAS, " +
                (int)champion.AH + " AH");

        if (printBuild) {
            print("  Build: ");
            boolean first1 = true;
            for (Item i : champion.getInventory().getItems()) {
                if (first1) first1 = false;
                else print(", ");
                print(i.name);
            }
            println();
        }

        if (printRunes) {
            print("  Runes:");
            boolean first1 = true;
            for (Rune i : champion.getRunes()) {
                if (first1) first1 = false;
                else print(", ");
                print(i.name);
            }
        }
    }

    public void printTimeTaken(float time) {
        println("Took " + time + " seconds to kill.");
    }

    /**
     * Used to print the list of builds and their scores
     */
    public void printScores(List<BuildTester.BuildScore> buildScores, int amount) {
        println();
        for (int i = 0; i < Math.min(amount, buildScores.size()); ++i) {
            String prov = String.format("%5f", buildScores.get(i).score);
            print(String.format("%4s%6s%1s", i+1 + ".", " (" + prov + ")", " "));
            boolean first = true;
            for (Item item : buildScores.get(i).build) {
                if (first) first = false;
                else print(",");
                print(" " + item.name);
            }
            println("  (" + buildScores.get(i).cost + "g)");
        }
        println();
    }

}

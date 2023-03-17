package simulationManager;

import simulationManager.simulation.*;

import java.util.*;

/**
 * Class to facilitate printing results and stats
 */
public class Printer {
    public static void printSeparator() {
        System.out.println("---------------------------------------------");
    }

    /**
     * Prints the damage dealt by everything (abilities, items, runes...)
     */
    public static void printDamages(Champion champion, Damage damage) {
        System.out.println("Total damage: " + damage.getTotalDamage() + "   (" + damage.totalPhysical +
                " physical, " + damage.totalMagic + " magic, " + damage.totalTrue + " true)");
        printSeparator();
        if (champion.autoDmg != 0) System.out.println(champion.autoDmg + " from autos");
        if (champion.Passive.getDamageDealt() != 0) System.out.println(champion.Passive.getDamageDealt() + " from passive");
        if (champion.Q.getDamageDealt() != 0) System.out.println(champion.Q.getDamageDealt() + " from Q");
        if (champion.W.getDamageDealt() != 0) System.out.println(champion.W.getDamageDealt() + " from W");
        if (champion.E.getDamageDealt() != 0) System.out.println(champion.E.getDamageDealt() + " from E");
        if (champion.R.getDamageDealt() != 0) System.out.println(champion.R.getDamageDealt() + " from R");

        for (Item i : champion.getInventory().getItems()) {
            if (i.is_hidden || i.damageDealt== 0) continue;
            System.out.println(i.damageDealt + " from " + i.name);
        }
        for (Rune i : champion.getRunes()) {
            if (i.is_hidden || i.damageDealt == 0) continue;
            System.out.println(i.damageDealt + " from " + i.name);
        }
    }

    /**
     * Prints a sequence of abilities, and the total amount of each used in total
     */
    public static void printCombo(AbilityType[] combo) {
        System.out.print("Combo used:");
        int p = 0, q = 0, w = 0, e = 0, r = 0, a = 0;
        for (AbilityType i : combo) {
            System.out.print(' ');
            switch (i) {
                case passive -> {System.out.print('P'); ++p;}
                case q -> {System.out.print('Q'); ++q;}
                case w -> {System.out.print('W'); ++w;}
                case e -> {System.out.print('E'); ++e;}
                case r -> {System.out.print('R'); ++r;}
                case auto -> {System.out.print('A'); ++a;}
            }
        }
        System.out.println("  (" + a + "A " + q + "Q " + w + "W " + e + "E " + r + "R)");
    }

    /**
     * Will only print the 3 dummy stats (HP, Armor, MR)
     */
    public static void printDummyStats(String name, int hp, int ar, int mr) {
        System.out.println("Enemy (" + name + ") stats: " + hp + " HP, " +
                ar + " armor and " + mr + " magic resist.");
    }

    /**
     * Prints all champion's basic stats and its build and runes, if specified
     */
    public static void printChampionStats(Champion champion, boolean printBuild, boolean printRunes) {
        System.out.println("Champion (" + champion.name + ") stats: Level " + champion.lvl + ", "
                + (int)champion.getAD() + " tAD, " + (int)champion.AP + " AP, "
                + (int)champion.getMaxHP() + " HP, " + (int)champion.ARMOR + " armor, "
                + (int)champion.MAGIC_RESIST + " MR, " + (int)champion.BONUS_AS + " bAS, " +
                (int)champion.AH + " AH");

        if (printBuild) {
            System.out.print("  Build: ");
            boolean first1 = true;
            for (Item i : champion.getInventory().getItems()) {
                if (first1) first1 = false;
                else System.out.print(", ");
                System.out.print(i.name);
            }
            System.out.println();
        }

        if (printRunes) {
            System.out.print("  Runes:");
            boolean first1 = true;
            for (Rune i : champion.getRunes()) {
                if (first1) first1 = false;
                else System.out.print(", ");
                System.out.print(i.name);
            }
        }
    }

    public static void printTimeTaken(float time) {
        System.out.println("Took " + time + " seconds to kill.");
    }

    /**
     * Used to print the list of builds and their scores
     */
    public static void printScores(List<BuildTester.BuildScore> buildScores, int amount) {
        for (int i = 0; i < Math.min(amount, buildScores.size()); ++i) {
            System.out.format("%4s%12s%1s", i+1 + ".", " (" + buildScores.get(i).score + ")", " ");
            boolean first = true;
            int totalCost = 0;
            for (Item item : buildScores.get(i).build) {
                if (first) first = false;
                else System.out.print(",");
                System.out.print(" " + item.name);
                totalCost += item.cost;
            }
            System.out.println("  (" + totalCost + "g)");
        }
        System.out.println();
    }

}

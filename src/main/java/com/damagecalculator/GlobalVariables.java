package com.damagecalculator;

public class GlobalVariables {
    //builds displayed in build tester results
    public static int displayedBuilds = 15;

    //time after the combo is done that burn will be ticked. Liandry (for now), from 0 to 3
    public static float extraBurnTime = 3;

    //wait for extra damage for dmg amp, or do it right away
    public static boolean waitForExtraDamage = false;

    //maximum 3D graphs to be displayed at once, from 1 to 6
    public static int max3DGraphs = 4;

    //maximum 2D graphs to be displayed at once, from 1 to 6
    public static int max2DGraphs = 4;

    //display aram items in item list
    public static boolean displayAramItems = false;

    //show results as "DPS" instead of "Time taken to kill"
    public static boolean DpsInsteadOfTime = false;
}

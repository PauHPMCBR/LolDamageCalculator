package com.damagecalculator;

import com.damagecalculator.displays.ExtraVariablesDisplay;
import com.damagecalculator.displays.InventoryDisplay;
import com.damagecalculator.displays.RunePageDisplay;
import com.damagecalculator.simulationManager.simulation.Champion;
import com.damagecalculator.simulationManager.simulation.Item;

import java.io.*;
import java.util.ArrayList;

public class Config {
    Champion champion;
    InventoryDisplay inventoryDisplay;
    RunePageDisplay runePageDisplay;
    int lvl;
    int championExtraVariableValue;
    String upgradeOrderString;
    String comboOrPriorityString;

    int enemyBaseHP, enemyBonusHP, enemyArmor, enemyMR;

    String modeSelected; //"Single Build", "Build Combinations", "Stat Graph"

    //for "Build Combinations"
    ArrayList<Item> variableItemList;
    int maxItems, maxBuildCost;

    //for "Stat Graph"
    String firstStatName, secondStatName;
    int firstStatMin, firstStatMax, secondStatMin, secondStatMax;


    ExtraVariablesDisplay savedEvd;

    public Config(MainView mv) {
        champion = mv.currentChampion;
        inventoryDisplay = mv.currentInventory;
        runePageDisplay = mv.currentRunes;
        savedEvd = mv.evd;

        lvl = Integer.parseInt(mv.level.getText());
        if (mv.extraVariableLabel.isVisible())
            championExtraVariableValue = Integer.parseInt(mv.extraVariableValue.getText());
        else
            championExtraVariableValue = 0;
        upgradeOrderString = mv.upgradeOrder.getText();
        comboOrPriorityString = mv.comboOrPriority.getText();

        enemyBaseHP = Integer.parseInt(mv.enemyBaseHP.getText());
        enemyBonusHP = Integer.parseInt(mv.enemyBonusHP.getText());
        enemyArmor = Integer.parseInt(mv.enemyArmor.getText());
        enemyMR = Integer.parseInt(mv.enemyMR.getText());

        modeSelected = mv.currentMode.getText();

        if (modeSelected.equals("Build Combinations")) {
            variableItemList = new ArrayList<>(mv.variableItemList);
            maxItems = Integer.parseInt(mv.maxItemsVal.getText());
            maxBuildCost = Integer.parseInt(mv.maxCostVal.getText());
        }
        else if (modeSelected.equals("Stat Graph")) {
            firstStatName = mv.var1type.getText();
            secondStatName = mv.var2type.getText();
            firstStatMin = Integer.parseInt(mv.var1min.getText());
            firstStatMax = Integer.parseInt(mv.var1max.getText());
            secondStatMin = Integer.parseInt(mv.var2min.getText());
            secondStatMax = Integer.parseInt(mv.var2max.getText());
        }
    }

    public void saveConfig(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
    }

    public static Config loadConfig(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        Config config = (Config) ois.readObject();
        ois.close();
        return config;
    }
}

package com.damagecalculator;

import com.damagecalculator.simulationManager.simulation.*;
import com.damagecalculator.simulationManager.simulation.champions.ChampionList;
import com.damagecalculator.simulationManager.simulation.items.ItemList;
import com.damagecalculator.simulationManager.simulation.runes.RuneList;
import com.damagecalculator.simulationManager.simulation.runes.Shards;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Config implements Serializable {
    String championName;

    List<String> itemNames;

    RunePath rp1, rp2;
    List<String> runeNames;
    int[] shardValues;

    List<Pair<String, Integer>> extraVariables;

    int lvl;
    int championExtraVariableValue;
    String upgradeOrderString;
    String comboOrPriorityString;

    int enemyBaseHP, enemyBonusHP, enemyArmor, enemyMR;

    String modeSelected; //"Single Build", "Build Combinations", "Stat Graph"

    //for "Build Combinations"
    List<String> variableItemListNames;
    int maxItems, maxBuildCost;

    //for "Stat Graph"
    String firstStatName, secondStatName;
    int firstStatMin, firstStatMax, secondStatMin, secondStatMax;



    public Config(MainView mv) {
        championName = mv.currentChampion.name;

        itemNames = new ArrayList<>();
        for (Item i : mv.currentInventory.getInventory().getItems()) itemNames.add(i.name);

        RunePage runePage = mv.currentRunes.getRunePage();
        rp1 = runePage.primary;
        rp2 = runePage.secondary;
        runeNames = new ArrayList<>();
        shardValues = new int[]{3, 3, 3}; //"null"
        for (Rune r : runePage.runeList) {
            if (r.name.equals("Shards")) {
                Shards shards = (Shards)r;
                shardValues = new int[]{shards.s1, shards.s2, shards.s3};
            }
            else runeNames.add(r.name);
        }

        extraVariables = new ArrayList<>();
        for (HBox hBox : mv.evd.hBoxes) {
            TextField tf = (TextField)(hBox).getChildren().get(2); //?
            extraVariables.add(new Pair<>(tf.getId(), Integer.parseInt(tf.getText())));
        }

        lvl = Integer.parseInt(mv.level.getText());

        if (mv.extraVariableLabel.isVisible())
            championExtraVariableValue = Integer.parseInt(mv.extraVariableValue.getText());
        else
            championExtraVariableValue = -1;

        upgradeOrderString = mv.upgradeOrder.getText();
        comboOrPriorityString = mv.comboOrPriority.getText();

        enemyBaseHP = Integer.parseInt(mv.enemyBaseHP.getText());
        enemyBonusHP = Integer.parseInt(mv.enemyBonusHP.getText());
        enemyArmor = Integer.parseInt(mv.enemyArmor.getText());
        enemyMR = Integer.parseInt(mv.enemyMR.getText());

        modeSelected = mv.currentMode.getText();

        if (modeSelected.equals("Build Combinations")) {
            variableItemListNames = new ArrayList<>();
            for (Item i : mv.variableItemList) variableItemListNames.add(i.name);
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

    public void applyConfig(MainView mv) {
        for (Champion c : ChampionList.allChampions) if (c.name.equals(championName)) {
            mv.setChampion(c);
            break;
        }


        Inventory inventory = new Inventory();
        for (String name : itemNames) inventory.add(ItemList.nameToItem.get(name));
        mv.currentInventory.inventory = inventory;
        mv.currentInventory.itemListDisplay.setAvailableItems(inventory);
        mv.currentInventory.updateInventory();


        mv.currentRunes.updatePrimaryPath(rp1);
        mv.currentRunes.updateSecondaryPath(rp2);

        for (String name : runeNames) {
            Rune rune = RuneList.nameToRune.get(name);
            if (rune == null) continue;
            if (rune.path.equals(rp1)) {
                switch (rune.column) {
                    case 0 -> mv.currentRunes.updateKeystone(rune);
                    case 1 -> mv.currentRunes.updatePrimary1(rune);
                    case 2 -> mv.currentRunes.updatePrimary2(rune);
                    case 3 -> mv.currentRunes.updatePrimary3(rune);
                }
            }
            else {
                switch (rune.column) {
                    case 1 -> mv.currentRunes.updateSecondary1(rune);
                    case 2 -> mv.currentRunes.updateSecondary2(rune);
                    case 3 -> mv.currentRunes.updateSecondary3(rune);
                }
            }
        }
        mv.currentRunes.updateShards(0, shardValues[0]);
        mv.currentRunes.updateShards(1, shardValues[1]);
        mv.currentRunes.updateShards(2, shardValues[2]);


        mv.evd.reset();
        for (Pair<String, Integer> psi : extraVariables) {
            String name = psi.getKey();
            int val = psi.getValue();
            if (ItemList.nameToItem.containsKey(name)) mv.evd.add(ItemList.nameToItem.get(name), val);
            else mv.evd.add(RuneList.nameToRune.get(name), val);
        }


        mv.level.setText(Integer.toString(lvl));
        if (championExtraVariableValue != -1) mv.extraVariableValue.setText(Integer.toString(championExtraVariableValue));

        mv.upgradeOrder.setText(upgradeOrderString);
        mv.comboOrPriority.setText(comboOrPriorityString);

        mv.enemyBaseHP.setText(Integer.toString(enemyBaseHP));
        mv.enemyBonusHP.setText(Integer.toString(enemyBonusHP));
        mv.enemyArmor.setText(Integer.toString(enemyArmor));
        mv.enemyMR.setText(Integer.toString(enemyMR));


        mv.onModeSwitch(modeSelected);

        if (modeSelected.equals("Build Combinations")) {
            mv.variableItemList.clear();
            for (String name : variableItemListNames) {
                mv.variableItemList.add(ItemList.nameToItem.get(name));
            }
            mv.variableItems.itemList = new ArrayList<>();
            for (Item i : ItemList.allItems) {
                mv.variableItems.itemList.add(new Pair<>(i, mv.variableItemList.contains(i)));
            }
            mv.maxItemsVal.setText(Integer.toString(maxItems));
            mv.maxCostVal.setText(Integer.toString(maxBuildCost));
        }
        else if (modeSelected.equals("Stat Graph")) {
            mv.var1type.setText(firstStatName);
            mv.var2type.setText(secondStatName);
            mv.var1min.setText(Integer.toString(firstStatMin));
            mv.var1max.setText(Integer.toString(firstStatMax));
            mv.var2min.setText(Integer.toString(secondStatMin));
            mv.var2max.setText(Integer.toString(secondStatMax));
        }
    }

}

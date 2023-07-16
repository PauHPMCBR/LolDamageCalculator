package com.damagecalculator;

import com.damagecalculator.displays.*;
import com.damagecalculator.simulationManager.BuildTester;
import com.damagecalculator.simulationManager.Printer;
import com.damagecalculator.simulationManager.SimulationManager;
import com.damagecalculator.simulationManager.StatsTester;
import com.damagecalculator.simulationManager.simulation.*;
import com.damagecalculator.simulationManager.simulation.champions.Dummy;
import com.damagecalculator.simulationManager.simulation.items.ItemList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MainView {
    public HBox champion;
    public HBox inventory;
    public HBox runes;
    public HBox graph;

    public HBox extraVariables;

    public TextField level;
    public Label extraVariableLabel;
    public TextField extraVariableValue;

    public TextField upgradeOrder;

    public TextField comboOrPriority;
    public Button comboButton;
    public Button dpsButton;

    public String[] modes = {"Single Build", "Build Combinations", "Stat Graph"};
    public MenuButton currentMode;
    public Button variableItemsButton;
    public Button resetButton;

    public HBox buildCombinationConstraints;
    public TextField maxItemsVal;
    public TextField maxCostVal;

    public HBox var1box, var2box;
    public MenuButton var1type, var2type;
    public TextField var1min, var1max, var2min, var2max;


    public TextField enemyBaseHP;
    public TextField enemyBonusHP;
    public TextField enemyArmor;
    public TextField enemyMR;

    public TextArea output;
    public Label LolPatch;

    InventoryDisplay currentInventory;
    RunePageDisplay currentRunes;
    ExtraVariablesDisplay evd;
    ItemListDisplay variableItems;

    Champion currentChampion;
    ArrayList<Item> variableItemList;

    GraphDisplay graphDisplay;

    public void setChampion(Champion c) {
        currentChampion = c; //had .makeCopy()
        champion.getChildren().clear();
        champion.getChildren().add(new ImageView(DisplayUtils.getChampionIcon(c)));
        champion.setOnMouseClicked((MouseEvent e) -> {
            new ChampionListDisplay() {
                public void pickedChampion(Champion c) {
                    setChampion(c);
                    closeWindow();
                }
            }.openWindow();
        });

        if (c.extraVariableName != null) {
            extraVariableLabel.setVisible(true);
            extraVariableLabel.setText(c.extraVariableName);
            extraVariableValue.setVisible(true);
            extraVariableValue.setText("0");
        }
        else {
            extraVariableValue.setVisible(false);
            extraVariableLabel.setVisible(false);
        }

        upgradeOrder.setText(DisplayUtils.getString(c.upgradeOrder));

        comboOrPriority.setText(DisplayUtils.getString(c.abilityPriorities));
    }

    public RunePage setUpRunes() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<Rune> oldRunes = currentRunes.getRunePage().runeList;
        ArrayList<Rune> runes = new ArrayList<>(oldRunes);

        ArrayList<Rune> specialRunes = evd.getRunes();
        for (Rune r : specialRunes) {
            runes.remove(r);
            runes.add(r.makeCopy());
        }
        RunePage rp = new RunePage(currentRunes.getRunePage().primary, currentRunes.getRunePage().secondary);
        rp.setRunes(runes);
        return rp;
    }

    public Inventory setUpInventory() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Inventory inventory = new Inventory(currentInventory.getInventory());
        ArrayList<Item> specialItems = evd.getItems();
        for (Item i : specialItems) {
            inventory.remove(i);
            inventory.add(i.makeCopy());
        }
        return inventory;
    }

    public Champion setUpChampion() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Champion c = currentChampion.makeCopy();
        if (extraVariableLabel.isVisible()) {
            c = currentChampion.getClass().getConstructor(int.class).newInstance(
                    Integer.parseInt(extraVariableValue.getText()));
        }
        c.lvl = Integer.parseInt(level.getText());
        c.upgradeOrder = DisplayUtils.getAbilityList(upgradeOrder.getText());
        c.setRunePage(setUpRunes());
        c.setInventory(setUpInventory());

        return c;
    }

    @FXML
    protected void onComboButtonClick() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Dummy dummy = new Dummy(
                Integer.parseInt(enemyBaseHP.getText()),
                Integer.parseInt(enemyBonusHP.getText()),
                Integer.parseInt(enemyArmor.getText()),
                Integer.parseInt(enemyMR.getText())
        );

        if (currentMode.getText().equals("Single Build")) {
            SimulationManager sm = new SimulationManager();
            sm.setChampion(setUpChampion());
            sm.setEnemy(dummy);
            output.setText("");
            sm.printer = new Printer() {
                public void print(String s) {
                    output.setText(output.getText() + s);
                }
            };
            sm.simulateCombo(DisplayUtils.getAbilityList(comboOrPriority.getText()));
        }
        else if (currentMode.getText().equals("Build Combinations")) {
            BuildTester bt = new BuildTester(
                    Integer.parseInt(maxItemsVal.getText()),
                    Integer.parseInt(maxCostVal.getText())
            );
            bt.setRunePage(setUpRunes());
            bt.setPermanentItems(currentInventory.getInventory().getItems());
            bt.setVariableItems(variableItemList);
            output.setText("");
            bt.printer = new Printer() {
                public void print(String s) {
                    output.setText(output.getText() + s);
                }
            };
            bt.sortBuilds(
                    setUpChampion(),
                    dummy,
                    DisplayUtils.getAbilityList(comboOrPriority.getText()),
                    true
            );
        }
        else {
            graph.getChildren().clear();
            StatsTester statsTester = new StatsTester(setUpChampion(), dummy, true, DisplayUtils.getAbilityList(comboOrPriority.getText()));
            int xMin = Integer.parseInt(var1min.getText());
            int xMax = Integer.parseInt(var1max.getText());
            int yMin = Integer.parseInt(var2min.getText());
            int yMax = Integer.parseInt(var2max.getText());
            int xStep = 1, yStep = 1;
            if (xMax - xMin >= 1000) xStep = 10;
            if (yMax - yMin >= 1000) yStep = 10;
            Data3D data3D = new Data3D(
                    var1type.getText(), xMin, xStep, var2type.getText(), yMin, yStep, "Damage");
            data3D.setData(statsTester.testStats(
                    StatsTester.getType(var1type.getText()), xMin, xStep, xMax,
                    StatsTester.getType(var2type.getText()), yMin, yStep, yMax
            ));
            graph.getChildren().add(graphDisplay.getDisplay(data3D));
        }
    }

    @FXML
    protected void onDpsButtonClick() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        output.setText("");
        Dummy dummy = new Dummy(
                Integer.parseInt(enemyBaseHP.getText()),
                Integer.parseInt(enemyBonusHP.getText()),
                Integer.parseInt(enemyArmor.getText()),
                Integer.parseInt(enemyMR.getText())
        );

        if (currentMode.getText().equals("Single Build")) {
            SimulationManager sm = new SimulationManager();
            sm.setChampion(setUpChampion());
            sm.setEnemy(dummy);
            sm.printer = new Printer() {
                public void print(String s) {
                    output.setText(output.getText() + s);
                }
            };
            sm.simulateDps(DisplayUtils.getAbilityList(comboOrPriority.getText()));
        }
        else if (currentMode.getText().equals("Build Combinations")) {
            BuildTester bt = new BuildTester(
                    Integer.parseInt(maxItemsVal.getText()),
                    Integer.parseInt(maxCostVal.getText())
            );
            bt.setRunePage(setUpRunes());
            bt.setPermanentItems(currentInventory.getInventory().getItems());
            bt.setVariableItems(variableItemList);
            bt.printer = new Printer() {
                public void print(String s) {
                    output.setText(output.getText() + s);
                }
            };
            bt.sortBuilds(
                    setUpChampion(),
                    dummy,
                    DisplayUtils.getAbilityList(comboOrPriority.getText()),
                    false
            );
        }
        else {
            StatsTester statsTester = new StatsTester(setUpChampion(), dummy, false, DisplayUtils.getAbilityList(comboOrPriority.getText()));
            int xMin = Integer.parseInt(var1min.getText());
            int xMax = Integer.parseInt(var1max.getText());
            int yMin = Integer.parseInt(var2min.getText());
            int yMax = Integer.parseInt(var2max.getText());
            int xStep = 1, yStep = 1;
            if (xMax - xMin >= 1000) xStep = 10;
            if (yMax - yMin >= 1000) yStep = 10;
            Data3D data3D = new Data3D(
                    var1type.getText(), xMin, xStep, var2type.getText(), yMin, yStep, "Time");
            data3D.setData(statsTester.testStats(
                    StatsTester.getType(var1type.getText()), xMin, xStep, xMax,
                    StatsTester.getType(var2type.getText()), yMin, yStep, yMax
            ));
            graph.getChildren().clear();
            graph.getChildren().add(graphDisplay.getDisplay(data3D));
        }
    }


    String previousState = "-";
    protected void onModeSwitch(String newState) {
        if (newState.equals(previousState)) return;

        if (newState.equals("Single Build")) {
            variableItemsButton.setVisible(false);
            resetButton.setVisible(false);
            buildCombinationConstraints.setVisible(false);

            var1box.setVisible(false);
            var2box.setVisible(false);
        }
        else if (newState.equals("Build Combinations")) {
            variableItemsButton.setVisible(true);
            resetButton.setVisible(true);
            buildCombinationConstraints.setVisible(true);

            var1box.setVisible(false);
            var2box.setVisible(false);
        }
        else { //Stat Graph
            variableItemsButton.setVisible(false);
            resetButton.setVisible(true);
            buildCombinationConstraints.setVisible(false);

            var1box.setVisible(true);
            var2box.setVisible(true);
        }
        previousState = newState;
        currentMode.setText(newState);
    }

    @FXML
    protected void onVariableItemsClick() {
        variableItems.openWindow();
    }

    @FXML
    protected void onResetClick() {
        variableItems.itemList = new ArrayList<>();
        for (Item i : ItemList.allItems) {
            variableItems.itemList.add(new Pair<>(i, false));
        }
        variableItemList.clear();

        graph.getChildren().clear();
        graphDisplay.clearDisplay();
    }
}

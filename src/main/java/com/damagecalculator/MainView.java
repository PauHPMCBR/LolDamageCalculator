package com.damagecalculator;

import com.damagecalculator.displays.*;
import com.damagecalculator.simulationManager.BuildTester;
import com.damagecalculator.simulationManager.Printer;
import com.damagecalculator.simulationManager.SimulationManager;
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
import java.util.Objects;

public class MainView {
    public HBox champion;
    public HBox inventory;
    public HBox runes;

    public HBox extraVariables;

    public TextField level;
    public Label extraVariableLabel;
    public TextField extraVariableValue;

    public TextField upgradeOrder;

    public TextField comboOrPriority;
    public Button comboButton;
    public Button dpsButton;

    public CheckBox buildCombinations;
    public Label modeSelected;
    public Button variableItemsButton;
    public Button resetVariableItemsButton;

    public Label maxItemsLabel;
    public TextField maxItemsVal;
    public Label maxCostLabel;
    public TextField maxCostVal;

    public TextField enemyHP;
    public TextField enemyArmor;
    public TextField enemyMR;

    public TextArea output;
    public Label LolPatch;


    Champion currentChampion;
    InventoryDisplay currentInventory;
    RunePageDisplay currentRunes;
    ExtraVariablesDisplay evd;
    ItemListDisplay variableItems;
    ArrayList<Item> variableItemList;

    public void setChampion(Champion c) {
        AbilityType[] oldAbilityPrio  = null;
        if (currentChampion != null) oldAbilityPrio = currentChampion.abilityPriorities;

        currentChampion = c.makeCopy();
        champion.getChildren().clear();
        champion.getChildren().add(new ImageView(DisplayUtils.getChampionIcon(c)));
        ChampionListDisplay championListDisplay = new ChampionListDisplay() {
            public void pickedChampion(Champion c) {
                setChampion(c);
                closeWindow();
            }
        };
        champion.setOnMouseClicked((MouseEvent e) -> {
            championListDisplay.openWindow();
        });

        if (c.extraVariableName != null) {
            extraVariableLabel.setVisible(true);
            extraVariableLabel.setText(c.extraVariableName);
            extraVariableValue.setVisible(true);
            if (Objects.equals(extraVariableValue.getText(), "")) extraVariableValue.setText("0");
        }
        else {
            extraVariableValue.setVisible(false);
            extraVariableLabel.setVisible(false);
        }

        upgradeOrder.setText(DisplayUtils.getString(c.upgradeOrder));

        if (oldAbilityPrio == null || comboOrPriority.getText().equals(DisplayUtils.getString(oldAbilityPrio)))
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
        Champion dummy = new Dummy(
                Integer.parseInt(enemyHP.getText()),
                Integer.parseInt(enemyArmor.getText()),
                Integer.parseInt(enemyMR.getText())
        );

        if (buildCombinations.isSelected()) {
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
    }

    @FXML
    protected void onDpsButtonClick() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Champion dummy = new Dummy(
                Integer.parseInt(enemyHP.getText()),
                Integer.parseInt(enemyArmor.getText()),
                Integer.parseInt(enemyMR.getText())
        );

        if (buildCombinations.isSelected()) {
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
                    false
            );
        }
        else {
            SimulationManager sm = new SimulationManager();
            sm.setChampion(setUpChampion());
            sm.setEnemy(dummy);
            output.setText("");
            sm.printer = new Printer() {
                public void print(String s) {
                    output.setText(output.getText() + s);
                }
            };
            sm.simulateDps(DisplayUtils.getAbilityList(comboOrPriority.getText()));
        }
    }

    boolean previousState = false;
    @FXML
    protected void onModeSwitch() {
        if (buildCombinations.isSelected() != previousState) {
            if (buildCombinations.isSelected()) {
                modeSelected.setText("Combinations");
                variableItemsButton.setVisible(true);
                resetVariableItemsButton.setVisible(true);
                maxCostLabel.setVisible(true);
                maxCostVal.setVisible(true);
                maxItemsVal.setVisible(true);
                maxItemsLabel.setVisible(true);
            }
            else {
                modeSelected.setText("Single build");
                variableItemsButton.setVisible(false);
                resetVariableItemsButton.setVisible(false);
                maxCostLabel.setVisible(false);
                maxCostVal.setVisible(false);
                maxItemsVal.setVisible(false);
                maxItemsLabel.setVisible(false);
            }
            previousState = !previousState;
        }
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
    }
}

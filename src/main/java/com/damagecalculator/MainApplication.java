package com.damagecalculator;

import com.damagecalculator.displays.*;
import com.damagecalculator.simulationManager.simulation.Champion;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.champions.Kaisa;
import com.damagecalculator.simulationManager.simulation.items.ItemList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        System.out.println();
        System.out.println();
        System.out.flush();

        DisplayUtils.preCalc();
        System.out.println("Starting GUI...");

        StartupThread startupThread = new StartupThread();
        startupThread.evd = new ExtraVariablesDisplay();
        startupThread.start();

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("main-view.fxml"));
        Parent root = fxmlLoader.load();
        MainView controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        stage.setTitle("Damage Calculator");
        stage.getIcons().add(new Image(
                Objects.requireNonNull(MainApplication.class.getResourceAsStream("trueDmgIcon.png"))));
        stage.setScene(scene);

        stage.show();

        controller.output.setEditable(false);

        controller.LolPatch.setText("Lol Patch: 13.10");

        controller.evd = startupThread.evd;
        controller.extraVariables.setMaxWidth(430);
        controller.extraVariables.getChildren().clear();
        controller.extraVariables.getChildren().add(controller.evd.col1);
        controller.extraVariables.getChildren().add(DisplayUtils.createSpacer());
        controller.extraVariables.getChildren().add(controller.evd.col2);

        controller.level.setText("1");

        controller.extraVariableLabel.setVisible(false);
        controller.extraVariableValue.setVisible(false);

        controller.modeSelected.setText("Single build");
        controller.resetVariableItemsButton.setVisible(false);
        controller.variableItemsButton.setVisible(false);
        controller.variableItems = new ItemListDisplay() {
            public void pickedItem(Item i, boolean b) {
                if (b) {
                    controller.variableItemList.remove(i);
                    controller.variableItems.setAvailable(i, false);
                }
                else {
                    controller.variableItemList.add(i);
                    controller.variableItems.setAvailable(i, true);
                }
                updateDisplay();
            }
        };
        controller.maxCostLabel.setVisible(false);
        controller.maxCostVal.setVisible(false);
        controller.maxItemsVal.setVisible(false);
        controller.maxItemsLabel.setVisible(false);

        controller.maxCostVal.setText("100000");
        controller.maxItemsVal.setText("6");

        controller.enemyHP.setText("1000");
        controller.enemyMR.setText("30");
        controller.enemyArmor.setText("50");

        startupThread.join();

        controller.setChampion(startupThread.currentChampion);
        controller.variableItemList = startupThread.variableItemList;

        controller.currentInventory = startupThread.currentInventory;
        controller.inventory.getChildren().add(controller.currentInventory.getDisplay());

        controller.currentRunes = startupThread.currentRunes;
        controller.runes.getChildren().add(controller.currentRunes.getDisplay());

        controller.onResetClick();

        System.out.println("GUI started.");
    }

    public static void main(String[] args) {
        launch();
    }

    public class StartupThread extends Thread {

        InventoryDisplay currentInventory;
        RunePageDisplay currentRunes;
        ExtraVariablesDisplay evd;

        Champion currentChampion;
        ArrayList<Item> variableItemList;

        public void run() {
            currentChampion = new Kaisa();
            variableItemList = new ArrayList<>();

            currentInventory = new InventoryDisplay(evd);
            currentRunes = new RunePageDisplay(evd);
        }
    }
}

package com.damagecalculator;

import com.damagecalculator.displays.*;
import com.damagecalculator.simulationManager.StatsTester;
import com.damagecalculator.simulationManager.simulation.Champion;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.champions.Kaisa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
public class MainApplication extends Application {
    final String autosaveName = "autosave.dcc";
    public static MainView controller;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException, ClassNotFoundException {
        System.out.println();
        System.out.println();
        System.out.flush();

        DisplayUtils.preCalc();
        System.out.println("Starting GUI...");

        DisplayCacheThread displayCacheThread = new DisplayCacheThread();
        displayCacheThread.start();

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("main-view.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        int screenWidth = (int) Screen.getPrimary().getBounds().getWidth(); //1536 def
        int screenHeight = (int) Screen.getPrimary().getBounds().getHeight(); //864 def

        Scene scene = new Scene(root, screenWidth-100, screenHeight-100);
        stage.setTitle("Damage Calculator");
        stage.setMaximized(true);
        stage.getIcons().add(new Image(
                Objects.requireNonNull(MainApplication.class.getResourceAsStream("trueDmgIcon.png"))));
        stage.setScene(scene);

        stage.show();

        controller.stageToClose = stage;

        controller.output.setEditable(false);

        controller.menuBar.prefWidthProperty().bind(stage.widthProperty());

        controller.LolPatch.setText("Lol Patch: 14.14");

        controller.evd = displayCacheThread.evd;
        controller.extraVariables.setMaxWidth(430);
        controller.extraVariables.getChildren().clear();
        controller.extraVariables.getChildren().add(controller.evd.col1);
        controller.extraVariables.getChildren().add(DisplayUtils.createSpacer());
        controller.extraVariables.getChildren().add(controller.evd.col2);

        controller.extraVariableLabel.setVisible(false);
        controller.extraVariableValue.setVisible(false);

        for (String mode : controller.modes) {
            MenuItem mi = new MenuItem(mode);
            mi.setOnAction(e -> controller.onModeSwitch(((MenuItem) e.getSource()).getText()));
            controller.currentMode.getItems().add(mi);
        }

        controller.variablesNum.setVisible(false);
        controller.resetButton.setVisible(false);
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
        controller.buildCombinationConstraints.setVisible(false);

        controller.variablesNum.setManaged(false);
        controller.variableItemsButton.setManaged(false);
        controller.resetButton.setManaged(false);
        controller.buildCombinationConstraints.setManaged(false);

        controller.var1box.setVisible(false);
        controller.var2box.setVisible(false);

        for (StatsTester.StatType statType : StatsTester.StatType.values()) {
            MenuItem mi = new MenuItem(statType.name());
            mi.setOnAction(e -> controller.var1type.setText(statType.name()));
            controller.var1type.getItems().add(mi);
        }
        for (StatsTester.StatType statType : StatsTester.StatType.values()) {
            MenuItem mi = new MenuItem(statType.name());
            mi.setOnAction(e -> controller.var2type.setText(statType.name()));
            controller.var2type.getItems().add(mi);
        }

        int graphSize = (int) (Math.min(screenWidth - controller.output.getWidth(), screenHeight) - 50);
        //System.out.println(graphSize);
        controller.graph.setPrefWidth(graphSize);
        controller.graph.setPrefHeight(graphSize);
        //System.out.println(controller.graph.getWidth());
        //System.out.println(controller.graph.getHeight());

        displayCacheThread.join();

        controller.setChampion(displayCacheThread.currentChampion);
        controller.variableItemList = displayCacheThread.variableItemList;

        controller.currentInventory = displayCacheThread.currentInventory;
        controller.inventory.getChildren().add(controller.currentInventory.getDisplay());

        controller.currentRunes = displayCacheThread.currentRunes;
        controller.runes.getChildren().add(controller.currentRunes.getDisplay());

        controller.graphDisplay = new GraphDisplay();
        controller.graph.setVisible(true);

        controller.onResetClick();

        controller.defaultConfig = new Config(controller);
        controller.statsTester = new StatsTester();

        System.out.println("GUI started.");

        // check if autosave present
        File autosave = new File(autosaveName);
        if (autosave.exists() && !autosave.isDirectory()) {
            Config savedConfig = Config.loadConfig(autosaveName);
            savedConfig.applyConfigTo(controller);
            System.out.println("Loaded config from autosave");
        }
    }

    @Override
    public void stop() throws IOException {
        Config currentConfig = new Config(controller);
        if (!currentConfig.equals(controller.defaultConfig)) {
            currentConfig.saveConfig(autosaveName);
            System.out.println("Autosaved current configuration");
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public static class DisplayCacheThread extends Thread {
        InventoryDisplay currentInventory;
        RunePageDisplay currentRunes;
        ExtraVariablesDisplay evd;
        Champion currentChampion;
        ArrayList<Item> variableItemList;
        public void run() {
            evd = new ExtraVariablesDisplay();

            currentChampion = new Kaisa();
            variableItemList = new ArrayList<>();

            currentInventory = new InventoryDisplay(evd);
            currentRunes = new RunePageDisplay(evd);
        }
    }
}

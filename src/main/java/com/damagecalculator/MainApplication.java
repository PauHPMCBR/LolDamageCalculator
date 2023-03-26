package com.damagecalculator;

import com.damagecalculator.displays.*;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.champions.Kaisa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("main-view.fxml"));
        Parent root = fxmlLoader.load();
        MainView controller = fxmlLoader.getController();

        controller.setChampion(new Kaisa());


        controller.evd = new ExtraVariablesDisplay();
        controller.extraVariables.setMaxWidth(430);
        controller.extraVariables.getChildren().clear();
        controller.extraVariables.getChildren().add(controller.evd.col1);
        controller.extraVariables.getChildren().add(DisplayUtils.createSpacer());
        controller.extraVariables.getChildren().add(controller.evd.col2);

        controller.currentInventory = new InventoryDisplay(controller.evd);
        controller.inventory.getChildren().add(controller.currentInventory.getDisplay());

        controller.currentRunes = new RunePageDisplay(controller.evd);
        controller.runes.getChildren().add(controller.currentRunes.getDisplay());

        controller.level.setText("1");

        controller.extraVariableLabel.setVisible(false);
        controller.extraVariableValue.setVisible(false);

        controller.variableItemList = new ArrayList<>();
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
        controller.onResetClick();

        controller.maxCostLabel.setVisible(false);
        controller.maxCostVal.setVisible(false);
        controller.maxItemsVal.setVisible(false);
        controller.maxItemsLabel.setVisible(false);
        controller.maxItemsVal.setText("6");
        controller.maxCostVal.setText("100000");


        controller.enemyHP.setText("1000");
        controller.enemyMR.setText("30");
        controller.enemyArmor.setText("50");

        controller.output.setEditable(false);

        Scene scene = new Scene(root);
        stage.setTitle("Damage Calculator");
        stage.getIcons().add(new Image(
                Objects.requireNonNull(MainApplication.class.getResourceAsStream("trueDmgIcon.png"))));
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

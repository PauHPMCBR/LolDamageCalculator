package com.damagecalculator.displays;

import com.damagecalculator.MainApplication;
import com.damagecalculator.simulationManager.simulation.Champion;
import com.damagecalculator.simulationManager.simulation.champions.*;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChampionListDisplay {
    static final int columns = 5;


    public Scene getScene() {
        TilePane tilePane = new TilePane();

        for (Champion c : ChampionList.allChampions) {
            ImageView iv = new ImageView(DisplayUtils.getChampionIcon(c));
            iv.setOnMouseClicked((MouseEvent e) -> pickedChampion(c));

            tilePane.getChildren().add(DisplayUtils.addBorder(iv));
        }

        VBox vBox = new VBox(tilePane);

        ScrollPane scrollPane = new ScrollPane(vBox);
        final double SPEED = 0.01;
        scrollPane.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            scrollPane.setVvalue(scrollPane.getVvalue() - deltaY);
        });

        double width = DisplayUtils.getChampionIcon(new Kaisa()).getWidth();
        width += 4; //borders
        width *= columns;
        width += 30;

        return new Scene(scrollPane, width, width * 1.4);
    }

    Stage stage;
    public void openWindow() {
        stage = new Stage();
        stage.setTitle("Champion List");
        stage.getIcons().add(new Image(
                Objects.requireNonNull(MainApplication.class.getResourceAsStream("dummyIcon.png"))));
        stage.setScene(getScene());
        stage.show();
    }
    public void closeWindow() {
        if (stage != null) stage.close();
    }

    public void pickedChampion(Champion c) {}

}
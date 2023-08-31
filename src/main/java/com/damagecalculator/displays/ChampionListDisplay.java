package com.damagecalculator.displays;

import com.damagecalculator.MainApplication;
import com.damagecalculator.simulationManager.simulation.Champion;
import com.damagecalculator.simulationManager.simulation.champions.*;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ChampionListDisplay {
    static final int columns = 5;

    public HashMap<String,ImageView> championImages;

    public TextField searchBar;

    public VBox total;

    public void updateDisplay() {
        String searchText = searchBar.getText();

        TilePane tilePane = new TilePane();

        for (Champion c : ChampionList.allChampions) {
            if (!DisplayUtils.containsSubsequence(c.name.toUpperCase(), searchText.toUpperCase())) continue;

            tilePane.getChildren().add(DisplayUtils.addBorder(championImages.get(c.name)));
        }

        total.getChildren().clear();
        total.getChildren().add(tilePane);
    }


    Stage stage;
    public void openWindow() {
        VBox withSearchBar = new VBox();
        searchBar.setText("");
        withSearchBar.getChildren().add(searchBar);
        withSearchBar.getChildren().add(new Separator());

        total = new VBox();
        updateDisplay();
        ScrollPane scrollPane = new ScrollPane(total);
        final double SPEED = 0.01;
        scrollPane.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            scrollPane.setVvalue(scrollPane.getVvalue() - deltaY);
        });

        withSearchBar.getChildren().add(scrollPane);

        double width = DisplayUtils.getChampionIcon(new Kaisa()).getWidth();
        width += 4; //borders
        width *= columns;
        width += 30;

        Scene scene = new Scene(withSearchBar, width, width * 1.4);

        stage = new Stage();
        stage.setTitle("Champion List");
        stage.getIcons().add(new Image(
                Objects.requireNonNull(MainApplication.class.getResourceAsStream("dummyIcon.png"))));
        stage.setScene(scene);
        stage.show();
    }
    public void closeWindow() {
        if (stage != null) stage.close();
    }

    public void pickedChampion(Champion c) {}

    public ChampionListDisplay() {
        searchBar = new TextField("");
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisplay();
        });

        championImages = new HashMap<>();
        for (Champion c : ChampionList.allChampions) {
            Tooltip tooltip = new Tooltip(c.name);
            tooltip.setStyle("-fx-font-weight: bold; -fx-font-size: 12");
            tooltip.setShowDelay(Duration.millis(0));

            ImageView iv = new ImageView(DisplayUtils.getChampionIcon(c));
            iv.setOnMouseClicked((MouseEvent e) -> pickedChampion(c));
            Tooltip.install(iv, tooltip);
            championImages.put(c.name, iv);
        }
    }
}
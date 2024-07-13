package com.damagecalculator.displays;

import com.damagecalculator.GlobalVariables;
import com.damagecalculator.MainApplication;
import com.damagecalculator.simulationManager.simulation.Inventory;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;
import com.damagecalculator.simulationManager.simulation.items.ItemList;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ItemListDisplay {
    static final int columns = 10;
    static final List<ItemType> itemTypes = List.of(ItemType.values());

    public ArrayList<Pair<Item, Boolean>> itemList;
    public HashMap<String,ImageView> itemImages;
    public HashMap<String,ImageView> itemDesaturatedImages;

    public TextField searchBar;

    public VBox total;


    public void updateDisplay() {
        String searchText = searchBar.getText();

        TilePane[] tilePanes = new TilePane[itemTypes.size()];

        for (int i = 0; i < tilePanes.length; ++i) {
            tilePanes[i] = new TilePane();
            tilePanes[i].setPrefColumns(columns);
        }

        for (Pair<Item, Boolean> p : itemList) {
            Item i = p.getKey();

            if (!DisplayUtils.containsSubsequence(i.name.toUpperCase(), searchText.toUpperCase())) continue;

            int pos = itemTypes.indexOf(i.type);
            if (i.name.startsWith("Guardian's")) {
                if (!GlobalVariables.displayAramItems) continue;
                pos = itemTypes.indexOf(ItemType.STARTER);
            }

            //add desaturated or not, depending on p.val
            if (p.getValue()) tilePanes[pos].getChildren().add(DisplayUtils.addBorder(itemImages.get(p.getKey().name)));
            else tilePanes[pos].getChildren().add(DisplayUtils.addBorder(itemDesaturatedImages.get(p.getKey().name)));
        }

        total.getChildren().clear();

        for (int i = 0; i < tilePanes.length; ++i) {
            if (tilePanes[i].getChildren().isEmpty()) continue;

            VBox prov = new VBox();
            prov.getChildren().add(new Label(itemTypes.get(i).name()));
            prov.getChildren().add(tilePanes[i]);

            total.getChildren().add(prov);
            total.getChildren().add(new Separator());
        }


    }

    public Stage stage;
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

        double width = DisplayUtils.getEmptyItemImage().getWidth();
        width += 4; //for border
        width *= columns;
        width += 30;

        Scene scene = new Scene(withSearchBar, width, width * 1.4);

        stage = new Stage();
        stage.setTitle("Item List");
        stage.getIcons().add(new Image(
                Objects.requireNonNull(MainApplication.class.getResourceAsStream("dBladeIcon.png"))));
        stage.setScene(scene);
        stage.show();
    }
    public void closeWindow() {
        if (stage != null) stage.close();
    }

    public void setAvailableItems(Inventory inventory) {
        for (int i = 0; i < itemList.size(); ++i) {
            if (inventory.canAdd(itemList.get(i).getKey())) itemList.set(i, new Pair<>(itemList.get(i).getKey(), true));
            else itemList.set(i, new Pair<>(itemList.get(i).getKey(), false));
        }
    }

    public void setAvailable(Item item, boolean available) {
        for (int i = 0; i < itemList.size(); ++i) {
            if (itemList.get(i).getKey().equals(item))
                itemList.set(i, new Pair<>(item, available));
        }
    }

    public void pickedItem(Item i, boolean b) {}

    public ItemListDisplay() {
        searchBar = new TextField("");
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisplay();
        });
        searchBar.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                String searchText = searchBar.getText();
                Item firstItem = null;
                for (Pair<Item, Boolean> p : itemList) {
                    if (!DisplayUtils.containsSubsequence(p.getKey().name.toUpperCase(), searchText.toUpperCase())) continue;
                    if (!p.getValue()) continue; //if cant select, ignore
                    if (firstItem == null || p.getKey().type.ordinal() < firstItem.type.ordinal()) firstItem = p.getKey();
                }
                if (firstItem != null) pickedItem(firstItem, true);
            }
        });

        itemList = new ArrayList<>();
        itemImages = new HashMap<>();
        itemDesaturatedImages = new HashMap<>();
        for (Item i : ItemList.allItems) {
            itemList.add(new Pair<>(i, true));

            Tooltip tooltip = new Tooltip(DisplayUtils.getItemDescription(i));
            tooltip.setStyle("-fx-font-weight: bold; -fx-font-size: 12");
            tooltip.setShowDelay(Duration.millis(0));

            ImageView iv = new ImageView(DisplayUtils.getItemImage(i));
            iv.setOnMouseClicked((MouseEvent e) -> pickedItem(i, true));
            Tooltip.install(iv, tooltip);
            itemImages.put(i.name, iv);

            ImageView iv2 = new ImageView(DisplayUtils.getItemImage(i));
            DisplayUtils.desaturate(iv2);
            iv2.setOnMouseClicked((MouseEvent e) -> pickedItem(i, false));
            Tooltip.install(iv2, tooltip);
            itemDesaturatedImages.put(i.name, iv2);
        }

    }
}
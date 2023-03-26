package com.damagecalculator.displays;

import com.damagecalculator.MainApplication;
import com.damagecalculator.simulationManager.simulation.Inventory;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;
import com.damagecalculator.simulationManager.simulation.items.ItemList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemListDisplay {
    static final int columns = 10;

    public List<Pair<Item, Boolean>> itemList;

    public VBox total;

    public void updateDisplay() {
        List<ItemType> itemTypes = List.of(ItemType.values());
        TilePane[] tilePanes = new TilePane[itemTypes.size()];

        for (int i = 0; i < tilePanes.length; ++i) {
            tilePanes[i] = new TilePane();
            tilePanes[i].setPrefColumns(columns);
        }

        for (Pair<Item, Boolean> p : itemList) {
            Item i = p.getKey();
            ImageView iv = new ImageView(DisplayUtils.getItemImage(i));
            if (p.getValue() == false) {
                DisplayUtils.desaturate(iv);
            }
            iv.setOnMouseClicked((MouseEvent e) -> {
                pickedItem(i, p.getValue());
            });

            int pos = itemTypes.indexOf(i.type);
            tilePanes[pos].getChildren().add(DisplayUtils.addBorder(iv));
        }

        total.getChildren().clear();

        for (int i = 0; i < tilePanes.length; ++i) {
            if (tilePanes[i].getChildren().size() == 0) continue;

            VBox prov = new VBox();
            prov.getChildren().add(new Label(itemTypes.get(i).name()));
            prov.getChildren().add(tilePanes[i]);

            total.getChildren().add(prov);
            total.getChildren().add(new Separator());
        }


    }

    public Stage stage;
    public void openWindow() {
        total = new VBox();
        updateDisplay();
        ScrollPane scrollPane = new ScrollPane(total);
        final double SPEED = 0.01;
        scrollPane.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            scrollPane.setVvalue(scrollPane.getVvalue() - deltaY);
        });

        double width = DisplayUtils.getEmptyItemImage().getWidth();
        width += 4; //for border
        width *= columns;
        width += 30;

        Scene scene = new Scene(scrollPane, width, width * 1.4);

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
        itemList = new ArrayList<>();
        for (Item i : ItemList.allItems) {
            itemList.add(new Pair<>(i, true));
        }
    }
}
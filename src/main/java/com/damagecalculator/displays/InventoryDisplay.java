package com.damagecalculator.displays;

import com.damagecalculator.simulationManager.simulation.Inventory;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.items.Empty;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


public class InventoryDisplay {
    ExtraVariablesDisplay evd;

    public Inventory inventory;
    public ItemListDisplay itemListDisplay;

    public HBox inventoryH;

    public HBox getDisplay() {
        return inventoryH;
    }

    public void updateInventory() {
        Item[] items = new Item[Inventory.maxSize];
        int ind = 0;
        for (Item i : inventory.getItems()) {
            if (i == null) items[ind] = new Empty();
            else items[ind] = i;
            ++ind;
        }
        for (int i = ind; i < Inventory.maxSize; ++i) items[i] = new Empty();

        ImageView[] imageViews = new ImageView[items.length];
        for (int i = 0; i < imageViews.length; ++i) {
            imageViews[i] = new ImageView(DisplayUtils.getItemImage(items[i]));
            int finalI = i;
            imageViews[i].setOnMouseClicked((MouseEvent e) -> {
                if (items[finalI] instanceof Empty) itemListDisplay.openWindow();
                else {
                    if (items[finalI].extraVariableName != null) evd.remove(items[finalI]);
                    inventory.remove(items[finalI]);
                    itemListDisplay.setAvailableItems(inventory);
                    updateInventory();
                }
            });
        }
        for (int i = 0; i < imageViews.length; ++i) {
            inventoryH.getChildren().set(i, DisplayUtils.addBorder(imageViews[i]));
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public InventoryDisplay(ExtraVariablesDisplay evd) {
        this.evd = evd;
        inventory = new Inventory();

        inventoryH = new HBox();
        inventoryH.setFillHeight(false); //?

        for (int i = 0; i < Inventory.maxSize; ++i) {
            ImageView emptyItem = new ImageView(DisplayUtils.getItemImage(new Empty()));
            DisplayUtils.addBorder(emptyItem);
            inventoryH.getChildren().add(emptyItem);
        }

        itemListDisplay = new ItemListDisplay() {
            public void pickedItem(Item i, boolean b) {
                if (!b) return;
                if (i.extraVariableName != null) evd.add(i);
                inventory.add(i);
                itemListDisplay.setAvailableItems(inventory);
                updateInventory();
                closeWindow();
            }
        };
        itemListDisplay.setAvailableItems(inventory);

        updateInventory();
    }
}

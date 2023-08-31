package com.damagecalculator.displays;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.Rune;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ExtraVariablesDisplay {
    public VBox col1;
    public VBox col2;

    public ArrayList<HBox> hBoxes;

    private final ArrayList<Item> items;
    private final ArrayList<Rune> runes;

    public HBox createSetter(Item i, int val) {
        TextField tf = new TextField(Integer.toString(val));
        tf.setId(i.name);
        tf.setPrefWidth(40);
        HBox res = new HBox(new Label(i.extraVariableName), DisplayUtils.createSpacer(), tf);
        res.setPrefWidth(200);
        return res;
    }
    public HBox createSetter(Rune r, int val) {
        TextField tf = new TextField(Integer.toString(val));
        tf.setId(r.name);
        tf.setPrefWidth(40);
        HBox res = new HBox(new Label(r.extraVariableName), DisplayUtils.createSpacer(), tf);
        res.setPrefWidth(200);
        return res;
    }

    public void update() {
        col1.getChildren().clear();
        col2.getChildren().clear();
        boolean parity = true;
        for (HBox hBox : hBoxes) {
            if (parity) col1.getChildren().add(hBox);
            else col2.getChildren().add(hBox);
            parity = !parity;
        }
    }

    public void add(Item i, int val) {
        items.add(i);
        hBoxes.add(createSetter(i, val));
        update();
    }
    public void add(Item i) {
        add(i, 0);
    }
    public void remove(Item i) {
        items.remove(i);
        for (HBox hBox : hBoxes)
            if (((Label)hBox.getChildren().get(0)).getText().equals(i.extraVariableName)) {
                hBoxes.remove(hBox);
                break;
            }
        update();
    }
    public void add(Rune r, int val) {
        runes.add(r);
        hBoxes.add(createSetter(r, val));
        update();
    }
    public void add(Rune r) {
        add(r, 0);
    }
    public void remove(Rune r) {
        runes.remove(r);
        for (HBox hBox : hBoxes)
            if (((Label)hBox.getChildren().get(0)).getText().equals(r.extraVariableName)) {
                hBoxes.remove(hBox);
                break;
            }
        update();
    }

    public ArrayList<Rune> getRunes() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ArrayList<Rune> ans = new ArrayList<>();
        for (Node v : col1.getChildren()) {
            TextField prov = (TextField) ((HBox) v).getChildren().get(2);
            for (Rune r : runes) {
                if (r.name.equals(prov.getId())) {
                    ans.add(r.getClass().getConstructor(int.class).newInstance(Integer.parseInt(prov.getText())));
                    break;
                }
            }
        }
        for (Node v : col2.getChildren()) {
            TextField prov = (TextField) ((HBox) v).getChildren().get(2);
            for (Rune r : runes) {
                if (r.name.equals(prov.getId())) {
                    ans.add(r.getClass().getConstructor(int.class).newInstance(Integer.parseInt(prov.getText())));
                    break;
                }
            }
        }
        return ans;
    }

    public ArrayList<Item> getItems() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ArrayList<Item> ans = new ArrayList<>();
        for (Node v : col1.getChildren()) {
            TextField prov = (TextField) ((HBox) v).getChildren().get(2);
            for (Item i : items) {
                if (i.name.equals(prov.getId())) {
                    ans.add(i.getClass().getConstructor(int.class).newInstance(Integer.parseInt(prov.getText())));
                    break;
                }
            }
        }
        for (Node v : col2.getChildren()) {
            TextField prov = (TextField) ((HBox) v).getChildren().get(2);
            for (Item i : items) {
                if (i.name.equals(prov.getId())) {
                    ans.add(i.getClass().getConstructor(int.class).newInstance(Integer.parseInt(prov.getText())));
                    break;
                }
            }
        }
        return ans;
    }

    public void reset() {
        items.clear();
        runes.clear();
        hBoxes.clear();
        update();
    }

    public ExtraVariablesDisplay() {
        col1 = new VBox();
        col2 = new VBox();
        hBoxes = new ArrayList<>();

        items = new ArrayList<>();
        runes = new ArrayList<>();
    }
}

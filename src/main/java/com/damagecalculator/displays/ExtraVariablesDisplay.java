package com.damagecalculator.displays;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.Rune;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ExtraVariablesDisplay {
    public VBox col1;
    public VBox col2;

    private final ArrayList<Item> items;
    private final ArrayList<Rune> runes;

    public HBox createSetter(Item i) {
        TextField tf = new TextField("0");
        tf.setId(i.name);
        tf.setPrefWidth(40);
        HBox res = new HBox(new Label(i.extraVariableName), DisplayUtils.createSpacer(), tf);
        res.setPrefWidth(200);
        return res;
    }
    public HBox createSetter(Rune r) {
        TextField tf = new TextField("0");
        tf.setId(r.name);
        tf.setPrefWidth(40);
        HBox res = new HBox(new Label(r.extraVariableName), DisplayUtils.createSpacer(), tf);
        res.setPrefWidth(200);
        return res;
    }

    public void update() {
        col1.getChildren().clear();
        col2.getChildren().clear();
        int parity = 1;
        for (Item i : items) {
            if (parity == 1) col1.getChildren().add(createSetter(i));
            else col2.getChildren().add(createSetter(i));
            parity *= -1;
        }
        for (Rune r : runes) {
            if (parity == 1) col1.getChildren().add(createSetter(r));
            else col2.getChildren().add(createSetter(r));
            parity *= -1;
        }
    }

    public void add(Item i) {
        items.add(i);
        update();
    }
    public void remove(Item i) {
        items.remove(i);
        update();
    }
    public void add(Rune r) {
        runes.add(r);
        update();
    }
    public void remove(Rune r) {
        runes.remove(r);
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

    public ExtraVariablesDisplay() {
        col1 = new VBox();
        col2 = new VBox();

        items = new ArrayList<>();
        runes = new ArrayList<>();
    }
}

package com.damagecalculator.displays;

import com.damagecalculator.MainApplication;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePage;
import com.damagecalculator.simulationManager.simulation.RunePath;
import com.damagecalculator.simulationManager.simulation.runes.Empty;
import com.damagecalculator.simulationManager.simulation.runes.RuneList;
import com.damagecalculator.simulationManager.simulation.runes.Shards;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class RunePageDisplay {
    ExtraVariablesDisplay evd;

    RunePath primary = RunePath.Precision;
    RunePath secondary = RunePath.Domination;
    Rune keystone;
    Rune p1, p2, p3;
    Rune s1, s2;
    Shards shards;

    HBox primaryH = new HBox();
    HBox keystoneH = new HBox();
    HBox p1H = new HBox();
    HBox p2H = new HBox();
    HBox p3H = new HBox();

    HBox secondaryH = new HBox();
    HBox s1H = new HBox();
    HBox s2H = new HBox();
    HBox s3H = new HBox();
    HBox shardsH = new HBox();

    static RunePath[] availablePaths = new RunePath[] {RunePath.Precision, RunePath.Domination, RunePath.Sorcery, RunePath.Resolve, RunePath.Inspiration};
    static Rune none = new Empty();

    public void updatePrimaryPath(RunePath selectedRP) {
        primary = selectedRP;
        primaryH.getChildren().clear();
        for (RunePath rp : availablePaths) {
            ImageView iv = new ImageView(DisplayUtils.getRunePathImage(rp));
            if (!rp.equals(selectedRP)) {
                DisplayUtils.desaturate(iv);
                iv.setOnMouseClicked((MouseEvent e) -> {
                    updatePrimaryPath(rp);
                });
            }
            primaryH.getChildren().add(iv);
        }

        updateKeystone(none);
        updatePrimary1(none);
        updatePrimary2(none);
        updatePrimary3(none);

        if (selectedRP.equals(secondary)) {
            if (secondary.equals(RunePath.Precision)) updateSecondaryPath(RunePath.Domination);
            else updateSecondaryPath(RunePath.Precision);
        }
        DisplayUtils.distribute(primaryH);
    }

    public void updateSecondaryPath(RunePath selectedRP) {
        secondary = selectedRP;
        secondaryH.getChildren().clear();
        for (RunePath rp : availablePaths) {
            ImageView iv = new ImageView(DisplayUtils.getRunePathImage(rp));
            if (!rp.equals(selectedRP)) {
                DisplayUtils.desaturate(iv);
                iv.setOnMouseClicked((MouseEvent e) -> {
                    if (!rp.equals(primary))
                        updateSecondaryPath(rp);
                });
            }
            secondaryH.getChildren().add(iv);
        }
        updateSecondary1(none);
        updateSecondary2(none);
        updateSecondary3(none);
        DisplayUtils.distribute(secondaryH);
    }

    public void updateKeystone(Rune selectedR) {
        if (keystone != null && keystone.extraVariableName != null) evd.remove(keystone);
        if (selectedR != null && selectedR.extraVariableName != null) evd.add(selectedR);

        keystone = selectedR.makeCopy();
        keystoneH.getChildren().clear();
        for (Rune r : RuneList.getKeystones(primary)) {
            ImageView iv = new ImageView(DisplayUtils.getRuneImage(r));
            if (!r.equals(selectedR)) {
                DisplayUtils.desaturate(iv);
                iv.setOnMouseClicked((MouseEvent e) -> {
                    updateKeystone(r);
                });
            }
            keystoneH.getChildren().add(iv);
        }
        DisplayUtils.distribute(keystoneH);
    }

    public void updatePrimary1(Rune selectedR) {
        if (p1 != null && p1.extraVariableName != null) evd.remove(p1);
        if (selectedR != null && selectedR.extraVariableName != null) evd.add(selectedR);

        p1 = selectedR.makeCopy();
        p1H.getChildren().clear();
        for (Rune r : RuneList.getColumn1(primary)) {
            ImageView iv = new ImageView(DisplayUtils.getRuneImage(r));
            if (!r.equals(selectedR)) {
                DisplayUtils.desaturate(iv);
                iv.setOnMouseClicked((MouseEvent e) -> {
                    updatePrimary1(r);
                });
            }
            p1H.getChildren().add(iv);
        }
        DisplayUtils.distribute(p1H);
    }

    public void updatePrimary2(Rune selectedR) {
        if (p2 != null && p2.extraVariableName != null) evd.remove(p2);
        if (selectedR != null && selectedR.extraVariableName != null) evd.add(selectedR);

        p2 = selectedR.makeCopy();
        p2H.getChildren().clear();
        for (Rune r : RuneList.getColumn2(primary)) {
            ImageView iv = new ImageView(DisplayUtils.getRuneImage(r));
            if (!r.equals(selectedR)) {
                DisplayUtils.desaturate(iv);
                iv.setOnMouseClicked((MouseEvent e) -> {
                    updatePrimary2(r);
                });
            }
            p2H.getChildren().add(iv);
        }
        DisplayUtils.distribute(p2H);
    }

    public void updatePrimary3(Rune selectedR) {
        if (p3 != null && p3.extraVariableName != null) evd.remove(p3);
        if (selectedR != null && selectedR.extraVariableName != null) evd.add(selectedR);

        p3 = selectedR.makeCopy();
        p3H.getChildren().clear();
        for (Rune r : RuneList.getColumn3(primary)) {
            ImageView iv = new ImageView(DisplayUtils.getRuneImage(r));
            if (!r.equals(selectedR)) {
                DisplayUtils.desaturate(iv);
                iv.setOnMouseClicked((MouseEvent e) -> {
                    updatePrimary3(r);
                });
            }
            p3H.getChildren().add(iv);
        }
        DisplayUtils.distribute(p3H);
    }


    public void updateSecondaryClear(int column) {
        if (column > 3 ||column < 1) return;
        Rune[] runes;
        HBox hBox;
        if (column == 1) {
            hBox = s1H;
            runes = RuneList.getColumn1(secondary);
        }
        else if (column == 2) {
            hBox = s2H;
            runes = RuneList.getColumn2(secondary);
        }
        else {
            hBox = s3H;
            runes = RuneList.getColumn3(secondary);
        }

        hBox.getChildren().clear();
        for (Rune r : runes) {
            ImageView iv = new ImageView(DisplayUtils.getRuneImage(r));
            DisplayUtils.desaturate(iv);
            iv.setOnMouseClicked((MouseEvent e) -> {
                if (column == 1) updateSecondary1(r);
                else if (column == 2) updateSecondary2(r);
                else updateSecondary3(r);
            });
            hBox.getChildren().add(iv);
        }

        DisplayUtils.distribute(hBox);
    }

    public void updateSecondary1(Rune selectedR) {
        if (s2.column != 1) {
            if (s1.extraVariableName != null) evd.remove(s1);
            updateSecondaryClear(s1.column);

            s1 = s2.makeCopy();
        }
        else if (s2.extraVariableName != null) evd.remove(s2);

        if (selectedR.extraVariableName != null) evd.add(selectedR);
        s2 = selectedR.makeCopy();

        s1H.getChildren().clear();
        for (Rune r : RuneList.getColumn1(secondary)) {
            ImageView iv = new ImageView(DisplayUtils.getRuneImage(r));
            if (!r.equals(selectedR)) {
                DisplayUtils.desaturate(iv);
                iv.setOnMouseClicked((MouseEvent e) -> {
                    updateSecondary1(r);
                });
            }
            s1H.getChildren().add(iv);
        }
        DisplayUtils.distribute(s1H);
    }

    public void updateSecondary2(Rune selectedR) {
        if (s2.column != 2) {
            if (s1.extraVariableName != null) evd.remove(s1);
            updateSecondaryClear(s1.column);

            s1 = s2.makeCopy();
        }
        else if (s2.extraVariableName != null) evd.remove(s2);

        if (selectedR.extraVariableName != null) evd.add(selectedR);
        s2 = selectedR.makeCopy();

        s2H.getChildren().clear();
        for (Rune r : RuneList.getColumn2(secondary)) {
            ImageView iv = new ImageView(DisplayUtils.getRuneImage(r));
            if (!r.equals(selectedR)) {
                DisplayUtils.desaturate(iv);
                iv.setOnMouseClicked((MouseEvent e) -> {
                    updateSecondary2(r);
                });
            }
            s2H.getChildren().add(iv);
        }
        DisplayUtils.distribute(s2H);
    }



    public void updateSecondary3(Rune selectedR) {
        if (s2.column != 3) {
            if (s1.extraVariableName != null) evd.remove(s1);
            updateSecondaryClear(s1.column);

            s1 = s2.makeCopy();
        }
        else if (s2.extraVariableName != null) evd.remove(s2);

        if (selectedR.extraVariableName != null) evd.add(selectedR);
        s2 = selectedR.makeCopy();

        s3H.getChildren().clear();
        for (Rune r : RuneList.getColumn3(secondary)) {
            ImageView iv = new ImageView(DisplayUtils.getRuneImage(r));
            if (!r.equals(selectedR)) {
                DisplayUtils.desaturate(iv);
                iv.setOnMouseClicked((MouseEvent e) -> {
                    updateSecondary3(r);
                });
            }
            s3H.getChildren().add(iv);
        }
        DisplayUtils.distribute(s3H);
    }

    public static Image[][] shardImages = new Image[][] {
            new Image[] {
                    new Image(String.valueOf(MainApplication.class.getResource(DisplayUtils.runesFolder + "Rune_shard_Adaptive_Force.png"))),
                    new Image(String.valueOf(MainApplication.class.getResource(DisplayUtils.runesFolder + "Rune_shard_Attack_Speed.png"))),
                    new Image(String.valueOf(MainApplication.class.getResource(DisplayUtils.runesFolder + "Rune_shard_Cooldown_Reduction.png")))
            },
            new Image[] {
                    new Image(String.valueOf(MainApplication.class.getResource(DisplayUtils.runesFolder + "Rune_shard_Adaptive_Force.png"))),
                    new Image(String.valueOf(MainApplication.class.getResource(DisplayUtils.runesFolder + "Rune_shard_Armor.png"))),
                    new Image(String.valueOf(MainApplication.class.getResource(DisplayUtils.runesFolder + "Rune_shard_Magic_Resistance.png")))
            },
            new Image[] {
                    new Image(String.valueOf(MainApplication.class.getResource(DisplayUtils.runesFolder + "Rune_shard_Health.png"))),
                    new Image(String.valueOf(MainApplication.class.getResource(DisplayUtils.runesFolder + "Rune_shard_Armor.png"))),
                    new Image(String.valueOf(MainApplication.class.getResource(DisplayUtils.runesFolder + "Rune_shard_Magic_Resistance.png")))
            }
    };
    public void updateShards(int row, int col) {
        shardsH.getChildren().clear();
        if (row == 0) shards.s1 = col;
        else if (row == 1) shards.s2 = col;
        else shards.s3 = col;
        int[] shardVals = new int[] {shards.s1, shards.s2, shards.s3};
        VBox total = new VBox();
        for (int i = 0; i < 3; ++i) {
            HBox hrow = new HBox();
            hrow.getChildren().add(DisplayUtils.createSpacer());
            for (int j = 0; j < 3; ++j) {
                ImageView iv = new ImageView(shardImages[i][j]);
                if (shardVals[i] != j) {
                    DisplayUtils.desaturate(iv);
                    int finalI = i;
                    int finalJ = j;
                    iv.setOnMouseClicked((MouseEvent e) -> {
                        updateShards(finalI, finalJ);
                    });
                }
                hrow.getChildren().add(iv);
            }
            hrow.getChildren().add(DisplayUtils.createSpacer());
            DisplayUtils.distribute(hrow);
            total.getChildren().add(hrow);
        }
        shardsH.getChildren().add(total);
    }


    public RunePage getRunePage() {
        RunePage rp = new RunePage(primary, secondary);
        rp.setRunes(List.of(new Rune[]{
                keystone,
                p1, p2, p3,
                s1, s2,
                shards
        }), true);
        return rp;
    }

    public HBox getDisplay() {
        HBox total = new HBox();
        total.getChildren().add(new VBox(
                primaryH,
                keystoneH,
                p1H,
                p2H,
                p3H
        ));
        total.getChildren().add(new Separator());
        total.getChildren().add(new VBox(
                secondaryH,
                s1H,
                s2H,
                s3H,
                shardsH
        ));
        total.getChildren().add(new Separator());
        return total;
    }

    public RunePageDisplay(ExtraVariablesDisplay evd) {
        this.evd = evd;

        shards = new Shards(3,3,3);

        updatePrimaryPath(primary);
        //updateKeystone(none);
        //updatePrimary1(none);
        //updatePrimary2(none);
        //updatePrimary3(none);

        s1 = none.makeCopy();
        s2 = none.makeCopy();
        updateSecondaryPath(secondary);
        //updateSecondary1(none);
        //updateSecondary2(none);
        //updateSecondary3(none);

        updateShards(3,3);
    }
}

//current bug: if first secondary selected is in row-col 3, it will disappear after choosing another secondary
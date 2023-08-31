package com.damagecalculator.displays;

import com.damagecalculator.GlobalVariables;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.jzy3d.chart.AWTChart;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.AbstractColorMap;
import org.jzy3d.colors.colormaps.IColorMap;
import org.jzy3d.javafx.JavaFXChartFactory;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.builder.concrete.OrthonormalTessellator;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.Quality;

import java.util.ArrayList;

public class GraphDisplay {
    public class ColorMapScale extends AbstractColorMap implements IColorMap {
        Color toAdd;
        public ColorMapScale(Color toAdd) {
            float tone = 0.5f;
            this.toAdd = new Color(toAdd.r * tone, toAdd.g * tone, toAdd.b * tone);
        }

        public Color getColor(double x, double y, double z, double zMin, double zMax) {
            double rel_value = this.processRelativeZValue(z, zMin, zMax) - 0.2; //?
            float r = (float)rel_value + toAdd.r;
            float g = (float)rel_value + toAdd.g;
            float b = (float)rel_value + toAdd.b;
            return new Color(r, g, b);
        }
    }
    final Color[] chartColors = {Color.WHITE, Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.MAGENTA, Color.CYAN};

    public JavaFXChartFactory factory;
    public AWTChart chart;

    public LineChart lineChart;

    int graphsPresent;
    boolean was2DBefore = true;

    public void clearDisplay() {
        factory = null;
        chart = null;
        lineChart = null;
        graphsPresent = 0;
    }
    public ImageView getDisplay(Data3D data3D) {
        if (graphsPresent > GlobalVariables.max3DGraphs || was2DBefore) {
            clearDisplay();
        }
        was2DBefore = false;
        if (chart == null || factory == null) {
            factory = new JavaFXChartFactory();
            chart = (AWTChart) factory.newChart(Quality.Advanced, "offscreen");
            graphsPresent = 0;
        }

        if (graphsPresent == 0) {
            Data3D point0 = new Data3D("a",0,0,"b",0,0,"a");
            point0.points = new ArrayList<>();
            for (Coord3d point : data3D.points) point0.points.add(new Coord3d(point.x, point.y, 0));
            addDataChart(point0);
            ++graphsPresent;
        }

        addDataChart(data3D);
        ++graphsPresent;

        chart.getAxeLayout().setXAxeLabel(data3D.xName);
        chart.getAxeLayout().setYAxeLabel(data3D.yName);
        chart.getAxeLayout().setZAxeLabel(data3D.zName);

        return factory.bindImageView(chart);
    }

    void addDataChart(Data3D data3D) {
        OrthonormalTessellator tesselator = new OrthonormalTessellator();
        final Shape surface = (Shape) tesselator.build(data3D.points);

        surface.setColorMapper(new ColorMapper(
                new ColorMapScale(chartColors[graphsPresent]), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)
        ));
        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(false);

        chart.getScene().getGraph().add(surface);
    }


    public HBox getDisplay(String var1Name, int start, int step, String var2Name, float[] vals) {
        if (graphsPresent > GlobalVariables.max2DGraphs || !was2DBefore) {
            clearDisplay();
        }
        was2DBefore = true;
        if (lineChart == null) {
            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel(var1Name);
            yAxis.setLabel(var2Name);
            lineChart = new LineChart<>(xAxis, yAxis);
            lineChart.setCreateSymbols(false);
            lineChart.setPrefWidth(7000); //make it cover the entire hbox
            lineChart.setPrefHeight(7000);
        }

        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("");
        //populating the series with data
        int var1val = start;
        for (float f : vals) {
            series.getData().add(new XYChart.Data(var1val, f));
            var1val += step;
        }

        lineChart.getData().add(series);
        ++graphsPresent;

        return new HBox(lineChart);
    }
}
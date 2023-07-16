package com.damagecalculator.displays;

import org.jzy3d.maths.Coord3d;

import java.util.ArrayList;
import java.util.List;

public class Data3D {
    public String xName, yName;
    public String zName;
    public int xStart, xStep;
    public int yStart, yStep;
    public List<Coord3d> points;

    public Data3D(String xName, int xStart, int xStep, String yName, int yStart, int yStep, String zName) {
        this.xName = xName;
        this.xStart = xStart;
        this.xStep = xStep;
        this.yName = yName;
        this.yStart = yStart;
        this.yStep = yStep;
        this.zName = zName;
    }

    public void setData(float[][] values) {
        points = new ArrayList<>();
        for (int i = 0; i < values.length; ++i) {
            for (int j = 0; j < values[i].length; ++j) {
                points.add(new Coord3d(
                        xStart + i * xStep,
                        yStart + j * yStep,
                        values[i][j]
                ));
            }
        }
    }
}
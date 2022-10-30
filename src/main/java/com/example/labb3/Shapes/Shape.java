package com.example.labb3.Shapes;

import com.example.labb3.Model;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class Shape  {


    private Position position;
    private Color color;

    private double size;

    public Shape(Position position, Color color, double size) {
        this.position = position;
        this.color = color;
        this.size = size;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }


    public abstract Shape copyOf();


}

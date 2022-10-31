package com.example.labb3.Shapes;

import com.example.labb3.Model;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Shape  {


    private double mouseX;
    private double mouseY;
    private ColorPicker color;

    private double size;

    public Shape(double mouseX, double mouseY, ColorPicker color, double size) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.color = color;
        this.size = size;
    }

    public double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public ColorPicker getColor() {
        return color;
    }

    public void setColor(ColorPicker color) {
        this.color = color;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
    public void draw(GraphicsContext graphicsContext, ColorPicker colorPicker) {

    }

    @Override
    public String toString() {
        return "Shape{" +
                "mouseX=" + mouseX +
                ", mouseY=" + mouseY +
                ", color=" + color +
                ", size=" + size +
                '}';
    }
}

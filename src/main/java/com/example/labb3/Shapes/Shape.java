package com.example.labb3.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public abstract class Shape  {

    private double mouseX;
    private double mouseY;

    private ColorPicker colorPicker;// set color here

    public Shape(double mouseX, double mouseY, ColorPicker colorPicker) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.colorPicker = colorPicker;
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

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    public void setColorPicker(ColorPicker colorPicker) {
        this.colorPicker = colorPicker;
    }

    public static Shape createShape(ShapeType type, double mouseX, double mouseY, GraphicsContext graphicsContext, ColorPicker colorPicker){

        if (type == ShapeType.CIRCLE) {

            return new Cirkel(mouseX, mouseY,colorPicker).draw(graphicsContext);
        } else
            return new Rectangle(mouseX, mouseY,colorPicker).draw(graphicsContext);

    }


    abstract Shape draw(GraphicsContext graphicsContext);
}

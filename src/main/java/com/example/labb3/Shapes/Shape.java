package com.example.labb3.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public abstract class Shape  {

    private double mouseX;
    private double mouseY;

    private final double size;

    private Color color;

    public Shape(double mouseX, double mouseY, double size, Color color) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.size = size;
        this.color = color;
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

    public double getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static Shape createShape(ShapeType type, double mouseX, double mouseY, GraphicsContext graphicsContext, double size,Color color){

        if (type == ShapeType.CIRCLE) {

            return new Cirkel(mouseX, mouseY,size,color).draw(graphicsContext);
        } else
            return new Rectangle(mouseX, mouseY, size,color).draw(graphicsContext);

    }


    protected abstract Shape draw(GraphicsContext graphicsContext);

}

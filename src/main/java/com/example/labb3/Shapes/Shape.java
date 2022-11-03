package com.example.labb3.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class Shape  {

    private double mouseX;
    private double mouseY;

    private double size;

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

    public void setSize(double size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Double.compare(shape.mouseX, mouseX) == 0 && Double.compare(shape.mouseY, mouseY) == 0 && Double.compare(shape.size, size) == 0 && Objects.equals(color, shape.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mouseX, mouseY, size, color);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "mouseX=" + mouseX +
                ", mouseY=" + mouseY +
                ", size=" + size +
                ", color=" + color +
                '}';
    }

    public static Shape createShape(ShapeType type, double mouseX, double mouseY, GraphicsContext graphicsContext, double size, Color color){

        if (type == ShapeType.CIRCLE) {

            return new Cirkel(mouseX, mouseY,size,color);
        } else
            return new Rectangle(mouseX, mouseY, size,color);

    }

    public abstract void draw(GraphicsContext graphicsContext);

    public abstract boolean isInsideShape(double mouseX, double mouseY);

}

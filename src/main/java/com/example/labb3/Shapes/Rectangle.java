package com.example.labb3.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class Rectangle extends Shape  {


    public Rectangle(double mouseX, double mouseY, double size, Color color) {
        super(mouseX, mouseY, size, color);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {

            graphicsContext.setFill(getColor());
            graphicsContext.fillRect(getMouseX(), getMouseY(), getSize(), getSize());


    }

    @Override
    public boolean isInsideShape(double mouseX, double mouseY) {
        double distX = Math.abs(mouseX - getMouseX());
        double distY = Math.abs(mouseY - getMouseY());

        return distX <= getSize() && distY <= getSize();

    }


}

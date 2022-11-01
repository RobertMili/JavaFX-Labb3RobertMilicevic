package com.example.labb3.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public class Cirkel extends Shape {

    public Cirkel(double mouseX, double mouseY, ColorPicker colorPicker) {
        super(mouseX, mouseY, colorPicker);
    }

    @Override
    Shape draw(GraphicsContext graphicsContext) {
        try {
            graphicsContext.setFill(getColorPicker().getValue());
            graphicsContext.fillOval(getMouseX(),getMouseY(),50,50);

        } catch (Exception e) {
            System.out.println("Error with draw");
        }
        return null;
    }

}


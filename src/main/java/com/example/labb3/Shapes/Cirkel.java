package com.example.labb3.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class Cirkel extends Shape {


    public Cirkel(double mouseX, double mouseY, double size, Color color) {
        super(mouseX, mouseY, size, color);
    }

    @Override
    public Shape draw(GraphicsContext graphicsContext) {
        try {
            graphicsContext.setFill(getColor());
            graphicsContext.fillOval(getMouseX(),getMouseY(),getSize(),getSize());

        } catch (Exception e) {
            System.out.println("Error with draw");
        }
        return null;
    }


    }




package com.example.labb3.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class Cirkel extends Shape {



    public Cirkel(Position position, Color color, double size) {
        super(position, color, size);
    }

    public void draw(GraphicsContext graphicsContext, ColorPicker colorPicker) {
        try {
            graphicsContext.setFill(colorPicker.getValue());
            graphicsContext.fillOval(getPosition().x(), getPosition().y(), 100, 100);


        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}


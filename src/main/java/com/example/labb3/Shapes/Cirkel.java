package com.example.labb3.Shapes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class Cirkel extends Shape {

    GraphicsContext graphicsContext;
    Canvas canvas;
    ColorPicker colorPicker;

    public Cirkel(Position position, Color color, double size) {
        super(position, color, size);
    }

    public void paint(GraphicsContext graphicsContext) {
        try {
            graphicsContext.setFill(colorPicker.getValue());
            graphicsContext.fillOval(50, 50, 100, 100);


        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}


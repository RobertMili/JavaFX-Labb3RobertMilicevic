package com.example.labb3.Shapes;

import com.example.labb3.GameViewController;
import com.example.labb3.Model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class Cirkel extends Shape {

    public Cirkel(Position position, Color color, double size) {
        super(position, color, size);
    }


    public void testDraw(GraphicsContext graphicsContext,ColorPicker colorPicker, double mouse, double size){
        try {
            //graphicsContext.setFill(colorPicker.getValue());
           graphicsContext.fillOval(mouse , mouse, size, size);

        } catch (Exception e) {
            System.out.println("Error with draw");
        }
    }

}


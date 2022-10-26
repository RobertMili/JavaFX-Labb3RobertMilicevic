package com.example.labb3.Shapes;

import com.example.labb3.Shapes.*;
import com.example.labb3.Shapes.Shape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Objects;

public class Rectangle extends Shape {



    public Rectangle(Position position, Color color, double size) {
        super(position, color, size);
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
    }

    @Override
    public Color getColor() {
        return super.getColor();
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
    }

    @Override
    public double getSize() {
        return super.getSize();

    }
    @Override
    public void setSize ( double size){
        super.setSize(size);
    }



    public void paint(GraphicsContext graphicsContext){
        try {
            graphicsContext.setFill(Color.BLUE);
            graphicsContext.fillRect(50, 50, 100, 100);

        } catch(Exception e) {
            System.out.println("Error");
        }
        }
    }

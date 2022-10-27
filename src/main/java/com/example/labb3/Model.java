package com.example.labb3;

import com.example.labb3.Shapes.Cirkel;
import com.example.labb3.Shapes.Position;
import com.example.labb3.Shapes.Rectangle;
import com.example.labb3.Shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private double mouseX;
    private double mouseY;

    List<Shape> shape = new ArrayList<>();

    public Rectangle rectangle = new Rectangle(new Position(getMouseX() , getMouseY()), Color.AQUA, 2.0); // This need to be i Modul
    public Cirkel cirkel =  new Cirkel(new Position(getMouseX(), getMouseY()), Color.AQUA, 2);

    //Shape shape = new Shape();


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

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void drawModel(GraphicsContext graphicsContext, ColorPicker colorPicker) {

        try {
            graphicsContext.setFill(colorPicker.getValue());
            graphicsContext.fillOval(getMouseX(),getMouseY() , 100, 100);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}

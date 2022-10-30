package com.example.labb3;

import com.example.labb3.Shapes.Cirkel;
import com.example.labb3.Shapes.Position;
import com.example.labb3.Shapes.Rectangle;
import com.example.labb3.Shapes.Shape;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private double mouseX;
    private double mouseY;
    private final int size = 50;

    List<Shape> shapeList = new ArrayList<>();

    public Rectangle rectangle;
    public Cirkel cirkel; //This connect
    private final StringProperty shapeSize;
    Shape shapeClass;
    Position position = new Position(getMouseX(), getMouseY());



    public Model() {
        this.shapeSize = new SimpleStringProperty("50");


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

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Cirkel getCirkel() {
        return cirkel;
    }

    public void setCirkel(Cirkel cirkel) {
        this.cirkel = cirkel;
    }

    public Shape drawCirkel(GraphicsContext graphicsContext, ColorPicker colorPicker) {

        try {
            graphicsContext.setFill(colorPicker.getValue());
            graphicsContext.fillOval(getMouseX() - size / 2, getMouseY() - size / 2, getShapeSizeAsDouble(), getShapeSizeAsDouble());

        } catch (Exception e) {
            System.out.println("Error with draw");
        }
        return null;
    }

    public Boolean addCirkelToList(GraphicsContext graphicsContext, ColorPicker colorPicker) {
        return shapeList.add(drawCirkel(graphicsContext, colorPicker));

        //System.out.println(shapeList.toString());
    }

    public Shape drawRectangle(GraphicsContext graphicsContext, ColorPicker colorPicker) {

        try {
            graphicsContext.setFill(colorPicker.getValue());
            graphicsContext.fillRect(getMouseX() - size / 2, getMouseY() - size / 2, getShapeSizeAsDouble(), getShapeSizeAsDouble());
        } catch (Exception e) {
            System.out.println("Error with draw");
        }
        return null;
    }

    public Boolean addRectangleTolist(GraphicsContext graphicsContext, ColorPicker colorPicker) {
        shapeList.add(drawRectangle(graphicsContext, colorPicker));

        return null;
    }

    public String getShapeSize() {
        return shapeSize.get();
    }

    public Double getShapeSizeAsDouble() {
        try {
            return Double.parseDouble(getShapeSize());
        } catch (Exception e) {
            System.out.println("Please write a number.");
        }
        return null;
    }

    public StringProperty shapeSizeProperty() {
        return shapeSize;
    }

    public void setShapeList(String shapeSize) {
        this.shapeSize.set(shapeSize);
    }

    public void changeSizeOnSelectedShapes() {
        shapeClass.setSize(getShapeSizeAsDouble());
    }

    public void setMouse() {
        this.position = new Position(getMouseX(), getMouseY());
        System.out.println(position);

        System.out.println("this is x  " + position.x());
        System.out.println("this is y " + position.y());

    }

    public void testMouse() {
        System.out.println("second class" + position.x());
    }

    public void test(ToggleButton cirkelButton,ToggleButton rectangelButton, GraphicsContext graphicsContext, ColorPicker colorPicker) {
        if (cirkelButton.isSelected()) {
            addCirkelToList(graphicsContext, colorPicker);


        } else if (rectangelButton.isSelected()) {
            drawRectangle(graphicsContext, colorPicker);


        }
    }
}





    /*
     public void deleteSelectedShapes() {
        addToUndoDeque();

        for (var shape : shape) {
            shapes.remove(shape);
        }
    }
    public void addToUndoDeque() {
        ObservableList<Shape> tempList = getTempList();
        undoDeque.addLast(tempList);
    }
    public ObservableList<Shape> getTempList() {
        ObservableList<Shape> tempList = FXCollections.observableArrayList();

        for (Shape shape : shapes) {
            tempList.add(shape.copyOf());
        }
        return tempList;
    }

  */






package com.example.labb3;

import com.example.labb3.Shapes.Shape;
import com.example.labb3.Shapes.ShapeType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;


import java.util.*;

@FunctionalInterface
interface Command {
    void execute();
}


public class Model {
    private final StringProperty shapeSize;
    private final ObjectProperty<Color> colorPicker;
    private final ObjectProperty<ShapeType> shapeTypeObjectProperty;

    ObservableList<Shape> shapeList;
    ObservableList<ShapeType> shapeTypesList;
    Deque<Command> undoStack = new ArrayDeque<>();
    Deque<Command> redoStack = new ArrayDeque<>();
    private double mouseX;
    private double mouseY;


    public Model() {
        this.shapeSize = new SimpleStringProperty("50");

        this.colorPicker = new SimpleObjectProperty<>(Color.BLUE);
        this.shapeTypeObjectProperty = new SimpleObjectProperty<>(ShapeType.CIRCLE);
        this.shapeList = FXCollections.observableArrayList();
        this.shapeTypesList = FXCollections.observableArrayList(ShapeType.values());


    }

    public ObservableList<Shape> getShapeList() {
        return shapeList;
    }

    public ObjectProperty<ShapeType> shapeTypeObjectPropertyProperty() {
        return shapeTypeObjectProperty;
    }

    public ShapeType getShapeTypeObjectProperty() {
        return shapeTypeObjectProperty.get();
    }

    public Color getColorPicker() {
        return colorPicker.get();
    }

    public ObjectProperty<Color> colorPickerProperty() {
        return colorPicker;
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

    public void createObjekt() {
        var creatingObjekt = Shape.createShape
                (getShapeTypeObjectProperty(),
                        getMouseX(),
                        getMouseY(),
                        getShapeSizeAsDouble(),
                        getColorPicker());

        shapeList.add(creatingObjekt);
        addUndo(creatingObjekt);
        addRedo(creatingObjekt);
    }

    private void addUndo(Shape creatingObjekt) {

        Command undo = () -> shapeList.remove(creatingObjekt);
        undoStack.push(undo);
    }
    //TOdo fix this
    private void addRedo(Shape creatingObjekt) {
        Command redo = () -> shapeList.add(creatingObjekt);
        redoStack.push(redo);
        System.out.println("This is redo redo size");
        System.out.println(creatingObjekt.toString());

    }

    public void undoCommand() {
        Command undoToExecute = undoStack.pop();
        undoToExecute.execute();

        System.out.println("This is undo list without last index: ");
        System.out.println(shapeList.toString());
    }
    public void redoCommand() {
        Command undoToExecute = redoStack.pop();
        undoToExecute.execute();
    }


    public Optional<Shape> checkIsInsideShape() {
        return shapeList.stream()
                .filter(shape -> shape.isInsideShape(getMouseX(), getMouseY()))
                .reduce((first, second) -> second);

    }


}













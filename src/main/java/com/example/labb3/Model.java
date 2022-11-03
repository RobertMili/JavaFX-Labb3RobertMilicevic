package com.example.labb3;

import com.example.labb3.Shapes.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Model {
    private double mouseX;
    private double mouseY;

    ObservableList<Shape> shapeToList = FXCollections.observableArrayList();
    ObservableList<ShapeType> shapeTypesList = FXCollections.observableArrayList(ShapeType.values());

    private final StringProperty shapeSize;

    private final ObjectProperty<Color> colorPicker;
    private final ObjectProperty<ShapeType> shapeTypeObjectProperty;

    public Stage stage;

    Deque<Command> undoStack= new ArrayDeque<>();




    public Model() {
        this.shapeSize = new SimpleStringProperty("50");

        this.colorPicker = new SimpleObjectProperty<>(Color.WHITE);
        this.shapeTypeObjectProperty = new SimpleObjectProperty<>(ShapeType.CIRCLE);



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


    public void onSaveAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));

        File filePath = fileChooser.showSaveDialog(stage);

        if (filePath != null)
            saveToFile(filePath.toPath());
    }

    public void saveToFile(Path file) {
        StringBuilder outPut = new StringBuilder();

        for (Shape p : shapeToList) {
            outPut.append(p.getMouseX());
            outPut.append(",");
            outPut.append(p.getMouseY());
            outPut.append(",");
            outPut.append(p.getSize());
            outPut.append(",");
            outPut.append(p.getColor());
            outPut.append("\n");
        }
        try {
            Files.writeString(file, outPut.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void createObjekt() {
       var test =  Shape.createShape
                (getShapeTypeObjectProperty(),
                            getMouseX(),
                            getMouseY(),
                        getShapeSizeAsDouble(),
                        getColorPicker());

       shapeToList.add(test);
        Command undo = () -> shapeToList.remove(test);
        undoStack.push(undo);
    }

    public void undoCommand() {
        Command undoToExecute = undoStack.pop();
        undoToExecute.execute();

        System.out.println("This is undo list without last index: ");
        System.out.println(shapeToList.toString());
    }


    public Optional<Shape> checkIsInsideShape() {
        return shapeToList.stream()
                .filter(shape -> shape.isInsideShape(getMouseX(), getMouseY()))
                .reduce((first, second) -> second);


    }


}


@FunctionalInterface
interface Command {
    void execute();
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






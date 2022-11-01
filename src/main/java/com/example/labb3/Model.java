package com.example.labb3;

import com.example.labb3.Shapes.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;



public class Model {

    private double mouseX;
    private double mouseY;

    ObservableList<Shape> shapeList = FXCollections.observableArrayList();
    ObservableList<ShapeType> shapeTypesList = FXCollections.observableArrayList(ShapeType.values());

    private final StringProperty shapeSize;

    private final ObjectProperty<Color> colorPicker;
    private final ObjectProperty<ShapeType> shapeTypeObjectProperty;
    Shape shapeClass;

    public Stage stage;

    public Model() {
        this.shapeSize = new SimpleStringProperty("50");

        this.colorPicker = new SimpleObjectProperty<>(Color.WHITE);
        this.shapeTypeObjectProperty = new SimpleObjectProperty<>(ShapeType.CIRCLE);
    }

    public ObjectProperty<ShapeType> shapeTypeObjectPropertyProperty() {
        return shapeTypeObjectProperty;
    }

    //Open  if you want to use choiceBox button
    public void setShapeTypeObjectProperty(ShapeType shapeTypeObjectProperty) {
        this.shapeTypeObjectProperty.set(shapeTypeObjectProperty);
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

    public void choiceButton(ToggleButton cirkelButton, ToggleButton rectangleButton, GraphicsContext graphicsContext) {
        if (cirkelButton.isSelected() && !rectangleButton.isSelected()) {

            creatingCirkel(graphicsContext);

            creatingShapeToLista();


        } else if (rectangleButton.isSelected() && !cirkelButton.isSelected()) {

            creatingRectangle(graphicsContext);

            creatingShapeToLista();

        }
    }

    private void creatingCirkel(GraphicsContext graphicsContext) {
        shapeClass = Shape.createShape
                (shapeTypesList.get(0),
                        getMouseX() - (getShapeSizeAsDouble() / 2),
                        getMouseY() - (getShapeSizeAsDouble() / 2),
                        graphicsContext, getShapeSizeAsDouble(),
                        getColorPicker());
    }

    private void creatingRectangle(GraphicsContext graphicsContext) {
        shapeClass = Shape.createShape(
                shapeTypesList.get(1),
                getMouseX() - (getShapeSizeAsDouble() / 2),
                getMouseY() - (getShapeSizeAsDouble() / 2),
                graphicsContext, getShapeSizeAsDouble(),
                getColorPicker());
    }


    public void creatingShapeToLista() {
        var shapeToList = new Shape(getMouseX(), getMouseY(), getShapeSizeAsDouble(), getColorPicker()) {
            @Override
            protected Shape draw(GraphicsContext graphicsContext) {
                return null;
            }
        };

        shapeList.add(shapeToList);

        System.out.println(shapeToList.toString()); // delete this
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

        for (Shape p : shapeList) {
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
}


// This is for choiceBox, I get it after.
//    public void creatObjekt(GraphicsContext graphicsContext) {
//        shapeClass = Shape.createShape
//                (getShapeTypeObjectProperty(),
//                        getMouseX() - (double)size / 2,
//                        getMouseY() - (double) size/ 2,
//                        graphicsContext,getShapeSizeAsDouble(),
//                        getColorPicker());
//
//    }
//







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






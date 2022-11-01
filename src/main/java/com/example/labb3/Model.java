package com.example.labb3;

import com.example.labb3.Shapes.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Model {

    private double mouseX;
    private double mouseY;
    private final int size = 50;

    List<Shape> shapeList = new ArrayList<>();
    ObservableList<Shape> shapeList2 =  FXCollections.observableArrayList();


    ObservableList<ShapeType>shapeTypesList = FXCollections.observableArrayList(ShapeType.values());

    public Rectangle rectangle;
    public Cirkel cirkel; //This connect
    private final StringProperty shapeSize;

    private  ObjectProperty<Color> colorPickerTest;


    Shape shapeClass;
    Position position = new Position(getMouseX(), getMouseY());
    public Stage stage;
    private Deque<Shape> undo;

    GameViewController controller;
    public Property<ShapeType> choiceBox;





//    public ObservableList<Shape> getShapeList2() {
//        return shapeList2;
//    }
//
//    public void setShapeList2(ObservableList<Shape> shapeList2) {
//        this.shapeList2 = shapeList2;
//    }

    public Model() {
        this.shapeSize = new SimpleStringProperty("50");
        this.undo = new ArrayDeque<>();
        this.colorPickerTest = new SimpleObjectProperty<>(Color.WHITE);

    }

    public Color getColorPickerTest() {
        return colorPickerTest.get();
    }

    public ObjectProperty<Color> colorPickerTestProperty() {
        return colorPickerTest;
    }

    public void setColorPickerTest(Color colorPickerTest) {
        this.colorPickerTest.set(colorPickerTest);
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

//    public double changeSizeOnSelectedShapes() {
//        shapeClass.setSize(getShapeSizeAsDouble());
//        return 0;
//    }

    public void setMouse() {
        this.position = new Position(getMouseX(), getMouseY());
        System.out.println(position);

        System.out.println("this is x  " + position.x());
        System.out.println("this is y " + position.y());

    }


    public Property<ShapeType> choiceButton(ToggleButton cirkelButton, ToggleButton rectangleButton, GraphicsContext graphicsContext ) {
        if (cirkelButton.isSelected() && !rectangleButton.isSelected()) {

            shapeClass = Shape.createShape
                    (shapeTypesList.get(0),
                            getMouseX() - (double)size / 2,
                            getMouseY() - (double) size/ 2,
                            graphicsContext,getShapeSizeAsDouble(),
                            getColorPickerTest());

            //creatingShape(colorPicker);

        } else if (rectangleButton.isSelected() && !cirkelButton.isSelected()) {

            shapeClass = Shape.createShape(
                    shapeTypesList.get(1),
                    getMouseX() - (double) size /2,
                    getMouseY() - (double) size/2,
                    graphicsContext,getShapeSizeAsDouble(),
                    getColorPickerTest());

        }
        return null;
    }

    public void creatingShape(ColorPicker colorPicker) {
//        var shapeToList = new Shape(getMouseX(),getMouseY(),colorPicker, getShapeSizeAsDouble());
//
//        shapeList2.add(shapeToList);
//
//        System.out.println(shapeList2.toString());

    }
    public void onSaveAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));

        File filePath = fileChooser.showSaveDialog(stage);

        if( filePath != null)
        saveToFile(filePath.toPath());
    }
    public void saveToFile(Path file) {
        StringBuilder outPut = new StringBuilder();
        List<String> list = new ArrayList();
        list.add("<svg width=\"1540.0\" height=\"740.0\" xmlns=\"http://www.w3.org/2000/svg/\" version=\"1.1\">");

        for (Shape p : shapeList2) {
            outPut.append(p.getMouseX());
            outPut.append(",");
            outPut.append(p.getMouseY());
            outPut.append(",");
            //outPut.append(p.getColor());
            outPut.append(",");
            //outPut.append(p.getSize());
            outPut.append("\n");
        }

        list.add(String.valueOf(outPut));
        list.add("</svg>");
        try {
            Files.writeString(file, outPut.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void onAddAction(ActionEvent actionEvent,ColorPicker colorPicker) {
        creatingShape(colorPicker);
    }


    public void undo(GraphicsContext graphicsContext, ColorPicker colorPicker) {


    }
    public Shape drawCirkel(GraphicsContext graphicsContext, ColorPicker colorPicker) {

        try {
            graphicsContext.setFill(colorPicker.getValue());
            graphicsContext.fillOval(getMouseX() - (double)size / 2, getMouseY() - (double)size / 2, getShapeSizeAsDouble(), getShapeSizeAsDouble());

        } catch (Exception e) {
            System.out.println("Error with draw");
        }
        return null;
    }

    public Shape drawRectangle(GraphicsContext graphicsContext, ColorPicker colorPicker) {

        try {
            graphicsContext.setFill(colorPicker.getValue());
            graphicsContext.fillRect(getMouseX() - (double)size / 2, getMouseY() - (double)size / 2, getShapeSizeAsDouble(), getShapeSizeAsDouble());
        } catch (Exception e) {
            System.out.println("Error with draw");
        }
        return null;
    }
//


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






package com.example.labb3;

import com.example.labb3.Shapes.*;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class GameViewController {

    public Canvas canvas;
    public GraphicsContext graphicsContext;

    public Model model = new Model();
    public ColorPicker colorPicked;

    public TextField sizeTextField;


    public ChoiceBox<ShapeType> choiceBox;
    public ToggleButton toggleButton;


    //Todo Save objects - export av ritade objekt ska kunna ske som svg-format
   // Make 2x test

    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
        sizeTextField.textProperty().bindBidirectional(model.shapeSizeProperty());

        choiceBox.setItems(model.shapeTypesList);
        choiceBox.setValue(ShapeType.CIRCLE);

        colorPicked.valueProperty().bindBidirectional(model.colorPickerProperty());

        choiceBox.valueProperty().bindBidirectional(model.shapeTypeObjectPropertyProperty());

    }


    public void canvasClicked(MouseEvent mouseEvent) {
        model.setMouseX(mouseEvent.getX());
        model.setMouseY(mouseEvent.getY());

        graphicsContext.clearRect(0,0, canvas.getWidth(), canvas.getHeight());


        model.createObjekt(graphicsContext);

        for (Shape shape : model.shapeList) {
            shape.draw(graphicsContext);
        }

    }



    public void saveButton() {
        model.onSaveAction();
    }

    public void undoButton() {
        model.undoCommand();
        graphicsContext.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

        for (Shape shape : model.shapeList) {
            shape.draw(graphicsContext);
        }
    }


    public void toggleButton() {
    }
}



package com.example.labb3;

import com.example.labb3.Shapes.*;

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
        sendingMouseCourseToModel(mouseEvent);

        if (toggleButton.isSelected()) {
            if (choiceBox.getValue().equals(ShapeType.CIRCLE)) {
                updateCirkel();
            }
                updateRectangle();

                drawOnCanvas();

        } else {
            creatingObjektOnCanvas();

        }
    }

    private void creatingObjektOnCanvas() {
        clearCanvas();

        model.createObjekt(graphicsContext);

        drawOnCanvas();
    }

    private void clearCanvas() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawOnCanvas() {
        for (Shape shape : model.shapeList) {
            shape.draw(graphicsContext);
        }
    }

    public void updateRectangle() {
        if (model.checkIsInsideShape().isEmpty())
            return;
        clearCanvas();
        model.checkIsInsideShape().ifPresent(shape -> shape.setColor(model.getColorPicker()));
        clearCanvas();
        model.checkIsInsideShape().ifPresent(shape -> shape.setSize(model.getShapeSizeAsDouble() ));

    }
    public void updateCirkel() {
        if (model.checkIsInsideShape().isEmpty())
            return;
        clearCanvas();
        model.checkIsInsideShape().ifPresent(shape -> shape.setColor(model.getColorPicker()));
        model.checkIsInsideShape().ifPresent(shape -> shape.setSize(model.getShapeSizeAsDouble()));
    }
    private void sendingMouseCourseToModel(MouseEvent mouseEvent) {
        model.setMouseX(mouseEvent.getX());
        model.setMouseY(mouseEvent.getY());
    }


    public void saveButton() {
        model.onSaveAction();
    }

    public void undoButton() {
        model.undoCommand();
        clearCanvas();

        drawOnCanvas();
    }


    public void toggleButton() {


    }
}



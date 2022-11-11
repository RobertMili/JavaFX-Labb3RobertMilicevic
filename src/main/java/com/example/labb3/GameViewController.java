package com.example.labb3;

import com.example.labb3.Model.Model;
import com.example.labb3.Shapes.Shape;
import com.example.labb3.Shapes.ShapeType;
import com.example.labb3.SvgFile.SvgReading;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameViewController {

    public Canvas canvas;
    public GraphicsContext graphicsContext;

    public Model model = new Model();
    public ColorPicker colorPicked;

    public TextField sizeTextField;


    public ChoiceBox<ShapeType> choiceBox;
    public ToggleButton toggleButton;
    public Stage stage;

    public ToggleButton sendingToServer;


    public void initialize() {


        graphicsContext = canvas.getGraphicsContext2D();
        sizeTextField.textProperty().bindBidirectional(model.shapeSizeProperty());

        choiceBox.setItems(model.shapeTypesList);
        choiceBox.setValue(ShapeType.CIRCLE);


        colorPicked.valueProperty().bindBidirectional(model.colorPickerProperty());

        choiceBox.valueProperty().bindBidirectional(model.shapeTypeObjectPropertyProperty());


        model.connectToServer();


    }


    public void canvasClicked(MouseEvent mouseEvent) {

        sendingMouseCourseToModel(mouseEvent);


        if (toggleButton.isSelected()) {


            if (choiceBox.getValue().equals(ShapeType.CIRCLE)) {
                updateCirkel();


            }
            updateRectangle();

            drawOnCanvas();

            sendShapeToServer();

        } else {
            model.setColorPicker2(model.getColorPicker());
            creatingObjektOnCanvas();


            sendShapeToServer();

        }
    }

    private void creatingObjektOnCanvas() {
        clearCanvas();

        model.createObjekt();

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
        model.checkIsInsideShape().ifPresent(shape -> shape.setSize(model.getShapeSizeAsDouble()));


    }

    public void updateCirkel() {
        if (model.checkIsInsideShape().isEmpty())
            return;

        clearCanvas();
        model.checkIsInsideShape().ifPresent(shape -> shape.setColor(model.getColorPicker()));
        System.out.println(model.getColorPicker().toString());
        model.checkIsInsideShape().ifPresent(shape -> shape.setSize(model.getShapeSizeAsDouble()));

    }

    private void sendingMouseCourseToModel(MouseEvent mouseEvent) {
        model.setMouseX(mouseEvent.getX());
        model.setMouseY(mouseEvent.getY());
    }

    public void saveButton() {
        SvgReading svgFile = new SvgReading();
        svgFile.save(model);

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void undoButton() {
        model.undoCommand();
        clearCanvas();

        model.checkIsInsideShape().ifPresent(shape -> shape.setColor(model.getColorPicker2()));
        drawOnCanvas();

    }

    public void redoButton() {
        model.redoCommand();
        clearCanvas();

        drawOnCanvas();
    }


    public void toggleButton() {
        }


    public void sendShapeToServer() {

        if (sendingToServer.isSelected()) {
            model.sendShape(model.lastShape().drawSVG());
        }

    }
}



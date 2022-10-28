package com.example.labb3;

import com.example.labb3.Shapes.*;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GameViewController {

    public Button cirkelButton;
    public Canvas canvas;
    public GraphicsContext graphicsContext;

    public Model model = new Model();
    public Button rectangleButton;
    public ColorPicker colorPicked;

    public Button deleteButton = new Button();
    public Button changeSizeButton;
    public TextField sizeTextField;
    public Cirkel cirkelClass;

    TextField firstValue = new TextField();

    Cirkel cirkel;
    Position position;

    //Todo Factory eller Builder pattern
    //TOdo Utritning av nya shapes ska ske genom att klicka med musen där objektet ska ritas ut. // if is click on button draw or nothing
    //Todo Redan utritade objekt ska kunna väljas genom att gå över i select mode och klicka på skärmen.
    /*
    Redan utritade objekt ska kunna väljas genom att gå över i select mode och klicka på skärmen.
    Använd musens koordinater för att leta upp det objekt du klickat på.
    Tips! Implementera en metod på dina shapes för att fråga om koordinaterna är inom shapens area.
     */
    // Mouse position to record class
    //todo Undo button kolla på slide
    //todo Save objects - export av ritade objekt ska kunna ske som svg-format
    //Todo test

    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
        sizeTextField.textProperty().bindBidirectional(model.shapeSizeProperty());

    }

    public void onCirkelClicked(ActionEvent actionEvent) {

        model.drawCirkel(graphicsContext,colorPicked);
    }

    public void onRectangleClicked(ActionEvent actionEvent) {

        //model.drawRectangle(graphicsContext,colorPicked);
        model.addShapeToList(graphicsContext,colorPicked);
    }

    public void canvasClicked(MouseEvent mouseEvent) {

        model.setMouseX(mouseEvent.getX());
        model.setMouseY(mouseEvent.getY());

    }

    public void sizeTextField(ActionEvent actionEvent) {


//        String test = "";
//
//        System.out.println("asdas");
//        int value = Integer.parseInt(firstValue.getText());
//        firstValue.setText(test);
//
//        System.out.println("This is a integer: " + value);
//        System.out.println("This is a String " +test);
    }

    public void changeSizeButton(ActionEvent actionEvent) {
        model.changeSizeOnSelectedShapes();
    }
}
/*
    Trying to do deleteButton
     public void deleteButton(ActionEvent actionEvent) {
        var contex = canvas.getGraphicsContext2D();

        contex.clearRect(0, 0,canvas.getWidth(), canvas.getHeight());


    }
    public void delete() {
        graphicsContext.setFill(Color.TRANSPARENT);
        //in
    }
 */



package com.example.labb3;

import com.example.labb3.Shapes.*;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import java.io.File;
import java.nio.file.Path;

public class GameViewController {


    public Canvas canvas;
    public GraphicsContext graphicsContext;

    public Model model = new Model();

    public ColorPicker colorPicked;

    public Button deleteButton = new Button();

    public Button changeSizeButton;
    public TextField sizeTextField;

    public Cirkel cirkelClass;
    public ToggleButton rectangleButton;
    public ToggleButton cirkelButton;
    public ChoiceBox <ShapeType>choiceBox;

    TextField firstValue = new TextField();
    Path file;

    Cirkel cirkel;
    Position position;
    Shape shapeClass;
   // ObservableList<ShapeType>shapeTypesList = FXCollections.observableArrayList(ShapeType.values());


    //Todo Redan utritade objekt ska kunna väljas genom att gå över i select mode och klicka på skärmen.
    /*
    Redan utritade objekt ska kunna väljas genom att gå över i select mode och klicka på skärmen.
    Använd musens koordinater för att leta upp det objekt du klickat på.
    Tips! Implementera en metod på dina shapes för att fråga om koordinaterna är inom shapens area.
     */
    // Mouse position to record class
    //todo Undo button kolla på slide
    //Todo Save objects - export av ritade objekt ska kunna ske som svg-format
    //Todo test

    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
        sizeTextField.textProperty().bindBidirectional(model.shapeSizeProperty());

        choiceBox.setItems(model.shapeTypesList);
        choiceBox.setValue(ShapeType.CIRCLE);

        colorPicked.valueProperty().bindBidirectional(model.colorPickerProperty());

       choiceBox.valueProperty().bindBidirectional(model.shapeTypeObjectPropertyProperty());    //bind it with Model this Martins tips

    }


    public void canvasClicked(MouseEvent mouseEvent) {
//
       model.setMouseX(mouseEvent.getX());
       model.setMouseY(mouseEvent.getY());

       model.choiceButton(cirkelButton,rectangleButton,graphicsContext);
       //model.creatObjekt(graphicsContext);

    }

    public void sizeTextField(ActionEvent actionEvent) {
    }

    public void cirkelButton(ActionEvent actionEvent) {

    }

    public void saveButton(ActionEvent actionEvent) {

    model.onSaveAction();
    }

    public void undoButton(ActionEvent actionEvent) {
//        GraphicsContext context = canvas.getGraphicsContext2D();
//        model.shapeList2.remove(model.shapeList2.size() - 1);
//        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        draw(graphicsContext);
    }

   // public void draw(GraphicsContext graphicsContext) {
//        System.out.println("test!");
//        for (var shape : model.shapeList2) {
//
//        }
//        System.out.println("test2");
  //  }
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



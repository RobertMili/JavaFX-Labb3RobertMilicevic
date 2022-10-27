package com.example.labb3;

import com.example.labb3.Shapes.*;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GameViewController {
    public Button cirkelButton;
    public Canvas canvas;
    public GraphicsContext graphicsContext;

    public Model model = new Model();
    public Button rectangleButton;
    public ColorPicker colorPicked;

    Cirkel cirkel;


    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public void onCirkelClicked(ActionEvent actionEvent) {

        model.cirkel.draw(graphicsContext,colorPicked);


    }

    public void onRectangleClicked(ActionEvent actionEvent) {

        model.rectangle.draw(graphicsContext,colorPicked);
    }

    public void canvasClicked(MouseEvent mouseEvent) {

       model.setMouseX(mouseEvent.getX());
       model.setMouseY(mouseEvent.getY());

    }
}

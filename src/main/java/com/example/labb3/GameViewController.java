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


    public Button rectangleButton;

    public Rectangle rectangle = new Rectangle(new Position(50,50),Color.AQUA,2.0);
    public Cirkel cirkel = new Cirkel(new Position(50,50), Color.AQUA,2);
    public ColorPicker colorPicked;


    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();

    }

    public void onCirkelClicked(ActionEvent actionEvent) {
        //graphicsContext.setFill(colorPicked.getValue());
        cirkel.paint(graphicsContext);
    }

    public void onRectangleClicked(ActionEvent actionEvent) {

        rectangle.paint(graphicsContext);


    }

    public void canvasClicked(MouseEvent mouseEvent) {


    }
}

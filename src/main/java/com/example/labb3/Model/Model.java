package com.example.labb3.Model;

import com.example.labb3.Shapes.Shape;
import com.example.labb3.Shapes.ShapeType;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

@FunctionalInterface
interface Command {
    void execute();
}


public class Model {
    private final StringProperty shapeSize;
    private final ObjectProperty<Color> colorPicker;
    private final ObjectProperty<ShapeType> shapeTypeObjectProperty;

    public ObservableList<Shape> shapeList;
    public ObservableList<ShapeType> shapeTypesList;
    Deque<Command> undoStack = new ArrayDeque<>();
    Deque<Command> redoStack = new ArrayDeque<>();
    private double mouseX;
    private double mouseY;


    private PrintWriter writer;
    private BufferedReader reader;

    public BooleanProperty ServerConnected = new SimpleBooleanProperty();
    public ObservableList<String> shapeList2 = FXCollections.observableArrayList();


    public BooleanProperty serverConnectedProperty() {
        return ServerConnected;
    }


    public Model() {
        this.shapeSize = new SimpleStringProperty("50");

        this.colorPicker = new SimpleObjectProperty<>(Color.BLUE);
        this.shapeTypeObjectProperty = new SimpleObjectProperty<>(ShapeType.CIRCLE);
        this.shapeList = FXCollections.observableArrayList();
        this.shapeTypesList = FXCollections.observableArrayList(ShapeType.values());


    }

    public ObservableList<Shape> getShapeList() {
        return shapeList;
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

    public void createObjekt() {
        var creatingObjekt = Shape.createShape
                (getShapeTypeObjectProperty(),
                        getMouseX(),
                        getMouseY(),
                        getShapeSizeAsDouble(),
                        getColorPicker());

        shapeList.add(creatingObjekt);
        addUndo(creatingObjekt);
        addRedo(creatingObjekt);
        shapeList2.add(creatingObjekt.toString());



    }

    public void addUndo(Shape creatingObjekt) {
        Command undo = () -> shapeList.remove(creatingObjekt);
        undoStack.push(undo);
    }

    private void addRedo(Shape creatingObjekt) {
        Command redo = () -> shapeList.add(creatingObjekt);
        redoStack.push(redo);

    }

    public void undoCommand() {
        Command undoToExecute = undoStack.pop();
        undoToExecute.execute();
        
    }

    public void redoCommand() {
        Command undoToExecute = redoStack.pop();
        undoToExecute.execute();
    }


    public Optional<Shape> checkIsInsideShape() {
        return shapeList.stream()
                .filter(shape -> shape.isInsideShape(getMouseX(), getMouseY()))
                .reduce((first, second) -> second);

    }


    public void connectToServer() {

        try {
            Socket socket = new Socket("localhost", 8000);
            OutputStream output = socket.getOutputStream();

            writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();

            reader = new BufferedReader(new InputStreamReader(input));


            var thread = new Thread(() -> {
                try {
                    while (true) {
                        serverConnectedProperty().setValue(false);
                        String line = reader.readLine();
                        Platform.runLater(() -> shapeList2.add(line));

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.setDaemon(true);
            thread.start();

        } catch (IOException e) {

            System.out.println("Error");
            throw new RuntimeException(e);
        }

    }

    public void sendShape(String string) {

        writer.println(string);

    }

    public Shape lastShape() {
        return shapeList.get(shapeList.size() - 1);
    }



}













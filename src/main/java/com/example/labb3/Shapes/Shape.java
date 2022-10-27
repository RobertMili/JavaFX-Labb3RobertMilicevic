package com.example.labb3.Shapes;

import com.example.labb3.Model;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Shape  {

    private Position position;
    private Color color;
    private double size;

    public Shape(Position position, Color color, double size) {
        this.position = position;
        this.color = color;
        this.size = size;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Double.compare(shape.size, size) == 0 && Objects.equals(position, shape.position) && Objects.equals(color, shape.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, color, size);
    }
}

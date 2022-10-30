package com.example.labb3.Shapes;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import java.util.Objects;

public final class Position {
    private double x;
    private final double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Position) obj;
        return Double.doubleToLongBits(this.x) == Double.doubleToLongBits(that.x) &&
                Double.doubleToLongBits(this.y) == Double.doubleToLongBits(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position[" +
                "x=" + x + ", " +
                "y=" + y + ']';
    }


}

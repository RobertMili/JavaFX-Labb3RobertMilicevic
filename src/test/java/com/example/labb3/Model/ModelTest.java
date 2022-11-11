package com.example.labb3.Model;

import com.example.labb3.Shapes.Cirkel;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class ModelTest {
    Model model = new Model();


    @Test
    void testListIsNotEmpty() {

        model.shapeList.add(new Cirkel(20.0,20.0,50, Color.AQUA));

        var expected = 1;
        var actual = model.shapeList.size();


        assertEquals(expected, actual);
    }

    @Test
    void testingDefaultShapeSize() {

        var actual = new Model().getShapeSize();


        assertEquals("50",actual);


    }
    @Test
    void checkDefaultColor() {
        var actual = new Model().getColorPicker();

        assertEquals(Color.BLUE, actual);
    }
    @Test
    void checkUndoList() {
        model.shapeList.add(new Cirkel(20.0,20.0,50, Color.AQUA));
        model.createObjekt();
        model.undoCommand();

        var actual = model.shapeList.size();

        assertEquals(1,actual);

    }

}
package com.example.labb3.SvgFile;

import com.example.labb3.Model.Model;
import com.example.labb3.Shapes.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SvgReading {
    FileChooser fileChooser = new FileChooser();

    public void save(Model model) {

        fileChooser.setTitle("Save to SVG");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVGFile", "*.svg"));
        Path path = Path.of(fileChooser.showSaveDialog(new Stage()).toURI());

        List<String> strings = new ArrayList<>();

        buildSVGString(model,strings);



        try {
            Files.write(path, strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildSVGString(Model model, List<String> strings) {
        strings.add(startOfSVGString());
        model.getShapeList().forEach(shape -> svgShapeToString(shape,strings));
        strings.add(endOfSVGStrings());
    }


    private static String startOfSVGString() {
        return String.join(" ",
                "<svg",
                "xmlns=\"http://www.w3.org/2000/svg\"",
                "version=\"1.1\"",
                "width=\"700.0\"",
                "height=\"700.0\">"
                );
    }
    private static void svgShapeToString(Shape shape, List<String> strings) {
        strings.add(String.join("", shape.drawSVG()));
    }

    private String endOfSVGStrings() {
    return "</svg>";
    }

}

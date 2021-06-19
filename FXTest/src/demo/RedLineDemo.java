package demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class RedLineDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox box = new VBox();
        final Scene scene = new Scene(box, 300,250);
        scene.setFill(null);

        Line line = new Line();
        line.setStartX(0.0f);
        line.setStartY(0.0f);
        line.setEndX(200.0f);
        line.setEndY(200.0f);
        line.setStroke(Color.RED);
        line.setStrokeWidth(10f);

        line.getStrokeDashArray().addAll(15d, 5d, 15d, 15d, 20d);
        line.setStrokeDashOffset(10);

        box.getChildren().add(line);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

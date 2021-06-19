package demo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RectangleDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("矩形示例");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.WHITE);

        Rectangle r = new Rectangle();
        r.setX(100);
        r.setY(100);
        r.setWidth(100);
        r.setHeight(100);
        r.setFill(Color.BLUE);

        root.getChildren().add(r);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

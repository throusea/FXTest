package demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SlideShowApp extends Application {

    private Image[] imageURLs = {
            new Image("https://www.bekwam.net/images/bekwam_rc_charging.png"),
            new Image("https://www.bekwam.net/images/bekwam_rc_discharging.png"),
            new Image("https://www.bekwam.net/images/bekwam_rl_scope.png")
    };

    private Label[] labels = {
            new Label("Hello"),
            new Label("World"),
            new Label("!")
    };

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pagination pagination = new Pagination(imageURLs.length, 0);
        pagination.setPageFactory(
                pageIndex -> new ImageView(imageURLs[pageIndex])
        );

        Pagination pagination1 = new Pagination(labels.length, 0);
        pagination1.setPageFactory(param -> labels[param]);

        Timeline timeline = new Timeline();

        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(pagination.currentPageIndexProperty(), 1)),
                new KeyFrame(Duration.millis(1000), new KeyValue(pagination.currentPageIndexProperty(), 10))
        );

        timeline.play();

        VBox vbox = new VBox( pagination1 );

        HBox hbox = new HBox( pagination);

        vbox.getChildren().addAll(pagination);

        //Group group = new Group();

        //group.getChildren().addAll(pagination, pagination1);

        Scene scene = new Scene(vbox);

        primaryStage.setScene( scene );
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



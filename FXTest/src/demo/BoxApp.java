package demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

public class BoxApp extends Application {

    HBox hbox = new HBox();

    VBox vbox = new VBox();

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vbox = new VBox();

        Button btnRefresh = new Button("Refresh");

        HBox topRightControls = new HBox();

        topRightControls.getChildren().add(new Label("Hello world!"));

        vbox.getChildren().addAll(btnRefresh, topRightControls);

        TableView<Pair<String, String>> tblCustomer = new TableView<>();

        TableColumn<Pair<String, String>, String> col1 = new TableColumn<>("col1");
        TableColumn<Pair<String, String>, String> col2 = new TableColumn<>("col2");
        TableColumn<Pair<String, String>, String> col3 = new TableColumn<>("col3");
        TableColumn<Pair<String, String>, String> col4 = new TableColumn<>("col4");

        tblCustomer.getColumns().addAll(col1, col2, col3, col4);


        tblCustomer.getItems().addAll(
                new Pair<>("Likai", "1"),
                new Pair<>("Beijihu", "2"),
                new Pair<>("Zitongtong", "3")
        );

        Separator sep = new Separator();

        HBox bottomControls = new HBox();

        Button btnClose = new Button("Close");

        bottomControls.getChildren().addAll(btnClose);

        vbox.getChildren().addAll(tblCustomer, sep, bottomControls);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

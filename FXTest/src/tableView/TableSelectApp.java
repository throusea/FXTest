package tableView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableSelectApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        TableView<Item> tblItems = new TableView<>();
        VBox.setVgrow(tblItems, Priority.ALWAYS);

        TableColumn<Item, String> colSKU = new TableColumn<>("SKU");
        TableColumn<Item, String> colDescr = new TableColumn<>("Item");
        TableColumn<Item, Float> colPrice = new TableColumn<>("Price");
        TableColumn<Item, Boolean> colTaxable = new TableColumn<>("Tax");
        colSKU.setCellValueFactory( new PropertyValueFactory<>("sku"));
        colDescr.setCellValueFactory( new PropertyValueFactory<>("descr"));
        colPrice.setCellValueFactory( new PropertyValueFactory<>("price"));
        colTaxable.setCellValueFactory( new PropertyValueFactory<>("taxable"));

        tblItems.getColumns().addAll(colSKU, colDescr, colPrice, colTaxable);
        tblItems.getItems().addAll(
                new Item("KBD-0455892", "Mechanical Keyboard", 100.0f, true),
                new Item( "145256", "Product Docs", 0.0f, false ),
                new Item( "OR-198975", "O-Ring (100)", 10.0f, true)
        );

        VBox vbox = new VBox();

        vbox.getChildren().addAll(tblItems);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

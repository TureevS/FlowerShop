package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button productButton;
    @FXML
    private Button deliveriesButton;
    @FXML
    private Button staffButton;
    @FXML
    private Button bookkeepingButton;

    @FXML
    void initialize() {

        productButton.setOnAction(event -> open("/sample/Products/product.fxml", productButton));
        deliveriesButton.setOnAction(event -> open("/sample/Shops/shops.fxml", deliveriesButton));
        staffButton.setOnAction(event -> open("/sample/Employees/employees.fxml", staffButton));
        bookkeepingButton.setOnAction(event -> open("/sample/Bookkeeping/bookkeeping.fxml", bookkeepingButton));
    }

    private void open(String w, Button b) {
        Stage stage = (Stage) b.getScene().getWindow();

        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(w));
        Parent root1 = null;
        try {
            root1 = fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("fjør");
        assert root1 != null;
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
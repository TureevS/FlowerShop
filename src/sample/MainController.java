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

        productButton.setOnAction(event ->{
            open("/sample/product.fxml", productButton);
        });
        deliveriesButton.setOnAction(event ->{
            open("/sample/Deliveries.fxml", deliveriesButton);
        });
        staffButton.setOnAction(event ->{
            open("/sample/staff.fxml", staffButton);
        });
        bookkeepingButton.setOnAction(event ->{
            open("/sample/Bookkeeping.fxml", bookkeepingButton);
        });

    }

    private void open(String w, Button b) {
        Stage stage = (Stage) b.getScene().getWindow();

        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(w));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Untitled");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
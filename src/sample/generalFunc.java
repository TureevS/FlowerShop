package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class generalFunc {
    DatabaseHandler dbHandler = new DatabaseHandler();
    public void open(String w, Button b) {
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

    public void updateChoiceBox(String column, String table, ComboBox<String> ChoiceBox) throws SQLException, ClassNotFoundException {
        ResultSet rs = dbHandler.selectData("SELECT " + column + " FROM " + table);
        ObservableList<String> tmp = FXCollections.observableArrayList();
        while(rs.next()){
            tmp.add(rs.getString(1));
        }
        ChoiceBox.setItems(tmp);
    }
}

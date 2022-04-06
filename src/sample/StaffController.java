package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Animations.Shake;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffController {

    @FXML
    private Button StPageProductButton;

    @FXML
    private Button StPageDeliveriesButton;

    @FXML
    private Button StPageBookkeepingButton;

    @FXML
    private Button StPageAddButton;

    @FXML
    private Button delButton;

    @FXML
    private Button staffUpdate;

    @FXML
    private TextField salaryTextField;

    @FXML
    private TextField fioTextField;

    @FXML
    private TextField positionTextField;

    @FXML
    private TextField idForDelTextField;

    @FXML
    private TextField storeIdTextField2;

    @FXML
    private TextField storeIdTextField;

    @FXML
    private TextField fioTextField2;

    @FXML
    private TextField positionTextField2;

    @FXML
    private TextField salaryTextField2;

    @FXML
    private TextField idForEditTextField;

    @FXML
    private Button StPageEditButton;

    @FXML
    private TableView<staff> StaffTable;

    @FXML
    private TableColumn<staff, Integer> idColumn;

    @FXML
    private TableColumn<staff, String> fioColumn;

    @FXML
    private TableColumn<staff, String> positionColumn;

    @FXML
    private TableColumn<staff, Integer> storeidColumn;

    @FXML
    private TableColumn<staff, Integer> salaryColumn;

    @FXML
    private Button stPageMenuButton;

    @FXML
    void initialize() {

        stPageMenuButton.setOnAction(event ->{
            open("/sample/sample.fxml", stPageMenuButton);
        });

        StPageProductButton.setOnAction(event ->{
            open("/sample/product.fxml", StPageProductButton);
        });
        StPageDeliveriesButton.setOnAction(event ->{
            open("/sample/Deliveries.fxml", StPageDeliveriesButton);
        });
        StPageBookkeepingButton.setOnAction(event ->{
            open("/sample/Bookkeeping.fxml", StPageBookkeepingButton);
        });

        StPageAddButton.setOnAction(event->{
            try {
                addData();
            } catch (SQLException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Невозможно добавить данного сотрудника, так как не существует соответствующих данных в других таблицах!");
                alert.showAndWait();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        staffUpdate.setOnAction(event ->{
            try {
                updateTable();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        delButton.setOnAction(event ->{
            try {
                delData();
            } catch (SQLException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Невозможно удалить данного сотрудника, так как существуют связанные с другими таблицами данные!");
                alert.showAndWait();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        StPageEditButton.setOnAction(event ->{
            try {
                editData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void addData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String fio = fioTextField.getText().trim();
        String position = positionTextField.getText().trim();
        String storeIdStr = storeIdTextField.getText().trim();
        String salaryStr = salaryTextField.getText().trim();
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(0);
        data.add(fio);
        data.add(position);
        data.add(storeIdStr);
        data.add(salaryStr);

        if(!fio.equals("") && !position.equals("")&& !storeIdStr.equals("") && !salaryStr.equals("")){
            dbHandler.insertRow(data, "employees");
        }
        else{
            Shake S1 = new Shake(fioTextField);
            Shake S2 = new Shake(positionTextField);
            Shake S3 = new Shake(storeIdTextField);
            Shake S4 = new Shake(salaryTextField);
            S1.play();
            S2.play();
            S3.play();
            S4.play();
        }
        updateTable();
        fioTextField.clear();
        positionTextField.clear();
        storeIdTextField.clear();
        salaryTextField.clear();
    }

    private void delData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = idForDelTextField.getText().trim();
        String condition = "employeeId = ";
        condition = condition.concat(idStr);
        dbHandler.deleteData("employees", condition);
        updateTable();
        idForDelTextField.clear();
    }

    private void updateTable() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet rs = dbHandler.selectData("SELECT * FROM employees");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 5; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<staff, Integer>("id"));
        fioColumn.setCellValueFactory(new PropertyValueFactory<staff, String>("fio"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<staff, String>("position"));
        storeidColumn.setCellValueFactory(new PropertyValueFactory<staff, Integer>("id2"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<staff, Integer>("salary"));

        ObservableList<staff> staffData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            staffData.add(new staff(Integer.parseInt(result.get(i).get(0)), result.get(i).get(1),result.get(i).get(2),
                    Integer.parseInt(result.get(i).get(3)),Integer.parseInt(result.get(i).get(4))));
        }
        StaffTable.setItems(staffData);
    }

    private void editData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = idForEditTextField.getText().trim();
        String fio = fioTextField2.getText().trim();
        String position = positionTextField2.getText().trim();
        String storeIdStr = storeIdTextField2.getText().trim();
        String salaryStr = salaryTextField2.getText().trim();
        String condition = "employeeId = ";
        condition = condition.concat(idStr);

        if(!idStr.equals("")){
            if(!fio.equals("")){
                dbHandler.updateData("employees", "employeeFIO", fio, condition);
            }
            if(!position.equals("")){
                dbHandler.updateData("employees", "employeePosition", position, condition);
            }
            if(!storeIdStr.equals("")){
                dbHandler.updateData("employees", "employeeShopId", storeIdStr, condition);
            }
            if(!salaryStr.equals("")){
                dbHandler.updateData("employees", "employeeSalary", salaryStr, condition);
            }
        }
        else{
            Shake S1 = new Shake(idForEditTextField);
            S1.play();
        }
        updateTable();
        fioTextField2.clear();
        positionTextField2.clear();
        storeIdTextField2.clear();
        salaryTextField2.clear();
        idForEditTextField.clear();
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
        stage.setTitle("fjør");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}


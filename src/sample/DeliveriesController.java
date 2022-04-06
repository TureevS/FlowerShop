package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Animations.Shake;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveriesController {

    @FXML
    private AnchorPane storePane;

    @FXML
    private TableView<shop> storeTable;

    @FXML
    private TableColumn<shop, Integer> shopIdColumn;

    @FXML
    private TableColumn<shop, String> shopTitleColumn;

    @FXML
    private TableColumn<shop, String> shopAdressColumn;

    @FXML
    private TableColumn<shop, Integer> shopRevenueColumn;

    @FXML
    private Button shopUpdate;

    @FXML
    private AnchorPane shopMenuPane;

    @FXML
    private TextField shopTitleTextField;

    @FXML
    private TextField shopRevenueTextField;

    @FXML
    private TextField shopIdForDelTextField;

    @FXML
    private Button shopAddButton;

    @FXML
    private Button shopDelButton;

    @FXML
    private TextField shopTitleEditTextField;

    @FXML
    private TextField shopRevenueEditTextField;

    @FXML
    private Button shopEditButton;

    @FXML
    private TextField shopAdressEditTextField;

    @FXML
    private TextField shopAdressTextField;

    @FXML
    private TextField shopIdForEditTextField;

    @FXML
    private Button DelPageStaffButton;

    @FXML
    private Button DelPageProductButton;

    @FXML
    private Button DelPageBookkeepingButton;

    @FXML
    private Button storeButton;

    @FXML
    private Button suppliersButton;

    @FXML
    private AnchorPane suppliersPane;

    @FXML
    private TableView<suppliers> suppliersTable;

    @FXML
    private TableColumn<suppliers, Integer> suppliersIdColumn;

    @FXML
    private TableColumn<suppliers, String> suppliersAddressColumn;

    @FXML
    private TableColumn<suppliers, String> suppliersContactsColumn;

    @FXML
    private Button suppliersUpdate;

    @FXML
    private AnchorPane suppliersMenuPane;

    @FXML
    private TextField suppliersAddressTextField;

    @FXML
    private TextField idForDelSuppliersTextField;

    @FXML
    private Button suppliersAddButton;

    @FXML
    private Button suppliersDelButton;

    @FXML
    private TextField suppliersAddressEditTextField;

    @FXML
    private Button suppliersEditButton;

    @FXML
    private TextField suppliersContactEditTextField;

    @FXML
    private TextField suppliersContactTextField;

    @FXML
    private TextField idForEditSuppliersTextField;

    @FXML
    private Button delPageMenuButton;

    @FXML
    void initialize() {

        delPageMenuButton.setOnAction(event ->{
            open("/sample/sample.fxml", delPageMenuButton);
        });

        DelPageProductButton.setOnAction(event ->{
            open("/sample/product.fxml", DelPageProductButton);
        });
        DelPageStaffButton.setOnAction(event ->{
            open("/sample/staff.fxml", DelPageStaffButton);
        });
        DelPageBookkeepingButton.setOnAction(event ->{
            open("/sample/Bookkeeping.fxml", DelPageBookkeepingButton);
        });


        shopAddButton.setOnAction(event ->{
            try {
                addShopData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        shopDelButton.setOnAction(event ->{
            try {
                delShopData();
            } catch (SQLException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Невозможно удалить данный магазин, так как существуют связанные с другими таблицами данные!");
                alert.showAndWait();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        shopUpdate.setOnAction(event ->{
            try {
                updateShopTable();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        shopEditButton.setOnAction(event ->{
            try {
                editShopData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });



        suppliersAddButton.setOnAction(event ->{
            try {
                addSuppliersData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        suppliersDelButton.setOnAction(event ->{
            try {
                delSuppliersData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        suppliersUpdate.setOnAction(event ->{
            try {
                updateSuppliersTable();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        suppliersEditButton.setOnAction(event ->{
            try {
                editSuppliersData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });



        storeButton.setOnAction(event ->{
            ShowStores();
        });
        suppliersButton.setOnAction(event ->{
            ShowSuppliers();
        });
    }

    //ТАБЛИЦА: МАГАЗИНЫ

    private void addShopData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String title = shopTitleTextField.getText().trim();
        String address = shopAdressTextField.getText().trim();
        String revenue = shopRevenueTextField.getText().trim();
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(0);
        data.add(title);
        data.add(address);
        data.add(revenue);

        if(!title.equals("") && !address.equals("") && !revenue.equals("")){
            dbHandler.insertRow(data, "shops");
            updateShopTable();
            shopTitleTextField.clear();
            shopAdressTextField.clear();
            shopRevenueTextField.clear();
        }
        else{
            Shake s1 = new Shake(shopTitleTextField);
            Shake s2 = new Shake(shopAdressTextField);
            Shake s3 = new Shake(shopRevenueTextField);
            s1.play();
            s2.play();
            s3.play();
        }
    }

    private void delShopData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = shopIdForDelTextField.getText().trim();

        if(!idStr.equals("")){
            String condition = "shopId = ";
            condition = condition.concat(idStr);
            dbHandler.deleteData("shops", condition);
            updateShopTable();
            shopIdForDelTextField.clear();
        }
        else{
            Shake s1 = new Shake(shopIdForDelTextField);
            s1.play();
        }
    }

    private void updateShopTable() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet rs = dbHandler.selectData("SELECT * FROM shops");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 4; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }

        shopIdColumn.setCellValueFactory(new PropertyValueFactory<shop, Integer>("id"));
        shopTitleColumn.setCellValueFactory(new PropertyValueFactory<shop, String>("title"));
        shopAdressColumn.setCellValueFactory(new PropertyValueFactory<shop, String>("address"));
        shopRevenueColumn.setCellValueFactory(new PropertyValueFactory<shop, Integer>("revenue"));

        ObservableList<shop> shopsData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            shopsData.add(new shop(Integer.parseInt(result.get(i).get(0)), result.get(i).get(1), result.get(i).get(2),
                    Integer.parseInt(result.get(i).get(3))));
        }
        storeTable.setItems(shopsData);
    }

    private void editShopData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = shopIdForEditTextField.getText().trim();
        String title = shopTitleEditTextField.getText().trim();
        String address = shopAdressEditTextField.getText().trim();
        String revenue = shopRevenueEditTextField.getText().trim();
        String condition = "shopId = ";
        condition = condition.concat(idStr);

        if(!idStr.equals("")){
            if(!title.equals("")){
                dbHandler.updateData("shops", "shopName", title, condition);
            }
            if(!address.equals("")){
                dbHandler.updateData("shops", "shopAddress", address, condition);
            }
            if(!revenue.equals("")){
                dbHandler.updateData("shops", "revenue", revenue, condition);
            }
        }
        else{
            Shake s1 = new Shake(shopIdForEditTextField);
            s1.play();
        }
        updateShopTable();
        shopTitleEditTextField.clear();
        shopAdressEditTextField.clear();
        shopRevenueEditTextField.clear();
        shopIdForEditTextField.clear();
    }

    //ТАБЛИЦА: ПОСТАВЩИКИ

    private void addSuppliersData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String address = suppliersAddressTextField.getText().trim();
        String contacts = suppliersContactTextField.getText().trim();
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(0);
        data.add(address);
        data.add(contacts);

        if(!address.equals("") && !contacts.equals("")){
            dbHandler.insertRow(data, "suppliers");
            updateSuppliersTable();
            suppliersAddressTextField.clear();
            suppliersContactTextField.clear();
        }
        else{
            Shake s1 = new Shake(suppliersAddressTextField);
            Shake s2 = new Shake(suppliersContactTextField);
            s1.play();
            s2.play();
        }
    }

    private void delSuppliersData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = idForDelSuppliersTextField.getText().trim();

        if(!idStr.equals("")){
            String condition = "suppliersId = ";
            condition = condition.concat(idStr);
            dbHandler.deleteData("suppliers", condition);
            updateSuppliersTable();
            idForDelSuppliersTextField.clear();
        }
        else{
            Shake s1 = new Shake(idForDelSuppliersTextField);
            s1.play();
        }
    }

    private void updateSuppliersTable() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet rs = dbHandler.selectData("SELECT * FROM suppliers");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 3; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }

        suppliersIdColumn.setCellValueFactory(new PropertyValueFactory<suppliers, Integer>("id"));
        suppliersAddressColumn.setCellValueFactory(new PropertyValueFactory<suppliers, String>("address"));
        suppliersContactsColumn.setCellValueFactory(new PropertyValueFactory<suppliers, String>("contacts"));

        ObservableList<suppliers> suppliersData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            suppliersData.add(new suppliers(Integer.parseInt(result.get(i).get(0)), result.get(i).get(1), result.get(i).get(2)));
        }
        suppliersTable.setItems(suppliersData);
    }

    private void editSuppliersData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = idForEditSuppliersTextField.getText().trim();
        String address = suppliersAddressEditTextField.getText().trim();
        String contacts = suppliersContactEditTextField.getText().trim();
        String condition = "suppliersId = ";
        condition = condition.concat(idStr);

        if(!idStr.equals("")){
            if(!address.equals("")){
                dbHandler.updateData("suppliers", "suppliersAddress", address, condition);
            }
            if(!contacts.equals("")){
                dbHandler.updateData("suppliers", "suppliersContacts", contacts, condition);
            }
        }
        else{
            Shake s1 = new Shake(idForEditSuppliersTextField);
            s1.play();
        }
        updateSuppliersTable();
        suppliersAddressEditTextField.clear();
        suppliersContactEditTextField.clear();
        idForEditSuppliersTextField.clear();
    }


    //ВИДИМОСТЬ ПАНЕЛЕЙ
    public void ShowStores(){
        storePane.setVisible(true);
        suppliersPane.setVisible(false);

        shopMenuPane.setVisible(true);
        shopMenuPane.toFront();
        suppliersMenuPane.setVisible(false);

        storeButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #007439;");
        suppliersButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");

    }
    public void ShowSuppliers(){
        storePane.setVisible(false);
        suppliersPane.setVisible(true);

        shopMenuPane.setVisible(false);
        suppliersMenuPane.setVisible(true);
        suppliersMenuPane.toFront();

        suppliersButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #007439;");
        storeButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
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

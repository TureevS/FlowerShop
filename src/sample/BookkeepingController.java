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

public class BookkeepingController {

    @FXML
    private AnchorPane storyPane;

    @FXML
    private TableView<story> storyTable;

    @FXML
    private TableColumn<story, Integer> storyIdColumn;

    @FXML
    private TableColumn<story, Integer> storyEmpIdColumn;

    @FXML
    private TableColumn<story, Integer> storyShopIdColumn;

    @FXML
    private TableColumn<story, Integer> storyCostColumn;

    @FXML
    private Button storyUpdate;

    @FXML
    private AnchorPane storyMenuPane;

    @FXML
    private TextField storyEmpIdTextField;

    @FXML
    private TextField storyCostTextField;

    @FXML
    private TextField storyIdForDelTextField;

    @FXML
    private Button storyAddButton;

    @FXML
    private Button storyDelButton;

    @FXML
    private TextField storyEmpIdEditTextField;

    @FXML
    private TextField storyCostEditTextField;

    @FXML
    private Button storyEditButton;

    @FXML
    private TextField storyShopIdEditTextField;

    @FXML
    private TextField storyShopIdTextField;

    @FXML
    private TextField storyIdForEditTextField;

    @FXML
    private Button bkPageStaffButton;

    @FXML
    private Button bkPageProductButton;

    @FXML
    private Button bkPageDeliverButton;

    @FXML
    private Button buyersButton;

    @FXML
    private Button storyButton;

    @FXML
    private Button bkPageMenuButton;

    @FXML
    private AnchorPane buyersPane;

    @FXML
    private TableView<buyers> buyersTable;

    @FXML
    private TableColumn<buyers, Integer> buyersIdColumn;

    @FXML
    private TableColumn<buyers, String> buyersFIOcolumn;

    @FXML
    private TableColumn<buyers, String> buyersShopIdColumn;

    @FXML
    private TableColumn<buyers, Integer> buyersRecIdColumn;

    @FXML
    private Button buyersUpdate;

    @FXML
    private AnchorPane buyersMenuPane;

    @FXML
    private TextField buyersFIOTextField;

    @FXML
    private TextField buyersRecIdTextField;

    @FXML
    private TextField buyersIdForDelTextField;

    @FXML
    private Button buyersAddButton;

    @FXML
    private Button buyersDelButton;

    @FXML
    private TextField buyersFIOEditTextField;

    @FXML
    private TextField buyersRecIdEditTextField;

    @FXML
    private Button buyersEditButton;

    @FXML
    private TextField buyersShopEditTextField;

    @FXML
    private TextField buyersShopTextField;

    @FXML
    private TextField buyersIdEditTextField;

    @FXML
    void initialize() {

        bkPageMenuButton.setOnAction(event ->{
            open("/sample/sample.fxml", bkPageMenuButton);
        });
        bkPageProductButton.setOnAction(event ->{
            open("/sample/product.fxml", bkPageProductButton);
        });
        bkPageStaffButton.setOnAction(event ->{
            open("/sample/staff.fxml", bkPageStaffButton);
        });
        bkPageDeliverButton.setOnAction(event ->{
            open("/sample/Deliveries.fxml", bkPageDeliverButton);
        });



        buyersAddButton.setOnAction(event ->{
            try {
                addBuyersData();
            } catch (SQLException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Невозможно добавить данного покупателя, так как не существует соответствующих данных в других таблицах!");
                alert.showAndWait();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        buyersDelButton.setOnAction(event ->{
            try {
                delBuyersData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        buyersUpdate.setOnAction(event ->{
            try {
                updateBuyersTable();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        buyersEditButton.setOnAction(event ->{
            try {
                editBuyersData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });



        storyAddButton.setOnAction(event ->{
            try {
                addStoryData();
            } catch (SQLException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Невозможно добавить данный чек, так как не существует соответствующих данных в других таблицах!");
                alert.showAndWait();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        storyDelButton.setOnAction(event ->{
            try {
                delStoryData();
            } catch (SQLException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Невозможно удалить данный чек, так как так как существуют связанные с другими таблицами данные!");
                alert.showAndWait();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        storyUpdate.setOnAction(event ->{
            try {
                updateStoryTable();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        storyEditButton.setOnAction(event ->{
            try {
                editStoryData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });



        buyersButton.setOnAction(event ->{
            ShowBuyers();
        });
        storyButton.setOnAction(event ->{
            ShowStory();
        });
    }

    private void addBuyersData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String fio = buyersFIOTextField.getText().trim();
        String date = buyersShopTextField.getText().trim();
        String receipt = buyersRecIdTextField.getText().trim();
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(0);
        data.add(fio);
        data.add(date);
        data.add(receipt);

        if(!fio.equals("") && !date.equals("") && !receipt.equals("")){
            dbHandler.insertRow(data, "buyers");
            updateBuyersTable();
            buyersFIOTextField.clear();
            buyersShopTextField.clear();
            buyersRecIdTextField.clear();
        }
        else{
            Shake s1 = new Shake(buyersFIOTextField);
            Shake s2 = new Shake(buyersShopTextField);
            Shake s3 = new Shake(buyersRecIdTextField);
            s1.play();
            s2.play();
            s3.play();
        }
    }

    private void delBuyersData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = buyersIdForDelTextField.getText().trim();

        if(!idStr.equals("")){
            String condition = "buyerId = ";
            condition = condition.concat(idStr);
            dbHandler.deleteData("buyers", condition);
            updateBuyersTable();
            buyersIdForDelTextField.clear();
        }
        else{
            Shake s1 = new Shake(buyersIdForDelTextField);
            s1.play();
        }
    }

    private void updateBuyersTable() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet rs = dbHandler.selectData("SELECT * FROM buyers");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 4; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }

        buyersIdColumn.setCellValueFactory(new PropertyValueFactory<buyers, Integer>("id"));
        buyersFIOcolumn.setCellValueFactory(new PropertyValueFactory<buyers, String>("fio"));
        buyersShopIdColumn.setCellValueFactory(new PropertyValueFactory<buyers, String>("date"));
        buyersRecIdColumn.setCellValueFactory(new PropertyValueFactory<buyers, Integer>("receiptId"));

        ObservableList<buyers> buyersData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            buyersData.add(new buyers(Integer.parseInt(result.get(i).get(0)), result.get(i).get(1), result.get(i).get(2),
                    Integer.parseInt(result.get(i).get(3))));
        }
        buyersTable.setItems(buyersData);
    }

    private void editBuyersData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = buyersIdEditTextField.getText().trim();
        String fio = buyersFIOEditTextField.getText().trim();
        String date = buyersShopEditTextField.getText().trim();
        String receipt = buyersRecIdEditTextField.getText().trim();
        String condition = "buyerId = ";
        condition = condition.concat(idStr);

        if(!idStr.equals("")){
            if(!fio.equals("")){
                dbHandler.updateData("buyers", "name", fio, condition);
            }
            if(!date.equals("")){
                dbHandler.updateData("buyers", "date", date, condition);
            }
            if(!receipt.equals("")){
                dbHandler.updateData("buyers", "receiptId", receipt, condition);
            }
        }
        else{
            Shake s1 = new Shake(buyersIdEditTextField);
            s1.play();
        }
        updateBuyersTable();
        buyersFIOEditTextField.clear();
        buyersShopEditTextField.clear();
        buyersRecIdEditTextField.clear();
        buyersIdEditTextField.clear();
    }



    private void addStoryData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String EmpId = storyEmpIdTextField.getText().trim();
        String shopId = storyShopIdTextField.getText().trim();
        String cost = storyCostTextField.getText().trim();
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(0);
        data.add(EmpId);
        data.add(shopId);
        data.add(cost);

        if(!EmpId.equals("") && !shopId.equals("") && !cost.equals("")){
            dbHandler.insertRow(data, "receipts");
            updateStoryTable();
            storyEmpIdTextField.clear();
            storyShopIdTextField.clear();
            storyCostTextField.clear();
        }
        else{
            Shake s1 = new Shake(storyEmpIdTextField);
            Shake s2 = new Shake(storyShopIdTextField);
            Shake s3 = new Shake(storyCostTextField);
            s1.play();
            s2.play();
            s3.play();
        }
    }

    private void delStoryData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = storyIdForDelTextField.getText().trim();

        if(!idStr.equals("")){
            String condition = "receiptId = ";
            condition = condition.concat(idStr);
            dbHandler.deleteData("receipts", condition);
            updateStoryTable();
            storyIdForDelTextField.clear();
        }
        else{
            Shake s1 = new Shake(storyIdForDelTextField);
            s1.play();
        }
    }

    private void updateStoryTable() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet rs = dbHandler.selectData("SELECT * FROM receipts");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 4; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }

        storyIdColumn.setCellValueFactory(new PropertyValueFactory<story, Integer>("id"));
        storyEmpIdColumn.setCellValueFactory(new PropertyValueFactory<story, Integer>("empId"));
        storyShopIdColumn.setCellValueFactory(new PropertyValueFactory<story, Integer>("shopId"));
        storyCostColumn.setCellValueFactory(new PropertyValueFactory<story, Integer>("cost"));

        ObservableList<story> storyData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            storyData.add(new story(Integer.parseInt(result.get(i).get(0)), Integer.parseInt(result.get(i).get(1)), Integer.parseInt(result.get(i).get(2)),
                    Integer.parseInt(result.get(i).get(3))));
        }
        storyTable.setItems(storyData);
    }

    private void editStoryData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = storyIdForEditTextField.getText().trim();
        String EmpId = storyEmpIdEditTextField.getText().trim();
        String shopId = storyShopIdEditTextField.getText().trim();
        String cost = storyCostEditTextField.getText().trim();
        String condition = "receiptId = ";
        condition = condition.concat(idStr);

        if(!idStr.equals("")){
            if(!EmpId.equals("")){
                dbHandler.updateData("receipts", "r_employeeId", EmpId, condition);
            }
            if(!shopId.equals("")){
                dbHandler.updateData("receipts", "r_shopId", shopId, condition);
            }
            if(!cost.equals("")){
                dbHandler.updateData("receipts", "cost", cost, condition);
            }
        }
        else{
            Shake s1 = new Shake(storyIdForEditTextField);
            s1.play();
        }
        updateStoryTable();
        storyEmpIdEditTextField.clear();
        storyShopIdEditTextField.clear();
        storyCostEditTextField.clear();
        storyIdForEditTextField.clear();
    }

    public void ShowBuyers(){
        buyersPane.setVisible(true);
        storyPane.setVisible(false);

        buyersMenuPane.setVisible(true);
        buyersMenuPane.toFront();
        storyMenuPane.setVisible(false);

        buyersButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #007439;");
        storyButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
    }
    public void ShowStory(){
        buyersPane.setVisible(false);
        storyPane.setVisible(true);

        buyersMenuPane.setVisible(false);
        storyMenuPane.setVisible(true);
        storyMenuPane.toFront();

        storyButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #007439;");
        buyersButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
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

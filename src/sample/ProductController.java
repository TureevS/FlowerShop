package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Animations.Shake;

import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController {

    @FXML
    private TableView<flowerinstock> flowInStockTable;

    @FXML
    private TableColumn<flowerinstock, Integer> idColumn;

    @FXML
    private TableColumn<flowerinstock, String> titleColumn;

    @FXML
    private TableColumn<flowerinstock, Integer> storeColumn;

    @FXML
    private TableColumn<flowerinstock, Integer> quantityColumn;

    @FXML
    private TableColumn<flowerinstock, Integer> priceColumn;

    @FXML
    private AnchorPane additionalMenuPane;

    @FXML
    private AnchorPane flowToOrderMenuPane;

    @FXML
    private TextField quantityFTOTextField;

    @FXML
    private TextField TitleFTOTextField;

    @FXML
    private TextField storeIdFTOTextField;

    @FXML
    private TextField idForDelFTOTextField;

    @FXML
    private Button FTOAddButton;

    @FXML
    private Button FTODelButton;

    @FXML
    private TextField TitleFTOEditTextField;

    @FXML
    private TextField storeIdFTOEditTextField;

    @FXML
    private TextField quantityFTOEdotTextField;

    @FXML
    private Button FTOEditButton;

    @FXML
    private TextField priceFTOEditTextField;

    @FXML
    private TextField priceFTOTextField;

    @FXML
    private TextField idFTOForEditTextField;

    @FXML
    private TextField suppIdFTOTextField;

    @FXML
    private TextField suppFTOEditTextField;

    @FXML
    private AnchorPane flowInOrderPane;

    @FXML
    private TableView<flowerinorder> flowInOrderTable;

    @FXML
    private TableColumn<flowerinorder, Integer> idFTOColumn;

    @FXML
    private TableColumn<flowerinorder, String> titleFTOColumn;

    @FXML
    private TableColumn<flowerinorder, Integer> priceFTOColumn;

    @FXML
    private TableColumn<flowerinorder, Integer> quantityFTOColumn;

    @FXML
    private TableColumn<flowerinorder, Integer> idSupFTOColumn;

    @FXML
    private TableColumn<flowerinorder, Integer> shopIdFTOColumn;

    @FXML
    private Button flowToOrderUpdate;

    @FXML
    private Button PrPageStaffButton;

    @FXML
    private Button PrPagedeliveriesButton;

    @FXML
    private Button PrPagebookkeepingButton;

    @FXML
    private Button flowInStockButton;

    @FXML
    private Button flowOrderButton;

    @FXML
    private Button bouquetsButton;

    @FXML
    private Button servicesButton;

    @FXML
    private Button additionalProductButton;

    @FXML
    private AnchorPane flowInStockPane;

    @FXML
    private Button productUpdate;

    @FXML
    private AnchorPane flowInStockMenuPane;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField TitleTextField;

    @FXML
    private TextField storeTextField;

    @FXML
    private TextField idForDelTextField;

    @FXML
    private Button PrPageAddButton;

    @FXML
    private Button PrPageDelButton;

    @FXML
    private TextField TitleTextField2;

    @FXML
    private TextField storeTextField2;

    @FXML
    private TextField quantityTextField2;

    @FXML
    private Button PrPageEditButton;

    @FXML
    private TextField priceTextField2;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField idForEditTextField;

    @FXML
    private AnchorPane bouquetsMenuPane;

    @FXML
    private TextField bouquetsTitleTextField;

    @FXML
    private TextField idForDelBouquetsTextField;

    @FXML
    private Button bouquetsAddButton;

    @FXML
    private Button bouquetsDelButton;

    @FXML
    private TextField bouquetsTitleEditTextField;

    @FXML
    private Button bouquetsEditButton;

    @FXML
    private TextField bouquetsPriceEditTextField;

    @FXML
    private TextField idForEditBouquetsTextField;

    @FXML
    private TextField bouquetsPriceTextField;

    @FXML
    private TextField bouquetsShopIdEditTextField;

    @FXML
    private TextField bouquetsShopIdTextField;

    @FXML
    private AnchorPane bouquetsPane;

    @FXML
    private TableView<bouquets> bouquetsTable;

    @FXML
    private TableColumn<bouquets, Integer> bouquetsIdColumn;

    @FXML
    private TableColumn<bouquets, String> bouquetsTitleColumn;

    @FXML
    private TableColumn<bouquets, Integer> bouquetsPriceColumn;

    @FXML
    private TableColumn<bouquets, Integer> bouquetsShopIdColumn;

    @FXML
    private Button bouquetsUpdate;

    @FXML
    private AnchorPane servMenuPane;

    @FXML
    private TextField servTitleTextField;

    @FXML
    private Button servAddButton;

    @FXML
    private Button servDelButton;

    @FXML
    private TextField servTitleEditTextField;

    @FXML
    private TextField idForEditServicesTextField;

    @FXML
    private TextField servShopIdEditTextField;

    @FXML
    private TextField servShopIdTextField;

    @FXML
    private Button servEditButton;

    @FXML
    private TextField servPriceEditTextField;

    @FXML
    private TextField servPriceTextField;

    @FXML
    private TextField idForDelServicesTextField;

    @FXML
    private AnchorPane servicesPane;

    @FXML
    private TableView<services> serviceTable;

    @FXML
    private TableColumn<services, Integer> servIdColumn;

    @FXML
    private TableColumn<services, String> servTitleColumn;

    @FXML
    private TableColumn<services, Integer> servPriceColumn;

    @FXML
    private TableColumn<services, Integer> servShopIdColumn;

    @FXML
    private Button servUpdate;

    @FXML
    private TextField additionalTitleTextField;

    @FXML
    private TextField additionalStoreTextField;

    @FXML
    private TextField additionalIdForDelTextField;

    @FXML
    private Button additionalAddButton;

    @FXML
    private Button additionalDelButton;

    @FXML
    private Button additionalEditButton;

    @FXML
    private TextField additionalPriceTextField;

    @FXML
    private TextField additionalIdForEditTextField;

    @FXML
    private AnchorPane additionalProductPane;

    @FXML
    private TableView<additional> additionalProductTable;

    @FXML
    private TableColumn<additional, Integer> additionalIdColumn;

    @FXML
    private TableColumn<additional, String> additionalTitleColumn;

    @FXML
    private TableColumn<additional, Integer> additionalPriceColumn;

    @FXML
    private TableColumn<additional, Integer> additionalShopIdColumn;

    @FXML
    private Button additionalUpdate;

    @FXML
    private TextField additionalTitleEditTextField;

    @FXML
    private TextField additionalStoreEditTextField;

    @FXML
    private TextField additionalPriceEditTextField;

    @FXML
    private Button prPageMenuButton;



    @FXML
    void initialize() {
        flowToOrderMenuPane.setVisible(false);
        prPageMenuButton.setOnAction(event ->{
            open("/sample/sample.fxml", prPageMenuButton);
        });

        PrPageStaffButton.setOnAction(event ->{
            open("/sample/staff.fxml", PrPageStaffButton);
        });
        PrPagedeliveriesButton.setOnAction(event ->{
            open("/sample/Deliveries.fxml", PrPagedeliveriesButton);
        });
        PrPagebookkeepingButton.setOnAction(event ->{
            open("/sample/Bookkeeping.fxml", PrPagebookkeepingButton);
        });

        PrPageAddButton.setOnAction(event ->{
            try {
                addFlowerInStockData();
            } catch (SQLException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Невозможно добавить данный товар, так как не существует соответствующих данных в других таблицах!");
                alert.showAndWait();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        PrPageDelButton.setOnAction(event ->{
            try {
                delFlowerInStockData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        productUpdate.setOnAction(event ->{
            try {
                updateFlowerInStockTable();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        PrPageEditButton.setOnAction(event ->{
            try {
                editData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });



        FTOAddButton.setOnAction(event ->{
            try {
                addFlowerInOrderData();
            } catch (SQLException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Невозможно добавить данный заказ, так как не существует соответствующих данных в других таблицах!");
                alert.showAndWait();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        FTODelButton.setOnAction(event ->{
            try {
                delFlowerInOrderData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        flowToOrderUpdate.setOnAction(event ->{
            try {
                updateFlowerInOrderTable();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        FTOEditButton.setOnAction(event ->{
            try {
                editFlowerInOrderData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });



        bouquetsAddButton.setOnAction(event ->{
            try {
                addBouquetsData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        bouquetsDelButton.setOnAction(event ->{
            try {
                delBouquetsData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        bouquetsUpdate.setOnAction(event ->{
            try {
                updateBouquetsTable();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        bouquetsEditButton.setOnAction(event ->{
            try {
                editBouquetsData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });



        additionalAddButton.setOnAction(event ->{
            try {
                addAdditionalData();
            } catch (SQLException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Невозможно добавить данный товар, так как не существует соответствующих данных в других таблицах!");
                alert.showAndWait();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        additionalDelButton.setOnAction(event ->{
            try {
                delAdditionalData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        additionalUpdate.setOnAction(event ->{
            try {
                updateAdditionalTable();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        additionalEditButton.setOnAction(event ->{
            try {
                editAdditionalData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });



        servAddButton.setOnAction(event ->{
            try {
                addServicesData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        servDelButton.setOnAction(event ->{
            try {
                delServicesData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        servUpdate.setOnAction(event ->{
            try {
                updateServicesTable();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        servEditButton.setOnAction(event ->{
            try {
                editServicesData();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });


        flowInStockButton.setOnAction(event ->{
            ShowFlowInStock();
        });
        flowOrderButton.setOnAction(event ->{
            ShowFlowOrder();
        });
        bouquetsButton.setOnAction(event ->{
            ShowBouquets();
        });
        servicesButton.setOnAction(event ->{
            ShowServices();
        });
        additionalProductButton.setOnAction(event ->{
            ShowAdditionalProduct();
        });
    }

    //ТАБЛИЦА: Цветы в наличии
    private void addFlowerInStockData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String title = TitleTextField.getText().trim();
        String price = priceTextField.getText().trim();
        String quantity = quantityTextField.getText().trim();
        String store = storeTextField.getText().trim();

        ArrayList<Object> data = new ArrayList<Object>();
        data.add(0);
        data.add(title);
        data.add(price);
        data.add(quantity);
        data.add(store);

        if(!title.equals("") && !store.equals("")&& !quantity.equals("") && !price.equals("")){
            dbHandler.insertRow(data, "flowersinstock");
            updateFlowerInStockTable();
            TitleTextField.clear();
            priceTextField.clear();
            quantityTextField.clear();
            storeTextField.clear();
        }
        else{
            Shake s1 = new Shake(TitleTextField);
            Shake s2 = new Shake(priceTextField);
            Shake s3 = new Shake(quantityTextField);
            Shake s4 = new Shake(storeTextField);
            s1.play();
            s2.play();
            s3.play();
            s4.play();
        }
    }

    private void delFlowerInStockData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = idForDelTextField.getText().trim();

        if(!idStr.equals("")){
            String condition = "flowerId = ";
            condition = condition.concat(idStr);
            dbHandler.deleteData("flowersinstock", condition);
            updateFlowerInStockTable();
            idForDelTextField.clear();
        }
        else{
            Shake s1 = new Shake(idForDelTextField);
            s1.play();
        }

    }

    private void updateFlowerInStockTable() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet rs = dbHandler.selectData("SELECT * FROM flowersinstock");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 5; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<flowerinstock, Integer>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<flowerinstock, String>("title"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<flowerinstock, Integer>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<flowerinstock, Integer>("quantity"));
        storeColumn.setCellValueFactory(new PropertyValueFactory<flowerinstock, Integer>("storeId"));

        ObservableList<flowerinstock> flowerinstocksData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            flowerinstocksData.add(new flowerinstock(Integer.parseInt(result.get(i).get(0)), result.get(i).get(1),Integer.parseInt(result.get(i).get(2)),
                    Integer.parseInt(result.get(i).get(3)),Integer.parseInt(result.get(i).get(4))));
        }
        flowInStockTable.setItems(flowerinstocksData);
    }

    private void editData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = idForEditTextField.getText().trim();
        String title = TitleTextField2.getText().trim();
        String price = priceTextField2.getText().trim();
        String quantity = quantityTextField2.getText().trim();
        String storeIdStr = storeTextField2.getText().trim();
        String condition = "flowerId = ";
        condition = condition.concat(idStr);

        if(!idStr.equals("")){
            if(!title.equals("")){
                dbHandler.updateData("flowersinstock", "flowerName", title, condition);
            }
            if(!price.equals("")){
                dbHandler.updateData("flowersinstock", "flowerPrice", price, condition);
            }
            if(!quantity.equals("")){
                dbHandler.updateData("flowersinstock", "flowerNumber", quantity, condition);
            }
            if(!storeIdStr.equals("")){
                dbHandler.updateData("flowersinstock", "stockShopId", storeIdStr, condition);
            }
        }
        else{
            Shake s1 = new Shake(idForEditTextField);
            s1.play();
        }
        updateFlowerInStockTable();
        TitleTextField2.clear();
        priceTextField2.clear();
        quantityTextField2.clear();
        storeTextField2.clear();
        idForEditTextField.clear();
    }

    //ТАБЛИЦА: Цветы на заказ

    private void addFlowerInOrderData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String title = TitleFTOTextField.getText().trim();
        String price = priceFTOTextField.getText().trim();
        String quantity = quantityFTOTextField.getText().trim();
        String IDsuppliers = suppIdFTOTextField.getText().trim();
        String IDstore = storeIdFTOTextField.getText().trim();
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(0);
        data.add(title);
        data.add(price);
        data.add(quantity);
        data.add(IDsuppliers);
        data.add(IDstore);

        if(!title.equals("") && !price.equals("") && !quantity.equals("") && !IDsuppliers.equals("") && !IDstore.equals("")){
            dbHandler.insertRow(data, "flowerstoorder");
            updateFlowerInOrderTable();
            TitleFTOTextField.clear();
            priceFTOTextField.clear();
            quantityFTOTextField.clear();
            suppIdFTOTextField.clear();
            storeIdFTOTextField.clear();
        }
        else{
            Shake s1 = new Shake(TitleFTOTextField);
            Shake s2 = new Shake(priceFTOTextField);
            Shake s3 = new Shake(quantityFTOTextField);
            Shake s4 = new Shake(suppIdFTOTextField);
            Shake s5 = new Shake(storeIdFTOTextField);
            s1.play();
            s2.play();
            s3.play();
            s4.play();
            s5.play();
        }
    }

    private void delFlowerInOrderData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = idForDelFTOTextField.getText().trim();

        if(!idStr.equals("")){
            String condition = "flowerId = ";
            condition = condition.concat(idStr);
            dbHandler.deleteData("flowerstoorder", condition);
            updateFlowerInOrderTable();
            idForDelFTOTextField.clear();
        }
        else{
            Shake s1 = new Shake(idForDelFTOTextField);
            s1.play();
        }
    }

    private void updateFlowerInOrderTable() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet rs = dbHandler.selectData("SELECT * FROM flowerstoorder");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 6; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }

        idFTOColumn.setCellValueFactory(new PropertyValueFactory<flowerinorder, Integer>("id"));
        titleFTOColumn.setCellValueFactory(new PropertyValueFactory<flowerinorder, String>("title"));
        priceFTOColumn.setCellValueFactory(new PropertyValueFactory<flowerinorder, Integer>("price"));
        quantityFTOColumn.setCellValueFactory(new PropertyValueFactory<flowerinorder, Integer>("quantity"));
        idSupFTOColumn.setCellValueFactory(new PropertyValueFactory<flowerinorder, Integer>("suppliersId"));
        shopIdFTOColumn.setCellValueFactory(new PropertyValueFactory<flowerinorder, Integer>("storeId"));

        ObservableList<flowerinorder> flowerinorderData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            flowerinorderData.add(new flowerinorder(Integer.parseInt(result.get(i).get(0)), result.get(i).get(1),Integer.parseInt(result.get(i).get(2)),
                    Integer.parseInt(result.get(i).get(3)),Integer.parseInt(result.get(i).get(4)),Integer.parseInt(result.get(i).get(5))));
        }
        flowInOrderTable.setItems(flowerinorderData);
    }

    private void editFlowerInOrderData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = idFTOForEditTextField.getText().trim();
        String title = TitleFTOEditTextField.getText().trim();
        String price = priceFTOEditTextField.getText().trim();
        String quantity = quantityFTOEdotTextField.getText().trim();
        String suppIdStr = suppFTOEditTextField.getText().trim();
        String storeIdStr = storeIdFTOEditTextField.getText().trim();
        String condition = "flowerId = ";
        condition = condition.concat(idStr);

        if(!idStr.equals("")){
            if(!title.equals("")){
                dbHandler.updateData("flowerstoorder", "flowerName", title, condition);
            }
            if(!price.equals("")){
                dbHandler.updateData("flowerstoorder", "flowerPrice", price, condition);
            }
            if(!quantity.equals("")){
                dbHandler.updateData("flowerstoorder", "flowerNumber", quantity, condition);
            }
            if(!suppIdStr.equals("")){
                dbHandler.updateData("flowerstoorder", "suppliersId", suppIdStr, condition);
            }
            if(!storeIdStr.equals("")){
                dbHandler.updateData("flowerstoorder", "shopId", storeIdStr, condition);
            }
        }
        else{
            Shake s1 = new Shake(idFTOForEditTextField);
            s1.play();
        }
        updateFlowerInStockTable();
        TitleFTOEditTextField.clear();
        priceFTOEditTextField.clear();
        quantityFTOEdotTextField.clear();
        suppFTOEditTextField.clear();
        storeIdFTOEditTextField.clear();
        idFTOForEditTextField.clear();
    }

    //ТАБЛИЦА: БУКЕТЫ

    private void addBouquetsData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String title = bouquetsTitleTextField.getText().trim();
        String price = bouquetsPriceTextField.getText().trim();
        String shopId = bouquetsShopIdTextField.getText().trim();
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(0);
        data.add(title);
        data.add(price);
        data.add(shopId);

        if(!title.equals("") && !price.equals("")){
            dbHandler.insertRow(data, "bouquets");
            updateBouquetsTable();
            bouquetsTitleTextField.clear();
            bouquetsPriceTextField.clear();
            bouquetsShopIdTextField.clear();
        }
        else{
            Shake s1 = new Shake(bouquetsTitleTextField);
            Shake s2 = new Shake(bouquetsPriceTextField);
            Shake s3 = new Shake(bouquetsShopIdTextField);
            s1.play();
            s2.play();
            s3.play();
        }
    }

    private void delBouquetsData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = idForDelBouquetsTextField.getText().trim();

        if(!idStr.equals("")){
            String condition = "bouquetId = ";
            condition = condition.concat(idStr);
            dbHandler.deleteData("bouquets", condition);
            updateBouquetsTable();
            idForDelBouquetsTextField.clear();
        }
        else{
            Shake s1 = new Shake(idForDelBouquetsTextField);
            s1.play();
        }
    }

    private void updateBouquetsTable() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet rs = dbHandler.selectData("SELECT * FROM bouquets");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 4; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }

        bouquetsIdColumn.setCellValueFactory(new PropertyValueFactory<bouquets, Integer>("id"));
        bouquetsTitleColumn.setCellValueFactory(new PropertyValueFactory<bouquets, String>("title"));
        bouquetsPriceColumn.setCellValueFactory(new PropertyValueFactory<bouquets, Integer>("price"));
        bouquetsShopIdColumn.setCellValueFactory(new PropertyValueFactory<bouquets, Integer>("shopId"));


        ObservableList<bouquets> bouquetsData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            bouquetsData.add(new bouquets(Integer.parseInt(result.get(i).get(0)), result.get(i).get(1),
                    Integer.parseInt(result.get(i).get(2)),Integer.parseInt(result.get(i).get(3))));
        }
        bouquetsTable.setItems(bouquetsData);
    }

    private void editBouquetsData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = idForEditBouquetsTextField.getText().trim();
        String title = bouquetsTitleEditTextField.getText().trim();
        String price = bouquetsPriceEditTextField.getText().trim();
        String shopId = bouquetsShopIdEditTextField.getText().trim();

        String condition = "bouquetId = ";
        condition = condition.concat(idStr);

        if(!idStr.equals("")){
            if(!title.equals("")){
                dbHandler.updateData("bouquets", "bouquetName", title, condition);
            }
            if(!price.equals("")){
                dbHandler.updateData("bouquets", "bouquetPrice", price, condition);
            }
            if(!shopId.equals("")){
                dbHandler.updateData("bouquets", "bouquetShopId", shopId, condition);
            }
        }
        else{
            Shake s1 = new Shake(idForEditBouquetsTextField);
            s1.play();
        }
        updateBouquetsTable();
        bouquetsTitleEditTextField.clear();
        bouquetsPriceEditTextField.clear();
        bouquetsShopIdEditTextField.clear();
        idForEditBouquetsTextField.clear();
    }

    //ТАБЛИЦА: УСЛУГИ

    private void addServicesData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String title = servTitleTextField.getText().trim();
        String price = servPriceTextField.getText().trim();
        String shopId = servShopIdTextField.getText().trim();
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(0);
        data.add(title);
        data.add(price);
        data.add(shopId);

        if(!title.equals("") && !price.equals("")){
            dbHandler.insertRow(data, "shopservices");
            updateServicesTable();
            servTitleTextField.clear();
            servPriceTextField.clear();
            servShopIdTextField.clear();
        }
        else{
            Shake s1 = new Shake(servTitleTextField);
            Shake s2 = new Shake(servPriceTextField);
            Shake s3 = new Shake(servShopIdTextField);
            s1.play();
            s2.play();
            s3.play();
        }
    }

    private void delServicesData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = idForDelServicesTextField.getText().trim();

        if(!idStr.equals("")){
            String condition = "serviceId = ";
            condition = condition.concat(idStr);
            dbHandler.deleteData("shopservices", condition);
            updateServicesTable();
            idForDelServicesTextField.clear();
        }
        else{
            Shake s1 = new Shake(idForDelServicesTextField);
            s1.play();
        }
    }

    private void updateServicesTable() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet rs = dbHandler.selectData("SELECT * FROM shopservices");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 4; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }

        servIdColumn.setCellValueFactory(new PropertyValueFactory<services, Integer>("id"));
        servTitleColumn.setCellValueFactory(new PropertyValueFactory<services, String>("title"));
        servPriceColumn.setCellValueFactory(new PropertyValueFactory<services, Integer>("price"));
        servShopIdColumn.setCellValueFactory(new PropertyValueFactory<services, Integer>("shopId"));


        ObservableList<services> servicesData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            servicesData.add(new services(Integer.parseInt(result.get(i).get(0)), result.get(i).get(1),Integer.parseInt(result.get(i).get(2)),Integer.parseInt(result.get(i).get(3))));
        }
        serviceTable.setItems(servicesData);
    }

    private void editServicesData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = idForEditServicesTextField.getText().trim();
        String title = servTitleEditTextField.getText().trim();
        String price = servPriceEditTextField.getText().trim();
        String shopId = servShopIdEditTextField.getText().trim();

        String condition = "serviceId = ";
        condition = condition.concat(idStr);

        if(!idStr.equals("")){
            if(!title.equals("")){
                dbHandler.updateData("shopservices", "serviceName", title, condition);
            }
            if(!price.equals("")){
                dbHandler.updateData("shopservices", "servicePrice", price, condition);
            }
            if(!shopId.equals("")){
                dbHandler.updateData("shopservices", "serviceShopId", shopId, condition);
            }
        }
        else{
            Shake s1 = new Shake(idForEditServicesTextField);
            s1.play();
        }
        updateServicesTable();
        servTitleEditTextField.clear();
        servPriceEditTextField.clear();
        servShopIdEditTextField.clear();
        idForEditServicesTextField.clear();
    }

    //ТАБЛИЦА: ДОПОЛНИТЕЛЬНО

    private void addAdditionalData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String title = additionalTitleTextField.getText().trim();
        String price = additionalPriceTextField.getText().trim();
        String store = additionalStoreTextField.getText().trim();
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(0);
        data.add(title);
        data.add(price);
        data.add(store);

        if(!title.equals("") && !store.equals("") && !price.equals("")){
            dbHandler.insertRow(data, "additionalproducts");
            updateAdditionalTable();
            additionalTitleTextField.clear();
            additionalPriceTextField.clear();
            additionalStoreTextField.clear();
        }
        else{
            Shake s1 = new Shake(additionalTitleTextField);
            Shake s2 = new Shake(additionalPriceTextField);
            Shake s3 = new Shake(additionalStoreTextField);
            s1.play();
            s2.play();
            s3.play();
        }
    }

    private void delAdditionalData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = additionalIdForDelTextField.getText().trim();

        if(!idStr.equals("")){
            String condition = "productId = ";
            condition = condition.concat(idStr);
            dbHandler.deleteData("additionalproducts", condition);
            updateAdditionalTable();
            additionalIdForDelTextField.clear();
        }
        else{
            Shake s1 = new Shake(additionalIdForDelTextField);
            s1.play();
        }

    }

    private void updateAdditionalTable() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet rs = dbHandler.selectData("SELECT * FROM additionalproducts");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 4; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }

        additionalIdColumn.setCellValueFactory(new PropertyValueFactory<additional, Integer>("id"));
        additionalTitleColumn.setCellValueFactory(new PropertyValueFactory<additional, String>("title"));
        additionalPriceColumn.setCellValueFactory(new PropertyValueFactory<additional, Integer>("price"));
        additionalShopIdColumn.setCellValueFactory(new PropertyValueFactory<additional, Integer>("shopId"));

        ObservableList<additional> additionalsData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            additionalsData.add(new additional(Integer.parseInt(result.get(i).get(0)), result.get(i).get(1),Integer.parseInt(result.get(i).get(2)),
                    Integer.parseInt(result.get(i).get(3))));
        }
        additionalProductTable.setItems(additionalsData);
    }

    private void editAdditionalData() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String idStr = additionalIdForEditTextField.getText().trim();
        String title = additionalTitleEditTextField.getText().trim();
        String price = additionalPriceEditTextField.getText().trim();
        String storeIdStr = additionalStoreEditTextField.getText().trim();
        String condition = "productId = ";
        condition = condition.concat(idStr);

        if(!idStr.equals("")){
            if(!title.equals("")){
                dbHandler.updateData("additionalproducts", "productName", title, condition);
            }
            if(!price.equals("")){
                dbHandler.updateData("additionalproducts", "productPrice", price, condition);
            }
            if(!storeIdStr.equals("")){
                dbHandler.updateData("additionalproducts", "productShopId", storeIdStr, condition);
            }
        }
        else{
            Shake s1 = new Shake(additionalIdForEditTextField);
            s1.play();
        }
        updateAdditionalTable();
        additionalTitleEditTextField.clear();
        additionalPriceEditTextField.clear();
        additionalStoreEditTextField.clear();
        additionalIdForEditTextField.clear();
    }


    //ВИДИМОСТЬ ПАНЕЛЕЙ
    public void ShowFlowInStock(){
        flowInStockPane.setVisible(true);
        flowInOrderPane.setVisible(false);
        bouquetsPane.setVisible(false);
        servicesPane.setVisible(false);
        additionalProductPane.setVisible(false);

        flowInStockMenuPane.setVisible(true);
        flowInStockMenuPane.toFront();
        flowToOrderMenuPane.setVisible(false);
        bouquetsMenuPane.setVisible(false);
        servMenuPane.setVisible(false);
        additionalMenuPane.setVisible(false);


        flowInStockButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #007439;");
        flowOrderButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        bouquetsButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        servicesButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        additionalProductButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
    }

    public void ShowFlowOrder(){
        flowInStockPane.setVisible(false);
        flowInOrderPane.setVisible(true);
        bouquetsPane.setVisible(false);
        servicesPane.setVisible(false);
        additionalProductPane.setVisible(false);

        flowInStockMenuPane.setVisible(false);
        flowToOrderMenuPane.setVisible(true);
        flowToOrderMenuPane.toFront();
        bouquetsMenuPane.setVisible(false);
        servMenuPane.setVisible(false);
        additionalMenuPane.setVisible(false);

        flowOrderButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #007439;");
        flowInStockButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        bouquetsButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        servicesButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        additionalProductButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
    }

    public void ShowBouquets(){
        flowInStockPane.setVisible(false);
        flowInOrderPane.setVisible(false);
        bouquetsPane.setVisible(true);
        servicesPane.setVisible(false);
        additionalProductPane.setVisible(false);

        flowInStockMenuPane.setVisible(false);
        flowToOrderMenuPane.setVisible(false);
        bouquetsMenuPane.setVisible(true);
        bouquetsMenuPane.toFront();
        servMenuPane.setVisible(false);
        additionalMenuPane.setVisible(false);

        bouquetsButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #007439;");
        flowInStockButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        flowOrderButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        servicesButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        additionalProductButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
    }

    public void ShowServices(){
        flowInStockPane.setVisible(false);
        flowInOrderPane.setVisible(false);
        bouquetsPane.setVisible(false);
        servicesPane.setVisible(true);
        additionalProductPane.setVisible(false);

        flowInStockMenuPane.setVisible(false);
        flowToOrderMenuPane.setVisible(false);
        bouquetsMenuPane.setVisible(false);
        servMenuPane.setVisible(true);
        servMenuPane.toFront();
        additionalMenuPane.setVisible(false);

        servicesButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #007439;");
        flowInStockButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        flowOrderButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        bouquetsButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        additionalProductButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
    }

    public void ShowAdditionalProduct(){
        flowInStockPane.setVisible(false);
        flowInOrderPane.setVisible(false);
        bouquetsPane.setVisible(false);
        servicesPane.setVisible(false);
        additionalProductPane.setVisible(true);

        flowInStockMenuPane.setVisible(false);
        flowToOrderMenuPane.setVisible(false);
        bouquetsMenuPane.setVisible(false);
        servMenuPane.setVisible(false);
        additionalMenuPane.setVisible(true);
        additionalMenuPane.toFront();

        additionalProductButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #007439;");
        flowInStockButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        flowOrderButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        bouquetsButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
        servicesButton.setStyle("-fx-background-color: #E5E5E5; -fx-text-fill: #666666");
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


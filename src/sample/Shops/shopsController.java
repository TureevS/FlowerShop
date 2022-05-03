package sample.Shops;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.Animations.Shake;
import sample.DatabaseHandler;
import sample.ModalWindow;
import sample.generalFunc;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class shopsController {
    DatabaseHandler dbHandler = new DatabaseHandler();
    @FXML
    private TableView<shops> shopsTable;
    @FXML
    private TableColumn<shops, String> shopNameColumn;
    @FXML
    private TableColumn<shops, Integer> rentColumn;


    @FXML
    private TextField shopNameTextField;
    @FXML
    private TextField rentTextField;
    @FXML
    private TextField searchTextField;


    @FXML
    private Button employeesButton;
    @FXML
    private Button productsButton;
    @FXML
    private Button bookkeepingButton;


    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button activeButton;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        generalFunc func = new generalFunc();
        productsButton.setOnAction(event -> func.open("/sample/Products/product.fxml", productsButton));
        employeesButton.setOnAction(event -> func.open("/sample/Employees/employees.fxml", employeesButton));
        bookkeepingButton.setOnAction(event -> func.open("/sample/Bookkeeping/bookkeeping.fxml", bookkeepingButton));
        updateShopsTable();
        shopsTable.setEditable(true);

        activeButton.setOnAction(event ->{
            try {
                switch (activeButton.getText()) {
                    case "Добавить" -> addShop();
                    case "Удалить" -> deleteShop();
                }
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        });

        saveButton.setOnAction(event ->{
            try {
                save();
            } catch (IOException | SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        });

        addButton.setOnAction(event ->{
            setFieldVisible(true, false, true);
            shopNameTextField.promptTextProperty().set("Введите название магазина");
            rentTextField.promptTextProperty().set("Введите арендную плату в рублях");
            activeButton.setText("Добавить");
        });
        deleteButton.setOnAction(event ->{
            setFieldVisible(false, false, true);
            activeButton.setText("Удалить");
        });
        searchButton.setOnAction(event ->{
            setFieldVisible(false, true, false);
            ObservableList<shops> shopsData = shopsTable.getItems();
            FilteredList<shops> filteredData = new FilteredList<>(shopsData, b->true);
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(shops ->{
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (shops.getShopName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else return(Integer.toString(shops.getRent()).toLowerCase().contains(lowerCaseFilter));
            }));
            SortedList<shops> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(shopsTable.comparatorProperty());
            shopsTable.setItems(sortedData);
        });
    }

    //ФУНКЦИЯ ДОБАВЛЕНИЕ МАГАЗИНА
    private void addShop() throws SQLException, ClassNotFoundException {
        String shopName = shopNameTextField.getText().trim();
        if(!shopName.equals("") && !rentTextField.getText().equals("")){
            int rent = Integer.parseInt(rentTextField.getText().trim());
            ArrayList<Object> data = new ArrayList<>();
            data.add(shopName);
            data.add(rent);
            dbHandler.insertRow(data, "shops");
            updateShopsTable();
            clear();
        }
        else{
            shake();
        }
    }

    //ФУНКЦИЯ УДАЛЕНИЯ МАГАЗИНА
    private void deleteShop() throws SQLException, ClassNotFoundException {
       String shopName = shopsTable.getSelectionModel().getSelectedItem().getShopName();
       String condition = "shopName = '" + shopName + "'";
       dbHandler.deleteData("shops", condition);
       updateShopsTable();
    }

    //ФУНКЦИЯ ОБНОВЛЕНИЯ И ИЗМЕНЕНИЯ ТАБЛИЦЫ МАГАЗИНОВ
    private void updateShopsTable() throws SQLException, ClassNotFoundException {
        ResultSet rs = dbHandler.selectData("SELECT * FROM shops");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 2; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }
        shopNameColumn.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        shopNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        shopNameColumn.setOnEditCommit(event -> {
            String conditional = "shopName = '" + shopsTable.getSelectionModel().getSelectedItem().getShopName() + "'";
            try {
                dbHandler.updateData("shops", "shopName", event.getNewValue(), conditional);
            } catch (SQLException | ClassNotFoundException exception) {
                ModalWindow.errorWindow("Невозможно изменить значение!");
                try {
                    updateShopsTable();
                } catch (SQLException | ClassNotFoundException throwAbles) {
                    throwAbles.printStackTrace();
                }
            }
        });
        rentColumn.setCellValueFactory(new PropertyValueFactory<>("rent"));
        rentColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        rentColumn.setOnEditCommit(event -> {
            String conditional = "shopName = '" + shopsTable.getSelectionModel().getSelectedItem().getShopName() + "'";
            try {
                dbHandler.updateData("shops", "rent", Integer.toString(event.getNewValue()), conditional);
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        });
        ObservableList<shops> shopsData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            shopsData.add(new shops(result.get(i).get(0), Integer.parseInt(result.get(i).get(1))));
        }
        shopsTable.setItems(shopsData);
    }

    //ФУНКЦИЯ СОХРАНЕНИЯ ТАБЛИЦЫ МАГАЗИНОВ
    private void save() throws SQLException, ClassNotFoundException, IOException {
        ResultSet rs = dbHandler.selectData("SELECT * FROM shops");
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("shops");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Название");
        header.createCell(1).setCellValue("Арендная плата в месяц");
        int index = 1;
        while (rs.next()){
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getString(1));
            row.createCell(1).setCellValue(rs.getString(2));
            index++;
        }
        FileOutputStream fileOut = new FileOutputStream("table/Shops.xlsx");
        wb.write(fileOut);
        fileOut.close();
        ModalWindow.errorWindow("Таблица успешно сохранена");
    }

    private void setFieldVisible(boolean flagField, boolean flagSearch, boolean flagActive)  {
        shopNameTextField.setVisible(flagField);
        rentTextField.setVisible(flagField);
        searchTextField.setVisible(flagSearch);
        activeButton.setVisible(flagActive);
    }

    private void clear()  {
        shopNameTextField.clear();
        rentTextField.clear();
    }
    private void shake()  {
        Shake s1 = new Shake(shopNameTextField);
        Shake s2 = new Shake(rentTextField);
        s1.play();
        s2.play();
    }
}

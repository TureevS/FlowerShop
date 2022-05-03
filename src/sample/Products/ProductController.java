package sample.Products;

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

public class ProductController {
    DatabaseHandler dbHandler = new DatabaseHandler();
    @FXML
    private TableView<products> productsTable;
    @FXML
    private TableColumn<products, String> titleColumn;
    @FXML
    private TableColumn<products, String> categoryColumn;
    @FXML
    private TableColumn<products, Integer> quantityColumn;
    @FXML
    private TableColumn<products, Integer> priceColumn;
    @FXML
    private TableColumn<products, String> shopColumn;


    @FXML
    private TextField titleTextField;
    @FXML
    private TextField categoryTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField searchTextField;


    @FXML
    private Button employeesButton;
    @FXML
    private Button shopsButton;
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
    private ComboBox<String> shopsChoiceBox;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        generalFunc func = new generalFunc();
        func.updateChoiceBox("shopName", "shops", shopsChoiceBox);
        employeesButton.setOnAction(event -> func.open("/sample/Employees/employees.fxml", employeesButton));
        shopsButton.setOnAction(event -> func.open("/sample/Shops/shops.fxml", shopsButton));
        bookkeepingButton.setOnAction(event -> func.open("/sample/Bookkeeping/bookkeeping.fxml", bookkeepingButton));
        updateProductsTable();
        productsTable.setEditable(true);

        activeButton.setOnAction(event ->{
            try {
                switch (activeButton.getText()) {
                    case "Добавить" -> addProduct();
                    case "Удалить" -> deleteProduct();
                }
            } catch (SQLException | ClassNotFoundException exception) {
                ModalWindow.errorWindow("Товар с таким названием уже существует");
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
            titleTextField.promptTextProperty().set("Введите название");
            categoryTextField.promptTextProperty().set("Введите категорию");
            quantityTextField.promptTextProperty().set("Введите количество");
            priceTextField.promptTextProperty().set("Введите цену");
            activeButton.setText("Добавить");
        });

        deleteButton.setOnAction(event ->{
            setFieldVisible(false, false, true);
            activeButton.setText("Удалить");
        });
        searchButton.setOnAction(event ->{
            setFieldVisible(false, true, false);
            ObservableList<products> productsData = productsTable.getItems();
            FilteredList<products> filteredData = new FilteredList<>(productsData, b->true);
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(products ->{
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (products.getProductTitle().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if(products.getProductCategory().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if(products.getShopName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if(Integer.toString(products.getProductPrice()).contains(lowerCaseFilter)){
                    return true;
                } else return Integer.toString(products.getProductQuantity()).toLowerCase().contains(lowerCaseFilter);
            }));
            SortedList<products> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(productsTable.comparatorProperty());
            productsTable.setItems(sortedData);
        });
    }

    //ФУНКЦИЯ ДОБАВЛЕНИЕ ТОВАРА
    private void addProduct() throws SQLException, ClassNotFoundException {
        String productTitle = titleTextField.getText().trim();
        String productCategory = categoryTextField.getText().trim();

        if(!productTitle.equals("") && !productCategory.equals("") && !quantityTextField.getText().equals("")
                && !priceTextField.getText().equals("")  && shopsChoiceBox.getValue()!=null){
            String shopName = shopsChoiceBox.getValue();
            int productQuantity = Integer.parseInt(quantityTextField.getText().trim());
            int price = Integer.parseInt(priceTextField.getText().trim());
            ArrayList<Object> data = new ArrayList<>();
            data.add(0);
            data.add(productTitle);
            data.add(productCategory);
            data.add(productQuantity);
            data.add(price);
            data.add(shopName);
            dbHandler.insertRow(data, "products");
            updateProductsTable();
            clear();
        }
        else{
            shake();
        }
    }

    //ФУНКЦИЯ УДАЛЕНИЯ ТОВАРА
    private void deleteProduct() throws SQLException, ClassNotFoundException {
        String productTitle = productsTable.getSelectionModel().getSelectedItem().getProductTitle();
        String condition = "productTitle = '" + productTitle + "'";
        dbHandler.deleteData("products", condition);
        updateProductsTable();
    }

    //ФУНКЦИЯ ОБНОВЛЕНИЯ И ИЗМЕНЕНИЯ ТАБЛИЦЫ ТОВАРОВ
    private void updateProductsTable() throws SQLException, ClassNotFoundException {
        ResultSet rs = dbHandler.selectData("SELECT * FROM products");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 6; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("productTitle"));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        titleColumn.setOnEditCommit(event -> {
            String conditional = "productId = " + productsTable.getSelectionModel().getSelectedItem().getId();
            try {
                dbHandler.updateData("products", "productTitle", event.getNewValue(), conditional);
            } catch (SQLException | ClassNotFoundException exception) {
                ModalWindow.errorWindow("Невозможно изменить значение!");
                try {
                    updateProductsTable();
                } catch (SQLException | ClassNotFoundException throwAbles) {
                    throwAbles.printStackTrace();
                }
            }
        });
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("productCategory"));
        categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryColumn.setOnEditCommit(event -> {
            String conditional = "productId = " + productsTable.getSelectionModel().getSelectedItem().getId();
            try {
                dbHandler.updateData("products", "productCategory", event.getNewValue(), conditional);
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        });
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityColumn.setOnEditCommit(event -> {
            String conditional = "productId = " + productsTable.getSelectionModel().getSelectedItem().getId();
            try {
                dbHandler.updateData("products", "productQuantity", Integer.toString(event.getNewValue()), conditional);
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        });
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceColumn.setOnEditCommit(event -> {
            String conditional = "productId = " + productsTable.getSelectionModel().getSelectedItem().getId();
            try {
                dbHandler.updateData("products", "productPrice", Integer.toString(event.getNewValue()), conditional);
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        });
        shopColumn.setCellValueFactory(new PropertyValueFactory<>("shopName"));

        ObservableList<products> productsData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            productsData.add(new products(Integer.parseInt(result.get(i).get(0)),result.get(i).get(1),
                    result.get(i).get(2),Integer.parseInt(result.get(i).get(3)),
                    Integer.parseInt(result.get(i).get(4)), result.get(i).get(5)));
        }
        productsTable.setItems(productsData);
    }

    //ФУНКЦИЯ СОХРАНЕНИЯ ТАБЛИЦЫ ТОВАРОВ
    private void save() throws SQLException, ClassNotFoundException, IOException {
        ResultSet rs = dbHandler.selectData("SELECT * FROM products");
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("products");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Название");
        header.createCell(1).setCellValue("Категория");
        header.createCell(2).setCellValue("Количество");
        header.createCell(3).setCellValue("Цена");
        header.createCell(4).setCellValue("Магазин");
        int index = 1;
        while (rs.next()){
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getString(2));
            row.createCell(1).setCellValue(rs.getString(3));
            row.createCell(2).setCellValue(rs.getString(4));
            row.createCell(3).setCellValue(rs.getString(5));
            row.createCell(4).setCellValue(rs.getString(6));
            index++;
        }
        FileOutputStream fileOut = new FileOutputStream("table/Products.xlsx");
        wb.write(fileOut);
        fileOut.close();
        ModalWindow.errorWindow("Таблица успешно сохранена");
    }

    private void setFieldVisible(boolean flagField, boolean flagSearch, boolean flagActive)  {
        titleTextField.setVisible(flagField);
        categoryTextField.setVisible(flagField);
        quantityTextField.setVisible(flagField);
        priceTextField.setVisible(flagField);
        shopsChoiceBox.setVisible(flagField);
        searchTextField.setVisible(flagSearch);
        activeButton.setVisible(flagActive);
    }

    private void clear()  {
        titleTextField.clear();
        categoryTextField.clear();
        quantityTextField.clear();
        priceTextField.clear();
    }

    private void shake()  {
        Shake s1 = new Shake(titleTextField);
        Shake s2 = new Shake(categoryTextField);
        Shake s3 = new Shake(quantityTextField);
        Shake s4 = new Shake(priceTextField);
        Shake s5 = new Shake(shopsChoiceBox);
        s1.play();
        s2.play();
        s3.play();
        s4.play();
        s5.play();
    }
}


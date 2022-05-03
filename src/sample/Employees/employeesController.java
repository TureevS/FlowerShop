package sample.Employees;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

public class employeesController {
    DatabaseHandler dbHandler = new DatabaseHandler();
    @FXML
    private TableView<employees> employeesTable;
    @FXML
    private TableColumn<employees, String> fioColumn;
    @FXML
    private TableColumn<employees, String> positionColumn;
    @FXML
    private TableColumn<employees, Integer> salaryColumn;
    @FXML
    private TableColumn<employees, String> shopNameColumn;


    @FXML
    private TextField fioTextField;
    @FXML
    private TextField positionTextField;
    @FXML
    private TextField salaryTextField;
    @FXML
    private TextField searchTextField;


    @FXML
    private Button shopsButton;
    @FXML
    private Button bookkeepingButton;
    @FXML
    private Button productsButton;


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
        productsButton.setOnAction(event -> func.open("/sample/Products/product.fxml", productsButton));
        shopsButton.setOnAction(event -> func.open("/sample/Shops/shops.fxml", shopsButton));
        bookkeepingButton.setOnAction(event -> func.open("/sample/Bookkeeping/bookkeeping.fxml", bookkeepingButton));
        updateEmployeesTable();
        employeesTable.setEditable(true);

        activeButton.setOnAction(event->{
            try {
                switch (activeButton.getText()) {
                    case "Добавить" -> addEmployee();
                    case "Удалить" -> deleteEmployee();
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
            fioTextField.promptTextProperty().set("Введите ФИО");
            positionTextField.promptTextProperty().set("Введите должность");
            salaryTextField.promptTextProperty().set("Введите зарплату");
            activeButton.setText("Добавить");
        });
        deleteButton.setOnAction(event ->{
            setFieldVisible(false, false, true);
            activeButton.setText("Удалить");
        });
        searchButton.setOnAction(event ->{
            setFieldVisible(false, true, false);
            ObservableList<employees> employeesData = employeesTable.getItems();
            FilteredList<employees> filteredData = new FilteredList<>(employeesData, b->true);
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(employees ->{
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (employees.getFio().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if(employees.getPosition().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if(employees.getShopName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else return (Integer.toString(employees.getSalary()).contains(lowerCaseFilter));
            }));
            SortedList<employees> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(employeesTable.comparatorProperty());
            employeesTable.setItems(sortedData);
        });
    }

    //ФУНКЦИЯ ДОБАВЛЕНИЕ СОТРУДНИКА
    private void addEmployee() throws SQLException, ClassNotFoundException {
        String fio = fioTextField.getText().trim();
        String position = positionTextField.getText().trim();
        String shopName = shopsChoiceBox.getValue();
        if(!fio.equals("") && !position.equals("")&& !shopName.equals("") && !salaryTextField.getText().equals("")){
            int salary = Integer.parseInt(salaryTextField.getText().trim());
            ArrayList<Object> data = new ArrayList<>();
            data.add(0);
            data.add(fio);
            data.add(position);
            data.add(salary);
            data.add(shopName);
            dbHandler.insertRow(data, "employees");
            updateEmployeesTable();
            clear();
        }
        else{
            shake();
        }
    }

    //ФУНКЦИЯ УДАЛЕНИЯ СОТРУДНИКА
    private void deleteEmployee() throws SQLException, ClassNotFoundException {
        String fio = employeesTable.getSelectionModel().getSelectedItem().getFio();
        String position = employeesTable.getSelectionModel().getSelectedItem().getPosition();
        int salary = employeesTable.getSelectionModel().getSelectedItem().getSalary();
        String shopName = employeesTable.getSelectionModel().getSelectedItem().getShopName();
        String condition = "FIO = '" + fio + "' AND position = '" + position + "' AND salary = " + salary
                + " AND shopName = '" + shopName + "'";
        dbHandler.deleteData("employees", condition);
        updateEmployeesTable();
    }

    //ФУНКЦИЯ ОБНОВЛЕНИЯ И ИЗМЕНЕНИЯ ТАБЛИЦЫ СОТРУДНИКОВ
    private void updateEmployeesTable() throws SQLException, ClassNotFoundException {
        ResultSet rs = dbHandler.selectData("SELECT * FROM employees");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 5; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }
        fioColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));
        fioColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fioColumn.setOnEditCommit(employeesStringCellEditEvent -> {
            String conditional = "employeeID = " + employeesTable.getSelectionModel().getSelectedItem().getId();
            try {
                dbHandler.updateData("employees", "FIO", employeesStringCellEditEvent.getNewValue(), conditional);
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        });
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        positionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        positionColumn.setOnEditCommit(employeesStringCellEditEvent -> {
            String conditional = "employeeID = " + employeesTable.getSelectionModel().getSelectedItem().getId();
            try {
                dbHandler.updateData("employees", "position", employeesStringCellEditEvent.getNewValue(), conditional);
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        });
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        salaryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        salaryColumn.setOnEditCommit(employeesStringCellEditEvent -> {
            String conditional = "employeeID = " + employeesTable.getSelectionModel().getSelectedItem().getId();
            try {
                dbHandler.updateData("employees", "salary", Integer.toString(employeesStringCellEditEvent.getNewValue()), conditional);
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        });
        shopNameColumn.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        ObservableList<employees> employeesData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            employeesData.add(new employees(Integer.parseInt(result.get(i).get(0)), result.get(i).get(1),
                    result.get(i).get(2), Integer.parseInt(result.get(i).get(3)), result.get(i).get(4)));
        }
        employeesTable.setItems(employeesData);
    }

    //ФУНКЦИЯ СОХРАНЕНИЯ ТАБЛИЦЫ СОТРУДНИКОВ
    private void save() throws SQLException, ClassNotFoundException, IOException {
        ResultSet rs = dbHandler.selectData("SELECT * FROM employees");
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("employees");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ФИО");
        header.createCell(1).setCellValue("Должность");
        header.createCell(2).setCellValue("Зарплата");
        header.createCell(3).setCellValue("Магазин");
        int index = 1;
        while (rs.next()){
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getString(2));
            row.createCell(1).setCellValue(rs.getString(3));
            row.createCell(2).setCellValue(rs.getString(4));
            row.createCell(3).setCellValue(rs.getString(5));
            index++;
        }
        FileOutputStream fileOut = new FileOutputStream("table/Employees.xlsx");
        wb.write(fileOut);
        fileOut.close();
        ModalWindow.errorWindow("Таблица успешно сохранена");
    }

    private void setFieldVisible(boolean flagField, boolean flagSearch, boolean flagActive)  {
        fioTextField.setVisible(flagField);
        positionTextField.setVisible(flagField);
        salaryTextField.setVisible(flagField);
        shopsChoiceBox.setVisible(flagField);
        searchTextField.setVisible(flagSearch);
        activeButton.setVisible(flagActive);
    }

    private void clear()  {
        fioTextField.clear();
        positionTextField.clear();
        salaryTextField.clear();
    }

    private void shake()  {
        Shake S1 = new Shake(fioTextField);
        Shake S2 = new Shake(positionTextField);
        Shake S3 = new Shake(salaryTextField);
        Shake S4 = new Shake(shopsChoiceBox);
        S1.play();
        S2.play();
        S3.play();
        S4.play();
    }
}


package sample.Bookkeeping;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class bookkeepingController {
    DatabaseHandler dbHandler = new DatabaseHandler();
    @FXML
    private TableView<checks> checksTable;
    @FXML
    private TableColumn<checks, String> productsColumn;
    @FXML
    private TableColumn<checks, Integer> quantityColumn;
    @FXML
    private TableColumn<checks, String> dateColumn;
    @FXML
    private TableColumn<checks, String> employeesColumn;
    @FXML
    private TableColumn<checks, String> shopsColumn;


    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField searchTextField;

    @FXML
    private Button productsButton;
    @FXML
    private Button employeesButton;
    @FXML
    private Button shopsButton;


    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button revenueButton;
    @FXML
    private Button revenuePerDayButton;


    @FXML
    private Button activeButton;
    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<String> productsChoiceBox;
    @FXML
    private ComboBox<String> employeesChoiceBox;
    @FXML
    private ComboBox<String> shopsChoiceBox;

    @FXML
    private DatePicker datePick;
    @FXML
    private DatePicker revenueDatePick;

    @FXML
    private Label revenueLabel;
    @FXML
    private Label employeeLabel;
    @FXML
    private Label incomeLabel;
    @FXML
    private Label profitLabel;
    @FXML
    private Label profitabilityLabel;
    @FXML
    private Label averageIncomeLabel;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        generalFunc func = new generalFunc();
        func.updateChoiceBox("productTitle", "products", productsChoiceBox);
        func.updateChoiceBox("FIO", "employees", employeesChoiceBox);
        func.updateChoiceBox("shopName", "shops", shopsChoiceBox);
        productsButton.setOnAction(event -> func.open("/sample/Products/product.fxml", productsButton));
        employeesButton.setOnAction(event -> func.open("/sample/Employees/employees.fxml", employeesButton));
        shopsButton.setOnAction(event -> func.open("/sample/Shops/shops.fxml", shopsButton));
        updateChecksTable();
        checksTable.setEditable(true);
        resultVisible(false);

        activeButton.setOnAction(event ->{
            try {
                switch (activeButton.getText()) {
                    case "Добавить" -> addChecks();
                    case "Удалить" -> deleteChecks();
                    case "Расчитать" -> calculateIncome();
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
            resultVisible(false);
            quantityTextField.promptTextProperty().set("Количество");
            activeButton.setText("Добавить");
        });

        deleteButton.setOnAction(event ->{
            setFieldVisible(false, false, true);
            resultVisible(false);
            activeButton.setText("Удалить");
        });

        searchButton.setOnAction(event ->{
            setFieldVisible(false, true, false);
            resultVisible(false);
            ObservableList<checks> checksData = checksTable.getItems();
            FilteredList<checks> filteredData = new FilteredList<>(checksData, b->true);
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(checks ->{
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (checks.getProductTitle().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if(checks.getDate().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if(checks.getEmployee().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if(checks.getQuantityString().contains(lowerCaseFilter)){
                    return true;
                } else return checks.getShopName().toLowerCase().contains(lowerCaseFilter);
            }));
            SortedList<checks> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(checksTable.comparatorProperty());
            checksTable.setItems(sortedData);
        });

        revenuePerDayButton.setOnAction(event ->{
            datePick.setValue(null);
            revenueDatePick.setValue(null);
            shopsChoiceBox.setValue(null);
            setFieldVisible(false, false, true);
            revenueDatePick.setVisible(true);
            shopsChoiceBox.setVisible(true);
            activeButton.setText("Расчитать");
        });

        revenueButton.setOnAction(event ->{
            datePick.setValue(null);
            revenueDatePick.setValue(null);
            shopsChoiceBox.setValue(null);
            setFieldVisible(false, false, true);
            datePick.setVisible(true);
            revenueDatePick.setVisible(true);
            shopsChoiceBox.setVisible(true);
            activeButton.setText("Расчитать");
        });
    }

    //ФУНКЦИЯ ДОБАВЛЕНИЯ ЧЕКА
    private void addChecks() throws SQLException, ClassNotFoundException {
        String productTitle = productsChoiceBox.getValue();
        String employee = employeesChoiceBox.getValue();
        String shopName = shopsChoiceBox.getValue();
        if(!productTitle.equals("") && !quantityTextField.getText().equals("") && !employee.equals("")
                && !shopName.equals("") && datePick.getValue() != null){
            int quantity = Integer.parseInt(quantityTextField.getText().trim());
            LocalDate localDate = datePick.getValue();
            String date = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            ArrayList<Object> data = new ArrayList<>();
            data.add(0);
            data.add(productTitle);
            data.add(quantity);
            data.add(date);
            data.add(employee);
            data.add(shopName);
            dbHandler.insertRow(data, "checks");
            updateChecksTable();
            clear();
        }
        else{
            shake();
        }
    }

    //ФУНКЦИЯ УДАЛЕНИЯ ЧЕКА
    private void deleteChecks() throws SQLException, ClassNotFoundException {
        String productTitle = checksTable.getSelectionModel().getSelectedItem().getProductTitle();
        int quantity = checksTable.getSelectionModel().getSelectedItem().getQuantity();
        String date = checksTable.getSelectionModel().getSelectedItem().getDate();
        String employee = checksTable.getSelectionModel().getSelectedItem().getEmployee();
        String shopName = checksTable.getSelectionModel().getSelectedItem().getShopName();
        String condition = "productTitle = '" + productTitle + "' AND date = '" + date + "' AND quantity = " + quantity
                + " AND employee = '" + employee + "' AND shopName = '" + shopName + "'";
        dbHandler.deleteData("checks", condition);
        updateChecksTable();
    }

    //ФУНКЦИЯ ОБНОВЛЕНИЯ ТАБЛИЦЫ ЧЕКОВ
    private void updateChecksTable() throws SQLException, ClassNotFoundException {
        ResultSet rs = dbHandler.selectData("SELECT * FROM checks");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while(rs.next()){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 1; i <= 6; i++){
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }
        productsColumn.setCellValueFactory(new PropertyValueFactory<>("productTitle"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityColumn.setOnEditCommit(event -> {
            String conditional = "checkID = " + checksTable.getSelectionModel().getSelectedItem().getId();
            try {
                dbHandler.updateData("checks", "quantity", Integer.toString(event.getNewValue()), conditional);
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        });
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        employeesColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
        shopsColumn.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        ObservableList<checks> checksData = FXCollections.observableArrayList();
        int n = result.size();
        for (int i = 0; i <= n-1; i++){
            checksData.add(new checks(Integer.parseInt(result.get(i).get(0)), result.get(i).get(1),
                    Integer.parseInt(result.get(i).get(2)), result.get(i).get(3),
                    (result.get(i).get(4)), (result.get(i).get(5))));
        }
        checksTable.setItems(checksData);
    }

    //ФУНКЦИЯ СОХРАНЕНИЯ ТАБЛИЦЫ ЧЕКОВ
    private void save() throws SQLException, ClassNotFoundException, IOException {
        ResultSet rs = dbHandler.selectData("SELECT * FROM checks");
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("checks");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Товар");
        header.createCell(1).setCellValue("Количество");
        header.createCell(2).setCellValue("Дата");
        header.createCell(3).setCellValue("Сотрудник");
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
        FileOutputStream fileOut = new FileOutputStream("table/Checks.xlsx");
        wb.write(fileOut);
        fileOut.close();
        ModalWindow.errorWindow("Таблица успешно сохранена");
    }

    //ФУНКЦИЯ РАСЧЕТА ВЫРУЧКИ
    private int calculateRevenue(int revenue, String shopName, String date) throws SQLException, ClassNotFoundException {
        ResultSet rs = dbHandler.selectData("SELECT * FROM checks WHERE date = '" + date +
                "' AND shop = '" + shopName + "'");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while (rs.next()) {
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 2; i <= 6; i++) {
                tmp.add(rs.getString(i));
            }
            result.add(tmp);
        }
        int n = result.size();
        for (int i = 0; i <= n - 1; i++) {
            int price = getPrice(result.get(i).get(0));
            int temp = price * Integer.parseInt(result.get(i).get(1));
            revenue = revenue + temp;
        }
        return revenue;
    }

    //ФУНКЦИЯ РАСЧЕТА ДОХОДА
    private void calculateIncome() throws SQLException, ClassNotFoundException {
        String shopName = shopsChoiceBox.getValue();
        double taxation = 0.06;
        double rent = getRent(shopName);
        double salary = getSalary(shopName);
        int revenue = 0;
        resultVisible(true);
        if(!shopName.equals("") && revenueDatePick.getValue() != null){
            LocalDate localDateLast = revenueDatePick.getValue();
            String dateLast = localDateLast.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (datePick.getValue() == null) {
                revenue = calculateRevenue(revenue, shopName, dateLast);
                int monthlyRevenue;
                switch (localDateLast.getDayOfMonth()) {
                    case 2 -> monthlyRevenue = revenue * 28;
                    case 4, 6, 9, 11 -> monthlyRevenue = revenue * 30;
                    default -> monthlyRevenue = revenue * 31;
                }
                double taxUSN = revenue * taxation;
                double expenses = rent + taxUSN + salary;
                String employee = getEmployee(dateLast, shopName);
                showResult(monthlyRevenue, expenses);
                revenueLabel.setText("Выручка за день: " + revenue + " Р");
                employeeLabel.setText("Сотрудник: " + employee);
            }else{
                LocalDate localDateFirst = datePick.getValue();
                localDateLast = localDateLast.plusDays(1);
                double quantityDays = 0;
                while (!localDateFirst.equals(localDateLast)){
                    String date = localDateFirst.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    revenue = calculateRevenue(revenue, shopName, date);
                    localDateFirst = localDateFirst.plusDays(1);
                    quantityDays++;
                }
                double taxUSN = revenue * taxation;
                double expenses = (rent/31 * quantityDays) + taxUSN + (salary/31 * quantityDays);
                showResult(revenue, expenses);
                revenueLabel.setText("Выручка за период: " + revenue + " Р");
                employeeLabel.setText("");
            }
        }
        else{
            shake();
        }
    }

    //ФУНКЦИЯ ВЫВОДА РЕЗУЛЬТАТА РАСЧЕТА ВЫРУЧКИ И ДОХОДА
    private void showResult(double revenue, double expenses){
        double tradeMargin = 0.3;
        double income = revenue * tradeMargin;
        if (income != 0) {
            double profit = income - expenses;
            String profitString = String.format("%.2f", profit);
            double profitability = profit / income * 100;
            String profitabilityString = String.format("%.2f", profitability);
            profitLabel.setText("Прибыль: " + profitString + " Р");
            profitabilityLabel.setText("Рентабельность: " + profitabilityString + "%");
            incomeLabel.setText("Доход: " + income + " Р");
        } else {
            averageIncomeLabel.setVisible(false);
            incomeLabel.setVisible(false);
            profitLabel.setVisible(false);
            profitabilityLabel.setVisible(false);
            employeeLabel.setVisible(false);
        }
    }
    public String getEmployee(String date, String shopName) throws SQLException, ClassNotFoundException {
        ResultSet rs = dbHandler.selectData("SELECT employee FROM checks WHERE date = '" + date +
                "' AND shop = '" + shopName + "'");
        if(rs.next()){
            return rs.getString(1);
        }else return "";
    }


    public double getSalary(String shopName) throws SQLException, ClassNotFoundException {
        ResultSet rs = dbHandler.selectData("SELECT salary FROM employees WHERE shopName = '" + shopName + "'");
        int salary = 0;
        while(rs.next()){
            salary = salary + rs.getInt(1);
        }
        return salary;
    }

    public double getRent(String shopName) throws SQLException, ClassNotFoundException {
        ResultSet rs = dbHandler.selectData("SELECT rent FROM shops WHERE shopName = '" + shopName + "'");
        rs.next();
        return rs.getInt(1);
    }

    public int getPrice(String title) throws SQLException, ClassNotFoundException {
        ResultSet rs = dbHandler.selectData("SELECT productPrice FROM products WHERE productTitle = '" + title + "'");
        rs.next();
        return rs.getInt(1);
    }

    private void setFieldVisible(boolean flagField, boolean flagSearch, boolean flagActive)  {
        productsChoiceBox.setVisible(flagField);
        quantityTextField.setVisible(flagField);
        datePick.setVisible(flagField);
        employeesChoiceBox.setVisible(flagField);
        shopsChoiceBox.setVisible(flagField);
        revenueDatePick.setVisible(flagField);
        searchTextField.setVisible(flagSearch);
        activeButton.setVisible(flagActive);
    }
    private void resultVisible(boolean flag)  {
        revenueLabel.setVisible(flag);
        employeeLabel.setVisible(flag);
        averageIncomeLabel.setVisible(flag);
        incomeLabel.setVisible(flag);
        profitLabel.setVisible(flag);
        profitabilityLabel.setVisible(flag);
    }

    private void clear()  {
        quantityTextField.clear();
    }
    private void shake()  {
        Shake s1 = new Shake(productsChoiceBox);
        Shake s2 = new Shake(quantityTextField);
        Shake s3 = new Shake(datePick);
        Shake s4 = new Shake(employeesChoiceBox);
        Shake s5 = new Shake(shopsChoiceBox);
        s1.play();
        s2.play();
        s3.play();
        s4.play();
        s5.play();
    }
}

package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DatabaseHandler extends Configs{

    private static Connection dbConnection;

    // Конструктор
    DatabaseHandler(){
        dbConnection =  null;
    }

    // Соединение с БД
    private Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,
                dbUser,dbPass);

        return dbConnection;
    }

    // Метод извлечения данных из БД
    public ResultSet selectData(String sqlQuery)
            throws SQLException, ClassNotFoundException {

        PreparedStatement prSt = getDbConnection().prepareStatement(sqlQuery);
        ResultSet resSet = prSt.executeQuery();

        return resSet;
    }

    // Метод вставки строки
    public void insertRow(ArrayList<Object> values, String table)
            throws SQLException, ClassNotFoundException {

        ResultSet rs = selectData("SELECT * FROM " + table);
        assert rs != null;
        ResultSetMetaData rsmd = rs.getMetaData();

        // Количество колонок в результирующем запросе
        int columns = rs.getMetaData().getColumnCount();

        StringBuilder header = new StringBuilder("("); //Строка наименований полей
        for (int i = 1; i <= columns; i++) {
            header.append(rsmd.getColumnName(i)).append(", ");
        }

        header.deleteCharAt(header.length() - 1).deleteCharAt(header.length() - 1).append(")");

        StringBuilder d = new StringBuilder("('");

        // The column count starts from 1
        for (int i = 0; i < columns; i++) {
            d.append(values.get(i)).append("', '");
        }
        d.deleteCharAt(d.length() - 1).deleteCharAt(d.length() - 1).deleteCharAt(d.length() - 1).append(")");

        PreparedStatement prSt = getDbConnection().prepareStatement("INSERT INTO " + table + " " + header + " VALUES" + d + ";");
        prSt.executeUpdate();
    }

    // Метод обновления данных в таблице
    public void updateData(String table, String column, String value, String condition)
            throws SQLException, ClassNotFoundException {
        if (condition == null) {
            PreparedStatement prSt = getDbConnection().prepareStatement("UPDATE "+ table +" SET "+ column +" = "+ "'" + value + "'" + ";");
            prSt.executeUpdate();
        }
        else {
            PreparedStatement prSt = getDbConnection().prepareStatement("UPDATE " + table + " SET " + column + " = " + "'" + value + "'" + " WHERE " + condition + ";");
            prSt.executeUpdate();
        }
    }

    // Перегрузка метода позволяет устанавливать для определенного столбца в таблице одно значение
    public void updateData(String table, String column, String value)
            throws SQLException, ClassNotFoundException {
        updateData(table, column, value, null);
    }

    public void deleteData(String table, String condition)
            throws SQLException, ClassNotFoundException {
        PreparedStatement prSt = getDbConnection().prepareStatement("DELETE from " + table + " WHERE " + condition + ";");
        prSt.executeUpdate();
    }


    // Заготовка метода проверки вводимого пользователем
    public boolean isCorrect (String table, String column, String line){

        boolean isCorrect = true;
        String regExp;

        switch (table) {
            case ("shops"):
                if (column == "employee_id") {
                    regExp = "([0-9]+)";
                    isCorrect = Pattern.matches(regExp, line);
                }
                if (column == "full_name") {
                    regExp = "(^[А-ЯЁA-Z]{1}([а-яёa-z])+)\\s([А-ЯЁA-Z]{1}([а-яёa-z])+)\\s([А-ЯЁA-Z]{1}([а-яёa-z])+)";
                    isCorrect = Pattern.matches(regExp, line);
                }
                if (column == "email") {
                    regExp = "(([А-ЯЁA-Zа-яёa-z])+)";
                    isCorrect = Pattern.matches(regExp, line);
                }
                if (column == "phone_number") {
                    regExp = "([0-9]{11})";
                    isCorrect = Pattern.matches(regExp, line);
                }
                if (column == "salary") {
                    regExp = "(^[1-9]{1}([0-9]{4,}))";
                    isCorrect = Pattern.matches(regExp, line);
                }
                break;
            case ("..."):
                // CODE
                break;
            //CODE
        }

        return isCorrect;
    }

}

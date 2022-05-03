package sample;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler extends Configs{

    private static Connection dbConnection;

    public DatabaseHandler(){
        dbConnection =  null;
    }

    // Соединение с БД
    private Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,
                dbUser,dbPass);
        return dbConnection;
    }

    // Метод извлечения данных из БД
    public ResultSet selectData(String sqlQuery)
            throws SQLException, ClassNotFoundException {
        PreparedStatement prSt = getDbConnection().prepareStatement(sqlQuery);
        return prSt.executeQuery();
    }

    // Метод вставки строки
    public void insertRow(ArrayList<Object> values, String table)
            throws SQLException, ClassNotFoundException {
        ResultSet rs = selectData("SELECT * FROM " + table);
        assert rs != null;
        ResultSetMetaData resultSetMetaData = rs.getMetaData();

        int columns = rs.getMetaData().getColumnCount();
        StringBuilder header = new StringBuilder("(");
        for (int i = 1; i <= columns; i++) {
            header.append(resultSetMetaData.getColumnName(i)).append(", ");
        }

        header.deleteCharAt(header.length() - 1).deleteCharAt(header.length() - 1).append(")");
        StringBuilder d = new StringBuilder("('");

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
        PreparedStatement prSt;
        if (condition == null) {
            prSt = getDbConnection().prepareStatement("UPDATE " + table + " SET " + column + " = " + "'" + value + "'" + ";");
        }
        else {
            prSt = getDbConnection().prepareStatement("UPDATE " + table + " SET " + column + " = " + "'" + value + "'" + " WHERE " + condition + ";");
        }
        prSt.executeUpdate();
    }

    public void deleteData(String table, String condition)
            throws SQLException, ClassNotFoundException {
        PreparedStatement prSt = getDbConnection().prepareStatement("DELETE from " + table + " WHERE " + condition + ";");
        prSt.executeUpdate();
    }
}

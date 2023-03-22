package jdbctests;

import java.sql.*;

public class jdbcShorts4 {

    public static void main(String[] args) {

        String dbUrl = "jdbc:oracle:thin:@44.197.123.56:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEES");

            ResultSetMetaData resultSetMetaData= resultSet.getMetaData();

            int columnCount= resultSetMetaData.getColumnCount();
            String firstColumnName= resultSetMetaData.getColumnName(1);
            System.out.println("columnCount = " + columnCount);
            System.out.println("firstColumnName = " + firstColumnName);

            for (int colIndex = 1; colIndex <=columnCount ; colIndex++) {
                System.out.println("resultSetMetaData.getColumnName("+colIndex+")= "+resultSetMetaData.getColumnName(colIndex));
            }


        } catch (SQLException e) {
            System.out.println("ERROR OCCURED "+e.getMessage());
        }



        }
}

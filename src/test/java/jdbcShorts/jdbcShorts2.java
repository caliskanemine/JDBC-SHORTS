package jdbcShorts;

import java.sql.*;

public class jdbcShorts2 {

    //JDBC_Project_FlexibleNavigation

    public static void main(String[] args) {
        String dbUrl = "jdbc:oracle:thin:@44.197.123.56:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM REGIONS");

            resultSet.first();
            System.out.println(resultSet.getString("REGION_ID")+" "+resultSet.getString("REGION_NAME"));
            resultSet.last();
            System.out.println(resultSet.getString("REGION_ID")+" "+resultSet.getString("REGION_NAME"));
            resultSet.previous();
            System.out.println(resultSet.getString("REGION_ID")+" "+resultSet.getString("REGION_NAME"));
            resultSet.absolute(2);
            System.out.println(resultSet.getString("REGION_ID")+" "+resultSet.getString("REGION_NAME"));

            resultSet.beforeFirst();
            resultSet.next();
            System.out.println(resultSet.getString("REGION_ID")+" "+resultSet.getString("REGION_NAME"));
            resultSet.afterLast();
            resultSet.previous();
            System.out.println(resultSet.getString("REGION_ID")+" "+resultSet.getString("REGION_NAME"));


        } catch (SQLException e) {
            System.out.println("ERROR HAS OCCURED " + e.getMessage());
        }

    }

}
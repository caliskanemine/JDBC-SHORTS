package jdbcShorts;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcShorts1 {

    //JDBC_Project_SimpleNavigation

    public static void main(String[] args)  {
        String dbUrl = "jdbc:oracle:thin:@44.197.123.56:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery("SELECT * FROM REGIONS");

            resultSet.next();
            System.out.println("Region ID on first row is "+resultSet.getString(1));
            System.out.println("Region ID on first row is "+resultSet.getString("REGION_ID"));
            System.out.println("Region name first row is "+resultSet.getString(2));
            System.out.println("Region name on first row is "+resultSet.getInt("RGION_NAME"));

            resultSet.next();
            System.out.println("Region Name on SECOND row is "+resultSet.getString("REGION_NAME"));
            resultSet.next();
            System.out.println("Region Name on THIRD row is "+resultSet.getString("REGION_NAME"));
            resultSet.next();
            System.out.println("Region Name on FOURTH row is "+resultSet.getString("REGION_NAME"));

            System.out.println("Do we have more data "+resultSet.next());
            // resultSet.previous();
            //System.out.println("Region Name on AFTER LAST row is "+resultSet.getString("REGION_NAME"));



        } catch (SQLException e) {
            System.out.println("con ahs failed "+e.getMessage());
        }


    }

}
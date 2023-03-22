package jdbctests;

import java.sql.*;

public class jdbcShorts3 {

    //JDBC_Project_LoopingResult

    public static void main(String[] args) {


        String dbUrl = "jdbc:oracle:thin:@44.197.123.56:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM REGIONS");

            resultSet.beforeFirst();
            while (resultSet.next()){
                System.out.println("ROW NUMBER IS "+resultSet.getRow()); //getting row number
                System.out.println(resultSet.getString(1)+" | "+ resultSet.getString(2));

            }resultSet.beforeFirst();
            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+" | "+ resultSet.getString(2));

            }
            resultSet.last();
            System.out.println("ROW COUNT IS "+resultSet.getRow());

            //getting row count--> move cursor to the last row and get the row number



        } catch (SQLException e) {
            System.out.println("ERROR HAS OCCURED " + e.getMessage());
        }



    }
}

package jdbcShorts;

import java.sql.*;

public class jdbcShorts5 {

    public static void main(String[] args) {


        String dbUrl = "jdbc:oracle:thin:@44.197.123.56:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM REGIONS");
            ResultSetMetaData resultSetMetaData= resultSet.getMetaData();

            int columnCount= resultSetMetaData.getColumnCount();

           // for (int colIndex = 1; colIndex <=columnCount ; colIndex++) {
           //     System.out.println("resultSetMetaData.getColumnName("+colIndex+")= "+resultSetMetaData.getColumnName(colIndex));
           // }

            //printing all data in one row
            //resultSet.next();
            for (int colIndex = 1; colIndex <=columnCount ; colIndex++) {
                System.out.print(resultSetMetaData.getColumnName(colIndex)+" \t");
            }
            System.out.println();
            // }

           while (resultSet.next()) {
               for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                   System.out.print(resultSet.getString(colIndex) + " \t");
               }
               System.out.println();
           }



        } catch (SQLException e) {
            System.out.println("ERROR OCCURED "+e.getMessage());
        }



    }
}

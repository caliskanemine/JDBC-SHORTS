package jdbctests;

import java.sql.*;

public class jdbcShorts6 {

    public static void main(String[] args) throws SQLException {


        String dbUrl = "jdbc:oracle:thin:@44.197.123.56:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //Connection connection=null;
        //Statement statement=null;
        //ResultSet resultSet=null;


        //try with resources-- only work with auto closable resources

        try (
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM REGIONS")) {
            resultSet.next();
            System.out.println(resultSet.getString(2));

        }catch (SQLException e) {
            System.out.println("ERROR HAS OCCURED " + e.getMessage());
        }



        //burayı yaparken username i hr1 olarak değiştirdik. neden???
  /* try {
    connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    resultSet = statement.executeQuery("SELECT * FROM REGIONS");
    System.out.println("ALL SUCCESSFUL");

   }catch (SQLException e){
    System.out.println("ERROR HAS OCCURED " + e.getMessage());
   }finally {

       try{
          if (resultSet!= null) resultSet.close();
          if (statement!=null)  statement.close();
          if (connection!=null) connection.close();
       }catch (SQLException e){
           System.out.println("ERROR WHILE CLOSING RESOURCES "+e.getMessage());
       }


   }
*/




    }

}

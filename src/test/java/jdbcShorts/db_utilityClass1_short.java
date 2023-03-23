package jdbcShorts;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class db_utilityClass1_short {

    static Connection connection;
    static ResultSet resultSet;
    static Statement statement;

    public static void createConnection(){
        String dbUrl = "jdbc:oracle:thin:@44.197.123.56:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            System.out.println("Connection Successfull");
        } catch (SQLException e) {
            System.out.println("Connection has failed " + e.getMessage());
        }

    }

    public static ResultSet runQuery(String query){
        try {
            statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet= statement.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING RESULTSET "+e.getMessage());
        }
        return resultSet;


    }


    //cleaning up the resources
    public static void destroy(){

            try {
                if (resultSet!= null) {
                    resultSet.close();
                }
                if (statement!=null){
                    statement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException throwables) {
               throwables.printStackTrace();
            }
        }

        //get the row count of the resultset
    public static int getRowCount(){
        int rowCount= 0;
        try {
            resultSet.last();
            rowCount= resultSet.getRow();
            resultSet.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW COUNT "+e.getMessage());
        }
        return rowCount;

    }

    //a method to get the column count
    public static int getColumnCount(){
        int columnCount=0;
        try {
            ResultSetMetaData resultSetMetaData= resultSet.getMetaData();
            columnCount= resultSetMetaData.getColumnCount();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE VOUNTING THE COLUMNS "+e.getMessage());
        }
        return columnCount;
    }


    //create a method that return all column names as list
    public static List<String> getColumnNames(){
        List<String> columnNamesList= new ArrayList<>();

        try {
            ResultSetMetaData resultSetMetaData= resultSet.getMetaData();
            for (int columnNum = 1; columnNum <=resultSetMetaData.getColumnCount() ; columnNum++) {
                String columnName= resultSetMetaData.getColumnLabel(columnNum);
                columnNamesList.add(columnName);
            }

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING COLUMN NAMES "+e.getMessage());
        }
        return columnNamesList;
    }


    //list of string that contains the row data
    public static List<String> getRowDataAsList(int rowNum){
        List<String> rowDataList= new ArrayList<>();
        try {
            resultSet.absolute(rowNum);
            for (int columnNum = 1; columnNum <=getColumnCount() ; columnNum++) {
                String cellValue= resultSet.getString(columnNum);
                rowDataList.add(cellValue);
            }

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getRowDataAsList "+e.getMessage());
        }
        return rowDataList;
    }




    public static void main(String[] args) throws SQLException {

        createConnection();
        ResultSet myResult= runQuery("SELECT * FROM REGIONS");
        resultSet.next();
        System.out.println(resultSet.getString(1));


        System.out.println(getRowCount());
        System.out.println(getColumnCount());
        System.out.println(getColumnNames());
        System.out.println(getRowDataAsList(3));


        destroy();



    }


}

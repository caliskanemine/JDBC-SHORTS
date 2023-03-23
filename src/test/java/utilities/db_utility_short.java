package utilities;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class db_utility_short {

    private static Connection connection;
    private static ResultSet resultSet;
    private static Statement statement;

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

    //getting single column cell value at certain row
    public static String getColumnDataAtRow(int rowNum, int columnIndex){

        String result="";
        try {
            resultSet.absolute(rowNum);
            result=resultSet.getString(columnIndex);
        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAtRow "+e.getMessage());
        }
        return result;


    }

    public static String getColumnDataAtRow(int rowNum, String columnName){

        String result="";
        try {
            resultSet.absolute(rowNum);
            result=resultSet.getString(columnName);
            resultSet.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAtRow "+e.getMessage());
        }
        return result;

    }


    //getting entire column value as list
    public static List<String> getColumnDataAsList(int columnIndex){
        List<String> columnDataList= new ArrayList<>();
        try {
            resultSet.beforeFirst();
            while (resultSet.next()){
                String cellValue= resultSet.getString(columnIndex);
                columnDataList.add(cellValue);
            }

        }catch (SQLException e){
            System.out.println("ERROR WHILE getColumnDataAsList "+e.getMessage());
        }
        return columnDataList;

    }


    public static List<String> getColumnDataAsList(String columnName){
        List<String> columnDataList= new ArrayList<>();
        try {
            resultSet.beforeFirst();
            while (resultSet.next()){
                String cellValue= resultSet.getString(columnName);
                columnDataList.add(cellValue);
            }

        }catch (SQLException e){
            System.out.println("ERROR WHILE getColumnDataAsList "+e.getMessage());
        }
        return columnDataList;


    }

    //a method to display all the data in the result set
    public static void displayAllData(){

        try {
            resultSet.beforeFirst();
            while (resultSet.next()){
                for (int columnNum = 1; columnNum <=getColumnCount() ; columnNum++) {
                    System.out.print(resultSet.getString(columnNum)+ "\t");
                }
                System.out.println();
            }


        } catch (SQLException e) {
            System.out.println("ERROR WHILE DISPLAYING ALL DATA "+e.getMessage());
        }

    }


    //to store certain row data as a Mag<String, String>
    public static Map<String, String> getRowMap(int rowNum){


        Map<String, String> rowMap= new LinkedHashMap<>();

        try {
            resultSet.absolute(rowNum);
            ResultSetMetaData resultSetMetaData= resultSet.getMetaData();

            for (int columnNum = 1; columnNum <=getColumnCount() ; columnNum++) {
                String columnName= resultSetMetaData.getColumnLabel(columnNum);
                String cellValue = resultSet.getString(columnNum);
                rowMap.put(columnName, cellValue);
            }
           resultSet.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR AT ROW MAP FUNCTION "+e.getMessage());
        }
        return rowMap;
    }


    //getting entire resultset data as List of Map object
    public static List< Map<String, String> > getAllDataAsListOfMap(){

        List<Map<String, String>> rowMapList= new ArrayList<>();

        for (int rowNum = 1; rowNum <=getRowCount() ; rowNum++) {
            rowMapList.add(getRowMap(rowNum));
        }
        return rowMapList;
    }


    //getting the first row first column data
    public static String getFirstData(){

        return getColumnDataAtRow(1, 1);
    }





    public static void main(String[] args) throws SQLException {

        createConnection();
        ResultSet myResult= runQuery("SELECT * FROM EMPLOYEES");
        resultSet.next();
        System.out.println(resultSet.getString(1));


        System.out.println(getRowCount());
        System.out.println(getColumnCount());
        System.out.println(getColumnNames());
        System.out.println(getRowDataAsList(3));
        System.out.println("3rd row second column "+getColumnDataAtRow(3, 2));
        System.out.println("3rd row REGION_NAME column "+getColumnDataAtRow(3, "REGION_NAME"));
        System.out.println("1st column as list "+getColumnDataAsList(1));
        System.out.println("1st column as list "+getColumnDataAsList("REGION_ID"));

        displayAllData();

        System.out.println(getRowMap(3));

        Map<String, String> thirdRowMap= getRowMap(3);

        //System.out.println("Get the last name "+thirdRowMap.get("LAST_NAME"));

        System.out.println(getAllDataAsListOfMap());

        System.out.println(getFirstData());

        destroy();



    }


}

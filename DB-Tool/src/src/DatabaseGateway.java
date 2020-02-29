package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseGateway {
    private static DatabaseGateway singleton;
    String connectionUrl = "jdbc:sqlserver://localhost:1433;database=TestMama;user=user;password=password";
    public List<ArrayList<String>> rows = new ArrayList<>();
    List<String> columnNames = new ArrayList();
    private Object rsm;

    public DatabaseGateway() {
        runQuery("");
    }
    
    public static DatabaseGateway getInstance(){
        if (singleton == null)
            singleton = new DatabaseGateway();
        return singleton;
    }

    public List<ArrayList<String>> getRows() {
        return rows;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void runQuery(String query) {
        String prev = "";
        System.out.println(query);
        rows = new ArrayList<>();
        columnNames = new ArrayList<>();
        if (query.isEmpty()) {
            query = "SELECT BKID, b.BEZEICHNUNG, w.BEZEICHNUNG FROM dbo.baukasten b INNER JOIN dbo.Werk w ON b.WID = w.WID";
        }

            try{
            ResultSet rs = establishConnection(query);
            ResultSetMetaData rsm = rs.getMetaData();//getting Metadata from rs to get column names
            //iterating through rsm to get column names
            for (int i = 1; i < rsm.getColumnCount() + 1; i++) {
                if (!prev.equals(rsm.getColumnName(i))) {
                    columnNames.add(rsm.getColumnName(i));
                }
                prev = rsm.getColumnName(i);
            }
            readRows(rs); // Read Rows
            }
            catch(Exception e){
               System.out.println(e);
            }
    }

    private ResultSet establishConnection(String query) throws Exception{
        Connection con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);//Query will be executed
            return rs;
    }
    public void readRows(ResultSet rs) throws SQLException {
        while (rs.next()) {
            rows.add(new ArrayList<String>() {
                {
                    for (String s : columnNames) {
                        add(rs.getString(s));
                    }
                }
            });
        }
    }

    public void searchFor(String table, String text) {
        runQuery(createQuery(table, text));
    }
    
    private String createQuery(String table, String text){
        String query = "SELECT * FROM " + table + " WHERE Bezeichnung = '" + text + "'";
        return query;        
    }


}

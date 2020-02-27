package src;



import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseGateway {

    String connectionUrl = "jdbc:sqlserver://TTLT001\\SQLEXPRESS:7101;database=TestMama;user=user;password=password";
    public List<ArrayList<String>> rows = new ArrayList<>();
    List<String> columnNames = new ArrayList();

    public DatabaseGateway(){
        runQuery();
    }

    public List<ArrayList<String>> getRows() {
        return rows;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void runQuery(){
        String prev = "";


        try{
            Connection con = DriverManager.getConnection(connectionUrl);
             Statement stmt = con.createStatement();
                String SQL = "SELECT BKID, BESCHREIBUNG, NAME FROM dbo.baukasten b INNER JOIN dbo.Werk w ON b.WID = w.WID";
                //TODO
                //getting different SQL statements
                //--------------------------------------
                ResultSet rs = stmt.executeQuery(SQL);//Query will be executed
                ResultSetMetaData rsm = rs.getMetaData();//getting Metadata from rs to get column names

                //iterating through rsm to get column names
                for(int i = 1; i < rsm.getColumnCount() +1; i++){
                    if(!prev.equals(rsm.getColumnName(i))) {
                        columnNames.add(rsm.getColumnName(i));
                    }
                    prev = rsm.getColumnName(i);
                }
            readRows(rs);
            }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readRows(ResultSet rs) throws SQLException {
        while(rs.next()){
            rows.add(new ArrayList<String>(){{
                for(String s : columnNames) {
                    add(rs.getString(s));
                }
            }});
        }
    }

}

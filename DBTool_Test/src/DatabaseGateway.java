import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseGateway {

    public void test(){
        String prev = "";
        List<String> columnNames = new ArrayList();
        String connectionUrl = "jdbc:sqlserver://TTLT001\\SQLEXPRESS:7101;database=TestMama;user=user;password=password";

        try{
            Connection con = DriverManager.getConnection(connectionUrl);
             Statement stmt = con.createStatement();
                String SQL = "SELECT * FROM dbo.baukasten b INNER JOIN dbo.Werk w ON b.WID = w.WID";
                ResultSet rs = stmt.executeQuery(SQL);
                ResultSetMetaData rsm = rs.getMetaData();
                for(int i = 1; i < rsm.getColumnCount() +1; i++){
                    if(!prev.equals(rsm.getColumnName(i))) {
                        System.out.println(rsm.getColumnName(i));
                        columnNames.add(rsm.getColumnName(i));
                    }
                    prev = rsm.getColumnName(i);
                }

                // Iterate through the data in the result set and display it.
                while (rs.next()) {
                    for(String s : columnNames)
                        System.out.print(rs.getString(s));
                    System.out.println();
                }
            }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DatabaseGateway test = new DatabaseGateway();
        test.test();
    }

}

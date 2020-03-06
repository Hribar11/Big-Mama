package src;

import data.SQLResult;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseGateway {

    private static DatabaseGateway singleton;
    String connectionUrl = "jdbc:sqlserver://192.168.43.241:1433;database=TestMama;user=user;password=password";
    private Object rsm;

    public DatabaseGateway() {
        runQuery("");
    }

    public static DatabaseGateway getInstance() {
        if (singleton == null) {
            singleton = new DatabaseGateway();
        }
        return singleton;
    }


    public SQLResult runQuery(String query) {

        System.out.println(query);

        String prev = "";
        
        if (query.isEmpty()) {
            query = "SELECT BKID, b.BEZEICHNUNG, w.BEZEICHNUNG FROM dbo.baukasten b INNER JOIN dbo.Werk w ON b.WID = w.WID";
        }
        ResultSet rs = null;
        try {
            rs = establishConnection(query);

        } catch (Exception e) {
            System.out.println(e);
        }
        return new SQLResult(rs);

    }

    private ResultSet establishConnection(String query) throws Exception {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);//Query will be executed
        return rs;
    }

    public void searchFor(String table, String text) {
        runQuery(createQuery(table, text));
    }

    private String createQuery(String table, String text) {
        String query = "SELECT * FROM " + table + " WHERE Bezeichnung = '" + text + "'";
        return query;
    }

}

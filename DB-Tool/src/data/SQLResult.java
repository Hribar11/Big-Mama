/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tobia
 */
public class SQLResult {

    public List<ArrayList<String>> rows;
    public List<String> headers;
    public ResultSet rs;

    public SQLResult(ResultSet rs) {
        this.rs = rs;
        try {
            getHeadersFromData();
            getRowsFromData();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void getHeadersFromData() throws Exception {
        String prev = "";
        ResultSetMetaData rsm = rs.getMetaData();//getting Metadata from rs to get column names
        //iterating through rsm to get column names
        for (int i = 1; i < rsm.getColumnCount() + 1; i++) {
            if (!prev.equals(rsm.getColumnName(i))) {
                headers.add(rsm.getColumnName(i));
            }
            prev = rsm.getColumnName(i);
        }
    }

    private void getRowsFromData() throws Exception {
        while (rs.next()) {
            rows.add(new ArrayList<String>() {
                {
                    for (String s : headers) {
                        add(rs.getString(s));
                    }
                }
            });
        }
    }

    public List<ArrayList<String>> getRows() {
        return rows;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public ArrayList<String> getRow(int index) {
        return rows.get(index);
    }
    
    public String getCell(int rowIndex, int columnIndex){
        return rows.get(rowIndex).get(columnIndex);
    }
}

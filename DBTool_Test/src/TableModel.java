import javax.swing.table.AbstractTableModel;
import java.util.*;

public class TableModel extends AbstractTableModel {

    private List<String> columnNames = new ArrayList();
    private List<ArrayList<String>> list = new ArrayList();
    private DatabaseGateway dbGateway = new DatabaseGateway();

    public TableModel(){
        //addTestData();
        loadFromDB();
        System.out.println(list.size());
    }

    public void addTestData(){
        list.add(new ArrayList<String>(){{
            add("test1");
            add("test2");
            add("test3");
        }});
        list.add(new ArrayList<String>(){{
            add("test1");
            add("test2");
            add("test3");
        }});
        list.add(new ArrayList<String>(){{
            add("test1");
            add("test2");
            add("test3");
        }});
    }

    public void loadFromDB(){
        list = dbGateway.getRows();
        columnNames = dbGateway.getColumnNames();
    }

    @Override
    public int getRowCount() {return list.size();}

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        return list.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int in){
        return columnNames.get(in);
    }

    @Override
    public boolean isCellEditable(int row, int col){
        if(col!=0)
            return true;
        return false;
    }

    public void clickedOn(int selectedRow) {
        System.out.println(list.get(selectedRow).get(0));
    }
}

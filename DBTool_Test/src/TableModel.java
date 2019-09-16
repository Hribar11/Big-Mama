import javax.swing.table.AbstractTableModel;
import java.util.*;

public class TableModel extends AbstractTableModel {

    private String[] columnNames = {"FirstColumn","SecondColumn","ThirdColumn"};
    List<ArrayList> list = new ArrayList();

    public TableModel(){
        addTestData();
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

    @Override
    public int getRowCount() {return list.get(0).size();}

    @Override
    public int getColumnCount() {
        return list.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return list.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int in){
        return columnNames[in];
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

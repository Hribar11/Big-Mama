import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MamaGUI {
    private JPanel panel1;
    private JTable table1;
    private JScrollPane scrollPane1;
    private TableModel tableModel = new TableModel();

    public MamaGUI() {
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableModel.clickedOn(table1.getSelectedRow());
            }
        });
        table1.setModel(tableModel);
    }

    public void test(){
        scrollPane1.setViewportView(table1);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        MamaGUI gui = new MamaGUI();
        gui.test();
        frame.setContentPane(gui.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.pack();
    }


}
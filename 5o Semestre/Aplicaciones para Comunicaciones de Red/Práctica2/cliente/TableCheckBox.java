import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class TableCheckBox extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    public Articulo a;
    public int nRows;

    public TableCheckBox(Object[][] data) {

    	setLayout(new GridLayout(1,1));

        nRows = data.length;

        String[] columnNames={" ", "ID", "Nombre", "Descripcion", "Precio", "Existencia", "Promocion", " "};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model) {

            private static final long serialVersionUID = 1L;

            /*@Override
            public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
            }*/
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    case 1:
                        return Integer.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return Double.class;
                    case 5:
                        return Integer.class;
                    case 6:
                        return String.class;
                    default:
                        return ImageIcon.class;
                }
            }
        };
        table.setRowHeight(50);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }

    public String getBuyOrder(){
        String buyOrder = "";
        for (int i = 0; i < nRows; i++) {
            boolean aux = (boolean)table.getValueAt(i, 0);
            if (aux) {
                buyOrder = buyOrder + table.getValueAt(i, 1) + ";";
            }
        }
        return buyOrder;
    }
}
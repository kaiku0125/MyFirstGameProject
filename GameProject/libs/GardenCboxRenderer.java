package GameProject.libs;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class GardenCboxRenderer extends JLabel implements ListCellRenderer<Object> {
    public GardenCboxRenderer() {
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        GardenItem item = (GardenItem) value;

        if (item != null) {
            // setText(item.getName());
            setIcon(item.getImg());
        } else {
            System.out.println("item null!!");
            setText("");
            setIcon(null);
        }

        if (isSelected) {
            setBackground(Color.BLUE);
            setForeground(Color.RED);
        } else {
            setForeground(Color.BLACK);
            setBackground(Color.LIGHT_GRAY);
        }
        setFont(list.getFont());
        return this;
    }

}

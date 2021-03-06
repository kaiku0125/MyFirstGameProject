package GameProject.libs;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ComboBoxRenderer extends JLabel implements ListCellRenderer<Object> {
    public ComboBoxRenderer() {
        setBorder(BorderFactory.createLineBorder(Color.white, 1));
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {

        Weapon weapon = (Weapon) value;

        if (weapon != null) {
            setText(weapon.getName());
            setIcon(weapon.getImg());
        } else {
            System.out.println("weapon null!!");
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

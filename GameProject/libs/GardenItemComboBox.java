package GameProject.libs;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import GameProject.Game.ModelInterface;

public class GardenItemComboBox extends JComboBox<Object> {
    private DefaultComboBoxModel<Object> model;
    ModelInterface modelInterface;
    GardenItem banana, apple, orange, melon;

    public GardenItemComboBox(ModelInterface modelInterface) {
        this.modelInterface = modelInterface;

        banana_init();

        model = new DefaultComboBoxModel<Object>();
        model.addElement(banana);

        setModel(model);
        setRenderer(new GardenCboxRenderer());
    }

    public void banana_init() {
        banana = new Banana(new ImageIcon("GameProject//res//pics//Banana.png"));
    }
}

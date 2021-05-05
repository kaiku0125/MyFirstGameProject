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
        apple_init();
        orange_init();
        melon_init();

        model = new DefaultComboBoxModel<Object>();
        model.addElement(banana);
        model.addElement(apple);
        model.addElement(orange);
        model.addElement(melon);

        setModel(model);
        setRenderer(new GardenCboxRenderer());
    }

    public void banana_init() {
        banana = new Banana(new ImageIcon("GameProject//res//pics//Banana.png"));
    }

    public void apple_init() {
        apple = new Apple(new ImageIcon("GameProject//res//pics//apple.jpg"));
    }

    public void orange_init() {
        orange = new Orange(new ImageIcon("GameProject//res//pics//orange.png"));
    }

    public void melon_init() {
        melon = new Melon(new ImageIcon("GameProject//res//pics//melon.jpg"));
    }
}

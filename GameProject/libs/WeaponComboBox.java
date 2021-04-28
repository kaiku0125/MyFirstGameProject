package GameProject.libs;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import GameProject.Game.ModelInterface;

public class WeaponComboBox extends JComboBox<Object> {
    private DefaultComboBoxModel<Object> model;
    ModelInterface modelInterface;
    Weapon sword, bow;

    public WeaponComboBox(ModelInterface modelInterface) {
        this.modelInterface = modelInterface;
        modelInterface.initWeapon();
        sword_init();
        bow_init();
        model = new DefaultComboBoxModel<Object>();
        model.addElement(sword);
        model.addElement(bow);
        setModel(model);
        setRenderer(new ComboBoxRenderer());
    }

    public void sword_init() {
        sword = new Weapon(new ImageIcon("GameProject//res//pics//Sword.jpg"), "   Sword");
        sword.setlevel(modelInterface.getSwordLevel());
        sword.setImgEntity(new ImageIcon("GameProject//res//pics//SwordEntity.jpg"));
    }

    public void bow_init() {
        bow = new Weapon(new ImageIcon("GameProject//res//pics//Bow.jpg"), "   Bow");
        bow.setlevel(modelInterface.getBowLevel());
        bow.setImgEntity(new ImageIcon("GameProject//res//pics//BowEntity.jpg"));
    }

}

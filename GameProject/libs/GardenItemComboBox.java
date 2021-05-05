package GameProject.libs;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import GameProject.Game.ModelInterface;

public class GardenItemComboBox extends JComboBox<Object> {
    private DefaultComboBoxModel<Object> model;
    ModelInterface modelInterface;
    GardenItem banana, apple, orange, melon;

    public GardenItemComboBox(ModelInterface modelInterface) {
        this.modelInterface = modelInterface;
        model = new DefaultComboBoxModel<Object>();

        model.addElement(modelInterface.getGardenEntity().getBananaEntity());
        model.addElement(modelInterface.getGardenEntity().getAppleEntity());
        model.addElement(modelInterface.getGardenEntity().getOrangeEntity());
        model.addElement(modelInterface.getGardenEntity().getMelonEntity());

        setModel(model);
        setRenderer(new GardenCboxRenderer());
    }
}

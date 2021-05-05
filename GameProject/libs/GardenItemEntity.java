package GameProject.libs;

import javax.swing.ImageIcon;

import GameProject.Game.ModelInterface;

public class GardenItemEntity {
    ModelInterface model;
    GardenItem bananaItem, appleItem, orangeItem, melonItem;

    public GardenItemEntity(ModelInterface model) {
        this.model = model;
        init_allEntity();
    }

    void init_allEntity() {
        bananaItem = new Banana(new ImageIcon("GameProject//res//pics//Banana.png"));
        appleItem = new Apple(new ImageIcon("GameProject//res//pics//apple.jpg"));
        orangeItem = new Orange(new ImageIcon("GameProject//res//pics//orange.png"));
        melonItem = new Melon(new ImageIcon("GameProject//res//pics//melon.jpg"));
    }

    public GardenItem getBananaEntity() {
        return this.bananaItem;
    }

    public GardenItem getAppleEntity() {
        return this.appleItem;
    }

    public GardenItem getOrangeEntity() {
        return this.orangeItem;
    }

    public GardenItem getMelonEntity() {
        return this.melonItem;
    }

}

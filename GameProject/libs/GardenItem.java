package GameProject.libs;

import javax.swing.Icon;

public abstract class GardenItem {
    private String name;
    private Icon icon;
    public int harvestNum = 0;

    public GardenItem(Icon icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getImg() {
        return icon;
    }

    public void setImg(Icon img) {
        this.icon = img;
    }

    public void setHarvest(int item_harvest) {
        this.harvestNum = item_harvest;
    }

    public int getHarvest() {
        return harvestNum;
    }
}

class Banana extends GardenItem {

    public Banana(Icon icon) {
        super(icon);
        setName("Banana");
    }

    // public void setharvest(int item_harvest){
    // this.banana_harvest = item_harvest;
    // }

    // public int getHarvest(){
    // return this.banana_harvest;
    // }
}

class Apple extends GardenItem {

    public Apple(Icon icon) {
        super(icon);
        setName("Apple");
    }
}

class Orange extends GardenItem {

    public Orange(Icon icon) {
        super(icon);
        setName("Orange");
    }
}

class Melon extends GardenItem {

    public Melon(Icon icon) {
        super(icon);
        setName("Melon");
    }

}

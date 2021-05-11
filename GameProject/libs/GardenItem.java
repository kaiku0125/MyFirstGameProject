package GameProject.libs;

import javax.swing.Icon;

public abstract class GardenItem {
    private String name, codeName;
    private Icon icon;
    public int harvestNum = 0;

    public GardenItem(Icon icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setCodeName(String name) {
        this.codeName = name;
    }

    public String getCodeName() {
        return codeName;
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
        setCodeName("b");
    }
}

class Apple extends GardenItem {

    public Apple(Icon icon) {
        super(icon);
        setName("Apple");
        setCodeName("a");
    }
}

class Orange extends GardenItem {

    public Orange(Icon icon) {
        super(icon);
        setName("Orange");
        setCodeName("o");
    }
}

class Melon extends GardenItem {

    public Melon(Icon icon) {
        super(icon);
        setName("Melon");
        setCodeName("m");
    }

}

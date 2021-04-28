package GameProject.libs;

import javax.swing.Icon;

public abstract class GardenItem {
    private String name;
    private Icon icon;

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
}

class Banana extends GardenItem {

    public Banana(Icon icon) {
        super(icon);
        setName("Banana");
    }

}

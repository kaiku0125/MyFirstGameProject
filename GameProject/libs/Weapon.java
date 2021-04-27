package GameProject.libs;

import javax.swing.Icon;

public class Weapon {
    private String name;
    private int level;
    private Icon img, imgEntity;

    public Weapon(Icon img, String name) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getLeveltext() {
        return "+" + String.valueOf(level);
    }

    public int getLevel() {
        return level;
    }

    public void setlevel(int level) {
        this.level = level;
    }

    public Icon getImg() {
        return img;
    }

    public void setImg(Icon img) {
        this.img = img;
    }

    public void setImgEntity(Icon img) {
        this.imgEntity = img;
    }

    public Icon getImgEntity() {
        return imgEntity;
    }
}

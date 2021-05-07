package GameProject.Game;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;

import GameProject.libs.GardenItem;
import GameProject.libs.GardenItemComboBox;
import GameProject.libs.Weapon;

public interface ControllerMainInterface {
    void login();

    void stop();

    void closeStore();

    // Coin
    void minusCoin(int coin);

    boolean coinchecker(int coin);

    // Stone
    void minusStone(int stone);

    boolean stoneChecker(int stone);

    void minusExtremeStone(int stone);

    boolean extremeStoneChecker(int stone);

    void minusProtectStone(int stone);

    boolean protectStoneChecker(int stone);

    // Element
    boolean bananChecker(int banana);

    boolean appleChecker(int apple);

    boolean orangeChecker(int orange);

    boolean melonChecker(int melon);

    // Daily
    void setDailybtnEnable(boolean b);

    void dailystart();

    void getdailyBouns();

    // Enhance
    void initEnhanceImg(Weapon weapon);

    void enhanceStart();

    void enhanceEnd();

    void getEnhanceResult();

    void enhanceThread(JProgressBar jProgressBar);

    boolean isEnhanceRunning();

    void enhanceProgressEnd();

    boolean isSuccess();

    void setprotect(boolean b);

    void setNotFailure(boolean b);

    // Alchemy
    void alchemyStart();

    void alchemyEnd();

    void alchemyThread(JProgressBar jProgressBar);

    boolean isAlchemyRunning();

    void alchemyProgressEnd();

    void alchemyMinus(int banana, int apple, int orange, int melon);

    void getAlchemyResult();

    // Garden
    void gd_start(JButton btn);

    void harvest(GardenItem gardenItem);

    void plusHarvest();

    void eventGdBtn(JButton btn, GardenItemComboBox gdCb);

    // Store
    void openStore();

    void buyControl(JButton btn);

    void sold(int have, int sell, int price);
}

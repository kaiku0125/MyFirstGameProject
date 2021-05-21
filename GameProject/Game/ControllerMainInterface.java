package GameProject.Game;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import GameProject.libs.GardenItem;
import GameProject.libs.GardenItemComboBox;
import GameProject.libs.Weapon;

public interface ControllerMainInterface {
    void login();

    void initLogin();

    void stop();

    // Exp
    void expController(JProgressBar pb, int plus_exp);

    // Coin
    void minusCoin(int coin);

    // Stone
    void minusStone(int stone);

    void minusExtremeStone(int stone);

    void minusProtectStone(int stone);

    // checker
    boolean checker(int inputNum, String name);

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
    void setAllGdCb();

    void gd_start(JButton btn);

    void harvest(GardenItem gardenItem);

    void plusHarvest();

    void eventGdBtn(JButton btn, GardenItemComboBox gdCb, int index);

    // Store
    void openStore();

    void closeStore();

    void buyControl(JButton btn);

    void sold(int have, int sell, int price);

    int getItemCbObjectPrice(JComboBox<String> myItemCb);

    void myItemCb_controller(int index, JLabel label, JComboBox<String> cb);

    void resetSoldNum();
}

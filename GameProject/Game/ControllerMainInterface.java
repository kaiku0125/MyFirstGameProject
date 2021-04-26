package GameProject.Game;

import javax.swing.JProgressBar;

public interface ControllerMainInterface {
    void login();

    void stop();

    // Coin
    void minusCoin(int coin);

    boolean coinchecker(int coin);

    // Stone
    void minusStone(int stone);

    boolean stoneChecker(int stone);

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
    void enhanceStart();

    void enhanceEnd();

    void getEnhanceResult();

    void enhanceThread(JProgressBar jProgressBar);

    boolean isEnhanceRunning();

    void enhanceProgressEnd();

    // Alchemy
    void alchemyStart();

    void alchemyEnd();

    void alchemyThread(JProgressBar jProgressBar);

    boolean isAlchemyRunning();

    void alchemyProgressEnd();

    void alchemyMinus(int banana, int apple, int orange, int melon);

    void getAlchemyResult();
}

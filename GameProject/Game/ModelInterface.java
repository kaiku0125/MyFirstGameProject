package GameProject.Game;

import GameProject.libs.GardenItem;
import GameProject.libs.GardenItemEntity;

public interface ModelInterface {
    void on();

    void off();

    void initData();

    void saveData();

    void initWeapon();

    void checkID();

    void regID();

    String getpassword();

    // Coin
    void coincounter();

    int getCoin();

    void setCoin(int coin);

    // Stone
    void setStone(int stone);

    int getStone();

    void setExtrmeneStone(int stone);

    int getExtremeStone();

    void setProtectStone(int protectStone);

    int getProtectStone();

    void setFailureTimes(int fail);

    int getFailureTimes();

    // Timer
    void initStoneTimer();

    void restartStoneTimer();

    void initDailyTimer();

    void restartDailyTimer();

    boolean isDailyfinish();

    // GD TIMER
    void initTimer_Gd1();

    void restartTimer_Gd1();

    boolean isfinish_Gd1();

    void initTimer_Gd2();

    void restartTimer_Gd2();

    boolean isfinish_Gd2();

    void initTimer_Gd3();

    void restartTimer_Gd3();

    boolean isfinish_Gd3();

    void initTimer_Gd4();

    void restartTimer_Gd4();

    boolean isfinish_Gd4();

    ClockTask getStoneClock();

    ClockTask getDailyClock();

    ClockTask getClock_gd1();

    ClockTask getClock_gd2();

    ClockTask getClock_gd3();

    ClockTask getClock_gd4();

    // Observer
    void registerCoinObserver(CoinObserver o);

    void removeCoinObserver(CoinObserver o);

    void notifyCoinObserver();

    void registerClockObserver(ClockObserver o);

    void removeClockObserver(ClockObserver o);

    void notifyStoneClockObserver();

    void notifyDailyClockObserver();

    String getStoneSecond();

    String getStoneMinute();

    String getDailySecond();

    String getDailyMinute();

    String getSecond_gd1();

    String getMinute_gd1();

    String getSecond_gd2();

    String getMinute_gd2();

    String getSecond_gd3();

    String getMinute_gd3();

    String getSecond_gd4();

    String getMinute_gd4();

    // Listener
    void regStoneCallback(Callback callback);

    void stoneListener();

    void extremeStoneListener();

    void protectStoneListener();

    void failureListener();

    void enhanceProgressListener();

    void alchemyProgressListener();

    void bananaListener(int num);

    void appleListener();

    void orangeListener();

    void melonListener();

    void gdBtn_1Listener();

    void gdBtn_2Listener();

    void gdBtn_3Listener();

    void gdBtn_4Listener();

    // Element
    int getBanana();

    void setBanana(int num);

    int getApple();

    void setApple(int num);

    int getOrange();

    void setOrange(int num);

    int getMelon();

    void setMelon(int num);

    // Weapon
    int getSwordLevel();

    void setSwordLevel(int level);

    int getBowLevel();

    void setBowLevel(int level);

    int getCurrentLevel();

    void setCurrentLevel(int level);

    // Garden
    GardenItemEntity getGardenEntity();

    // GardenItem getBananaEntity();

    // GardenItem getAppleEntity();

    // GardenItem getOrangeEntity();

    // GardenItem getMelonEntity();

}

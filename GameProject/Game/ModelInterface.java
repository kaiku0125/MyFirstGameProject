package GameProject.Game;

import GameProject.libs.GardenItemEntity;

public interface ModelInterface {
    void on();

    void off();

    void initData();

    void saveData();

    void initWeapon();

    void initGdCbList(String gdCbItem);

    String getGdCbList(int index);

    void setGdCbLiset(int index, String gdCbItem);

    void checkID();

    void regID();

    String getpassword();

    void setPlayerlevel(int level);

    int getPlayerlevel();

    void setExp(int exp);

    int getExp();

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

    void initTimer_Gd5();

    void restartTimer_Gd5();

    boolean isfinish_Gd5();

    void initTimer_Gd6();

    void restartTimer_Gd6();

    boolean isfinish_Gd6();

    ClockTask getStoneClock();

    ClockTask getDailyClock();

    ClockTask getClock_gd1();

    ClockTask getClock_gd2();

    ClockTask getClock_gd3();

    ClockTask getClock_gd4();

    ClockTask getClock_gd5();

    ClockTask getClock_gd6();

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

    String getSecond_gd5();

    String getMinute_gd5();

    String getSecond_gd6();

    String getMinute_gd6();

    // Listener
    void regStoneCallback(Callback callback);

    void stoneListener();

    void extremeStoneListener();

    void protectStoneListener();

    void failureListener();

    void enhanceProgressListener();

    void alchemyProgressListener();

    void playerlevelListener();

    void bananaListener(int num);

    void appleListener();

    void orangeListener();

    void melonListener();

    void dregsListener();

    void gdBtn_1Listener();

    void gdBtn_2Listener();

    void gdBtn_3Listener();

    void gdBtn_4Listener();

    void gdBtn_5Listener();

    void gdBtn_6Listener();

    // Element
    int getBanana();

    void setBanana(int num);

    int getApple();

    void setApple(int num);

    int getOrange();

    void setOrange(int num);

    int getMelon();

    void setMelon(int num);

    int getDredgs();

    void setDregs(int num);

    // Weapon
    int getSwordLevel();

    void setSwordLevel(int level);

    int getBowLevel();

    void setBowLevel(int level);

    int getCurrentLevel();

    void setCurrentLevel(int level);

    // Garden
    GardenItemEntity getGardenEntity();

    // Store
    boolean getFarm1Sold();

    void setFarm1Sold(boolean b);

    boolean getFarm2Sold();

    void setFarm2Sold(boolean b);
}

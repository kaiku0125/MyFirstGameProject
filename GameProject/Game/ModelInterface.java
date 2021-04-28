package GameProject.Game;

public interface ModelInterface {
    void on();

    void off();

    void initData();

    void saveData();

    void initWeapon();

    void checkID();

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

    void initTimer_Gd1();

    void restartTimer_Gd1();

    boolean isfinish_Gd1();

    ClockTask getStoneClock();

    ClockTask getDailyClock();

    ClockTask getClock_gd1();

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

}

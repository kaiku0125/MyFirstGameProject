package GameProject.Game;

import java.util.*;
import java.util.logging.Logger;
import GameProject.libs.GardenItemEntity;

public class Model implements ModelInterface {
    private static Logger logger = Logger.getLogger(Model.class.getName());
    ArrayList<CoinObserver> coinObservers = new ArrayList<CoinObserver>();
    ArrayList<ClockObserver> clockObservers = new ArrayList<ClockObserver>();
    Dataset dataset;
    Callback callback;
    ClockTask stoneTask, dailyTask, gd1_Task, gd2_Task, gd3_Task, gd4_Task;
    ClockTask gd5_Task, gd6_Task;
    GardenItemEntity gardenItemEntity;
    String password = null;
    public static int coin;
    public int stone, extremeStone, protectStone, failureTimes;
    boolean ismain = false;
    int banana, apple, orange, melon, dregs;
    int swordLevel, bowLevel, currentLevel;
    boolean isfarm1_sold, isfarm2_sold;

    public Model() {
        dataset = new Dataset(this);
        gardenItemEntity = new GardenItemEntity(this);
    }

    @Override
    public void on() {
        ismain = true;
        initData();
        coincounter();
        startallTimer();
    }

    @Override
    public void off() {
        saveData();
    }

    public void startallTimer() {
        initDailyTimer();
        initStoneTimer();
        initTimer_Gd1();
        initTimer_Gd2();
        initTimer_Gd3();
        initTimer_Gd4();
        initTimer_Gd5();
        initTimer_Gd6();
    }

    @Override
    public void initData() {
        // dataset.readcoin();
        // dataset.readStone();
        // dataset.readClock();
        // dataset.readElement();
        // dataset.readFailure();
        // dataset.readSoldState();
        dataset.initAllData();
    }

    @Override
    public void saveData() {
        // dataset.savecoin();
        // dataset.saveStone();
        // dataset.saveClock();
        // dataset.saveElement();
        // dataset.saveWeaponLevel();
        // dataset.saveFailure();
        // dataset.saveSoldState();
        dataset.saveAllData();
    }

    @Override
    public void initWeapon() {
        dataset.readWeaponLevel();
    }

    @Override
    public void checkID() {
        dataset.checkUserID();
        password = dataset.getPassword();
    }

    @Override
    public void regID() {
        dataset.regUserID();
    }

    @Override
    public String getpassword() {
        return password;
    }

    @Override
    public void coincounter() {
        Timer cointimer = new Timer();
        cointimer.schedule(new CoinTask(this), 0, 333);
    }

    @Override
    public int getCoin() {
        return coin;
    }

    @Override
    public void setCoin(int coin) {
        Model.coin = coin;
        notifyCoinObserver();
    }

    @Override
    public void setStone(int stone) {
        this.stone = stone;
        stoneListener();
    }

    @Override
    public int getStone() {
        return stone;
    }

    @Override
    public void setExtrmeneStone(int stone) {
        this.extremeStone = stone;
        extremeStoneListener();
    }

    @Override
    public int getExtremeStone() {
        return extremeStone;
    }

    @Override
    public void setProtectStone(int protectStone) {
        this.protectStone = protectStone;
        protectStoneListener();
    }

    @Override
    public int getProtectStone() {
        return protectStone;
    }

    @Override
    public void setFailureTimes(int fail) {
        this.failureTimes = fail;
        failureListener();
    }

    @Override
    public int getFailureTimes() {
        return failureTimes;
    }

    @Override
    public void initStoneTimer() {
        stoneTask = new StoneClock(this);
        stoneTask.setMinute(dataset.getClockindex(0));
        stoneTask.setSecond(dataset.getClockindex(1));
        stoneTask.getTimer().scheduleAtFixedRate(stoneTask, 0, 1000);

    }

    @Override
    public void restartStoneTimer() {
        stoneTask = new StoneClock(this);
        stoneTask.setMinute(1);
        stoneTask.setSecond(1);
        stoneTask.getTimer().scheduleAtFixedRate(stoneTask, 0, 1000);
    }

    @Override
    public void initDailyTimer() {
        dailyTask = new DailyClock(this);
        dailyTask.setMinute(dataset.getClockindex(2));
        dailyTask.setSecond(dataset.getClockindex(3));
        dailyTask.getTimer().scheduleAtFixedRate(dailyTask, 0, 1000);
    }

    @Override
    public void restartDailyTimer() {
        dailyTask.again();
        dailyTask = new DailyClock(this);
        dailyTask.setMinute(1);
        dailyTask.setSecond(1);
        dailyTask.getTimer().scheduleAtFixedRate(dailyTask, 0, 1000);
    }

    @Override
    public boolean isDailyfinish() {
        return dailyTask.isfinish();
    }

    @Override
    public void initTimer_Gd1() {
        gd1_Task = new Gd1_Clock(this);
        gd1_Task.setMinute(dataset.getClockindex(4));
        gd1_Task.setSecond(dataset.getClockindex(5));
        gd1_Task.getTimer().scheduleAtFixedRate(gd1_Task, 0, 1000);
    }

    @Override
    public void restartTimer_Gd1() {
        gd1_Task.again();
        gd1_Task = new Gd1_Clock(this);
        gd1_Task.setMinute(1);
        gd1_Task.setSecond(1);
        gd1_Task.getTimer().scheduleAtFixedRate(gd1_Task, 0, 1000);
    }

    @Override
    public boolean isfinish_Gd1() {
        return gd1_Task.isfinish();
    }

    @Override
    public void initTimer_Gd2() {
        gd2_Task = new Gd2_Clock(this);
        gd2_Task.setMinute(dataset.getClockindex(6));
        gd2_Task.setSecond(dataset.getClockindex(7));
        gd2_Task.getTimer().scheduleAtFixedRate(gd2_Task, 0, 1000);

    }

    @Override
    public void restartTimer_Gd2() {
        gd2_Task.again();
        gd2_Task = new Gd2_Clock(this);
        gd2_Task.setMinute(1);
        gd2_Task.setSecond(1);
        gd2_Task.getTimer().scheduleAtFixedRate(gd2_Task, 0, 1000);

    }

    @Override
    public boolean isfinish_Gd2() {
        return gd2_Task.isfinish();
    }

    @Override
    public void initTimer_Gd3() {
        gd3_Task = new Gd3_Clock(this);
        gd3_Task.setMinute(dataset.getClockindex(8));
        gd3_Task.setSecond(dataset.getClockindex(9));
        gd3_Task.getTimer().scheduleAtFixedRate(gd3_Task, 0, 1000);

    }

    @Override
    public void restartTimer_Gd3() {
        gd3_Task.again();
        gd3_Task = new Gd3_Clock(this);
        gd3_Task.setMinute(1);
        gd3_Task.setSecond(1);
        gd3_Task.getTimer().scheduleAtFixedRate(gd3_Task, 0, 1000);

    }

    @Override
    public boolean isfinish_Gd3() {
        return gd3_Task.isfinish();
    }

    @Override
    public void initTimer_Gd4() {
        gd4_Task = new Gd4_Clock(this);
        gd4_Task.setMinute(dataset.getClockindex(10));
        gd4_Task.setSecond(dataset.getClockindex(11));
        gd4_Task.getTimer().scheduleAtFixedRate(gd4_Task, 0, 1000);

    }

    @Override
    public void restartTimer_Gd4() {
        gd4_Task.again();
        gd4_Task = new Gd4_Clock(this);
        gd4_Task.setMinute(1);
        gd4_Task.setSecond(1);
        gd4_Task.getTimer().scheduleAtFixedRate(gd4_Task, 0, 1000);
    }

    @Override
    public boolean isfinish_Gd4() {
        return gd4_Task.isfinish();
    }

    @Override
    public void initTimer_Gd5() {
        if (getFarm1Sold()) {
            gd5_Task = new Gd5_Clock(this);
            gd5_Task.setMinute(dataset.getClockindex(12));
            gd5_Task.setSecond(dataset.getClockindex(13));
            gd5_Task.getTimer().scheduleAtFixedRate(gd5_Task, 0, 1000);
        }
    }

    @Override
    public void restartTimer_Gd5() {
        gd5_Task = new Gd5_Clock(this);
        gd5_Task.again();
        gd5_Task.setMinute(1);
        gd5_Task.setSecond(1);
        gd5_Task.getTimer().scheduleAtFixedRate(gd5_Task, 0, 1000);
    }

    @Override
    public boolean isfinish_Gd5() {
        return gd5_Task.isfinish();
    }

    @Override
    public void initTimer_Gd6() {
        if (getFarm2Sold()) {
            gd6_Task = new Gd6_Clock(this);
            gd6_Task.setMinute(dataset.getClockindex(14));
            gd6_Task.setSecond(dataset.getClockindex(15));
            gd6_Task.getTimer().scheduleAtFixedRate(gd6_Task, 0, 1000);
        }
    }

    @Override
    public void restartTimer_Gd6() {
        if (getFarm2Sold()) {
            gd6_Task = new Gd6_Clock(this);
            gd6_Task.again();
            gd6_Task.setMinute(1);
            gd6_Task.setSecond(1);
            gd6_Task.getTimer().scheduleAtFixedRate(gd6_Task, 0, 1000);
        }

    }

    @Override
    public boolean isfinish_Gd6() {
        return gd6_Task.isfinish();
    }

    @Override
    public ClockTask getDailyClock() {
        return dailyTask;
    }

    @Override
    public ClockTask getStoneClock() {
        return stoneTask;
    }

    @Override
    public ClockTask getClock_gd1() {
        return gd1_Task;
    }

    @Override
    public ClockTask getClock_gd2() {
        return gd2_Task;
    }

    @Override
    public ClockTask getClock_gd3() {
        return gd3_Task;
    }

    @Override
    public ClockTask getClock_gd4() {
        return gd4_Task;
    }

    @Override
    public ClockTask getClock_gd5() {
        return gd5_Task;
    }

    @Override
    public ClockTask getClock_gd6() {
        return gd6_Task;
    }

    @Override
    public void registerCoinObserver(CoinObserver o) {
        coinObservers.add(o);
    }

    @Override
    public void removeCoinObserver(CoinObserver o) {
        int index = coinObservers.indexOf(o);
        coinObservers.remove(index);
    }

    @Override
    public void notifyCoinObserver() {
        for (int i = 0; i < coinObservers.size(); i++) {
            CoinObserver observers = coinObservers.get(i);
            observers.updateCoin();
        }
    }

    @Override
    public void registerClockObserver(ClockObserver o) {
        clockObservers.add(o);

    }

    @Override
    public void removeClockObserver(ClockObserver o) {
        int index = clockObservers.indexOf(o);
        clockObservers.remove(index);
    }

    @Override
    public void notifyStoneClockObserver() {
        for (int i = 0; i < clockObservers.size(); i++) {
            ClockObserver observers = clockObservers.get(i);
            observers.updateStoneClock();
        }
    }

    @Override
    public void notifyDailyClockObserver() {
        for (int i = 0; i < clockObservers.size(); i++) {
            ClockObserver observers = clockObservers.get(i);
            observers.updateDailyClock();
        }
    }

    @Override
    public String getStoneSecond() {
        return stoneTask.dsecond;
    }

    @Override
    public String getStoneMinute() {
        return stoneTask.dminute;
    }

    @Override
    public String getDailySecond() {
        return dailyTask.dsecond;
    }

    @Override
    public String getDailyMinute() {
        return dailyTask.dminute;
    }

    @Override
    public String getSecond_gd1() {
        return gd1_Task.dsecond;
    }

    @Override
    public String getMinute_gd1() {
        return gd1_Task.dminute;
    }

    @Override
    public String getSecond_gd2() {
        return gd2_Task.dsecond;
    }

    @Override
    public String getMinute_gd2() {
        return gd2_Task.dminute;
    }

    @Override
    public String getSecond_gd3() {
        return gd3_Task.dsecond;
    }

    @Override
    public String getMinute_gd3() {
        return gd3_Task.dminute;
    }

    @Override
    public String getSecond_gd4() {
        return gd4_Task.dsecond;
    }

    @Override
    public String getMinute_gd4() {
        return gd4_Task.dminute;
    }

    @Override
    public String getSecond_gd5() {
        return gd5_Task.dsecond;
    }

    @Override
    public String getMinute_gd5() {
        return gd5_Task.dminute;
    }

    @Override
    public String getSecond_gd6() {
        return gd6_Task.dsecond;
    }

    @Override
    public String getMinute_gd6() {
        return gd6_Task.dminute;
    }

    @Override
    public void stoneListener() {
        callback.updateStone();
    }

    @Override
    public void extremeStoneListener() {
        callback.updateExtremeStone();
    }

    @Override
    public void protectStoneListener() {
        callback.updateProtectStone();
    }

    @Override
    public void failureListener() {
        callback.updateFailure();
    }

    @Override
    public void enhanceProgressListener() {
        callback.enhanceProgressEnd();
    }

    @Override
    public void alchemyProgressListener() {
        callback.alchemyProgressEnd();
    }

    @Override
    public void bananaListener(int num) {
        callback.updateBanana(banana);

    }

    @Override
    public void appleListener() {
        callback.updateApple();
    }

    @Override
    public void orangeListener() {
        callback.updateOrange();
    }

    @Override
    public void melonListener() {
        callback.updateMelon();
    }

    @Override
    public void dregsListener() {
        callback.updateDregs();
    }

    @Override
    public void gdBtn_1Listener() {
        callback.updateClockGdBtn_1();
    }

    @Override
    public void gdBtn_2Listener() {
        callback.updateClockGdBtn_2();
    }

    @Override
    public void gdBtn_3Listener() {
        callback.updateClockGdBtn_3();
    }

    @Override
    public void gdBtn_4Listener() {
        callback.updateClockGdBtn_4();
    }

    @Override
    public void gdBtn_5Listener() {
        callback.updateClockGdBtn_5();
    }

    @Override
    public void gdBtn_6Listener() {
        callback.updateClockGdBtn_6();
    }

    @Override
    public void regStoneCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getBanana() {
        return banana;
    }

    @Override
    public void setBanana(int num) {
        this.banana = num;
        bananaListener(banana);
    }

    @Override
    public int getApple() {
        return apple;
    }

    @Override
    public void setApple(int num) {
        this.apple = num;
        appleListener();
    }

    @Override
    public int getOrange() {
        return orange;
    }

    @Override
    public void setOrange(int num) {
        this.orange = num;
        orangeListener();
    }

    @Override
    public int getMelon() {
        return melon;
    }

    @Override
    public void setMelon(int num) {
        this.melon = num;
        melonListener();
    }

    @Override
    public int getDredgs() {
        return dregs;
    }

    @Override
    public void setDregs(int num) {
        this.dregs = num;
        dregsListener();
    }

    // Weapon
    @Override
    public int getSwordLevel() {
        return swordLevel;
    }

    @Override
    public void setSwordLevel(int level) {
        this.swordLevel = level;
        dataset.saveWeaponLevel();
    }

    @Override
    public int getBowLevel() {
        return bowLevel;
    }

    @Override
    public void setBowLevel(int level) {
        this.bowLevel = level;
        dataset.saveWeaponLevel();
    }

    @Override
    public int getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void setCurrentLevel(int level) {
        this.currentLevel = level;
    }

    // Garden
    @Override
    public GardenItemEntity getGardenEntity() {
        return this.gardenItemEntity;
    }

    // Store
    @Override
    public boolean getFarm1Sold() {
        return this.isfarm1_sold;
    }

    @Override
    public void setFarm1Sold(boolean b) {
        this.isfarm1_sold = b;
    }

    @Override
    public boolean getFarm2Sold() {
        return isfarm2_sold;
    }

    @Override
    public void setFarm2Sold(boolean b) {
        this.isfarm2_sold = b;
    }

}

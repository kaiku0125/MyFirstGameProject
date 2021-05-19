package GameProject.Game;

import java.awt.Color;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import GameProject.Game.Rate.BounsItem;
import GameProject.Game.Rate.Result;
import GameProject.libs.GardenItem;
import GameProject.libs.GardenItemComboBox;
import GameProject.libs.Weapon;

public class ControllerMain implements ControllerMainInterface {
    private static Logger logger = Logger.getLogger(ControllerMain.class.getName());
    ExecutorService agent;
    ModelInterface model;
    JProgressBar jProgressBar;
    ProgressBar progress;
    ViewLogin viewlogin;
    ViewMain viewMain;
    ViewStore viewStore;
    BounsItem bonus;
    Result result;
    int tempcoin, tempstone, tempFail, tempcheck;
    int dailySecond, dailyMinute;
    int failurePlus;
    StringBuilder sb;
    boolean success, protect, notFailure;
    GardenItem bananaItem, appleItem, orangeItem, melonItem;
    public static final boolean SOLD = true;
    protected Random random;
    int rInt;
    private static final Color DARK_GREEN = new Color(0, 153, 0);

    public ControllerMain(ModelInterface model) {
        this.model = model;
        // ViewLogin viewlogin = new ViewLogin(this, model);
        // ViewMain viewMain = new ViewMain(this, model);
        // this.viewlogin = viewlogin;
        // this.viewMain = viewMain;
        // viewlogin.createLoginView();
        random = new Random();
    }

    @Override
    public void initLogin() {
        viewlogin = new ViewLogin(this, model);
        viewlogin.createLoginView();
    }

    @Override
    public void login() {
        viewMain = new ViewMain(this, model);
        viewMain.createMainView();
        viewMain.createControls();
        model.on();
        setGardenItem();
        setAllGdCb();
        setPlayerInformation();
    }

    @Override
    public void stop() {
        model.off();
    }

    public void setPlayerInformation() {
        viewMain.setExpBar(model.getExp(), String.valueOf(model.getExp()) + " / 100");
    }

    @Override
    public void expController(JProgressBar expb, int plus_exp) {
        int originExp = expb.getValue();
        int templevel = model.getPlayerlevel();
        agent = Executors.newSingleThreadExecutor();
        agent.execute(new Runnable() {
            @Override
            public void run() {
                if (templevel < 10) {
                    for (int exp = originExp; exp <= originExp + plus_exp; exp++) {
                        try {
                            Thread.sleep(150);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (exp >= 100) {
                            if (templevel + 1 >= 10) {
                                expb.setString("MAX");
                                model.setPlayerlevel(10);
                            } else {
                                model.setPlayerlevel(templevel + 1);
                                model.setExp(exp - 100);
                                expb.setValue(model.getExp());
                                expb.setString(exp - 100 + " / 100");
                            }
                        } else {
                            model.setExp(exp);
                            expb.setValue(model.getExp());
                            expb.setString(exp + " / 100");
                        }
                    }
                }
            }
        });
        agent.shutdown();
    }

    @Override
    public void minusCoin(int coin) {
        tempcoin = model.getCoin();
        tempcoin -= coin;
        model.setCoin(tempcoin);
    }

    public void plusCoin(int coin) {
        tempcoin = model.getCoin();
        tempcoin += coin;
        model.setCoin(tempcoin);
    }

    @Override
    public void minusStone(int stone) {
        tempstone = model.getStone();
        tempstone -= stone;
        model.setStone(tempstone);
    }

    public void plusStone(int stone) {
        tempstone = model.getStone();
        tempstone += stone;
        model.setStone(tempstone);
    }

    // .......................Extreme Stone.....................//
    @Override
    public void minusExtremeStone(int stone) {
        tempstone = model.getExtremeStone();
        tempstone -= stone;
        model.setExtrmeneStone(tempstone);

    }

    public void plusExtremeStone(int stone) {
        tempstone = model.getExtremeStone();
        tempstone += stone;
        model.setExtrmeneStone(tempstone);
    }

    // .........................Protect Stone......................//
    @Override
    public void minusProtectStone(int stone) {
        tempstone = model.getProtectStone();
        tempstone -= stone;
        model.setProtectStone(tempstone);
    }

    public void plusProtectStone(int stone) {
        tempstone = model.getProtectStone();
        tempstone += stone;
        model.setProtectStone(tempstone);
    }

    // .........................Failure Times......................//
    public void plusFailureTimes(int fail) {
        tempFail = model.getFailureTimes();
        tempFail += fail;
        model.setFailureTimes(tempFail);
    }

    // check all minus item..............................//
    @Override
    public boolean checker(int inputNum, String name) {
        switch (name) {
            case "Coin":
                tempcheck = model.getCoin();
                break;
            case "Banana":
                tempcheck = model.getBanana();
                break;
            case "Apple":
                tempcheck = model.getApple();
                break;
            case "Orange":
                tempcheck = model.getOrange();
                break;
            case "Melon":
                tempcheck = model.getMelon();
                break;
            case "Stone":
                tempcheck = model.getStone();
                break;
            case "ExtremeStone":
                tempcheck = model.getExtremeStone();
                break;
            case "ProtectStone":
                tempcheck = model.getProtectStone();
                break;
        }
        tempcheck = tempcheck - inputNum;
        if (tempcheck < 0) {
            return false;
        }
        return true;
    }

    // ...............................Daily controller...................//
    @Override
    public void setDailybtnEnable(boolean b) {
        viewMain.enableDailybtn(b);
    }

    @Override
    public void dailystart() {
        model.restartDailyTimer();
    }

    @Override
    public void getdailyBouns() {
        String msg = getformateTime();
        bonus = Rate.getDailyBouns();
        switch (bonus) {
            case STONE:
                rInt = random.nextInt(5) + 1;
                plusStone(rInt);
                viewMain.showDialog("獲得" + rInt + "強化石");
                viewMain.setdescription(msg + ", get" + rInt + "Stone", Color.BLACK);
                break;

            case COIN:
                rInt = random.nextInt(5) + 1;
                plusCoin(1000 * rInt);
                viewMain.showDialog("獲得" + 1000 * rInt + "金幣");
                viewMain.setdescription(msg + ", get" + 1000 * rInt + "coin", Color.BLACK);
                break;
            case EXTREME:
                rInt = random.nextInt(3) + 1;
                plusExtremeStone(rInt);
                viewMain.showDialog("獲得" + rInt + "凝縮強化石");
                viewMain.setdescription(msg + ", get" + rInt + "exetreme Stone", Color.BLACK);
                break;
            case FAIL:
                rInt = random.nextInt(5) + 1;
                plusFailureTimes(2 * rInt);
                viewMain.showDialog("獲得" + 2 * rInt + "曾失敗強化");
                viewMain.setdescription(msg + ", get" + 2 * rInt + "Failuer times", Color.BLACK);
                break;

            default:
                break;
        }
    }

    // ................................Enhance part...............................//
    @Override
    public void initEnhanceImg(Weapon weapon) {
        viewMain.setEnhanceLabelImg(weapon.getImgEntity());
    }

    @Override
    public void enhanceStart() {
        viewMain.visibleProgressbar(true);
        viewMain.enableEnhanceBtn(false);
        viewMain.enableAlchemyBtn(false);
        viewMain.enableFailureCheck(false);
        viewMain.enableProtectCheck(false);
    }

    @Override
    public void enhanceEnd() {
        viewMain.visibleProgressbar(false);
        if (model.getCurrentLevel() == 20) {
            viewMain.enableEnhanceBtn(false);
        } else {
            viewMain.enableEnhanceBtn(true);
        }
        viewMain.enableAlchemyBtn(true);
        viewMain.enableFailureCheck(true);
        viewMain.enableProtectCheck(true);
        viewMain.setFailureCkeck(false);
        viewMain.setProtectCheck(false);
    }

    @Override
    public void getEnhanceResult() {
        if (notFailure) {
            failurePlus = model.getFailureTimes();
            model.setFailureTimes(0);
        } else {
            failurePlus = 0;
        }
        String msg = getformateTime();
        int level = viewMain.getWeaponLabel();
        int playerLevel = model.getPlayerlevel();
        if (level < 5) {
            result = Rate.getResult(Rate.NORMAL, failurePlus, playerLevel);
        } else if (level >= 5 && level < 10) {
            result = Rate.getResult(Rate.MEDIUM, failurePlus, playerLevel);
        } else if (level >= 10 && level < 16) {
            result = Rate.getResult(Rate.HARD, failurePlus, playerLevel);
        } else if (level >= 16 && level <= 20) {
            result = Rate.getResult(Rate.EXTREME, failurePlus, playerLevel);
        }

        switch (result) {
            case SUCCESS:
                jProgressBar.setString("強化成功");
                viewMain.setdescription(msg + ", enhance Success!", DARK_GREEN);
                model.setCurrentLevel(model.getCurrentLevel() + 1);
                expController(viewMain.getExpBar(), 7);
                break;

            case FAIL:
                jProgressBar.setString("強化失敗");
                viewMain.setdescription(msg + ", enhance Fail!", Color.RED);
                int tempPlusfail = model.getCurrentLevel();
                model.setFailureTimes(model.getFailureTimes() + tempPlusfail);
                viewMain.setFailureLabelText(model.getFailureTimes());
                if (model.getCurrentLevel() == 0) {
                    model.setCurrentLevel(model.getCurrentLevel());
                } else {
                    if (protect) {
                        model.setCurrentLevel(model.getCurrentLevel());
                    } else {
                        model.setCurrentLevel(model.getCurrentLevel() - 1);
                    }
                }
                expController(viewMain.getExpBar(), 3);
                break;

            default:
                break;
        }

    }

    @Override
    public void enhanceThread(JProgressBar jProgressBar) {
        this.jProgressBar = jProgressBar;
        progress = new ProgressBar(jProgressBar, this);
        progress.runEnhance();
    }

    @Override
    public boolean isEnhanceRunning() {
        return ProgressBar.enhanceRunning;
    }

    @Override
    public void enhanceProgressEnd() {
        model.enhanceProgressListener();
    }

    @Override
    public boolean isSuccess() {
        if (success == true) {
            return true;
        }
        return false;
    }

    @Override
    public void setprotect(boolean b) {
        this.protect = b;
    }

    @Override
    public void setNotFailure(boolean b) {
        this.notFailure = b;
    }

    // ...............................Alchemy part ...............................//
    @Override
    public void alchemyStart() {
        viewMain.enableAlchemyBtn(false);
        viewMain.visibleAlchemybar(true);
        viewMain.enableEnhanceBtn(false);
    }

    @Override
    public void alchemyEnd() {
        viewMain.enableAlchemyBtn(true);
        viewMain.visibleAlchemybar(false);
        viewMain.enableEnhanceBtn(true);
        viewMain.alchemyEndCheck();
    }

    public void alchemyThread(JProgressBar jProgressBar) {
        this.jProgressBar = jProgressBar;
        progress = new ProgressBar(jProgressBar, this);
        progress.runAlchemy();
    }

    @Override
    public boolean isAlchemyRunning() {
        return ProgressBar.alchemyRunning;
    }

    @Override
    public void alchemyProgressEnd() {
        model.alchemyProgressListener();
    }

    @Override
    public void alchemyMinus(int banana, int apple, int orange, int melon) {
        int e = model.getBanana();
        model.setBanana(e - banana);
        e = model.getApple();
        model.setApple(e - apple);
        e = model.getOrange();
        model.setOrange(e - orange);
        e = model.getMelon();
        model.setMelon(e - melon);
    }

    @Override
    public void getAlchemyResult() {
        SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String msg = form.format(date);
        result = Rate.getResult(Rate.ALCHEMY, 0, 0);
        sb = new StringBuilder();
        // sb.append(String.valueOf(viewMain.getBananaCombo()));
        sb.append(viewMain.getBananaCombo());
        sb.append(String.valueOf(viewMain.getAppleCombo()));
        sb.append(String.valueOf(viewMain.getOrangeCombo()));
        sb.append(String.valueOf(viewMain.getMelonCombo()));
        System.out.println("煉金序號:" + sb);
        switch (sb.toString()) {
            case "1111":
                plusStone(1);
                viewMain.showDialog("獲得強化石");
                viewMain.setdescription(msg + ", Alchemy result : Stone +1", Color.BLACK);
                break;

            case "2222":
                plusExtremeStone(1);
                viewMain.showDialog("獲得凝縮強化石");
                viewMain.setdescription(msg + ",Alchemy result : Extreme Stone +1", Color.BLACK);
                break;

            case "4444":
                plusStone(5);
                viewMain.showDialog("獲得強化石*5");
                viewMain.setdescription(msg + ",Alchemy result : Stone +5", Color.BLACK);
                break;

            case "3333":
                plusProtectStone(1);
                viewMain.showDialog("獲得保護石");
                viewMain.setdescription(msg + ",Alchemy result : Protect Stone +1", Color.BLACK);
                break;

            case "3132":
                plusFailureTimes(15);
                viewMain.showDialog("獲得失敗疊層*15");
                viewMain.setdescription(msg + ",Alchemy result : FailureTimes +15", Color.BLACK);
                break;

            case "1211":
                plusFailureTimes(5);
                viewMain.showDialog("獲得失敗疊層*5");
                viewMain.setdescription(msg + ",Alchemy result : FailureTimes +5", Color.BLACK);
                break;

            default:
                viewMain.showDialog("沒發生什麼事");
                viewMain.setdescription(msg + ", Nothing happened GG!", Color.BLACK);
                break;
        }
        rInt = random.nextInt(5) + 1;
        model.setDregs(model.getDredgs() + rInt);
        if (rInt == 2) {
            model.setRareDregs(model.getRareDregs() + 1);
        }

    }

    // ................................Garden part ...............................//
    @Override
    public void setAllGdCb() {
        int[] a = new int[6];
        for (int i = 0; i < 6; i++) {
            String str = model.getGdCbList(i);
            switch (str) {
                case "b":
                    a[i] = 0;
                    break;
                case "a":
                    a[i] = 1;
                    break;
                case "o":
                    a[i] = 2;
                    break;
                case "m":
                    a[i] = 3;
                    break;
                case "N":
                    a[i] = 99;
                    break;
                default:
                    break;
            }
            System.out.print(a[i] + "__");
        }
        viewMain.setallGdCbItem(a[0], a[1], a[2], a[3], a[4], a[5]);
    }

    @Override
    public void gd_start(JButton btn) {
        String name = btn.getName();
        switch (name) {
            case "1":
                model.restartTimer_Gd1();
                break;
            case "2":
                model.restartTimer_Gd2();
                break;
            case "3":
                model.restartTimer_Gd3();
                break;
            case "4":
                model.restartTimer_Gd4();
                break;
            case "5":
                model.restartTimer_Gd5();
                break;
            case "6":
                model.restartTimer_Gd6();
                break;

            default:
                break;
        }
    }

    public void setGardenItem() {
        bananaItem = model.getGardenEntity().getBananaEntity();
        appleItem = model.getGardenEntity().getAppleEntity();
        orangeItem = model.getGardenEntity().getOrangeEntity();
        melonItem = model.getGardenEntity().getMelonEntity();
    }

    @Override
    public void harvest(GardenItem gardenItem) {
        String name = gardenItem.getName();
        rInt = random.nextInt(3) + 1; // 調整收割數量
        System.out.println("機率為 : " + rInt);
        switch (name) {
            case "Banana":
                bananaItem.setHarvest(bananaItem.getHarvest() + rInt);
                break;
            case "Apple":
                appleItem.setHarvest(appleItem.getHarvest() + rInt);
                break;
            case "Orange":
                orangeItem.setHarvest(orangeItem.getHarvest() + rInt);
                break;
            case "Melon":
                melonItem.setHarvest(melonItem.getHarvest() + rInt);
                break;
            default:
                break;
        }
    }

    public void plusHarvest() {
        int b = bananaItem.getHarvest();
        model.setBanana(model.getBanana() + b);
        if (b != 0) {
            viewMain.showDialog("獲得" + b + "個Banana");
        }
        int a = appleItem.getHarvest();
        model.setApple(model.getApple() + a);
        if (a != 0) {
            viewMain.showDialog("獲得" + a + "個Apple");
        }
        int o = orangeItem.getHarvest();
        model.setOrange(model.getOrange() + o);
        if (o != 0) {
            viewMain.showDialog("獲得" + o + "個Orange");
        }
        int m = melonItem.getHarvest();
        model.setMelon(model.getMelon() + m);
        if (m != 0) {
            viewMain.showDialog("獲得" + m + "個Melon");
        }
        setAllHarvsetToZero(0);
    }

    public void setAllHarvsetToZero(int num) {
        bananaItem.setHarvest(num);
        appleItem.setHarvest(num);
        orangeItem.setHarvest(num);
        melonItem.setHarvest(num);
    }

    @Override
    public void eventGdBtn(JButton btn, GardenItemComboBox gdCb, int index) {
        if (btn.getText() == "start" && gdCb.getSelectedItem() == null) {
            viewMain.showDialog("選擇一種煉金素材種植");
        }
        if (btn.getText() == "收割" && gdCb.getSelectedItem() == null) {
            btn.setText("start");
        }

        if (btn.getText() == "收割") {
            harvest((GardenItem) gdCb.getSelectedItem());
            plusHarvest();
            model.setGdCbLiset(index, "N");
            gdCb.setSelectedItem(null);
            gdCb.setEnabled(true);
            viewMain.enableGdBtn(true, btn);
            btn.setText("start");
        }
        if (gdCb.getSelectedItem() != null) {
            viewMain.enableGdBtn(false, btn);
            viewMain.enableGdCb(false, gdCb);
            model.setGdCbLiset(index, ((GardenItem) gdCb.getSelectedItem()).getCodeName());
            gd_start(btn);
        }
    }

    // ...............................View Store..........................//
    public ViewStore getViewStoreInstance() {
        if (ViewStore.instance == null) {
            synchronized (ViewStore.class) {
                if (ViewStore.instance == null) {
                    ViewStore.instance = new ViewStore(this, model);
                }
            }
        }
        return ViewStore.instance;
    }

    @Override
    public void openStore() {
        getViewStoreInstance();
        this.viewStore = ViewStore.instance;
    }

    @Override
    public void closeStore() {
        viewStore.closeStore();
    }

    @Override
    public void buyControl(JButton btn) {
        btn.setEnabled(false);
        if (btn.getName() == "buy1") {
            model.setFarm1Sold(SOLD);
            viewMain.enableGdBtn_5(true);
            model.restartTimer_Gd5();
        } else if (btn.getName() == "buy2") {
            model.setFarm2Sold(SOLD);
            viewMain.enableGdBtn_6(true);
            model.restartTimer_Gd6();
        }
    }

    @Override
    public void sold(int have, int sell, int price) {
        int temppluscoin = sell * price;
        model.setCoin(model.getCoin() + temppluscoin);
        viewStore.showDialog("獲得" + temppluscoin + "金幣");
    }

    @Override
    public int getItemCbObjectPrice(JComboBox<String> myItemCb) {
        int index = myItemCb.getSelectedIndex();
        int price = 0;
        switch (index) {

            case 0: // Dregs
                price = 400;
                break;
            case 1: // Rare
                price = 1000;
                break;
            case 2: // Banana
                price = 200;
                break;
            case 3: // Apple
                price = 200;
                break;
            case 4: // Orange
                price = 200;
                break;
            case 5: // Melon
                price = 200;
                break;
        }
        return price;
    }

    public String getformateTime() {
        SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return form.format(date);
    }

}

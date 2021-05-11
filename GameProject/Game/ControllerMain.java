package GameProject.Game;

import java.awt.Color;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JProgressBar;

import GameProject.Game.Rate.BounsItem;
import GameProject.Game.Rate.Result;
import GameProject.libs.GardenItem;
import GameProject.libs.GardenItemComboBox;
import GameProject.libs.Weapon;

public class ControllerMain implements ControllerMainInterface {
    private static Logger logger = Logger.getLogger(ControllerMain.class.getName());
    ModelInterface model;
    JProgressBar jProgressBar;
    ProgressBar progress;
    ViewLogin viewlogin;
    ViewMain viewMain;
    ViewStore viewStore;
    BounsItem bonus;
    Result result;
    int tempcoin, tempstone, tempFail;
    int dailySecond, dailyMinute;
    int failurePlus;
    StringBuilder sb;
    boolean success, protect, notFailure;
    GardenItem bananaItem, appleItem, orangeItem, melonItem;
    public static final boolean SOLD = true;
    public static final boolean UNSOLD = false;
    private static final Color DARK_GREEN = new Color(0, 153, 0);

    public ControllerMain(ModelInterface model) {
        this.model = model;
        ViewLogin viewlogin = new ViewLogin(this, model);
        ViewMain viewMain = new ViewMain(this, model);
        this.viewlogin = viewlogin;
        this.viewMain = viewMain;
        viewlogin.createLoginView();
    }

    @Override
    public void login() {
        viewlogin.enableLoginFrame(false);
        viewMain.createMainView();
        viewMain.createControls();
        model.on();
        setGardenItem();
        setAllGdCb();
    }

    @Override
    public void stop() {
        model.off();
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
    public boolean coinchecker(int coin) {
        int i = model.getCoin();
        i = i - coin;
        if (i < 0) {
            logger.warning("coin < 0");
            return false;
        }
        return true;
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

    @Override
    public boolean stoneChecker(int stone) {
        int i = model.getStone();
        i = i - stone;
        if (i < 0) {
            logger.warning("stone < 0");
            return false;
        }
        return true;
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

    @Override
    public boolean extremeStoneChecker(int stone) {
        int i = model.getExtremeStone();
        i = i - stone;
        if (i < 0) {
            logger.warning("stone < 0");
            return false;
        }
        return true;
    }

    // Protect Stone
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

    @Override
    public boolean protectStoneChecker(int stone) {
        int i = model.getProtectStone();
        i = i - stone;
        if (i < 0) {
            logger.warning("prtectstone < 0");
            return false;
        }
        return true;
    }

    public void plusFailureTimes(int fail) {
        tempFail = model.getFailureTimes();
        tempFail += fail;
        model.setFailureTimes(tempFail);
    }

    // Element Checker
    @Override
    public boolean bananChecker(int banana) {
        int i = model.getBanana();
        i = i - banana;
        if (i < 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean appleChecker(int apple) {
        int i = model.getApple();
        i = i - apple;
        if (i < 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean orangeChecker(int orange) {
        int i = model.getOrange();
        i = i - orange;
        if (i < 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean melonChecker(int melon) {
        int i = model.getMelon();
        i = i - melon;
        if (i < 0) {
            return false;
        }
        return true;
    }

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
                plusStone(5);
                viewMain.showDialog("獲得強化石*5");
                viewMain.setdescription(msg + ", get 5 Stone", Color.BLACK);
                break;

            case COIN:
                plusCoin(5000);
                viewMain.showDialog("獲得5000金幣");
                viewMain.setdescription(msg + ", get 5000 coin", Color.BLACK);
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
        if (level < 5) {
            result = Rate.getResult(Rate.NORMAL, failurePlus);
        } else if (level >= 5 && level < 10) {
            result = Rate.getResult(Rate.MEDIUM, failurePlus);
        } else if (level >= 10 && level < 16) {
            result = Rate.getResult(Rate.HARD, failurePlus);
        } else if (level >= 16 && level <= 20) {
            result = Rate.getResult(Rate.EXTREME, failurePlus);
        }

        switch (result) {
            case SUCCESS:
                jProgressBar.setString("強化成功");
                viewMain.setdescription(msg + ", enhance Success", DARK_GREEN);
                model.setCurrentLevel(model.getCurrentLevel() + 1);
                break;

            case FAIL:
                jProgressBar.setString("強化失敗");
                viewMain.setdescription(msg + ", enhance Fail", Color.RED);
                model.setFailureTimes(model.getFailureTimes() + 1);
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
        result = Rate.getResult(Rate.ALCHEMY, 0);
        sb = new StringBuilder();
        sb.append(String.valueOf(viewMain.getBananaCombo()));
        sb.append(String.valueOf(viewMain.getAppleCombo()));
        sb.append(String.valueOf(viewMain.getOrangeCombo()));
        sb.append(String.valueOf(viewMain.getMelonCombo()));
        System.out.println(sb);
        switch (sb.toString()) {
            case "0010":
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
                default:
                    break;
            }
            System.out.println(a[i]);
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
        switch (name) {
            case "Banana":
                bananaItem.setHarvest(bananaItem.getHarvest() + 1);
                break;
            case "Apple":
                appleItem.setHarvest(appleItem.getHarvest() + 1);
                break;
            case "Orange":
                orangeItem.setHarvest(orangeItem.getHarvest() + 1);
                break;
            case "Melon":
                melonItem.setHarvest(melonItem.getHarvest() + 1);
                break;
            default:
                break;
        }
        System.out.println("banana harvest : " + bananaItem.getHarvest());
    }

    public void plusHarvest() {
        int b = bananaItem.getHarvest();
        model.setBanana(model.getBanana() + b);
        int a = appleItem.getHarvest();
        model.setApple(model.getApple() + a);
        int o = orangeItem.getHarvest();
        model.setOrange(model.getOrange() + o);
        int m = melonItem.getHarvest();
        model.setMelon(model.getMelon() + m);
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
        if (btn.getText() == "收割") {
            harvest((GardenItem) gdCb.getSelectedItem());
            gdCb.setSelectedItem(null);
            gdCb.setEnabled(true);
            viewMain.enableGdBtn(true, btn);
            btn.setText("start");
        }
        if (gdCb.getSelectedItem() != null) {
            plusHarvest();
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
            viewMain.enableGdCb_5(true);
            viewMain.enableGdBtn_5(true);
            model.restartTimer_Gd5();
        } else if (btn.getName() == "buy2") {
            model.setFarm2Sold(SOLD);
            // viewMain.enableGdCb_6(true);
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

    public String getformateTime() {
        SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return form.format(date);
    }

}

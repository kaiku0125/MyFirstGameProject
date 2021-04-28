package GameProject.Game;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.swing.JProgressBar;

import GameProject.Game.Rate.BounsItem;
import GameProject.Game.Rate.Result;
import GameProject.libs.Weapon;

public class ControllerMain implements ControllerMainInterface {
    private static Logger logger = Logger.getLogger(ControllerMain.class.getName());
    ModelInterface model;
    JProgressBar jProgressBar;
    ProgressBar progress;
    ViewLogin viewlogin;
    ViewMain viewMain;
    BounsItem bonus;
    Result result;
    int tempcoin, tempstone;
    int dailySecond, dailyMinute;
    int failurePlus;
    StringBuilder sb;
    boolean success, protect, notFailure;

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
            viewMain.setdescription(msg + ", get 5 Stone");
            break;

        case COIN:
            plusCoin(5000);
            viewMain.showDialog("獲得5000金幣");
            viewMain.setdescription(msg + ", get 5000 coin");
            break;

        default:
            break;
        }
    }

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
        viewMain.enableEnhanceBtn(true);
        viewMain.enableAlchemyBtn(true);
        viewMain.enableFailureCheck(true);
        viewMain.enableProtectCheck(true);
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
        }

        switch (result) {
        case SUCCESS:
            jProgressBar.setString("強化成功");
            viewMain.setdescription(msg + ", enhance Success");
            model.setCurrentLevel(model.getCurrentLevel() + 1);
            break;

        case FAIL:
            jProgressBar.setString("強化失敗");
            viewMain.setdescription(msg + ", enhance Fail");
            model.setFailureTimes(model.getFailureTimes() + 1);
            viewMain.setFailureLabelText(model.getFailureTimes());
            if (protect) {
                model.setCurrentLevel(model.getCurrentLevel());
            } else {
                model.setCurrentLevel(model.getCurrentLevel() - 1);
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
        model.setMelon(e - orange);
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
            viewMain.setdescription(msg + ", Alchemy result : Stone +1");
            break;

        case "2222":
            plusExtremeStone(1);
            viewMain.showDialog("獲得凝縮強化石");
            viewMain.setdescription(msg + ",Alchemy result : Extreme Stone +1");

        case "4444":
            plusStone(5);
            viewMain.showDialog("獲得強化石*5");
            viewMain.setdescription(msg + ",Alchemy result : Stone +5");

        case "3333":
            plusProtectStone(1);
            viewMain.showDialog("獲得保護石");
            viewMain.setdescription(msg + ",Alchemy result : Protect Stone +1");

        default:
            viewMain.showDialog("沒發生什麼事");
            viewMain.setdescription(msg + ", Nothing happened GG!");
            break;
        }
    }

    @Override
    public void gd1_start() {
        model.restartTimer_Gd1();
    }

    public String getformateTime() {
        SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return form.format(date);
    }

}

package GameProject.Game;

import java.util.logging.Logger;
import java.util.Timer;

public class Gd5_Clock extends ClockTask {
    private Logger logger = Logger.getLogger(Gd5_Clock.class.getName());
    Timer gd5_Timer;
    private boolean finish = false;
    Thread thread;

    public Gd5_Clock(ModelInterface model) {
        super(model);
        this.gd5_Timer = new Timer();
    }

    @Override
    public void run() {
        if (!finish) {
            minusSecond();
            model.gdBtn_5Listener();
            if (second == -1) {
                if (minute == 0) {
                    try {
                        logger.info("daily分秒皆0");
                        second = 0;
                        finish = true;
                        gd5_Timer.cancel();
                        model.gdBtn_5Listener();
                        return;
                    } catch (Exception e) {
                        logger.warning("minute == 0 error!");
                    }
                }
                second = 59;
                minusMinute();
                model.gdBtn_5Listener();
            }
        }
    }

    @Override
    protected Timer getTimer() {
        return gd5_Timer;
    }

    @Override
    protected void again() {
        finish = false;
        this.gd5_Timer = new Timer();
        model.gdBtn_5Listener();
    }

    @Override
    protected boolean isfinish() {
        return this.finish;
    }
}

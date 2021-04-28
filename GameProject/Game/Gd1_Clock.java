package GameProject.Game;

import java.util.logging.Logger;
import java.util.Timer;

public class Gd1_Clock extends ClockTask {
    private Logger logger = Logger.getLogger(Gd1_Clock.class.getName());
    Timer gd1_Timer;
    private boolean finish = false;
    Thread thread;

    public Gd1_Clock(ModelInterface model) {
        super(model);
        this.gd1_Timer = new Timer();
    }

    @Override
    public void run() {
        if (!finish) {
            minusSecond();
            model.gdBtn_1Listener();
            if (second == -1) {
                if (minute == 0) {
                    try {
                        logger.info("daily分秒皆0");
                        second = 0;
                        finish = true;
                        gd1_Timer.cancel();
                        model.gdBtn_1Listener();
                        return;
                    } catch (Exception e) {
                        logger.warning("minute == 0 error!");
                    }
                }
                second = 59;
                minusMinute();
                model.gdBtn_1Listener();
            }
        }
    }

    @Override
    protected Timer getTimer() {
        return gd1_Timer;
    }

    @Override
    protected void again() {
        finish = false;
        this.gd1_Timer = new Timer();
        model.gdBtn_1Listener();
    }

    @Override
    protected boolean isfinish() {
        return this.finish;
    }

}

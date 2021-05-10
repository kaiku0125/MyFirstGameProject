package GameProject.Game;

import java.util.logging.Logger;
import java.util.Timer;

public class Gd6_Clock extends ClockTask {
    private Logger logger = Logger.getLogger(Gd6_Clock.class.getName());
    Timer gd6_Timer;
    private boolean finish = false;
    Thread thread;

    public Gd6_Clock(ModelInterface model) {
        super(model);
        this.gd6_Timer = new Timer();
    }

    @Override
    public void run() {
        if (!finish) {
            minusSecond();
            model.gdBtn_6Listener();
            if (second == -1) {
                if (minute == 0) {
                    try {
                        logger.info("daily分秒皆0");
                        second = 0;
                        finish = true;
                        gd6_Timer.cancel();
                        model.gdBtn_6Listener();
                        return;
                    } catch (Exception e) {
                        logger.warning("minute == 0 error!");
                    }
                }
                second = 59;
                minusMinute();
                model.gdBtn_6Listener();
            }
        }
    }

    @Override
    protected Timer getTimer() {
        return gd6_Timer;
    }

    @Override
    protected void again() {
        finish = false;
        this.gd6_Timer = new Timer();
        model.gdBtn_6Listener();
    }

    @Override
    protected boolean isfinish() {
        return this.finish;
    }
}

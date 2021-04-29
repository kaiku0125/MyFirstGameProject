package GameProject.Game;

import java.util.logging.Logger;
import java.util.Timer;

public class Gd3_Clock extends ClockTask {
    private Logger logger = Logger.getLogger(Gd3_Clock.class.getName());
    Timer gd3_Timer;
    private boolean finish = false;
    Thread thread;

    public Gd3_Clock(ModelInterface model) {
        super(model);
        this.gd3_Timer = new Timer();
    }

    @Override
    public void run() {
        if (!finish) {
            minusSecond();
            model.gdBtn_3Listener();
            if (second == -1) {
                if (minute == 0) {
                    try {
                        logger.info("daily分秒皆0");
                        second = 0;
                        finish = true;
                        gd3_Timer.cancel();
                        model.gdBtn_3Listener();
                        return;
                    } catch (Exception e) {
                        logger.warning("minute == 0 error!");
                    }
                }
                second = 59;
                minusMinute();
                model.gdBtn_3Listener();
            }
        }
    }

    @Override
    protected Timer getTimer() {
        return gd3_Timer;
    }

    @Override
    protected void again() {
        finish = false;
        this.gd3_Timer = new Timer();
        model.gdBtn_3Listener();
    }

    @Override
    protected boolean isfinish() {
        return this.finish;
    }
}

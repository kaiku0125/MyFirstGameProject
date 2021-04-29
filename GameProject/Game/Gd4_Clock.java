package GameProject.Game;

import java.util.logging.Logger;
import java.util.Timer;

public class Gd4_Clock extends ClockTask {
    private Logger logger = Logger.getLogger(Gd4_Clock.class.getName());
    Timer gd4_Timer;
    private boolean finish = false;
    Thread thread;

    public Gd4_Clock(ModelInterface model) {
        super(model);
        this.gd4_Timer = new Timer();
    }

    @Override
    public void run() {
        if (!finish) {
            minusSecond();
            model.gdBtn_4Listener();
            if (second == -1) {
                if (minute == 0) {
                    try {
                        logger.info("daily分秒皆0");
                        second = 0;
                        finish = true;
                        gd4_Timer.cancel();
                        model.gdBtn_4Listener();
                        return;
                    } catch (Exception e) {
                        logger.warning("minute == 0 error!");
                    }
                }
                second = 59;
                minusMinute();
                model.gdBtn_4Listener();
            }
        }
    }

    @Override
    protected Timer getTimer() {
        return gd4_Timer;
    }

    @Override
    protected void again() {
        finish = false;
        this.gd4_Timer = new Timer();
        model.gdBtn_4Listener();
    }

    @Override
    protected boolean isfinish() {
        return this.finish;
    }
}

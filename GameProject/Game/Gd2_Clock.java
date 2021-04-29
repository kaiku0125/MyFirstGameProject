package GameProject.Game;

import java.util.logging.Logger;
import java.util.Timer;

public class Gd2_Clock extends ClockTask {
    private Logger logger = Logger.getLogger(Gd2_Clock.class.getName());
    Timer gd2_Timer;
    private boolean finish = false;
    Thread thread;

    public Gd2_Clock(ModelInterface model) {
        super(model);
        this.gd2_Timer = new Timer();
    }

    @Override
    public void run() {
        if (!finish) {
            minusSecond();
            model.gdBtn_2Listener();
            if (second == -1) {
                if (minute == 0) {
                    try {
                        logger.info("daily分秒皆0");
                        second = 0;
                        finish = true;
                        gd2_Timer.cancel();
                        model.gdBtn_2Listener();
                        return;
                    } catch (Exception e) {
                        logger.warning("minute == 0 error!");
                    }
                }
                second = 59;
                minusMinute();
                model.gdBtn_2Listener();
            }
        }
    }

    @Override
    protected Timer getTimer() {
        return gd2_Timer;
    }

    @Override
    protected void again() {
        finish = false;
        this.gd2_Timer = new Timer();
        model.gdBtn_2Listener();
    }

    @Override
    protected boolean isfinish() {
        return this.finish;
    }

}

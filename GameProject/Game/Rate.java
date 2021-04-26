package GameProject.Game;

import java.util.Random;

public class Rate {
    ModelInterface model;
    static int[] rate;

    public static int NORMAL = 0;
    public static int MEDIUM = 1;
    public static int HARD = 2;
    public static int ALCHEMY = 3;

    enum BounsItem {
        STONE, COIN
    }

    enum Result {
        SUCCESS, FAIL
    }

    public Rate(ModelInterface model) {
        this.model = model;
    }

    public static void setRate(int[] rate) {
        Rate.rate = rate;
    }

    public static BounsItem getDailyBouns() {
        int[] rate = { 40, 60 };
        setRate(rate);
        int total = 0;
        for (int i = 0; i < rate.length; i++) {
            total += rate[i];
        }
        Random r = new Random();
        int randomInt = r.nextInt(total);
        // System.out.println("此次機率為 : " + randomInt);
        for (int i = 0; i < rate.length; i++) {
            randomInt = randomInt - rate[i];
            if (randomInt < 0) {
                return BounsItem.values()[i];
            }
        }
        return null;
    }

    public static Result getResult(int state) {
        if (state == NORMAL) {
            int[] rate = { 70, 30 };
            setRate(rate);
            System.out.println("70,30");
        } else if (state == MEDIUM) {
            int[] rate = { 50, 50 };
            setRate(rate);
            System.out.println("50,50");
        } else if (state == HARD) {
            int[] rate = { 30, 70 };
            setRate(rate);
            System.out.println("30,70");
        } else if (state == ALCHEMY) {
            int[] rate = { 100, 0 };
            setRate(rate);
            System.out.println("100,30");
        }

        int total = 0;
        for (int i = 0; i < rate.length; i++) {
            total += rate[i];
        }
        Random r = new Random();
        int randomInt = r.nextInt(total);
        // System.out.println("此次機率為 : " + randomInt);
        for (int i = 0; i < rate.length; i++) {
            randomInt = randomInt - rate[i];
            if (randomInt < 0) {
                return Result.values()[i];
            }
        }
        return null;
    }

}

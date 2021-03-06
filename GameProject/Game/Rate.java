package GameProject.Game;

import java.util.Random;

public class Rate {
    ModelInterface model;
    static int[] rate;

    public static int NORMAL = 0;
    public static int MEDIUM = 1;
    public static int HARD = 2;
    public static int EXTREME = 3;
    public static int ALCHEMY = 4;

    enum BounsItem {
        STONE, COIN, EXTREME, FAIL
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
        int[] rate = { 30, 55, 10, 5 };
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

    public static Result getResult(int state, int x, int level) {
        x = x + level;
        if (state == NORMAL) {
            int[] rate = { 70 + x, 30 - x };
            setRate(rate);
            System.out.println(String.valueOf(70 + x) + "," + String.valueOf(30 - x));
        } else if (state == MEDIUM) {
            int[] rate = { 50 + x, 50 - x };
            setRate(rate);
            System.out.println(String.valueOf(50 + x) + "," + String.valueOf(50 - x));
        } else if (state == HARD) {
            int[] rate = { 30 + x, 70 - x };
            setRate(rate);
            System.out.println(String.valueOf(30 + x) + "," + String.valueOf(70 - x));
        } else if (state == EXTREME) {
            int[] rate = { 10 + x, 90 - x };
            setRate(rate);
            System.out.println(String.valueOf(10 + x) + "," + String.valueOf(90 - x));
        } else if (state == ALCHEMY) {
            int[] rate = { 100, 0 };
            setRate(rate);
            System.out.println("100,0");
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

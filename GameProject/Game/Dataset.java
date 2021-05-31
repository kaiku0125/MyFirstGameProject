package GameProject.Game;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;
import java.util.logging.Logger;

public class Dataset {
    private static Logger logger = Logger.getLogger(Dataset.class.getName());
    ArrayList<String> allClock = new ArrayList<String>();
    ArrayList<String> weaponLevel = new ArrayList<String>();
    ArrayList<String> stone = new ArrayList<String>();
    ArrayList<String> sold = new ArrayList<String>();
    ArrayList<Boolean> issold = new ArrayList<Boolean>();
    HashMap<String, Integer> allElement = new HashMap<String, Integer>();
    ModelInterface model;
    BufferedReader reader = null;
    FileWriter fw = null;
    int tempcoin, count, tempStone, tempFailure;
    String UserID, temp;
    String path1 = Dataset.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    public static String decodedpath;
    // 使用IDE將路徑replace為: decodedpath + "data/Coin.data"

    public Dataset(ModelInterface model) {
        this.model = model;
        try {
            decodedpath = URLDecoder.decode(path1, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void readcoin() {
        try {
            reader = new BufferedReader(new FileReader("GameProject//res//Coin.txt"));
            String temp = reader.readLine();
            tempcoin = model.getCoin();
            tempcoin = Integer.valueOf(temp);
            model.setCoin(tempcoin);
            logger.info("Read Coin :" + String.valueOf(tempcoin));
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void savecoin() {
        try {
            fw = new FileWriter("GameProject//res//Coin.txt");
            tempcoin = model.getCoin();
            fw.write(String.valueOf(tempcoin));
            logger.info("Save coin : " + String.valueOf(tempcoin));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void regUserID() {
        try {
            fw = new FileWriter("GameProject//res//UserID.txt");
            String tempPassword = ViewLogin.regID;
            fw.write(tempPassword);
            logger.info("reg ID :" + tempPassword);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkUserID() {
        try {
            reader = new BufferedReader(new FileReader("GameProject//res//UserID.txt"));
            String tempPassword = reader.readLine();
            UserID = tempPassword;
            reader.close();
        } catch (Exception e) {
            System.out.println("cant login");
        }
    }

    public String getPassword() {
        return UserID;
    }

    public void readClock() {
        try {
            reader = new BufferedReader(new FileReader("GameProject//res//Clock.txt"));
            while ((temp = reader.readLine()) != null) {
                String[] s = temp.split(",");
                allClock.add(s[0]);
                allClock.add(s[1]);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveClock() {
        try {
            fw = new FileWriter("GameProject//res//Clock.txt");
            allClock.set(1, model.getStoneClock().getSecond());
            allClock.set(0, model.getStoneClock().getMinute());
            allClock.set(3, model.getDailyClock().getSecond());
            allClock.set(2, model.getDailyClock().getMinute());
            allClock.set(5, model.getClock_gd1().getSecond());
            allClock.set(4, model.getClock_gd1().getMinute());
            allClock.set(7, model.getClock_gd2().getSecond());
            allClock.set(6, model.getClock_gd2().getMinute());
            allClock.set(9, model.getClock_gd3().getSecond());
            allClock.set(8, model.getClock_gd3().getMinute());
            allClock.set(11, model.getClock_gd4().getSecond());
            allClock.set(10, model.getClock_gd4().getMinute());
            if (model.getFarm1Sold()) {
                allClock.set(13, model.getClock_gd5().getSecond());
                allClock.set(12, model.getClock_gd5().getMinute());
            }
            if (model.getFarm2Sold()) {
                allClock.set(15, model.getClock_gd6().getSecond());
                allClock.set(14, model.getClock_gd6().getMinute());
            }
            for (int i = 0; i < allClock.size(); i = i + 2) {
                fw.write(allClock.get(i) + "," + allClock.get(i + 1) + "\n");
            }
            for (int i = 0; i < allClock.size(); i++) {
                String print = allClock.get(i);
                System.out.print(print + ",");
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getClockindex(int index) {
        return Integer.parseInt(allClock.get(index));
    }

    public void readWeaponLevel() {
        try {
            reader = new BufferedReader(new FileReader("GameProject//res//Weapon.txt"));
            temp = reader.readLine();
            String[] s = temp.split(",");
            weaponLevel.add(s[0]);
            weaponLevel.add(s[1]);
            model.setSwordLevel(Integer.parseInt(s[0]));
            model.setBowLevel(Integer.parseInt(s[1]));
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveWeaponLevel() {
        try {
            fw = new FileWriter("GameProject//res//Weapon.txt");
            weaponLevel.set(0, String.valueOf(model.getSwordLevel()));
            weaponLevel.set(1, String.valueOf(model.getBowLevel()));
            fw.write(weaponLevel.get(0) + "," + weaponLevel.get(1));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getWeaponLevelindex(int index) {
        return Integer.parseInt(weaponLevel.get(index));
    }

    public void readElement() {
        try {
            reader = new BufferedReader(new FileReader("GameProject//res//Element.txt"));
            temp = reader.readLine();
            String[] s = temp.split(",");
            allElement.put("element1", Integer.parseInt(s[0]));
            allElement.put("element2", Integer.parseInt(s[1]));
            allElement.put("element3", Integer.parseInt(s[2]));
            allElement.put("element4", Integer.parseInt(s[3]));
            allElement.put("dregs", Integer.parseInt(s[4]));
            allElement.put("rare", Integer.parseInt(s[5]));
            model.setBanana(allElement.get("element1"));
            model.setApple(allElement.get("element2"));
            model.setOrange(allElement.get("element3"));
            model.setMelon(allElement.get("element4"));
            model.setDregs(allElement.get("dregs"));
            model.setRareDregs(allElement.get("rare"));
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveElement() {
        try {
            fw = new FileWriter("GameProject//res//Element.txt");
            allElement.put("element1", model.getBanana());
            allElement.put("element2", model.getApple());
            allElement.put("element3", model.getOrange());
            allElement.put("element4", model.getMelon());
            allElement.put("dregs", model.getDredgs());
            allElement.put("rare", model.getRareDregs());
            fw.write(allElement.get("element1") + "," + allElement.get("element2") + "," + allElement.get("element3")
                    + "," + allElement.get("element4") + "," + allElement.get("dregs") + "," + allElement.get("rare"));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readStone() {
        try {
            reader = new BufferedReader(new FileReader("GameProject//res//Stone.txt"));
            temp = reader.readLine();
            String[] s = temp.split(",");
            stone.add(s[0]);
            stone.add(s[1]);
            stone.add(s[2]);
            model.setStone(Integer.parseInt(s[0]));
            model.setExtrmeneStone(Integer.parseInt(s[1]));
            model.setProtectStone(Integer.parseInt(s[2]));
            logger.info("Read stone : " + s[0]);
            logger.info("Read ExtremeStone : " + s[1]);
            logger.info("Read Protectstone : " + s[2]);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveStone() {
        try {
            fw = new FileWriter("GameProject//res//Stone.txt");
            stone.set(0, String.valueOf(model.getStone()));
            stone.set(1, String.valueOf(model.getExtremeStone()));
            stone.set(2, String.valueOf(model.getProtectStone()));
            fw.write(stone.get(0) + "," + stone.get(1) + "," + stone.get(2));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readSoldState() {
        try {
            reader = new BufferedReader(new FileReader("GameProject//res//StoreSold.txt"));
            temp = reader.readLine();
            String[] s = temp.split(",");
            sold.add(s[0]);
            sold.add(s[1]);
            for (int i = 0; i < sold.size(); i++) {
                // System.out.println(s[i]);
                if ((s[i]).equals("false")) {
                    issold.add(false);
                } else if (s[i].equals("true")) {
                    issold.add(true);
                } else {
                    logger.warning("無法儲存狀態");
                }
            }
            model.setFarm1Sold(issold.get(0));
            System.out.println("Farm1Sold =" + model.getFarm1Sold());
            model.setFarm2Sold(issold.get(1));
            System.out.println("Farm2Sold =" + model.getFarm2Sold());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveSoldState() {
        try {
            fw = new FileWriter("GameProject//res//StoreSold.txt");
            sold.set(0, String.valueOf(model.getFarm1Sold()));
            sold.set(1, String.valueOf(model.getFarm2Sold()));
            fw.write(sold.get(0) + "," + sold.get(1));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFailure() {
        try {
            reader = new BufferedReader(new FileReader("GameProject//res//Failure.txt"));
            temp = reader.readLine();
            model.setFailureTimes(Integer.parseInt(temp));
            logger.info("Read failure : " + temp);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFailure() {
        try {
            fw = new FileWriter("GameProject//res//Failure.txt");
            tempFailure = model.getFailureTimes();
            fw.write(String.valueOf(tempFailure));
            logger.info("Save failure : " + String.valueOf(tempFailure));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readGdCb() {
        try {
            reader = new BufferedReader(new FileReader("GameProject//res//GdCbItem.txt"));
            temp = reader.readLine();
            String[] s = temp.split(",");
            System.out.print("花園種植:");
            for (int i = 0; i < 6; i++) {
                model.initGdCbList(s[i]);
                System.out.print(s[i] + ",");
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveGdCb() {
        try {
            fw = new FileWriter("GameProject//res//GdCbItem.txt");
            for (int i = 0; i < 6; i++) {
                fw.write(model.getGdCbList(i) + ",");
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readPlayer() {
        try {
            reader = new BufferedReader(new FileReader("GameProject//res//Player.txt"));
            temp = reader.readLine();
            String[] s = temp.split(",");
            model.setPlayerlevel(Integer.parseInt(s[0]));
            model.setExp(Integer.parseInt(s[1]));
            System.out.println("\n" + "level:" + s[0] + "," + "exp:" + s[1]);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void savePlayer() {
        try {
            fw = new FileWriter("GameProject//res//Player.txt");
            fw.write(String.valueOf(model.getPlayerlevel()) + "," + String.valueOf(model.getExp()));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initAllData() {
        readcoin();
        readStone();
        readClock();
        readElement();
        readFailure();
        readSoldState();
        readGdCb();
        readPlayer();
    }

    public void saveAllData() {
        savecoin();
        saveStone();
        saveClock();
        saveElement();
        saveWeaponLevel();
        saveFailure();
        saveSoldState();
        saveGdCb();
        savePlayer();
    }
}

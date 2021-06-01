package GameProject.Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.logging.Logger;

public class ViewStore extends JFrame implements ActionListener, CoinObserver {
    private static Logger logger = Logger.getLogger(ViewStore.class.getName());
    ControllerMainInterface controller;
    ModelInterface model;
    JFrame storeFrame;
    JPanel mainPanel, titlePanel;
    JLabel storeTitle, backImgLabel;
    // buyPanel
    JLabel farmImg_1, farmTitle_1;
    JLabel farmImg_2, farmTitle_2;
    JLabel farmImg_3, farmTitle_3;
    JLabel farmImg_4, farmTitle_4;
    JButton backBtn, hintBtn, farmBtn_1, farmBtn_2, farmBtn_3, farmBtn_4;
    // soldPanel
    JLabel myItemDes, itemNumDes, soldNumDes;
    JComboBox<String> myItemCb;
    JLabel itemNumLabel, soldPriceLabel;
    JTextField soldNumTextField;
    JButton soldBtn;
    JLayeredPane pane;
    public static volatile ViewStore instance = null;

    public ViewStore(ControllerMainInterface controller, ModelInterface model) {
        this.controller = controller;
        this.model = model;
        model.registerCoinObserver(this);
        createStoreView();
        createControls();
    }

    public void createStoreView() {
        if (instance == null) {
            storeFrame = new JFrame("商店");
            storeFrame.setSize(390, 300);
            storeFrame.setResizable(false);
            storeFrame.setLocationRelativeTo(null);
            storeFrame.setLocation(new Point(ViewMain.WIDTH + 600, ViewMain.HEIGHT - 200));
            storeFrame.setLayout(null);
            storeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            mainPanel = new JPanel();
            mainPanel.setBounds(0, 20, 390, 300);
            mainPanel.setLayout(null);

            backImgLabel = new JLabel(new ImageIcon("GameProject//res//pics//store.jpg"));
            backImgLabel.setBounds(0, -10, 390, 300);

            titlePanel = new JPanel();
            titlePanel.setLayout(null);
            titlePanel.setBounds(0, 0, 390, 20);
            titlePanel.setOpaque(true);
            titlePanel.setBackground(Color.BLUE);

            backBtn = new JButton("關閉");
            backBtn.setBounds(0, 0, 60, 20);

            storeTitle = new JLabel("凱酷的店舖", SwingConstants.CENTER);
            storeTitle.setBounds(60, 0, 254, 20);
            storeTitle.setOpaque(true);
            storeTitle.setBackground(Color.black);
            storeTitle.setForeground(Color.WHITE);
            storeTitle.setFont(new Font("Serif", Font.BOLD, 12));

            hintBtn = new JButton("提示");
            hintBtn.setBounds(314, 0, 60, 20);

            farmTitle_1 = new JLabel("田地+1", SwingConstants.CENTER);
            farmTitle_1.setBounds(10, 10, 80, 20);
            farmTitle_1.setOpaque(true);
            farmTitle_1.setBackground(MyColor.SKYBLUE);

            farmImg_1 = new JLabel(new ImageIcon("GameProject//res//pics//farm.jpg"));
            farmImg_1.setBounds(10, 30, 80, 80);
            farmImg_1.setOpaque(true);
            farmImg_1.setBackground(Color.white);

            farmBtn_1 = new JButton("$50,000");
            farmBtn_1.setBounds(10, 115, 80, 20);
            farmBtn_1.setFont(new Font("Arial", Font.PLAIN, 11));
            farmBtn_1.setName("buy1");
            // farmBtn_1.setEnabled(false);

            farmTitle_2 = new JLabel("田地+1", SwingConstants.CENTER);
            farmTitle_2.setBounds(100, 10, 80, 20);
            farmTitle_2.setOpaque(true);
            farmTitle_2.setBackground(MyColor.SKYBLUE);

            farmImg_2 = new JLabel(new ImageIcon("GameProject//res//pics//farm.jpg"));
            farmImg_2.setBounds(100, 30, 80, 80);
            farmImg_2.setOpaque(true);
            farmImg_2.setBackground(Color.white);

            farmBtn_2 = new JButton("$100,000");
            farmBtn_2.setBounds(100, 115, 80, 20);
            farmBtn_2.setFont(new Font("Arial", Font.PLAIN, 11));
            farmBtn_2.setName("buy2");

            farmTitle_3 = new JLabel("強化石+1", SwingConstants.CENTER);
            farmTitle_3.setBounds(190, 10, 80, 20);
            farmTitle_3.setOpaque(true);
            farmTitle_3.setBackground(MyColor.SKYBLUE);

            farmImg_3 = new JLabel(new ImageIcon("GameProject//res//pics//stone.jpg"));
            farmImg_3.setBounds(190, 30, 80, 80);
            farmImg_3.setOpaque(true);
            farmImg_3.setBackground(Color.white);

            farmBtn_3 = new JButton("$1,500");
            farmBtn_3.setBounds(190, 115, 80, 20);
            farmBtn_3.setFont(new Font("Arial", Font.PLAIN, 11));
            farmBtn_3.setName("buy3");

            farmTitle_4 = new JLabel("保護石+1", SwingConstants.CENTER);
            farmTitle_4.setBounds(280, 10, 80, 20);
            farmTitle_4.setOpaque(true);
            farmTitle_4.setBackground(MyColor.SKYBLUE);

            farmImg_4 = new JLabel(new ImageIcon("GameProject//res//pics//protect.jpg"));
            farmImg_4.setBounds(280, 30, 80, 80);
            farmImg_4.setOpaque(true);
            farmImg_4.setBackground(Color.white);

            farmBtn_4 = new JButton("$5,000");
            farmBtn_4.setBounds(280, 115, 80, 20);
            farmBtn_4.setFont(new Font("Arial", Font.PLAIN, 11));
            farmBtn_4.setName("buy4");

            myItemDes = new JLabel("選擇販賣項目", SwingConstants.CENTER);
            myItemDes.setBounds(10, 145, 100, 20);
            myItemDes.setOpaque(true);
            myItemDes.setBackground(Color.white);
            myItemDes.setBorder(BorderFactory.createLineBorder(MyColor.SKYBLUE, 1));

            itemNumDes = new JLabel("擁有數量", SwingConstants.CENTER);
            itemNumDes.setBounds(115, 145, 60, 20);
            itemNumDes.setOpaque(true);
            itemNumDes.setBackground(Color.white);
            itemNumDes.setBorder(BorderFactory.createLineBorder(MyColor.SKYBLUE, 1));

            soldNumDes = new JLabel("販賣數量", SwingConstants.CENTER);
            soldNumDes.setBounds(180, 145, 60, 20);
            soldNumDes.setOpaque(true);
            soldNumDes.setBackground(Color.white);
            soldNumDes.setBorder(BorderFactory.createLineBorder(MyColor.SKYBLUE, 1));

            String[] solditem = new String[] { "煉金殘渣", "高級煉金石", "Banana", "Apple", "Orange", "Melon" };
            myItemCb = new JComboBox<String>(solditem);
            myItemCb.setBounds(10, 175, 100, 20);
            myItemCb.setSelectedItem(null);

            itemNumLabel = new JLabel();
            itemNumLabel.setBounds(115, 175, 60, 20);
            itemNumLabel.setOpaque(true);
            itemNumLabel.setBackground(Color.white);
            itemNumLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));

            soldNumTextField = new JTextField();
            soldNumTextField.setHorizontalAlignment(JTextField.CENTER);
            soldNumTextField.setBounds(180, 174, 60, 21);
            soldNumTextField.setOpaque(true);
            soldNumTextField.setBackground(Color.white);
            soldNumTextField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));

            soldBtn = new JButton("販賣");
            soldBtn.setBounds(245, 173, 60, 22);

            soldPriceLabel = new JLabel("$0", SwingConstants.CENTER);
            soldPriceLabel.setBounds(10, 200, 100, 20);
            soldPriceLabel.setOpaque(true);
            soldPriceLabel.setBackground(MyColor.VERY_LIGHT_YELLOW);
            soldPriceLabel.setForeground(Color.BLACK);

            titlePanel.add(backBtn);
            titlePanel.add(storeTitle);
            titlePanel.add(hintBtn);

            pane = new JLayeredPane();
            pane.setBounds(0, 0, 400, 360);

            pane.add(farmImg_1);
            pane.add(farmTitle_1);
            pane.add(farmBtn_1);
            pane.add(farmImg_2);
            pane.add(farmTitle_2);
            pane.add(farmBtn_2);
            pane.add(farmImg_3);
            pane.add(farmTitle_3);
            pane.add(farmBtn_3);
            pane.add(farmImg_4);
            pane.add(farmTitle_4);
            pane.add(farmBtn_4);

            pane.add(myItemDes);
            pane.add(itemNumDes);
            pane.add(soldNumDes);
            pane.add(myItemCb);
            pane.add(itemNumLabel);
            pane.add(soldNumTextField);
            pane.add(soldBtn);
            pane.add(soldPriceLabel);

            pane.add(backImgLabel);

            mainPanel.add(pane);

            storeFrame.add(mainPanel);
            storeFrame.add(titlePanel);

            storeFrame.setVisible(true);
        }
    }

    public void createControls() {
        if (instance == null) {
            backBtn.addActionListener(this);
            farmBtn_1.addActionListener(this);
            farmBtn_2.addActionListener(this);
            farmBtn_3.addActionListener(this);
            farmBtn_4.addActionListener(this);
            myItemCb.addActionListener(this);
            soldBtn.addActionListener(this);
            hintBtn.addActionListener(this);
        }
    }

    // ............................actionPerformed..........................//
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            controller.closeStore();
        } else if (e.getSource() == farmBtn_1) {
            if (showConfirmDialog("是否購買田地?", "購買確認")) {
                controller.minusCoin(50000);
                controller.buyControl(farmBtn_1);
            }
        } else if (e.getSource() == farmBtn_2) {
            if (showConfirmDialog("是否購買田地?", "購買確認")) {
                controller.minusCoin(100000);
                controller.buyControl(farmBtn_2);
            }
        } else if (e.getSource() == myItemCb) {
            controller.myItemCb_controller(myItemCb.getSelectedIndex(), itemNumLabel, myItemCb);
        } else if (e.getSource() == soldBtn) {
            soldBtn_controller();
        } else if (e.getSource() == farmBtn_3) {
            if (showConfirmDialog("是否購買強化石?", "購買確認")) {
                controller.minusCoin(1500);
                model.setStone(model.getStone() + 1);
            }
        } else if (e.getSource() == farmBtn_4) {
            if (showConfirmDialog("是否購買保護石?", "購買確認")) {
                controller.minusCoin(5000);
                model.setProtectStone(model.getProtectStone() + 1);
            }
        } else if (e.getSource() == hintBtn) {
            hintController();
        }
    }

    public void enableStoreFrame(Boolean b) {
        storeFrame.setVisible(b);
    }

    @Override
    public void updateCoin() {
        if (instance != null) {
            if (model.getCoin() >= 1500) {
                enableBtn(farmBtn_3, true);
            } else {
                enableBtn(farmBtn_3, false);
            }

            if (model.getCoin() >= 5000) {
                enableBtn(farmBtn_4, true);
            } else {
                enableBtn(farmBtn_4, false);
            }

            if (model.getCoin() >= 50000 && model.getFarm1Sold() == false) {
                if (model.getFarm1Sold() == false) {
                    enableBtn(farmBtn_1, true);
                }
            } else {
                enableBtn(farmBtn_1, false);
            }

            if (model.getCoin() >= 100000 && model.getFarm1Sold() == true) {
                if (model.getFarm2Sold() == true) {
                    enableBtn(farmBtn_2, false);
                } else if (model.getFarm2Sold() == false) {
                    enableBtn(farmBtn_2, true);
                }
            } else {
                enableBtn(farmBtn_2, false);
            }
        }
    }

    public void enabelSoldBtn(boolean b) {
        soldBtn.setEnabled(b);
    }

    public void enableBtn(JButton btn, boolean b) {
        btn.setEnabled(b);
    }

    public void closeStore() {
        storeFrame.dispose();
        instance = null;
    }

    public void setSoldItemNum(int num) {
        String item = (String) myItemCb.getSelectedItem();
        switch (item) {
            case "煉金殘渣":
                model.setDregs(num);
                break;
            case "高級煉金石":
                model.setRareDregs(num);
                break;
            case "Banana":
                model.setBanana(num);
                break;
            case "Apple":
                model.setApple(num);
                break;
            case "Orange":
                model.setOrange(num);
                break;
            case "Melon":
                model.setMelon(num);
                break;

            default:
                break;
        }
    }

    public void resetCbSelectIndex() {
        int index = myItemCb.getSelectedIndex();
        myItemCb.setSelectedIndex(index);
    }

    public boolean getmyItemCb() {
        if (myItemCb.getSelectedItem() != null) {
            return true;
        }
        return false;
    }

    public void setSoldPriceLabelText(String text) {
        soldPriceLabel.setText(text);
    }

    private void soldBtn_controller() {
        if (isNumber(soldNumTextField.getText())) {
            int have = Integer.parseInt(itemNumLabel.getText());
            int price = controller.getItemCbObjectPrice(myItemCb);
            int sell = Integer.parseInt(soldNumTextField.getText());
            if (0 < sell && sell <= have) {
                controller.sold(have, sell, price);
                setSoldItemNum(have - sell);
                itemNumLabel.setText(String.valueOf(have - sell));
                soldNumTextField.setText("");
            }
            if (sell > have) {
                showDialog("想騙484?");
                soldNumTextField.setText("");
            }
        } else {
            showDialog("是不會輸入數字逆?");
        }
    }

    private boolean isNumber(String Userinput) {
        try {
            int i = Integer.valueOf(Userinput).intValue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void showDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public boolean showConfirmDialog(String msg, String title) {
        int result = JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            return true;
        }
        return false;
    }

    private void hintController() {
        StringBuilder sb = new StringBuilder();
        sb.append("1.煉金手冊: \n");
        sb.append("    (1).1111 : 強化石\n");
        sb.append("    (2).2222 : 凝縮強化石\n");
        sb.append("    (3).4444 : 強化石*5\n");
        sb.append("    (4).3333 : 保護石\n");
        sb.append("    (5).3132 : 失敗疊層*15\n");
        sb.append("    (6).1211 : 失敗疊層*5\n");
        sb.append("2.煉金殘渣、高級煉金石可直接賣商店，換取金幣" + "\n");
        sb.append("3.武器最高20階，利用保護石與失敗疊層，增加強化機率");
        showDialog(sb.toString());
    }

}

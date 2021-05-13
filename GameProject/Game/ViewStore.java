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
    JPanel mainPanel, titlePanel, buyPanel, soldPanel;
    JLabel storeTitle, backImgLabel;
    // buyPanel
    JLabel farmImg_1, farmTitle_1;
    JLabel farmImg_2, farmTitle_2;
    JLabel farmImg_3, farmTitle_3;
    JButton backBtn, hintBtn, farmBtn_1, farmBtn_2, farmBtn_3;
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
            storeFrame.setSize(310, 300);
            storeFrame.setResizable(false);
            storeFrame.setLocationRelativeTo(null);
            storeFrame.setLocation(new Point(ViewMain.WIDTH + 600, ViewMain.HEIGHT - 200));
            storeFrame.setLayout(null);
            storeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            mainPanel = new JPanel();
            mainPanel.setBounds(0, 20, 310, 300);
            mainPanel.setLayout(null);

            backImgLabel = new JLabel(new ImageIcon("GameProject//res//pics//store.jpg"));
            backImgLabel.setBounds(0, 0, 310, 300);

            titlePanel = new JPanel();
            titlePanel.setLayout(null);
            titlePanel.setBounds(0, 0, 350, 20);
            titlePanel.setOpaque(true);
            titlePanel.setBackground(Color.BLUE);

            // buyPanel = new JPanel();
            // buyPanel.setLayout(null);
            // buyPanel.setBounds(0, 20, 310, 200);

            // soldPanel = new JPanel();
            // soldPanel.setLayout(null);
            // soldPanel.setBounds(0, 220, 400, 100);
            // soldPanel.setOpaque(true);
            // soldPanel.setBackground(Color.red);

            backBtn = new JButton("關閉");
            backBtn.setBounds(0, 0, 60, 20);

            storeTitle = new JLabel("凱酷的店舖", SwingConstants.CENTER);
            storeTitle.setBounds(60, 0, 174, 20);
            storeTitle.setOpaque(true);
            storeTitle.setBackground(Color.black);
            storeTitle.setForeground(Color.WHITE);
            storeTitle.setFont(new Font("Serif", Font.BOLD, 12));

            hintBtn = new JButton("提示");
            hintBtn.setBounds(234, 0, 60, 20);

            farmTitle_1 = new JLabel("田地+1", SwingConstants.CENTER);
            farmTitle_1.setBounds(10, 10, 80, 20);
            farmTitle_1.setOpaque(true);
            farmTitle_1.setBackground(Color.gray);

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
            farmTitle_2.setBackground(Color.gray);

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
            farmTitle_3.setBackground(Color.gray);

            farmImg_3 = new JLabel(new ImageIcon("GameProject//res//pics//stone.jpg"));
            farmImg_3.setBounds(190, 30, 80, 80);
            farmImg_3.setOpaque(true);
            farmImg_3.setBackground(Color.white);

            farmBtn_3 = new JButton("$10,000");
            farmBtn_3.setBounds(190, 115, 80, 20);
            farmBtn_3.setFont(new Font("Arial", Font.PLAIN, 11));
            farmBtn_3.setName("buy3");

            myItemDes = new JLabel("選擇販賣項目", SwingConstants.CENTER);
            myItemDes.setBounds(10, 145, 100, 20);
            myItemDes.setOpaque(true);
            myItemDes.setBackground(Color.white);
            itemNumDes = new JLabel("擁有數量", SwingConstants.CENTER);
            itemNumDes.setBounds(115, 145, 50, 20);
            itemNumDes.setOpaque(true);
            itemNumDes.setBackground(Color.white);
            soldNumDes = new JLabel("販賣數量", SwingConstants.CENTER);
            soldNumDes.setBounds(170, 145, 50, 20);
            soldNumDes.setOpaque(true);
            soldNumDes.setBackground(Color.white);

            String[] solditem = new String[] { "煉金殘渣", "Banana", "Apple", "Orange", "Melon" };
            myItemCb = new JComboBox<String>(solditem);
            myItemCb.setBounds(10, 175, 100, 20);
            myItemCb.setSelectedItem(null);

            itemNumLabel = new JLabel();
            itemNumLabel.setBounds(115, 175, 50, 20);
            itemNumLabel.setOpaque(true);
            itemNumLabel.setBackground(Color.white);
            itemNumLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));

            soldNumTextField = new JTextField();
            soldNumTextField.setHorizontalAlignment(JTextField.CENTER);
            soldNumTextField.setBounds(170, 174, 50, 21);
            soldNumTextField.setOpaque(true);
            soldNumTextField.setBackground(Color.white);
            soldNumTextField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));

            soldBtn = new JButton("販賣");
            soldBtn.setBounds(225, 173, 60, 22);

            soldPriceLabel = new JLabel("$0", SwingConstants.CENTER);
            soldPriceLabel.setBounds(10, 200, 100, 20);
            soldPriceLabel.setOpaque(true);
            soldPriceLabel.setBackground(new Color(255, 255, 204));
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
            myItemCb.addActionListener(this);
            soldBtn.addActionListener(this);
        }
    }

    // ............................actionPerformed..........................//
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            controller.closeStore();
        } else if (e.getSource() == farmBtn_1) {
            controller.minusCoin(50000);
            controller.buyControl(farmBtn_1);
        } else if (e.getSource() == farmBtn_2) {
            controller.minusCoin(100000);
            controller.buyControl(farmBtn_2);
        } else if (e.getSource() == myItemCb) {
            myItemCb_controller();
        } else if (e.getSource() == soldBtn) {
            if (isNumber(soldNumTextField.getText())) {
                int have = Integer.parseInt(itemNumLabel.getText());
                int price = getItemCbObjectPrice();
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
        } else if (e.getSource() == farmBtn_3) {
            if (showConfirmDialong("是否購買強化石?", "購買確認")) {
                controller.minusCoin(10000);
                model.setStone(model.getStone() + 1);
            }
        }
    }

    public void enableStoreFrame(Boolean b) {
        storeFrame.setVisible(b);
    }

    @Override
    public void updateCoin() {
        if (instance != null) {
            if (model.getCoin() >= 10000) {
                enableBtn(farmBtn_3, true);
            } else {
                enableBtn(farmBtn_3, false);
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

    public void enableBtn(JButton btn, boolean b) {
        btn.setEnabled(b);
    }

    public void closeStore() {
        storeFrame.dispose();
        instance = null;
    }

    public int getItemCbObjectPrice() {
        int index = myItemCb.getSelectedIndex();
        int price = 0;
        switch (index) {
            case 0:
                price = 2000;
                break;
            case 1:
                price = 1000;
                break;
            case 2:
                price = 1000;
                break;
            case 3:
                price = 1000;
                break;
            case 4:
                price = 1000;
                break;
            case 5:
                price = 1000;
                break;
        }
        return price;
    }

    public void setSoldItemNum(int num) {
        String item = (String) myItemCb.getSelectedItem();
        switch (item) {
            case "煉金殘渣":
                model.setDregs(num);
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

    private void myItemCb_controller() {
        int i = myItemCb.getSelectedIndex();
        switch (i) {
            case 0:
                itemNumLabel.setText(String.valueOf(model.getDredgs()));
                break;
            case 1:
                itemNumLabel.setText(String.valueOf(model.getBanana()));
                break;
            case 2:
                itemNumLabel.setText(String.valueOf(model.getApple()));
                break;
            case 3:
                itemNumLabel.setText(String.valueOf(model.getOrange()));
                break;
            case 4:
                itemNumLabel.setText(String.valueOf(model.getMelon()));
                break;
        }
        itemNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        soldPriceLabel.setText("$" + String.valueOf(getItemCbObjectPrice()));
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

    public boolean showConfirmDialong(String msg, String title) {
        int result = JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            return true;
        }
        return false;
    }

}

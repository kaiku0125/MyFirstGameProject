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
    JLabel storeTitle;
    // buyPanel
    JLabel farmImg_1, farmTitle_1;
    JLabel farmImg_2, farmTitle_2;
    JButton backBtn, hintBtn, farmBtn_1, farmBtn_2;
    // soldPanel
    JLabel myItemDes, itemNumDes, soldNumDes;
    JComboBox<String> myItemCb;
    JLabel itemNumLabel;
    JTextField soldNumTextField;
    JButton soldBtn;
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
            storeFrame.setSize(400, 350);
            storeFrame.setResizable(false);
            storeFrame.setLocationRelativeTo(null);
            storeFrame.setLocation(new Point(ViewMain.WIDTH + 600, ViewMain.HEIGHT));
            storeFrame.setLayout(null);
            storeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            mainPanel = new JPanel();
            mainPanel.setBounds(0, 0, 400, 300);
            mainPanel.setLayout(null);

            titlePanel = new JPanel();
            titlePanel.setLayout(null);
            titlePanel.setBounds(0, 0, 400, 20);
            titlePanel.setOpaque(true);
            titlePanel.setBackground(Color.BLUE);

            buyPanel = new JPanel();
            buyPanel.setLayout(null);
            buyPanel.setBounds(0, 20, 400, 200);
            buyPanel.setOpaque(true);
            buyPanel.setBackground(Color.GREEN);

            soldPanel = new JPanel();
            soldPanel.setLayout(null);
            soldPanel.setBounds(0, 220, 400, 100);
            soldPanel.setOpaque(true);
            soldPanel.setBackground(Color.red);

            backBtn = new JButton("關閉");
            backBtn.setBounds(0, 0, 60, 20);

            storeTitle = new JLabel("凱酷的店舖", SwingConstants.CENTER);
            storeTitle.setBounds(60, 0, 280, 20);
            storeTitle.setOpaque(true);
            storeTitle.setBackground(Color.black);
            storeTitle.setForeground(Color.WHITE);
            storeTitle.setFont(new Font("Serif", Font.BOLD, 12));

            hintBtn = new JButton("提示");
            hintBtn.setBounds(340, 0, 44, 20);

            farmTitle_1 = new JLabel("田地+1", SwingConstants.CENTER);
            farmTitle_1.setBounds(10, 10, 80, 20);
            farmTitle_1.setOpaque(true);
            farmTitle_1.setBackground(Color.gray);

            farmImg_1 = new JLabel();
            farmImg_1.setBounds(10, 30, 80, 80);
            farmImg_1.setOpaque(true);
            farmImg_1.setBackground(Color.white);

            farmBtn_1 = new JButton("$50,000");
            farmBtn_1.setBounds(10, 115, 80, 20);
            farmBtn_1.setFont(new Font("Arial", Font.PLAIN, 11));
            farmBtn_1.setName("buy1");

            farmTitle_2 = new JLabel("田地+1", SwingConstants.CENTER);
            farmTitle_2.setBounds(100, 10, 80, 20);
            farmTitle_2.setOpaque(true);
            farmTitle_2.setBackground(Color.gray);

            farmImg_2 = new JLabel();
            farmImg_2.setBounds(100, 30, 80, 80);
            farmImg_2.setOpaque(true);
            farmImg_2.setBackground(Color.white);

            farmBtn_2 = new JButton("$100,000");
            farmBtn_2.setBounds(100, 115, 80, 20);
            farmBtn_2.setFont(new Font("Arial", Font.PLAIN, 11));
            farmBtn_2.setName("buy2");

            myItemDes = new JLabel("選擇販賣項目", SwingConstants.CENTER);
            myItemDes.setBounds(10, 10, 100, 20);
            itemNumDes = new JLabel("擁有數量", SwingConstants.CENTER);
            itemNumDes.setBounds(115, 10, 50, 20);
            soldNumDes = new JLabel("販賣數量", SwingConstants.CENTER);
            soldNumDes.setBounds(170, 10, 50, 20);

            String[] solditem = new String[] { "煉金殘渣", "Banana" };
            myItemCb = new JComboBox<String>(solditem);
            myItemCb.setBounds(10, 30, 100, 20);
            myItemCb.setSelectedItem(null);

            itemNumLabel = new JLabel();
            itemNumLabel.setBounds(115, 30, 50, 20);
            itemNumLabel.setOpaque(true);
            itemNumLabel.setBackground(Color.white);
            itemNumLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));

            soldNumTextField = new JTextField();
            soldNumTextField.setHorizontalAlignment(JTextField.CENTER);
            soldNumTextField.setBounds(170, 29, 50, 21);
            soldNumTextField.setOpaque(true);
            soldNumTextField.setBackground(Color.white);
            soldNumTextField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));

            soldBtn = new JButton("販賣");
            soldBtn.setBounds(225, 28, 60, 22);

            titlePanel.add(backBtn);
            titlePanel.add(storeTitle);
            titlePanel.add(hintBtn);

            buyPanel.add(farmImg_1);
            buyPanel.add(farmTitle_1);
            buyPanel.add(farmBtn_1);
            buyPanel.add(farmImg_2);
            buyPanel.add(farmTitle_2);
            buyPanel.add(farmBtn_2);

            soldPanel.add(myItemDes);
            soldPanel.add(itemNumDes);
            soldPanel.add(soldNumDes);
            soldPanel.add(myItemCb);
            soldPanel.add(itemNumLabel);
            soldPanel.add(soldNumTextField);
            soldPanel.add(soldBtn);

            storeFrame.add(titlePanel);
            storeFrame.add(buyPanel);
            storeFrame.add(soldPanel);

            storeFrame.setVisible(true);
        }
    }

    public void createControls() {
        if (instance == null) {
            backBtn.addActionListener(this);
            farmBtn_1.addActionListener(this);
            farmBtn_2.addActionListener(this);
            myItemCb.addActionListener(this);
            soldBtn.addActionListener(this);
        }
    }

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
            int i = myItemCb.getSelectedIndex();
            switch (i) {
                case 0:
                    itemNumLabel.setText(String.valueOf(model.getFailureTimes()));
                    itemNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    break;
                case 1:
                    itemNumLabel.setText(String.valueOf(model.getBanana()));
                    itemNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    break;
            }
        } else if (e.getSource() == soldBtn) {
            if (isNumber(soldNumTextField.getText())) {
                int have = Integer.parseInt(itemNumLabel.getText());
                int price = getItemCbObjectPrice();
                int sell = Integer.parseInt(soldNumTextField.getText());
                if (0 < sell && sell < have) {
                    controller.sold(have, sell, price);
                    logger.info("...");
                }
                if (sell > have) {
                    showDialog("想騙484?");
                    soldNumTextField.setText("");
                }
            } else {
                showDialog("是不會輸入數字逆?");
            }
        }
    }

    public void enableStoreFrame(Boolean b) {
        storeFrame.setVisible(b);
    }

    @Override
    public void updateCoin() {
        if (model.getCoin() >= 50000) {
            enableBtn(farmBtn_1, true);
        } else {
            enableBtn(farmBtn_1, false);
        }

        if (model.getCoin() >= 100000 && model.getFarm1Sold() == true) {
            if (model.getFarm2Sold() == false) {
                enableBtn(farmBtn_2, true);
            }
        } else {
            enableBtn(farmBtn_2, false);
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
        int i = myItemCb.getSelectedIndex();
        int price = 0;
        switch (i) {
            case 0:
                price = 2000;
                break;
            case 1:
                price = 1000;
                break;
        }
        return price;
    }

    public void showDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private boolean isNumber(String strnumber) {
        int i;
        try {
            i = Integer.valueOf(strnumber).intValue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

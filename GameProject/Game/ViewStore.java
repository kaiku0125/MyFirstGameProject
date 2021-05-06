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
    JLabel farmImg_1, farmTitle_1;
    JLabel farmImg_2, farmTitle_2;
    JComboBox<String> myItemCb;
    JButton backBtn, hintBtn, farmBtn_1, farmBtn_2;
    public static volatile ViewStore instance = null;

    public ViewStore(ControllerMainInterface controller, ModelInterface model) {
        this.controller = controller;
        this.model = model;
        model.registerCoinObserver(this);
        createStoreView();
        createControls();

    }

    public void createStoreView() {
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

        titlePanel.add(backBtn);
        titlePanel.add(storeTitle);
        titlePanel.add(hintBtn);

        buyPanel.add(farmImg_1);
        buyPanel.add(farmTitle_1);
        buyPanel.add(farmBtn_1);
        buyPanel.add(farmImg_2);
        buyPanel.add(farmTitle_2);
        buyPanel.add(farmBtn_2);

        storeFrame.add(titlePanel);
        storeFrame.add(buyPanel);
        storeFrame.add(soldPanel);

        storeFrame.setVisible(false);
    }

    public void createControls() {
        backBtn.addActionListener(this);
        farmBtn_1.addActionListener(this);
        farmBtn_2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            controller.closeStore();
        } else if (e.getSource() == farmBtn_1) {
            controller.buyControl(farmBtn_1);
        }

    }

    public void enableStoreFrame(Boolean b) {
        storeFrame.setVisible(b);

    }

    @Override
    public void updateCoin() {
        if (model.getFarm1Sold() == true) {
            enabelBtn(farmBtn_1, false);
        } else {
            if (model.getCoin() >= 50000) {
                enabelBtn(farmBtn_1, true);
            } else {
                enabelBtn(farmBtn_1, false);
            }
        }

    }

    public void enabelBtn(JButton btn, boolean b) {
        btn.setEnabled(b);
    }

}

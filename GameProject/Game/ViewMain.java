package GameProject.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import GameProject.libs.GardenItemComboBox;
import GameProject.libs.Weapon;
import GameProject.libs.WeaponComboBox;

public class ViewMain implements ActionListener, CoinObserver, ClockObserver, Callback {
    private Logger logger = Logger.getLogger(ViewMain.class.getName());
    static int WIDTH = 700;
    static int HEIGHT = 520;
    ControllerMainInterface controller;
    ModelInterface model;

    JFrame mainFrame;
    JPanel mainPanel, leftPanel, storePanel, elementCbPanel, middlePanel, CoinDailyPanel;
    JPanel elementPanel, elementNumPanel, downPanel, gardenPanel;
    JLayeredPane pane, ALpane, Gdpane, Storepane;
    JLabel coinLabel, coinImgLabel, dailyImgLabel, stoneClockLabel, descriptionLabel;
    JLabel stoneLabel, stoneNumLabel, extremeLabel, extremeNumLabel;
    JLabel protectLabel, protectNumLabel, failureTimesLabel;
    JLabel alchemyImgLabel, enhanceImgLabel, weaponLevelLabel;
    JLabel bananaLabel, appleLabel, orangeLabel, melonLabel, dregsLabel;
    JLabel bananaNum, appleNum, orangeNum, melonNum, dregsNum;
    JLabel gardenTitleLabel, gardenImgLabel, storeImgLabel;
    JCheckBox failcheck, protectcheck;
    JComboBox<Integer> element1, element2, element3, element4;
    JButton enhanceBtn, dailyBtn, alchemyBtn, storeBtn;
    WeaponComboBox weaponCombo;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem saveItem;
    JProgressBar enhancementBar, alchemyBar;
    Weapon tempItem;
    // Garden Component
    GardenItemComboBox gdCb_1, gdCb_2, gdCb_3, gdCb_4, gdCb_5, gdCb_6;
    JButton gdBtn_1, gdBtn_2, gdBtn_3, gdBtn_4, gdBtn_5, gdBtn_6;

    public ViewMain(ControllerMainInterface controller, ModelInterface model) {
        this.controller = controller;
        this.model = model;
        model.registerCoinObserver(this);
        model.registerClockObserver(this);
        model.regStoneCallback(this);
    }

    // create all component in frame
    public void createMainView() {
        mainFrame = new JFrame("KaiKuKu's Game");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.getContentPane().setBackground(Color.BLACK);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);

        // Panel area ..................................//
        mainPanel = new JPanel();
        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(Color.BLACK);
        leftPanel.setBounds(0, 0, 200, 470);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        middlePanel.setBackground(Color.BLACK);
        middlePanel.setBounds(200, 0, 300, 320);

        elementCbPanel = new JPanel();
        elementCbPanel.setBackground(Color.black);
        elementCbPanel.setOpaque(true);
        elementCbPanel.setBounds(200, 320, 300, 30);

        elementPanel = new JPanel();
        elementPanel.setBackground(Color.black);
        elementPanel.setOpaque(true);
        elementPanel.setBounds(200, 350, 300, 30);

        elementNumPanel = new JPanel();
        elementNumPanel.setBackground(Color.black);
        elementNumPanel.setOpaque(true);
        elementNumPanel.setBounds(200, 380, 300, 30);

        downPanel = new JPanel();
        downPanel.setLayout(null);
        downPanel.setBounds(200, 410, 300, 50);
        downPanel.setOpaque(true);
        downPanel.setBackground(Color.BLACK);

        CoinDailyPanel = new JPanel();
        CoinDailyPanel.setLayout(null);
        CoinDailyPanel.setBounds(500, 0, 200, 50);
        CoinDailyPanel.setOpaque(true);
        CoinDailyPanel.setBackground(Color.RED);

        gardenPanel = new JPanel();
        gardenPanel.setLayout(null);
        gardenPanel.setBounds(500, 50, 200, 330);
        gardenPanel.setOpaque(true);
        gardenPanel.setBackground(Color.GREEN);

        storePanel = new JPanel();
        storePanel.setLayout(null);
        storePanel.setBounds(500, 380, 200, 120);
        storePanel.setOpaque(true);
        storePanel.setBackground(Color.RED);
        // component.............................................//
        weaponCombo = new WeaponComboBox(model);
        weaponCombo.setBounds(0, 0, 200, 50);
        weaponCombo.setSelectedItem(null);

        failcheck = new JCheckBox();
        failcheck.setBounds(150, 0, 20, 20);
        failcheck.setToolTipText("使用累積失敗疊層，增加下次強化成功機率");
        failcheck.setOpaque(true);
        failcheck.setBackground(Color.BLACK);

        failureTimesLabel = new JLabel("0", SwingConstants.CENTER);
        failureTimesLabel.setBounds(170, 0, 30, 20);
        failureTimesLabel.setOpaque(true);
        failureTimesLabel.setForeground(Color.white);
        failureTimesLabel.setBackground(Color.BLACK);
        failureTimesLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));

        weaponLevelLabel = new JLabel("", SwingConstants.CENTER);
        weaponLevelLabel.setBounds(75, 140, 50, 20);
        weaponLevelLabel.setOpaque(true);
        weaponLevelLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        weaponLevelLabel.setForeground(Color.RED);
        weaponLevelLabel.setBackground(Color.BLACK);

        enhanceImgLabel = new JLabel();
        enhanceImgLabel.setOpaque(true);
        enhanceImgLabel.setBackground(Color.BLACK);
        enhanceImgLabel.setBounds(0, 0, 200, 200);

        pane = new JLayeredPane();
        pane.setBounds(0, 50, 200, 200);

        pane.add(failcheck);
        pane.add(weaponLevelLabel);
        pane.add(failureTimesLabel);
        pane.add(enhanceImgLabel);

        enhancementBar = new JProgressBar(0, 100);
        enhancementBar.setBounds(0, 250, 200, 20);
        enhancementBar.setStringPainted(true);
        enhancementBar.setString("");
        enhancementBar.setVisible(false);

        stoneLabel = new JLabel("強化石", SwingConstants.CENTER);
        stoneLabel.setBackground(Color.lightGray);
        stoneLabel.setOpaque(true);
        stoneLabel.setBounds(0, 270, 50, 20);
        stoneLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        extremeLabel = new JLabel("凝縮的強化石", SwingConstants.CENTER);
        extremeLabel.setBackground(Color.lightGray);
        extremeLabel.setOpaque(true);
        extremeLabel.setBounds(50, 270, 80, 20);
        extremeLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        protectcheck = new JCheckBox();
        protectcheck.setHorizontalAlignment(JCheckBox.LEFT);
        protectcheck.setBounds(131, 271, 19, 18);
        protectcheck.setOpaque(true);
        protectcheck.setBackground(Color.orange);
        protectcheck.setToolTipText("下次強化將注入保護石");

        protectLabel = new JLabel("保護石", SwingConstants.CENTER);
        protectLabel.setBackground(Color.ORANGE);
        protectLabel.setOpaque(true);
        protectLabel.setBounds(150, 271, 49, 18);

        stoneNumLabel = new JLabel("0", SwingConstants.CENTER);
        stoneNumLabel.setBackground(Color.white);
        stoneNumLabel.setOpaque(true);
        stoneNumLabel.setBounds(0, 290, 50, 20);
        stoneNumLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        extremeNumLabel = new JLabel("0", SwingConstants.CENTER);
        extremeNumLabel.setBackground(Color.white);
        extremeNumLabel.setOpaque(true);
        extremeNumLabel.setBounds(50, 290, 80, 20);
        extremeNumLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        protectNumLabel = new JLabel("0", SwingConstants.CENTER);
        protectNumLabel.setBackground(Color.white);
        protectNumLabel.setOpaque(true);
        protectNumLabel.setBounds(130, 290, 70, 20);
        protectNumLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        stoneClockLabel = new JLabel("00:00", SwingConstants.CENTER);
        stoneClockLabel.setBackground(Color.WHITE);
        stoneClockLabel.setOpaque(true);
        stoneClockLabel.setBounds(0, 310, 50, 40);
        stoneClockLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        enhanceBtn = new JButton("強化");
        enhanceBtn.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        enhanceBtn.setBounds(50, 310, 150, 40);

        descriptionLabel = new JLabel("...");
        descriptionLabel.setVerticalAlignment(JLabel.NORTH);
        descriptionLabel.setHorizontalAlignment(JLabel.LEFT);
        descriptionLabel.setBackground(new Color(204, 204, 204));
        descriptionLabel.setOpaque(true);
        descriptionLabel.setBounds(0, 350, 200, 120);
        descriptionLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        alchemyBtn = new JButton("煉金");
        alchemyBtn.setBounds(120, 275, 60, 20);

        alchemyImgLabel = new JLabel(new ImageIcon("GameProject//res//pics//alchemy.jpg"));
        alchemyImgLabel.setBackground(Color.PINK);
        alchemyImgLabel.setOpaque(true);
        alchemyImgLabel.setBounds(0, 0, 300, 300);

        ALpane = new JLayeredPane();
        ALpane.setBounds(0, 0, 300, 300);

        ALpane.add(alchemyBtn);
        ALpane.add(alchemyImgLabel);

        alchemyBar = new JProgressBar(0, 100);
        alchemyBar.setBounds(0, 300, 300, 20);
        alchemyBar.setStringPainted(true);
        alchemyBar.setString("");
        alchemyBar.setVisible(false);

        Integer[] allelement = new Integer[5];
        for (int i = 0; i < 5; i++) {
            allelement[i] = i;
        }
        element1 = new JComboBox<Integer>(allelement);
        element1.setPreferredSize(new Dimension(60, 20));
        element1.setOpaque(true);
        element1.setBackground(Color.white);
        element2 = new JComboBox<Integer>(allelement);
        element2.setPreferredSize(new Dimension(60, 20));
        element3 = new JComboBox<Integer>(allelement);
        element3.setPreferredSize(new Dimension(60, 20));
        element4 = new JComboBox<Integer>(allelement);
        element4.setPreferredSize(new Dimension(60, 20));
        setElementComboNull();

        bananaLabel = new JLabel("Banana", SwingConstants.CENTER);
        bananaLabel.setBackground(Color.black);
        bananaLabel.setForeground(new Color(255, 204, 0));
        bananaLabel.setOpaque(true);
        bananaLabel.setPreferredSize(new Dimension(60, 20));
        bananaLabel.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 0), 1));

        appleLabel = new JLabel("Apple", SwingConstants.CENTER);
        appleLabel.setBackground(Color.BLACK);
        appleLabel.setForeground(new Color(255, 51, 51));
        appleLabel.setOpaque(true);
        appleLabel.setPreferredSize(new Dimension(60, 20));
        appleLabel.setBorder(BorderFactory.createLineBorder(new Color(255, 51, 51), 1));

        orangeLabel = new JLabel("Orange", SwingConstants.CENTER);
        orangeLabel.setBackground(Color.black);
        orangeLabel.setForeground(new Color(255, 153, 0));
        orangeLabel.setOpaque(true);
        orangeLabel.setPreferredSize(new Dimension(60, 20));
        orangeLabel.setBorder(BorderFactory.createLineBorder(new Color(255, 153, 0), 1));

        melonLabel = new JLabel("Melon", SwingConstants.CENTER);
        melonLabel.setBackground(Color.black);
        melonLabel.setForeground(new Color(0, 255, 51));
        melonLabel.setOpaque(true);
        melonLabel.setPreferredSize(new Dimension(60, 20));
        melonLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 51), 1));

        bananaNum = new JLabel("0", SwingConstants.CENTER);
        bananaNum.setBackground(Color.black);
        bananaNum.setForeground(new Color(255, 204, 0));
        bananaNum.setOpaque(true);
        bananaNum.setPreferredSize(new Dimension(60, 20));
        bananaNum.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 0), 1));

        appleNum = new JLabel("0", SwingConstants.CENTER);
        appleNum.setBackground(Color.black);
        appleNum.setForeground(new Color(255, 51, 51));
        appleNum.setOpaque(true);
        appleNum.setPreferredSize(new Dimension(60, 20));
        appleNum.setBorder(BorderFactory.createLineBorder(new Color(255, 51, 51), 1));

        orangeNum = new JLabel("0", SwingConstants.CENTER);
        orangeNum.setBackground(Color.black);
        orangeNum.setForeground(new Color(255, 153, 0));
        orangeNum.setOpaque(true);
        orangeNum.setPreferredSize(new Dimension(60, 20));
        orangeNum.setBorder(BorderFactory.createLineBorder(new Color(255, 153, 0), 1));

        melonNum = new JLabel("0", SwingConstants.CENTER);
        melonNum.setBackground(Color.black);
        melonNum.setForeground(new Color(0, 255, 51));
        melonNum.setOpaque(true);
        melonNum.setPreferredSize(new Dimension(60, 20));
        melonNum.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 51), 1));

        dregsLabel = new JLabel("煉金殘渣", SwingConstants.CENTER);
        dregsLabel.setBackground(Color.BLACK);
        dregsLabel.setForeground(Color.RED);
        dregsLabel.setOpaque(true);
        dregsLabel.setBounds(10, 10, 70, 20);

        dregsNum = new JLabel("0", SwingConstants.LEFT);
        dregsNum.setBackground(Color.BLACK);
        dregsNum.setForeground(Color.RED);
        dregsNum.setOpaque(true);
        dregsNum.setBounds(80, 10, 20, 20);

        coinImgLabel = new JLabel();
        coinImgLabel.setIcon(new ImageIcon("GameProject//res//pics//Coin.png"));
        coinImgLabel.setBounds(0, 0, 20, 20);

        coinLabel = new JLabel("0", SwingConstants.CENTER);
        coinLabel.setBackground(new Color(255, 204, 51));
        coinLabel.setFont(new Font("Serif", Font.BOLD, 13));
        coinLabel.setOpaque(true);
        coinLabel.setBounds(20, 0, 70, 20);

        dailyImgLabel = new JLabel();
        dailyImgLabel.setIcon(new ImageIcon("GameProject//res//pics//Present.jpg"));
        dailyImgLabel.setBounds(90, 0, 20, 20);

        dailyBtn = new JButton("00:00");
        dailyBtn.setBounds(110, 0, 74, 20);

        gardenTitleLabel = new JLabel("花園", SwingConstants.CENTER);
        gardenTitleLabel.setBounds(0, 20, 184, 30);
        gardenTitleLabel.setOpaque(true);
        gardenTitleLabel.setBackground(new Color(0, 204, 0));
        gardenTitleLabel.setBorder(BorderFactory.createLineBorder(Color.green, 1));

        gdCb_1 = new GardenItemComboBox(model);
        gdCb_1.setBounds(10, 10, 75, 60);
        gdCb_1.setEnabled(false);

        gdCb_2 = new GardenItemComboBox(model);
        gdCb_2.setBounds(95, 10, 75, 60);
        gdCb_2.setEnabled(false);

        gdCb_3 = new GardenItemComboBox(model);
        gdCb_3.setBounds(10, 115, 75, 60);
        gdCb_3.setEnabled(false);

        gdCb_4 = new GardenItemComboBox(model);
        gdCb_4.setBounds(95, 115, 75, 60);
        gdCb_4.setEnabled(false);

        gdCb_5 = new GardenItemComboBox(model);
        gdCb_5.setBounds(10, 220, 75, 60);
        gdCb_5.setEnabled(false);

        gdCb_6 = new GardenItemComboBox(model);
        gdCb_6.setBounds(95, 220, 75, 60);
        gdCb_6.setEnabled(false);

        gdBtn_1 = new JButton("00:00");
        gdBtn_1.setBounds(10, 75, 75, 20);
        gdBtn_1.setName("1");

        gdBtn_2 = new JButton("00:00");
        gdBtn_2.setBounds(95, 75, 75, 20);
        gdBtn_2.setName("2");

        gdBtn_3 = new JButton("00:00");
        gdBtn_3.setBounds(10, 180, 75, 20);
        gdBtn_3.setName("3");

        gdBtn_4 = new JButton("00:00");
        gdBtn_4.setBounds(95, 180, 75, 20);
        gdBtn_4.setName("4");

        gdBtn_5 = new JButton("收割");
        gdBtn_5.setBounds(10, 285, 75, 20);
        gdBtn_5.setName("5");
        gdBtn_5.setEnabled(false);

        gdBtn_6 = new JButton("收割");
        gdBtn_6.setBounds(95, 285, 75, 20);
        gdBtn_6.setName("6");
        gdBtn_6.setEnabled(false);

        gardenImgLabel = new JLabel(new ImageIcon("GameProject//res//pics//garden.jpg"));
        gardenImgLabel.setBounds(0, 0, 200, 330);

        Gdpane = new JLayeredPane();
        Gdpane.setBounds(0, 0, 200, 330);

        Gdpane.add(gdCb_1);
        Gdpane.add(gdCb_2);
        Gdpane.add(gdCb_3);
        Gdpane.add(gdCb_4);
        Gdpane.add(gdCb_5);
        Gdpane.add(gdCb_6);
        Gdpane.add(gdBtn_1);
        Gdpane.add(gdBtn_2);
        Gdpane.add(gdBtn_3);
        Gdpane.add(gdBtn_4);
        Gdpane.add(gdBtn_5);
        Gdpane.add(gdBtn_6);
        Gdpane.add(gardenImgLabel);

        storeBtn = new JButton("商店");
        storeBtn.setBounds(125, 58, 60, 20);

        storeImgLabel = new JLabel(new ImageIcon("GameProject//res//pics//store1.jpg"));
        storeImgLabel.setBounds(-1, -25, 200, 120);

        Storepane = new JLayeredPane();
        Storepane.setBounds(0, 0, 200, 120);
        Storepane.add(storeBtn);
        Storepane.add(storeImgLabel);

        // panel add component...................................//
        leftPanel.add(weaponCombo);
        leftPanel.add(pane);
        leftPanel.add(enhancementBar);
        leftPanel.add(stoneLabel);
        leftPanel.add(stoneNumLabel);
        leftPanel.add(extremeLabel);
        leftPanel.add(extremeNumLabel);
        leftPanel.add(protectLabel);
        leftPanel.add(protectNumLabel);
        leftPanel.add(stoneClockLabel);
        leftPanel.add(enhanceBtn);
        leftPanel.add(protectcheck);
        leftPanel.add(descriptionLabel);

        middlePanel.add(ALpane);
        middlePanel.add(alchemyBar);

        elementCbPanel.add(element1);
        elementCbPanel.add(element2);
        elementCbPanel.add(element3);
        elementCbPanel.add(element4);

        elementPanel.add(bananaLabel);
        elementPanel.add(appleLabel);
        elementPanel.add(orangeLabel);
        elementPanel.add(melonLabel);

        elementNumPanel.add(bananaNum);
        elementNumPanel.add(appleNum);
        elementNumPanel.add(orangeNum);
        elementNumPanel.add(melonNum);

        downPanel.add(dregsLabel);
        downPanel.add(dregsNum);

        CoinDailyPanel.add(coinImgLabel);
        CoinDailyPanel.add(coinLabel);
        CoinDailyPanel.add(dailyImgLabel);
        CoinDailyPanel.add(dailyBtn);
        CoinDailyPanel.add(gardenTitleLabel);

        gardenPanel.add(Gdpane);

        storePanel.add(Storepane);

        // mainFrame add panel................//
        mainFrame.add(leftPanel);
        mainFrame.add(storePanel);
        mainFrame.add(middlePanel);
        mainFrame.add(elementCbPanel);
        mainFrame.add(elementPanel);
        mainFrame.add(elementNumPanel);
        mainFrame.add(downPanel);
        mainFrame.add(CoinDailyPanel);
        mainFrame.add(gardenPanel);

        mainFrame.setVisible(true);
    }

    public void createControls() {
        saveItem = new JMenuItem("Save & Quit");
        menu.add(saveItem);
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stop();
                System.exit(0);
            }
        });
        element1.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                element1.setBackground(Color.white);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

        element2.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                element2.setBackground(Color.white);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

        element3.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                element3.setBackground(Color.white);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

        element4.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                element4.setBackground(Color.white);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

        element1.addActionListener(this);
        element2.addActionListener(this);
        element3.addActionListener(this);
        element4.addActionListener(this);
        enhanceBtn.addActionListener(this);
        dailyBtn.addActionListener(this);
        alchemyBtn.addActionListener(this);
        weaponCombo.addActionListener(this);
        gdBtn_1.addActionListener(this);
        gdBtn_2.addActionListener(this);
        gdBtn_3.addActionListener(this);
        gdBtn_4.addActionListener(this);
        gdBtn_5.addActionListener(this);
        gdBtn_6.addActionListener(this);
        storeBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enhanceBtn) {
            if (getWeaponCombObject() != null) {
                if (isProtectEnable()) {
                    controller.setprotect(true);
                    logger.info("protect = true");
                    if (controller.protectStoneChecker(1)) {
                        checkFailure();
                    } else {
                        showDialog("沒有保護石");
                    }
                } else {
                    logger.info("protect = false");
                    controller.setprotect(false);
                    checkFailure();
                }
            } else {
                showDialog("Please select weapon");
            }
        } else if (e.getSource() == dailyBtn) {
            if (model.isDailyfinish()) {
                controller.getdailyBouns();
                enableDailybtn(false);
                controller.dailystart();
            }
        } else if (e.getSource() == alchemyBtn) {
            if (rightSet()) {
                controller.alchemyMinus(getBananaCombo(), getAppleCombo(), getOrangeCombo(), getMelonCombo());
                controller.alchemyStart();
                controller.alchemyThread(alchemyBar);
                alchemyBar.setString("煉金中...");
            }
        } else if (e.getSource() == element1) {
            if (!controller.bananChecker(getBananaCombo())) {
                element1.setSelectedItem(null);
                showDialog("Can't insert more than u have");
            }
        } else if (e.getSource() == element2) {
            if (!controller.appleChecker(getAppleCombo())) {
                element2.setSelectedItem(null);
                showDialog("Can't insert more than u have");
            }
        } else if (e.getSource() == element3) {
            if (!controller.orangeChecker(getOrangeCombo())) {
                element3.setSelectedItem(null);
                showDialog("Can't insert more than u have");
            }
        } else if (e.getSource() == element4) {
            if (!controller.melonChecker(getMelonCombo())) {
                element4.setSelectedItem(null);
                showDialog("Can't insert more than u have");
            }
        } else if (e.getSource() == weaponCombo) {
            tempItem = (Weapon) weaponCombo.getSelectedItem();
            model.setCurrentLevel(tempItem.getLevel());
            String level = tempItem.getLeveltext();
            weaponLevelLabel.setText(level);
            controller.initEnhanceImg(tempItem);
        } else if (e.getSource() == gdBtn_1) {
            controller.eventGdBtn(gdBtn_1, gdCb_1);
        } else if (e.getSource() == gdBtn_2) {
            controller.eventGdBtn(gdBtn_2, gdCb_2);
        } else if (e.getSource() == gdBtn_3) {
            controller.eventGdBtn(gdBtn_3, gdCb_3);
        } else if (e.getSource() == gdBtn_4) {
            controller.eventGdBtn(gdBtn_4, gdCb_4);
        } else if (e.getSource() == gdBtn_5) {
            controller.eventGdBtn(gdBtn_5, gdCb_5);
        } else if (e.getSource() == gdBtn_6) {
            controller.eventGdBtn(gdBtn_6, gdCb_6);
        } else if (e.getSource() == storeBtn) {
            controller.openStore();
        }
    }

    public void enhance() {
        if (controller.coinchecker(1000)) {
            if (controller.stoneChecker(1)) {
                if (protectcheck.isSelected()) {
                    controller.minusProtectStone(1);
                }
                controller.minusCoin(1000);
                controller.minusStone(1);
                controller.enhanceStart();
                controller.enhanceThread(enhancementBar);
                enhancementBar.setString("強化中...");
            } else {
                showDialog("No stone!");
            }
        } else {
            showDialog("No coin!");
        }
    }

    public void checkFailure() {
        if (isFailureEnabel()) {
            controller.setNotFailure(true);
            enhance();
        } else {
            controller.setNotFailure(false);
            enhance();
        }
    }

    @Override
    public void updateCoin() {
        coinLabel.setText(String.valueOf(model.getCoin()));
    }

    @Override
    public void updateStoneClock() {
        stoneClockLabel.setText(model.getStoneMinute() + ":" + model.getStoneSecond());
    }

    @Override
    public void updateDailyClock() {
        if (!model.isDailyfinish()) {
            dailyBtn.setText(model.getDailyMinute() + ":" + model.getDailySecond());
            enableDailybtn(false);
        } else {
            dailyBtn.setText("領獎勵");
            enableDailybtn(true);
        }
    }

    @Override
    public void updateClockGdBtn_1() {
        if (!model.isfinish_Gd1()) {
            gdBtn_1.setText(model.getMinute_gd1() + ":" + model.getSecond_gd1());
            enableGdBtn_1(false);
        } else {
            gdBtn_1.setText("收割");
            enableGdBtn_1(true);
        }
    }

    @Override
    public void updateClockGdBtn_2() {
        if (!model.isfinish_Gd2()) {
            gdBtn_2.setText(model.getMinute_gd2() + ":" + model.getSecond_gd2());
            enableGdBtn_2(false);
        } else {
            gdBtn_2.setText("收割");
            enableGdBtn_2(true);
        }
    }

    @Override
    public void updateClockGdBtn_3() {
        if (!model.isfinish_Gd3()) {
            gdBtn_3.setText(model.getMinute_gd3() + ":" + model.getSecond_gd3());
            enableGdBtn_3(false);
        } else {
            gdBtn_3.setText("收割");
            enableGdBtn_3(true);
        }
    }

    @Override
    public void updateClockGdBtn_4() {
        if (!model.isfinish_Gd4()) {
            gdBtn_4.setText(model.getMinute_gd4() + ":" + model.getSecond_gd4());
            enableGdBtn_4(false);
        } else {
            gdBtn_4.setText("收割");
            enableGdBtn_4(true);
        }
    }

    @Override
    public void updateClockGdBtn_5() {
        if (model.getFarm1Sold()) {
            if (!model.isfinish_Gd5()) {
                gdBtn_5.setText(model.getMinute_gd5() + ":" + model.getSecond_gd5());
                enableGdBtn(false, gdBtn_5);
            } else {
                gdBtn_5.setText("收割");
                enableGdBtn(true, gdBtn_5);
            }
        }
    }

    @Override
    public void updateClockGdBtn_6() {
        if (model.getFarm2Sold()) {
            if (!model.isfinish_Gd6()) {
                gdBtn_6.setText(model.getMinute_gd6() + ":" + model.getSecond_gd6());
                enableGdBtn(false, gdBtn_6);
            } else {
                gdBtn_6.setText("收割");
                enableGdBtn(true, gdBtn_6);
            }
        }
    }

    @Override
    public void updateStone() {
        stoneNumLabel.setText(String.valueOf(model.getStone()));
    }

    @Override
    public void updateExtremeStone() {
        extremeNumLabel.setText(String.valueOf(model.getExtremeStone()));
    }

    @Override
    public void updateProtectStone() {
        protectNumLabel.setText(String.valueOf(model.getProtectStone()));
    }

    @Override
    public void updateFailure() {
        failureTimesLabel.setText(String.valueOf(model.getFailureTimes()));
    }

    @Override
    public void enhanceProgressEnd() {
        tempItem = (Weapon) weaponCombo.getSelectedItem();
        if (controller.isEnhanceRunning() == false) {
            controller.enhanceEnd(); // UI contorll
            tempItem.setlevel(model.getCurrentLevel());
            String level = tempItem.getLeveltext();
            weaponLevelLabel.setText(level);
            String name = tempItem.getName();
            switch (name) {
                case "Sword":
                    model.setSwordLevel(model.getCurrentLevel());

                    break;
                case "Bow":
                    model.setBowLevel(model.getCurrentLevel());
                    break;
            }
        }
    }

    // Alchemy
    @Override
    public void alchemyProgressEnd() {
        if (controller.isAlchemyRunning() == false) {
            controller.alchemyEnd();
        }
    }

    @Override
    public void updateBanana(int num) {
        bananaNum.setText(String.valueOf(num));
    }

    @Override
    public void updateApple() {
        appleNum.setText(String.valueOf(model.getApple()));
    }

    @Override
    public void updateOrange() {
        orangeNum.setText(String.valueOf(model.getOrange()));
    }

    @Override
    public void updateMelon() {
        melonNum.setText(String.valueOf(model.getMelon()));
    }

    @Override
    public void updateDregs() {
        dregsNum.setText(String.valueOf(model.getDredgs()));
    }

    public void enableEnhanceBtn(boolean b) {
        enhanceBtn.setEnabled(b);
    }

    public void visibleProgressbar(boolean b) {
        enhancementBar.setVisible(b);
    }

    public void enableAlchemyBtn(boolean b) {
        alchemyBtn.setEnabled(b);
    }

    public void visibleAlchemybar(boolean b) {
        alchemyBar.setVisible(b);
    }

    public void enableDailybtn(boolean b) {
        dailyBtn.setEnabled(b);
    }

    public void enableGdBtn_1(boolean b) {
        gdBtn_1.setEnabled(b);
    }

    public void enableGdBtn_2(boolean b) {
        gdBtn_2.setEnabled(b);
    }

    public void enableGdBtn_3(boolean b) {
        gdBtn_3.setEnabled(b);
    }

    public void enableGdBtn_4(boolean b) {
        gdBtn_4.setEnabled(b);
    }

    public void enableGdBtn_5(boolean b) {
        gdBtn_5.setEnabled(b);
    }

    public void enableGdBtn_6(boolean b) {
        gdBtn_6.setEnabled(b);
    }

    public void enableGdBtn(boolean b, JButton btn) {
        btn.setEnabled(b);
    }

    public void enableGdCb_1(boolean b) {
        gdCb_1.setEnabled(b);
    }

    public void enableGdCb_2(boolean b) {
        gdCb_2.setEnabled(b);
    }

    public void enableGdCb_3(boolean b) {
        gdCb_3.setEnabled(b);
    }

    public void enableGdCb_4(boolean b) {
        gdCb_4.setEnabled(b);
    }

    public void enableGdCb_5(boolean b) {
        gdCb_5.setEnabled(b);
    }

    public void enableGdCb_6(boolean b) {
        gdCb_6.setEnabled(b);
    }

    public void enableGdCb(boolean b, GardenItemComboBox gdCb) {
        gdCb.setEnabled(b);
    }

    public void enableProtectCheck(boolean b) {
        protectcheck.setEnabled(b);
    }

    public void enableFailureCheck(boolean b) {
        failcheck.setEnabled(b);
    }

    public void setdescription(String msg, Color color) {
        descriptionLabel.setText(msg);
        descriptionLabel.setForeground(color);
    }

    public void setElementComboNull() {
        element1.setSelectedItem(null);
        element2.setSelectedItem(null);
        element3.setSelectedItem(null);
        element4.setSelectedItem(null);
    }

    public int getBananaCombo() {
        if (element1.getSelectedItem() != null) {
            return (int) element1.getSelectedItem();
        }
        return 0;
    }

    public int getAppleCombo() {
        if (element2.getSelectedItem() != null) {
            return (int) element2.getSelectedItem();
        }
        return 0;
    }

    public int getOrangeCombo() {
        if (element3.getSelectedItem() != null) {
            return (int) element3.getSelectedItem();
        }
        return 0;
    }

    public int getMelonCombo() {
        if (element4.getSelectedItem() != null) {
            return (int) element4.getSelectedItem();
        }
        return 0;
    }

    public void showDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public boolean rightSet() {
        if (element1.getSelectedItem() != null) {
            if (element2.getSelectedItem() != null) {
                if (element3.getSelectedItem() != null) {
                    if (element4.getSelectedItem() != null) {
                        return true;
                    } else {
                        element4.setOpaque(true);
                        element4.setBackground(Color.RED);
                    }
                } else {
                    element3.setOpaque(true);
                    element3.setBackground(Color.RED);
                }
            } else {
                element2.setOpaque(true);
                element2.setBackground(Color.RED);
            }
        } else {
            element1.setOpaque(true);
            element1.setBackground(Color.RED);
        }
        showDialog("Please insert element number for Alchemy");
        return false;
    }

    public int getWeaponLabel() {
        return ((Weapon) weaponCombo.getSelectedItem()).getLevel();
    }

    public Object getWeaponCombObject() {
        return weaponCombo.getSelectedItem();
    }

    public void setFailureLabelText(int fail) {
        failureTimesLabel.setText(String.valueOf(fail));
    }

    public void setEnhanceLabelImg(Icon icon) {
        enhanceImgLabel.setIcon(icon);
    }

    public boolean isProtectEnable() {
        if (protectcheck.isSelected()) {
            return true;
        }
        return false;
    }

    public boolean isFailureEnabel() {
        if (failcheck.isSelected()) {
            return true;
        }
        return false;
    }

    public boolean isComboGd1Enable() {
        return gdCb_1.isEnabled();
    }

    public void alchemyEndCheck() {
        int i = model.getBanana() - (int) element1.getSelectedItem();
        if (i < 0) {
            System.out.println(i);
            element1.setSelectedItem(null);
        }
        int x = model.getApple() - (int) element2.getSelectedItem();
        if (x < 0) {
            System.out.println(x);
            element2.setSelectedItem(null);
        }
        int y = model.getOrange() - (int) element3.getSelectedItem();
        if (y < 0) {
            System.out.println(y);
            element3.setSelectedItem(null);
        }
        int z = model.getMelon() - (int) element4.getSelectedItem();
        if (z < 0) {
            System.out.println(z);
            element4.setSelectedItem(null);
        }
    }

    public void enableGdCb(GardenItemComboBox gdCb, boolean b) {
        gdCb.setEnabled(b);
    }

}

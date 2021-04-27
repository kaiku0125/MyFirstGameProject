package GameProject.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.Icon;
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

import GameProject.libs.Weapon;
import GameProject.libs.WeaponComboBox;

public class ViewMain implements ActionListener, CoinObserver, ClockObserver, Callback {
    private Logger logger = Logger.getLogger(ViewMain.class.getName());
    ControllerMainInterface controller;
    ModelInterface model;

    JFrame mainFrame;
    JPanel mainPanel, leftPanel, rightPanel, downPanel, middlePanel;
    JPanel elementPanel, elementNumPanel;

    JLabel coinLabel, stoneClockLabel, descriptionLabel;
    JLabel stoneLabel, stoneNumLabel, extremeLabel, extremeNumLabel;
    JLabel protectLabel, protectNumLabel, failureTimesLabel;
    JLabel alchemyImgLabel, enhanceImgLabel, weaponLevelLabel;
    JLabel bananaLabel, appleLabel, orangeLabel, melonLabel;
    JLabel bananaNum, appleNum, orangeNum, melonNum;
    JCheckBox failcheck, protectcheck;
    JComboBox<Integer> element1, element2, element3, element4;
    JButton enhanceBtn, dailyBtn, alchemyBtn;
    WeaponComboBox weaponCombo;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem saveItem;
    JProgressBar enhancementBar, alchemyBar;
    Weapon tempItem;

    public ViewMain(ControllerMainInterface controller, ModelInterface model) {
        this.controller = controller;
        this.model = model;

        model.registerCoinObserver(this);
        model.registerClockObserver(this);
        model.regStoneCallback(this);
    }

    public void createMainView() {
        mainFrame = new JFrame("KaiKuKu's Game");
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.getContentPane().setBackground(Color.BLACK);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);

        mainPanel = new JPanel();
        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setBounds(0, 0, 200, 500);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        middlePanel.setBackground(Color.RED);
        middlePanel.setBounds(200, 0, 300, 320);

        downPanel = new JPanel();
        // downPanel.setLayout(null);
        downPanel.setBackground(Color.BLUE);
        downPanel.setOpaque(true);
        downPanel.setBounds(200, 320, 300, 30);

        elementPanel = new JPanel();
        elementPanel.setBackground(Color.BLUE);
        elementPanel.setOpaque(true);
        elementPanel.setBounds(200, 350, 300, 30);

        elementNumPanel = new JPanel();
        elementNumPanel.setBackground(Color.BLUE);
        elementNumPanel.setOpaque(true);
        elementNumPanel.setBounds(200, 380, 300, 30);

        rightPanel = new JPanel();
        rightPanel.setBounds(500, 0, 100, 500);
        // component.............//
        weaponCombo = new WeaponComboBox(model);
        weaponCombo.setBounds(0, 0, 200, 50);
        weaponCombo.setSelectedItem(null);

        failcheck = new JCheckBox();
        failcheck.setBounds(145, 0, 20, 20);
        failcheck.setToolTipText("使用累積失敗疊層，增加下次強化成功機率");

        failureTimesLabel = new JLabel("0", SwingConstants.CENTER);
        failureTimesLabel.setBounds(165, 0, 35, 20);
        failureTimesLabel.setOpaque(true);
        failureTimesLabel.setBackground(Color.white);

        weaponLevelLabel = new JLabel("..", SwingConstants.CENTER);
        weaponLevelLabel.setBounds(75, 140, 50, 20);
        weaponLevelLabel.setOpaque(true);
        weaponLevelLabel.setBackground(Color.WHITE);

        enhanceImgLabel = new JLabel();
        enhanceImgLabel.setOpaque(true);
        enhanceImgLabel.setBackground(Color.ORANGE);
        enhanceImgLabel.setBounds(0, 0, 200, 200);

        JLayeredPane pane = new JLayeredPane();
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

        extremeLabel = new JLabel("凝縮的強化石", SwingConstants.CENTER);
        extremeLabel.setBackground(Color.lightGray);
        extremeLabel.setOpaque(true);
        extremeLabel.setBounds(50, 270, 80, 20);

        protectcheck = new JCheckBox();
        protectcheck.setBounds(130, 270, 20, 20);
        protectcheck.setOpaque(true);
        protectcheck.setBackground(Color.orange);
        protectcheck.setToolTipText("下次強化將注入保護石");

        protectLabel = new JLabel("保護石");
        protectLabel.setBackground(Color.ORANGE);
        protectLabel.setOpaque(true);
        protectLabel.setBounds(150, 270, 50, 20);

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
        // enhanceBtn.setBounds(0, 0, 150, 40);

        // JLayeredPane pane1 = new JLayeredPane();
        // pane1.setBounds(50, 310, 150, 40);

        // pane1.add(protectcheck);
        // pane1.add(enhanceBtn);

        descriptionLabel = new JLabel("...");
        descriptionLabel.setVerticalAlignment(JLabel.NORTH);
        descriptionLabel.setHorizontalAlignment(JLabel.LEFT);
        descriptionLabel.setBackground(Color.GRAY);
        descriptionLabel.setOpaque(true);
        descriptionLabel.setBounds(0, 350, 200, 150);

        alchemyImgLabel = new JLabel();
        alchemyImgLabel.setBackground(Color.PINK);
        alchemyImgLabel.setOpaque(true);
        alchemyImgLabel.setBounds(0, 0, 300, 300);

        alchemyBar = new JProgressBar(0, 100);
        alchemyBar.setBounds(0, 300, 300, 20);
        alchemyBar.setStringPainted(true);
        alchemyBar.setString("");
        alchemyBar.setVisible(false);

        Integer[] allelement = new Integer[5];
        for (int i = 0; i < 5; i++) {
            allelement[i] = i;
        }
        // String[] element77 = { "0", "1", "2", "3", "4" };
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
        bananaLabel.setBackground(Color.WHITE);
        bananaLabel.setOpaque(true);
        bananaLabel.setPreferredSize(new Dimension(60, 20));

        appleLabel = new JLabel("Apple", SwingConstants.CENTER);
        appleLabel.setBackground(Color.WHITE);
        appleLabel.setOpaque(true);
        appleLabel.setPreferredSize(new Dimension(60, 20));

        orangeLabel = new JLabel("Orange", SwingConstants.CENTER);
        orangeLabel.setBackground(Color.WHITE);
        orangeLabel.setOpaque(true);
        orangeLabel.setPreferredSize(new Dimension(60, 20));

        melonLabel = new JLabel("Melon", SwingConstants.CENTER);
        melonLabel.setBackground(Color.WHITE);
        melonLabel.setOpaque(true);
        melonLabel.setPreferredSize(new Dimension(60, 20));

        bananaNum = new JLabel("0", SwingConstants.CENTER);
        bananaNum.setBackground(Color.WHITE);
        bananaNum.setOpaque(true);
        bananaNum.setPreferredSize(new Dimension(60, 20));

        appleNum = new JLabel("0", SwingConstants.CENTER);
        appleNum.setBackground(Color.WHITE);
        appleNum.setOpaque(true);
        appleNum.setPreferredSize(new Dimension(60, 20));

        orangeNum = new JLabel("0", SwingConstants.CENTER);
        orangeNum.setBackground(Color.WHITE);
        orangeNum.setOpaque(true);
        orangeNum.setPreferredSize(new Dimension(60, 20));

        melonNum = new JLabel("0", SwingConstants.CENTER);
        melonNum.setBackground(Color.WHITE);
        melonNum.setOpaque(true);
        melonNum.setPreferredSize(new Dimension(60, 20));

        coinLabel = new JLabel("0", SwingConstants.CENTER);
        coinLabel.setBackground(Color.YELLOW);
        coinLabel.setOpaque(true);
        coinLabel.setPreferredSize(new Dimension(100, 20));

        dailyBtn = new JButton("00:00");
        dailyBtn.setPreferredSize(new Dimension(100, 20));

        alchemyBtn = new JButton("alchemy");
        // panel add component...................//
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
        // leftPanel.add(pane1);
        leftPanel.add(enhanceBtn);
        leftPanel.add(protectcheck);
        leftPanel.add(descriptionLabel);

        middlePanel.add(alchemyImgLabel);
        middlePanel.add(alchemyBar);

        downPanel.add(element1);
        downPanel.add(element2);
        downPanel.add(element3);
        downPanel.add(element4);

        elementPanel.add(bananaLabel);
        elementPanel.add(appleLabel);
        elementPanel.add(orangeLabel);
        elementPanel.add(melonLabel);

        elementNumPanel.add(bananaNum);
        elementNumPanel.add(appleNum);
        elementNumPanel.add(orangeNum);
        elementNumPanel.add(melonNum);

        rightPanel.add(coinLabel);
        rightPanel.add(dailyBtn);
        rightPanel.add(alchemyBtn);
        // mainFrame add panel................//
        mainFrame.add(leftPanel);
        mainFrame.add(rightPanel);
        mainFrame.add(middlePanel);
        mainFrame.add(downPanel);
        mainFrame.add(elementPanel);
        mainFrame.add(elementNumPanel);
        mainFrame.setSize(700, 550);
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
            dailyBtn.setText("領取獎勵");
            enableDailybtn(true);
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
            controller.enhanceEnd();
            String name = tempItem.getName();
            switch (name) {
            case "Sword":
                model.setSwordLevel(model.getCurrentLevel());
                break;
            case "Bow":
                model.setBowLevel(model.getCurrentLevel());
                break;
            }
            tempItem.setlevel(model.getCurrentLevel());
            String level = tempItem.getLeveltext();
            weaponLevelLabel.setText(level);
        }
    }

    @Override
    public void alchemyProgressEnd() {
        if (controller.isAlchemyRunning() == false) {
            controller.alchemyEnd();
        }
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

    public void enableProtectCheck(boolean b) {
        protectcheck.setEnabled(b);
    }

    public void enableFailureCheck(boolean b) {
        failcheck.setEnabled(b);
    }

    public void setdescription(String msg) {
        descriptionLabel.setText(msg);
    }

    public void setElementComboNull() {
        element1.setSelectedItem(null);
        element2.setSelectedItem(null);
        element3.setSelectedItem(null);
        element4.setSelectedItem(null);
    }

    public int getBananaCombo() {
        return (int) element1.getSelectedItem();
    }

    public int getAppleCombo() {
        return (int) element2.getSelectedItem();
    }

    public int getOrangeCombo() {
        return (int) element3.getSelectedItem();
    }

    public int getMelonCombo() {
        return (int) element4.getSelectedItem();
    }

    @Override
    public void updateBanana(int num) {
        bananaNum.setText(String.valueOf(num));

    }

    @Override
    public void updateApple() {
        appleNum.setText(String.valueOf(model.getBanana()));

    }

    @Override
    public void updateOrange() {
        orangeNum.setText(String.valueOf(model.getOrange()));

    }

    @Override
    public void updateMelon() {
        melonNum.setText(String.valueOf(model.getMelon()));

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

}

package GameProject.Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.logging.Logger;

public class ViewStore extends JFrame implements ActionListener {
    private static Logger logger = Logger.getLogger(ViewStore.class.getName());
    ControllerMainInterface controller;
    ModelInterface model;
    JFrame storeFrame;
    JPanel mainPanel;
    JButton backBtn;
    public static volatile ViewStore instance = null;

    public ViewStore(ControllerMainInterface controller, ModelInterface model) {
        this.controller = controller;
        this.model = model;
        createStoreView();
        createControls();
    }

    public void createStoreView() {
        storeFrame = new JFrame("Store");
        storeFrame.setSize(400, 300);
        storeFrame.setResizable(false);
        storeFrame.setLocationRelativeTo(null);
        storeFrame.setLocation(new Point(ViewMain.WIDTH + 600, ViewMain.HEIGHT));
        storeFrame.setLayout(null);
        storeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setBounds(0, 0, 400, 300);

        backBtn = new JButton("close");
        backBtn.setPreferredSize(new Dimension(50, 20));

        mainPanel.add(backBtn);

        storeFrame.getContentPane().add(mainPanel);

        storeFrame.setVisible(false);
    }

    public void createControls() {
        backBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            controller.closeStore();
        }

    }

    public void enableStoreFrame(Boolean b) {
        storeFrame.setVisible(b);

    }

}

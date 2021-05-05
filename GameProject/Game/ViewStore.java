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

    public ViewStore(ControllerMainInterface controller, ModelInterface model) {
        this.controller = controller;
        this.model = model;
    }

    public void createStoreView() {
        storeFrame = new JFrame("Store");
        storeFrame.setResizable(false);
        storeFrame.setLocationRelativeTo(null);
        storeFrame.setLayout(null);
        storeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setBounds(0, 0, 400, 300);

        backBtn = new JButton("close");
        backBtn.setPreferredSize(new Dimension(50, 20));

        mainPanel.add(backBtn);

        storeFrame.getContentPane().add(mainPanel);

        storeFrame.setSize(400, 300);
        storeFrame.setVisible(true);
    }

    public void createControls() {
        backBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            // WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
            // Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
            controller.closeStore();
            System.out.println("...............");
        }

    }

    public void enableStoreFrame(Boolean b) {
        storeFrame.setVisible(b);

    }

}

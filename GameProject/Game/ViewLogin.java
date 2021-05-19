package GameProject.Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.logging.Logger;

public class ViewLogin extends JFrame implements KeyListener, ActionListener {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(ViewLogin.class.getName());
    ControllerMainInterface controller;
    ModelInterface model;
    JFrame loginFrame;
    JPanel mainPanel, downPanel;
    JLabel IDlabel, welcomeLabel;
    JTextField IDtextField;
    JButton regBtn;
    String password;
    String welcome = "Welcome to Kaikuku's Game!";
    static String regID;

    public ViewLogin(ControllerMainInterface controller, ModelInterface model) {
        this.controller = controller;
        this.model = model;
    }

    public void createLoginView() {
        loginFrame = new JFrame("login");
        loginFrame.setResizable(false);
        loginFrame.setSize(240, 150);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(null);
        loginFrame.getContentPane().setBackground(Color.black);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setBounds(0, 40, 210, 30);
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.BLACK);

        downPanel = new JPanel();
        downPanel.setBounds(0, 70, 240, 50);
        downPanel.setOpaque(true);
        downPanel.setBackground(Color.black);

        welcomeLabel = new JLabel(welcome, SwingConstants.CENTER);
        welcomeLabel.setBounds(0, 0, 230, 40);
        welcomeLabel.setOpaque(true);
        welcomeLabel.setBackground(Color.black);
        welcomeLabel.setForeground(Color.red);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 15));

        IDlabel = new JLabel("UserID");
        IDlabel.setOpaque(true);
        IDlabel.setBackground(Color.black);
        IDlabel.setForeground(Color.white);
        IDlabel.setPreferredSize(new Dimension(50, 20));

        IDtextField = new JTextField();
        IDtextField.setPreferredSize(new Dimension(110, 20));
        IDtextField.addKeyListener(this);

        regBtn = new JButton("註冊");
        regBtn.setBounds(90, 85, 60, 20);

        mainPanel.add(IDlabel);
        mainPanel.add(IDtextField);
        downPanel.add(regBtn);

        loginFrame.getContentPane().add(welcomeLabel);
        loginFrame.getContentPane().add(mainPanel);
        loginFrame.add(downPanel);
        loginFrame.setVisible(true);
        createControls();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == regBtn) {
            regID = IDtextField.getText();
            model.regID();
        }
    }

    public void createControls() {
        regBtn.addActionListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            logger.info("User press enter");
            model.checkID();
            if ((IDtextField.getText().equals("Master"))) {
                ControllerMainInterface master = new MasterController(model);
                enableLoginFrame(false);
                master.login();
            } else {
                if (IDtextField.getText().equals(model.getpassword())) {
                    enableLoginFrame(false);
                    controller.login();
                } else {
                    JOptionPane.showMessageDialog(null, "Uncorrected UserID", "Login error", 0);
                }
            }
        }
    }

    public void enableLoginFrame(Boolean b) {
        IDtextField.setText("");
        loginFrame.setVisible(b);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}

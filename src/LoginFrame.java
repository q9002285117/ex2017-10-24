import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends JFrame {
    private JLabel jLabelID=new JLabel("ID:");
    private JLabel jLabelPS=new JLabel("PassWord:");
    private JTextField jTextFieldID=new JTextField();
    //    private JTextField jTextFieldPS=new JTextField();
    private JPasswordField jPsField=new JPasswordField();
    private JButton jButtonEX=new JButton("Exit");
    private JButton jButtonLG=new JButton("Login");
    private Container cp;
    private Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
    private int frmW=300,frmH=150,screenW,screenH;

    public LoginFrame(){
        init();
    }
    private void init(){
        screenW=dim.width;
        screenH=dim.height;
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
        this.setBounds(screenW/2-frmW/2,screenH/2-frmH/2,frmW,frmH);
        cp=this.getContentPane();
        cp.setLayout(new GridLayout(3,2,3,3));
        cp.add(jLabelID);
        cp.add(jTextFieldID);
        cp.add(jLabelPS);
        cp.add(jPsField);
        cp.add(jButtonEX);
        cp.add(jButtonLG);
        jLabelID.setHorizontalAlignment(JLabel.RIGHT);
        jLabelPS.setHorizontalAlignment(JLabel.RIGHT);

        jButtonEX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jButtonLG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jTextFieldID.getText().equals("h304")&&(new String(jPsField.getPassword()).equals("23323456"))){

                    LoginFrame.this.setVisible(false);
                    MainFrame mainFrame=new MainFrame(LoginFrame.this);
                    mainFrame.setVisible(true);

                }else {
                    JOptionPane.showMessageDialog(null,"ERROR");
                }
            }
        });
    }
}
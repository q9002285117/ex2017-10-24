import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame {
    private int screenW= Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH= Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW=500,frmH=450;

    private JMenuBar jmb=new JMenuBar();
    private JMenu jmF=new JMenu("File");
    private JMenu jmS=new JMenu("Set");
    private JMenu jmG=new JMenu("Game");
    private JMenu jmA=new JMenu("About");

    private JMenuItem jMenuItemFExit=new JMenuItem("Exit");
    private JMenuItem jMenuItemGLoto=new JMenuItem("Loto");
    private JDesktopPane jdp=new JDesktopPane();
    private JInternalFrame jInternalFrame=new JInternalFrame();

    private Container jtfCP;
    private JPanel jpn=new JPanel(new GridLayout(1,6,5,5));
    private JPanel jpn1=new JPanel(new GridLayout(1,2,5,5));
    private JLabel jlb[]=new JLabel[6];
    private JButton jbtnClose=new JButton("Close");
    private JButton jbtnRegen=new JButton("Generate");
    private int data[]=new int[6];
    private Random rnd=new Random(System.currentTimeMillis());

    private LoginFrame loginFrame;


public MainFrame(LoginFrame login){
    loginFrame=login;
    init();
}
private void init(){
    this.setBounds(screenW/2-frmW/2,screenH/2-frmH/2,frmW,frmH);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setJMenuBar(jmb);
    this.setContentPane(jdp);
    jmb.add(jmF);
    jmb.add(jmS);
    jmb.add(jmG);
    jmb.add(jmA);
    jmF.add(jMenuItemFExit);
    jmG.add(jMenuItemGLoto);
    this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            loginFrame.setVisible(true);
        }
    });
    jInternalFrame.setBounds(0,0,200,80);
    jtfCP=jInternalFrame.getContentPane();
    jtfCP.setLayout(new BorderLayout(5,5));
    jtfCP.add(jpn);
    jMenuItemFExit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });
    jMenuItemGLoto.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            jdp.add(jInternalFrame);
            jInternalFrame.setVisible(true);
        }
    });
    jbtnRegen.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
    jMenuItemFExit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
}
private void lotoGrnerate(){
    int i=0;
    while (i<6){
        data[i]=rnd.nextInt(42)+1;
        int j=0;

    }
}
public void reset(){

}

}

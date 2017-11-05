import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class MainFrame extends JFrame {
    private int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 500, frmH = 450;

    private JMenuBar jmb = new JMenuBar();
    private JMenu jmF = new JMenu("File");
    private JMenu jmS = new JMenu("Set");
    private JMenu jmG = new JMenu("Game");
    private JMenu jmA = new JMenu("About");

    private JMenuItem jMenuItemFExit = new JMenuItem("Exit");
    private JMenuItem jMenuItemGLoto = new JMenuItem("Loto");
    private JMenuItem jMenuItemGFont = new JMenuItem("Font");

    private JPanel jpanel1 = new JPanel(new GridLayout(2, 3, 5, 5));
    private JLabel jlbFontSize = new JLabel("size");
    private JLabel jlbFontFamily = new JLabel("family");
    private JLabel jlbFontStyle = new JLabel("style");
    private TextField jtfFamily = new TextField("Time New Roman");
    private TextField jtfSize = new TextField("24");
    private String[] options = {"PLAIN", "BOLD", "ITALIC", "BOLD+ITALIC"};
    private JComboBox jcbFStyle = new JComboBox(options);

    private JDesktopPane jdp = new JDesktopPane();
    private JInternalFrame jInternalFrame = new JInternalFrame();


    private Container jtfCP;
    private JPanel jpn = new JPanel(new GridLayout(1, 6, 5, 5));
    private JPanel jpn1 = new JPanel(new GridLayout(1, 2, 5, 5));
    private JLabel jlb[] = new JLabel[6];
    private JButton jbtnClose = new JButton("Close");
    private JButton jbtnRegen = new JButton("Generate");
    private int data[] = new int[6];
    private Random rnd = new Random(System.currentTimeMillis());

    private LoginFrame loginFrame = new LoginFrame();

    private JInternalFrame jIFAddCategory = new JInternalFrame();
    private Container jIFAddCategoryCp;
    private JMenuBar jIFAddCategoryJmb = new JMenuBar();
    private JMenu jmData = new JMenu("Data");
    private JMenuItem jmiDataLoad = new JMenuItem("Load");
    private JMenuItem jmiDataNew = new JMenuItem("New");
    private JMenuItem jmiDataClose = new JMenuItem("Close");
    private JMenuItem jmiAddCategory = new JMenuItem("Category");
    private JScrollPane jsp1 = new JScrollPane();
    private JFileChooser jfc=new JFileChooser();


    public MainFrame(LoginFrame log) {
        loginFrame = log;
        init();
    }

    private void init() {
        this.setBounds(screenW / 2 - frmW / 2, screenH / 2 - frmH / 2, frmW, frmH);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setJMenuBar(jmb);
        this.setContentPane(jdp);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                MainFrame.this.setVisible(false);
                loginFrame.setVisible(true);
                loginFrame.reset();
            }
        });
        jmb.add(jmF);
        jmb.add(jmS);
        jmb.add(jmG);
        jmb.add(jmA);

        jmF.add(jMenuItemFExit);
        jmG.add(jMenuItemGLoto);
        jmS.add(jMenuItemGFont);

        jpanel1.add(jlbFontSize);
        jpanel1.add(jlbFontFamily);
        jpanel1.add(jlbFontStyle);
        jpanel1.add(jtfFamily);
        jpanel1.add(jtfSize);
        jpanel1.add(jcbFStyle);

        for(int i=0;i<6;i++){
            jlb[i]=new JLabel();
            jlb[i].setOpaque(true);
            jlb[i].setBackground(Color.PINK);
            jpn.add(jlb[i]);
        }


        jInternalFrame.setBounds(0, 0, 200, 80);
        jtfCP = jInternalFrame.getContentPane();
        jtfCP.setLayout(new BorderLayout(5, 5));
        jtfCP.add(jpn, BorderLayout.CENTER);
        jtfCP.add(jpn1, BorderLayout.SOUTH);
        jpn1.add(jbtnClose);
        jpn1.add(jbtnRegen);
        jMenuItemFExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(true);
                dispose();
                loginFrame.reset();
            }
        });
        jMenuItemGLoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jInternalFrame);
                jInternalFrame.setVisible(true);
                lotoGenerate();
            }
        });
        jMenuItemGFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(MainFrame.this,
                        jpanel1,
                        "Font setting",
                        JOptionPane.OK_CANCEL_OPTION);
                int fontStyle = 0;
                switch (jcbFStyle.getSelectedIndex()) {
                    case 0:
                        fontStyle = Font.PLAIN;
                        break;
                    case 1:
                        fontStyle = Font.BOLD;
                        break;
                    case 2:
                        fontStyle = Font.ITALIC;
                        break;
                    case 3:
                        fontStyle = Font.BOLD + Font.ITALIC;
                        break;
                }
                if (result == JOptionPane.OK_OPTION) {
                    UIManager.put("Menu.font", new Font(jtfFamily.getText(), fontStyle, Integer.parseInt(jtfSize.getText())));
                    UIManager.put("MenuItem.font",new Font(jtfFamily.getText(),fontStyle,Integer.parseInt(jtfSize.getText())));
                }
            }
        });
        jbtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jInternalFrame.dispose();
            }
        });
        jbtnRegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lotoGenerate();
            }
        });
        jmiAddCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jIFAddCategory.setVisible(true);
                jIFAddCategoryCp = jIFAddCategory.getContentPane();
                jIFAddCategoryCp.setLayout(new BorderLayout(5, 5));
                jIFAddCategoryCp.add(jsp1, BorderLayout.CENTER);
                jIFAddCategory.setJMenuBar(jIFAddCategoryJmb);
                jIFAddCategory.setBounds(0, 0, 500, 500);
                jIFAddCategoryJmb.add(jmData);
                jmData.add(jmiDataLoad);
                jmData.add(jmiDataNew);
                jmData.add(jmiDataClose);
                jdp.add(jIFAddCategory);
            }
        });
        jmiDataLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
//                try {
                    File infile = jfc.getSelectedFile();
//                    BufferedReader br=new BufferedReader(new FileReader(infile));
                    System.out.println("FileName:" + infile.getName());
                    String str = " ";
//                    while (str==br.readLine()!=null){
//                        jta.append(str+"\n");
//                    }
                }
            }
//            }
        });
        jMenuItemFExit.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuItemGLoto.setAccelerator(KeyStroke.getKeyStroke('L',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainFrame.this.setVisible(false);
                loginFrame.setVisible(true);
            }
        });
    }

    private void lotoGenerate() {
        int i = 0;
        while (i < 6) {
            data[i] = rnd.nextInt(42) + 1;
            int j = 0;
            boolean flag = true;
            while (j < i && flag) {
                if (data[i] == data[j]) {
                    flag = false;
                }
                j++;
            }
            if (flag) {
                jlb[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }
}

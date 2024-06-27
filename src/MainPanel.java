import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame {
    private JPanel mainPanel;
    private JButton zarządzajWewnątrzButton;
    private JButton zarządzajZewnątrzButton;
    private JLabel welcomeLabel;
    private JLabel flatLabel;
    private JButton logOutButton;
    private JButton zgłośUsterkeButton;


    private int width=400, height=400;


    public MainPanel(int id,int id_f){
        super("Okno zarządzania");
        this.setContentPane(this.mainPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        zarządzajWewnątrzButton.setFocusable(false);
        zarządzajZewnątrzButton.setFocusable(false);

        DBManageData dbmd = new DBManageData();
        String name = dbmd.whatsmyName(id, id_f);
        welcomeLabel.setText(welcomeLabel.getText()+" "+name);
        flatLabel.setText(flatLabel.getText()+" "+id_f);

        zarządzajZewnątrzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        zarządzajWewnątrzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                roomSelect rS = new roomSelect(id,id_f);
                rS.setVisible(true);
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login l = new Login();
                l.setVisible(true);
            }
        });
        zgłośUsterkeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendReport sR = new SendReport(flatLabel.getText(),id,id_f);
                sR.setVisible(true);
            }
        });
    }
}

import javax.swing.*;

public class MainPanel extends JFrame {
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JLabel emailReceived;
    private JLabel howManyMails;

    private int width=920, height=540;


    public MainPanel(String us){
        super("Okno zarządzania");
        this.setContentPane(this.mainPanel);
        this.nameLabel.setText(us);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);

    }
}

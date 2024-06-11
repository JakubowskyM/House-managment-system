import javax.swing.*;

public class MainPanel extends JFrame {
    private JPanel infoPanel;
    private JPanel mainPanel;
    private JPanel mailPanel;
    private JPanel fastSettingPanel;
    private JLabel helloLabel;
    private JButton logOffButton;
    private JButton applyButton;
    private JLabel tempLabel;
    private JLabel doorLabel;
    private JLabel camerasLabel;
    private JLabel fanLabel;
    private JLabel doorsLabel;
    private JLabel camLabel;
    private int width=700, height=400;



    public MainPanel(String us){
        super("Logowanie do systemu");
        this.setContentPane(this.mainPanel);
        String u = this.helloLabel.getText();
        u=u+us;
        this.helloLabel.setText(u);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
    }
}

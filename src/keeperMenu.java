import javax.swing.*;

public class keeperMenu extends JFrame{
    private JPanel mainPanel;
    private JPanel messagesPanel;
    private int width=400, height=300;

    public keeperMenu() {

        super("Interfejs konserwatora");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel logowaniePane;
    private JPasswordField passField;
    private JTextField userField;
    private JButton loginButton;
    private JButton exitLoginButton;
    private JPanel midPanel;
    private JPanel topPanel;
    private JPanel buttonPanel;
    private int width=400, height=300;

    public static void main(String[] args) {
        Login loginForm = new Login();
        loginForm.setVisible(true);
    }

    public Login() {

        super("Logowanie do systemu");
        this.setContentPane(this.logowaniePane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);


        exitLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String userNameInput = userField.getText();
                String passInput = new String(passField.getPassword());

                if(userNameInput.equals("admin") && passInput.equals("admin"))
                {
                    dispose();
                    MainPanel m = new MainPanel(userNameInput);
                    m.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null,"bledne dane",
                            "blad logowania",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


}

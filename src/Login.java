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
                DBLogin log = new DBLogin();
                Resident result = log.loginToDB(userNameInput,passInput);
                if(result!=null) {
                    JOptionPane.showMessageDialog(null, "Zalogowano", "Logowanie", JOptionPane.INFORMATION_MESSAGE);
                    if(result.getLvl()==1)
                    {
                        dispose();
                        MainPanel m = new MainPanel(userNameInput);
                        m.setVisible(true);
                    }
                    if (result.getLvl()==2) {
                        dispose();
                        keeperMenu k = new keeperMenu();
                        k.setVisible(true);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Podano bledne dane logowania", "Logowanie", JOptionPane.ERROR_MESSAGE);
                }
                    ;
            }
        });

    }


}

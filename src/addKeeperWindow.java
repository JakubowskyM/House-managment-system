import javax.naming.InvalidNameException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addKeeperWindow extends JFrame {
    private JTextField nameField;
    private JTextField surnameField;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel panel1;
    private JTextField loginField;
    private JTextField passField;
    private JLabel yourLoginLabel;
    private int width = 500;
    private int height = 400;

    addKeeperWindow() {
        super("Dodawanie konserwatora");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        KeeperDAO keeperDAO = new KeeperDAO();
        yourLoginLabel.setText(keeperDAO.lastLogin());

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!nameField.getText().isEmpty() || !surnameField.getText().isEmpty() || !passField.getText().isEmpty()) {
                    validateNameFields(nameField.getText());
                    validateNameFields(surnameField.getText());
                    validateLoginsFields(passField.getText());

                    keeperDAO.addKeeper(nameField.getText(), surnameField.getText(), Integer.parseInt(passField.getText()));
                    JOptionPane.showMessageDialog(null, "Konserwator dodany", "Dodano", JOptionPane.INFORMATION_MESSAGE);
                    dispose();}
                    else {
                        JOptionPane.showMessageDialog(null,"Wypełnij wszystkie pola","Błąd",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (InvalidNameException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }



    public void validateNameFields(String string) throws InvalidNameException {
        if (!string.matches("^[a-zA-Z-ąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+$")) {
            throw new InvalidNameException("Pola zawierają błędne znaki");
        }
    }

    public void validateLoginsFields(String login) throws InvalidNameException{
            if(!login.matches("\\d{4}")) {
                throw new InvalidNameException("Loginy i hasla powinny mieć długość równą 4");
            }
        }
    }




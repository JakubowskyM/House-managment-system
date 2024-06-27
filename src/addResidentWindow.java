import javax.naming.InvalidNameException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class addResidentWindow extends  JFrame {
    private JPanel panel1;
    private JTextField nameField;
    private JTextField surnameField;
    private JButton cancelButton;
    private JButton addButton;
    private JTextField passField;
    private JLabel yourLoginLabel;
    private JComboBox comboBox1;
    private int width = 500;
    private int height = 400;

    addResidentWindow(){
        super("Dodawanie mieszkańca");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);

        addToJComboBox();

        String lastLog = new ResidentDAO().lastLogin();
        yourLoginLabel.setText(lastLog);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!nameField.getText().isEmpty() && !surnameField.getText().isEmpty() && !passField.getText().isEmpty()) {
                        validateNameFields(nameField.getText());
                        validateNameFields(surnameField.getText());
                        validateLoginsFields(passField.getText());


                        ResidentDAO residentDAO = new ResidentDAO();
                        residentDAO.addResident(nameField.getText(), surnameField.getText(), Integer.parseInt(passField.getText()),
                                Integer.parseInt(comboBox1.getSelectedItem().toString()), residentDAO.lastID());

                        JOptionPane.showMessageDialog(null, "Mieszkaniec dodany", "Dodano", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Wypełnij wszystkie pola","Błąd",JOptionPane.ERROR_MESSAGE);
                    }
                }
                    catch(InvalidNameException ex){
                        JOptionPane.showMessageDialog(null,ex.getMessage(),"Błąd",JOptionPane.ERROR_MESSAGE);
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
    private void addToJComboBox(){

        String getFlatsId = "SELECT Id_f FROM flats";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(getFlatsId);
            ResultSet rs = ps.executeQuery()) {

            List<Integer> id_fList = new ArrayList<>();
            while(rs.next()) {
                int id=rs.getInt("Id_f");
                id_fList.add(id);
            }
            for(int id : id_fList)
                comboBox1.addItem(String.valueOf(id));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
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






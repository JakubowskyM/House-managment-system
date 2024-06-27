import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendReport extends JFrame{
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton cancelButton;
    private JButton sendButton;
    private JTextField tittleField;
    private int width=400, height=400;

    SendReport(String flatName, int id_resident, int id_f){

        super("Wyślij wiadomość");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textArea1.getText().isEmpty() && !tittleField.getText().isEmpty()){
                    String message = textArea1.getText();
                    String tittle = tittleField.getText();
                    MessagesDAO.sendReport(flatName,id_resident,message,tittle,id_f);
                    JOptionPane.showMessageDialog(null,"Wysłano wiadomosć");
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Wypelnij wszystkie pola");
                }
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}

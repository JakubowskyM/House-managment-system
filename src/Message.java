import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

public class Message extends JFrame {
    private JPanel panel1;
    private JButton closeButton;
    private JTextArea textArea1;
    private JButton takeTask;

    private int width=500, height=500;

    public Message(int keeper_id,int id, String msg){
        super("Interfejs konserwatora");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        textArea1.setText(msg);



        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        takeTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int wybor = JOptionPane.showOptionDialog(null,"Na pewno chcesz przyjąć to zlecenie?","Nowe zlecenie",
                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Tak","Anuluj"},"Tak");
                if(wybor == JOptionPane.YES_OPTION){

                    MessagesDAO.getTask(keeper_id,id);

                    JOptionPane.showMessageDialog(null,"Zlecenie przyjete");

                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Zlecenie nie zostalo przyjete");
                    dispose();
                }
            }
        });
    }
}

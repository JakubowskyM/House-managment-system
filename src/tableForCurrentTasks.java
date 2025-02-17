import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class tableForCurrentTasks extends JFrame {


    private JPanel panel1;
    private JTable table1;
    private JButton closeButton;
    private JButton endTask;
    private int width=700, height=400;
    private int selectedTask;

    public tableForCurrentTasks(int id){
        super("Obecne zadania");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);

        MessagesDAO cT = new MessagesDAO();
        List<RecordsForMessages> cM = cT.getTakenReportByKeeperId(id);
        String [] columnNames = {"Id","Tytul","Data zgloszenia","Numer mieszkania", "Data przyjecia"};
        DefaultTableModel dTm = new DefaultTableModel(0,0);
        dTm.setColumnIdentifiers(columnNames);
        for(int i =0; i<cM.size();i++){
            dTm.addRow(new Object[]{cM.get(i).getId(),cM.get(i).getTitle(), cM.get(i).getDateSent(),cM.get(i).getId_f(),cM.get(i).getTakenDate()});
        }
        table1.setModel(dTm);

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table1.rowAtPoint(e.getPoint());
                if(row>=0)
                {
                    selectedTask = (int) table1.getValueAt(row,0);
                    endTask.setEnabled(true);
                }
            }
        });


        endTask.setEnabled(false);

        endTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int wybor = JOptionPane.showOptionDialog(null,"Czy na pewno ukonczyles to zlecenie?","Ukonczone zlecenie",
                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Tak","Anuluj"},"Tak");
                if(wybor == JOptionPane.YES_OPTION){

                    MessagesDAO.endTask(selectedTask,id);
                    JOptionPane.showMessageDialog(null,"Zadanie zakonczone, dziękujemy.");

                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Potwierdzenie zakończenia zadania anulowane.");
                    dispose();
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

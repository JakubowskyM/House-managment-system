import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class keeperMenu extends JFrame{
    private JPanel mainPanel;
    private JPanel lastMessagesPanel;
    private JLabel messagesLabel;
    private JLabel notificationsLabel;
    private JLabel newTasks;
    private JTable messagesTable;
    private JPanel messagePanel;
    private JButton closeButton;
    private JButton logOutButton;
    private JButton currentTasks;
    private JButton refreshButton;
    private JLabel keeperId;
    private int width=700, height=400;

    public keeperMenu(int lvl, int id) {

        super("Interfejs konserwatora");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        keeperId.setText(keeperId.getText()+id);

        int howManyReports = reportsCounter.countReportsFromlast30Dasys();
        newTasks.setText(String.valueOf(howManyReports));

        //pobieranie wiadomosci z bazy
        MessagesDAO messagesDAO = new MessagesDAO();
        List<RecordsForMessages> rfm = messagesDAO.getMessagesForKeepers();
        String [] columnNames = {"Id","Tytul","Data wyslania","Numer mieszkania"};

        DefaultTableModel dtm = new DefaultTableModel(0,0);
        dtm.setColumnIdentifiers(columnNames);

        for(int i=0;i<rfm.size();i++){
            dtm.addRow(new Object[] {rfm.get(i).getId(),rfm.get(i).getTitle(),rfm.get(i).getDateSent(),rfm.get(i).getId_f()});
        }
        messagesTable.setModel(dtm);


        messagesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = messagesTable.rowAtPoint(e.getPoint());
                if(row>=0)
                {
                    Message m = new Message(id,rfm.get(row).getId(),rfm.get(row).getMessage());
                    m.setVisible(true);
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login l = new Login();
                l.setVisible(true);
            }
        });
        currentTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tableForCurrentTasks currentTasks = new tableForCurrentTasks(id);
                currentTasks.setVisible(true);
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<RecordsForMessages> rfrsh = messagesDAO.getMessagesForKeepers();
                dtm.setRowCount(0);
                for(int i=0;i<rfrsh.size();i++){
                    dtm.addRow(new Object[] {rfrsh.get(i).getId(),rfrsh.get(i).getTitle(),rfrsh.get(i).getDateSent(),rfrsh.get(i).getId_f()});
                }
                messagesTable.setModel(dtm);

            }
        });
    }



}

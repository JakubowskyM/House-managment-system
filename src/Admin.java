import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Admin extends  JFrame{
    private JPanel panel1;
    private JButton residentsListButton;
    private JButton flatListButton;
    private JButton keepersListButton;
    private JButton optionButton;
    private JTable infoTable;
    private JButton exitButton;
    private JButton logoutButton;
    private int width=700, height=400;

    public Admin(){
        super("Interfejs administratora");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        residentsListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBManageData allResidents = new DBManageData();
                String [] columnNames = {"Imie","Nazwisko","Id mieszkania","Data wprowadzenia"};

            }
        });

        keepersListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBManageData allKeepers = new DBManageData();
                String [] columnNames = {"Id","Imie","Nazwisko","Liczba ukonczonych zadan"};
                List<Keepers> keepersList = allKeepers.getAllKeepers();
                DefaultTableModel dtm = new DefaultTableModel(0,0);
                dtm.setColumnIdentifiers(columnNames);
                for(int i = 0; i<keepersList.size();i++)
                    dtm.addRow(new Object[] {keepersList.get(i).getId(),keepersList.get(i).getName(),keepersList.get(i).getSurname(),keepersList.get(i).getCompleted_tasks()});
                infoTable.setModel(dtm);

                optionButton.setEnabled(true);
            }
        });

    }
}

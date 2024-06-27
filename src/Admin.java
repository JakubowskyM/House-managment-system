import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Admin extends  JFrame{
    private JPanel panel1;
    private JButton residentsListButton;
    private JButton keepersListButton;
    private JButton optionButton;
    private JTable infoTable;
    private JButton exitButton;
    private JButton logoutButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private int selectedKeeperID;
    private int selectedResidentID;

    private int width=700, height=400;

    public Admin(){
        super("Interfejs administratora");
        this.setContentPane(panel1);
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
                infoTable.setModel(getResidentTable());
                infoTable.setDefaultEditor(Object.class,null);
                addButton.setEnabled(true);
                deleteButton.setEnabled(true);


                infoTable.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = infoTable.rowAtPoint(e.getPoint());
                        if(row>=0)
                        {
                            selectedResidentID = (int) infoTable.getValueAt(row,0);
                        }


                        deleteButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                int wybor = JOptionPane.showOptionDialog(null,"Na pewno chcesz usunac tego mieszkańca?","Usuwanie mieszkańca",
                                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Tak","Anuluj"},"Tak");
                                if(wybor==JOptionPane.YES_OPTION){
                                    ResidentDAO residentDAO = new ResidentDAO();
                                    residentDAO.deleteResident(selectedResidentID);
                                    residentDAO.updateAutoIncrementUsers();
                                    JOptionPane.showMessageDialog(null,"Usunięto mieszkańca");
                                    infoTable.setModel(getResidentTable());
                                }
                                else {
                                    JOptionPane.showMessageDialog(null,"Anulowano usuwanie");
                                }
                            }
                        });
                    }
                });

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addResidentWindow aRW = new addResidentWindow();
                        aRW.setVisible(true);
                        infoTable.setModel(getKeeperTable());
                    }
                });
                refreshButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    infoTable.setModel(getResidentTable());
                    }
                });
            }
        });

        keepersListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                infoTable.setModel(getKeeperTable());

                infoTable.setDefaultEditor(Object.class,null);
                addButton.setEnabled(true);
                deleteButton.setEnabled(true);


                infoTable.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = infoTable.rowAtPoint(e.getPoint());
                        if(row>=0)
                        {
                            selectedKeeperID = (int) infoTable.getValueAt(row,0);
                        }
                        deleteButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                int wybor = JOptionPane.showOptionDialog(null,"Na pewno chcesz usunac tego konserwatora?","Usuwanie konserwatora",
                                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Tak","Anuluj"},"Tak");
                                if(wybor==JOptionPane.YES_OPTION){
                                    KeeperDAO keeperDAO = new KeeperDAO();
                                    keeperDAO.deleteKeeper(selectedKeeperID);
                                    keeperDAO.updateAutoIncrementUsers();
                                    JOptionPane.showMessageDialog(null,"Usunięto konserwatora");
                                    infoTable.setModel(getKeeperTable());
                                }
                                else {
                                    JOptionPane.showMessageDialog(null,"Anulowano usuwanie");
                                }
                            }
                        });
                    }
                });

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addKeeperWindow aKW = new addKeeperWindow();
                        aKW.setVisible(true);
                        infoTable.setModel(getKeeperTable());
                    }
                });

                refreshButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    infoTable.setModel(getKeeperTable());
                    }
                });


            }
        });




        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login l =new Login();
                l.setVisible(true);
            }
        });

        getContentPane().requestFocusInWindow();

    }

    public DefaultTableModel getKeeperTable() {
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        KeeperDAO allKeepers = new KeeperDAO();
        String[] columnNames = {"Id", "Imie", "Nazwisko", "Liczba ukonczonych zadan"};
        List<Keepers> keepersList = allKeepers.getAllKeepers();
        dtm.setColumnIdentifiers(columnNames);
        for (int i = 0; i < keepersList.size(); i++)
            dtm.addRow(new Object[]{keepersList.get(i).getId(), keepersList.get(i).getName(), keepersList.get(i).getSurname(), keepersList.get(i).getCompleted_tasks()});
        return dtm;
    }
    public DefaultTableModel getResidentTable(){
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        ResidentDAO allResidents = new ResidentDAO();
        String [] columnNames = {"Imie","Nazwisko","Id mieszkania"};
        List<Resident> residentList = allResidents.getAllResidents();
        dtm.setColumnIdentifiers(columnNames);
        for (int i = 0; i < residentList.size(); i++)
            dtm.addRow(new Object[]{residentList.get(i).getId(), residentList.get(i).getName(), residentList.get(i).getSurname()});
        return dtm;
    }
}


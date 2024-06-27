import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class roomSelect extends JFrame{
    private JPanel panel1;
    private JButton bedroomButton;
    private JButton bedroom2Button;
    private JButton kitchenButton;
    private JButton livingroomButton;
    private JButton bathroomButton;
    private JButton backButton;
    private int width=600;
    private int height=400;

    public roomSelect(int id, int id_f) {

        super("Wybierz pokój");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainPanel mP = new MainPanel(id,id_f);
                mP.setVisible(true);
            }
        });


        bedroomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                insideControl iC = new insideControl(id,id_f,"Sypialnia");
                iC.setVisible(true);
            }
        });

        bedroom2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                insideControl iC = new insideControl(id,id_f,"Sypialnia2");
                iC.setVisible(true);
            }
        });

        kitchenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                insideControl iC = new insideControl(id,id_f,"Kuchnia");
                iC.setVisible(true);
            }
        });

        livingroomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                insideControl iC = new insideControl(id,id_f,"Salon");
                iC.setVisible(true);
            }
        });

        bathroomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                insideControl iC = new insideControl(id,id_f,"Łazienka");
                iC.setVisible(true);
            }
        });


    }
}

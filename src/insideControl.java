import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class insideControl extends JFrame{
    private JPanel panel1;
    private JRadioButton airConditioningRadioButton;
    private JRadioButton lightRadioButton;
    private JRadioButton blindsRadioButton;
    private JButton saveChangesButton;
    private JButton backButton;
    private JSlider slider1;
    private JLabel temperatureLabel;
    private JRadioButton lightOnRadioButton;
    private JRadioButton lightOffRadioButton;
    private JRadioButton blindOnRadioButton;
    private JRadioButton blindsOffRadioButton;
    private JLabel roomName;
    private ButtonGroup controlButtonsGroup;
    private ButtonGroup lightButtons;

    private int width=600;
    private int height=400;


    public insideControl(int id, int id_f, String room_Name){

        super(room_Name);
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        roomName.setText(room_Name);
        slider1.setMinimum(15);
        slider1.setMaximum(27);

        ButtonGroup blindsButtonGroup = new ButtonGroup();
        blindsButtonGroup.add(blindOnRadioButton);
        blindsButtonGroup.add(blindsOffRadioButton);

        String s_id_f = String.valueOf(id_f);

        ButtonGroup lightButtonGroup = new ButtonGroup();
        lightButtonGroup.add(lightOnRadioButton);
        lightButtonGroup.add(lightOffRadioButton);

        int light = roomSettingsFromFile.loadSettings(room_Name,s_id_f).getLight();
        int blinds = roomSettingsFromFile.loadSettings(room_Name,s_id_f).getBlinds();
        int airConditioning = roomSettingsFromFile.loadSettings(room_Name,s_id_f).getAirConditioning();


        if(blinds==1){
            blindOnRadioButton.setEnabled(true);
            blindsOffRadioButton.setEnabled(true);
            blindOnRadioButton.setSelected(true);
        }
        if(light==1){
            lightOnRadioButton.setEnabled(true);
            lightOnRadioButton.setSelected(true);
            lightOffRadioButton.setEnabled(true);
        }
        if(airConditioning==1)
        {
            slider1.setEnabled(true);
            slider1.setValue(roomSettingsFromFile.loadSettings(room_Name,s_id_f).getTemperature());
            temperatureLabel.setText(temperatureLabel.getText()+String.valueOf(roomSettingsFromFile.loadSettings(room_Name,s_id_f).getTemperature()));
        }



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                roomSelect rS = new roomSelect(id,id_f);
                rS.setVisible(true);
            }
        });


        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int temperature = slider1.getValue();
                if(temperature==15) {
                    temperatureLabel.setText("Klimatyzacja wyłączona");
                    temperature = slider1.getValue();
                }
                else {
                    temperatureLabel.setText("Temperatura: " + slider1.getValue() + " °C");
                    temperature = 0;
                }
            }
        });

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int light = lightOnRadioButton.isSelected() ? 1 : 0;
                int blinds = blindOnRadioButton.isSelected() ? 1 : 0;

                int temperature = slider1.getValue();
                if(temperature!=15){
                int airConditioning = 1;
                    RoomSettings updatedSettings = new RoomSettings(light, blinds, airConditioning, temperature);
                    roomSettingsFromFile.saveSettings(room_Name,updatedSettings,s_id_f);


                }
                else {
                    int airConditioning = 0;
                    RoomSettings updatedSettings = new RoomSettings(light,blinds,airConditioning);
                    roomSettingsFromFile.saveSettings(room_Name,updatedSettings,s_id_f);


                }



            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                roomSelect rS = new roomSelect(id,id_f);
                rS.setVisible(true);
            }
        });

    }

}


import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatMacLightLaf());}
        catch (Exception e){
            e.printStackTrace();
        };
        WelcomeForm wf = new WelcomeForm();
    }
}
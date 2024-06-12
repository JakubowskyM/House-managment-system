import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatDarkLaf());}
        catch (Exception e){
            e.printStackTrace();
        };
        WelcomeForm wf = new WelcomeForm();

    }
}
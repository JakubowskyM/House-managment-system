import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class roomSettingsFromFile {

    public static RoomSettings loadSettings(String roomName, String id_f){
            try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/......./ustawienia_"+id_f+".txt")))    {
            String line;
            while((line = br.readLine())!=null) {
                String [] settingsArray = line.split(";");
                    if(settingsArray[0].equals(roomName)) {
                        int light = Integer.parseInt(settingsArray[1]);
                        int blinds = Integer.parseInt(settingsArray[2]);
                        int airCondition = Integer.parseInt(settingsArray[3]);
                        if (airCondition == 1) {
                            int temperature = Integer.parseInt(settingsArray[4]);
                            System.out.println(light+""+blinds+""+airCondition+""+temperature);
                             return new RoomSettings(light, blinds, airCondition, temperature);
                        } else {
                            System.out.println(light+""+blinds+""+airCondition);
                            return new RoomSettings(light, blinds, airCondition);
                        }
                    }
            }
            }
            catch(IOException e) {
                e.printStackTrace();
            }
           return null;
    }

    public static void saveSettings(String roomName, RoomSettings roomSettings, String id_f) {
        List<String> lines = new ArrayList<>();
        boolean roomFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/....../ustawienia_"+id_f+".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] settingsArray = line.split(";");
                if (settingsArray.length >= 4 && settingsArray[0].trim().equals(roomName)) {
                    roomFound = true;
                    line = roomSettings.toStringTemperature(roomName);
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!roomFound) {
            lines.add(roomSettings.toStringTemperature(roomName));
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter("C:/Users/....../ustawienia_"+id_f+".txt"))) {
            for (String line : lines) {
                pw.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

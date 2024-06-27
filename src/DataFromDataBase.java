import java.util.ArrayList;

public interface DataFromDataBase {

    ArrayList<RecordsForMessages> getMessagesForKeepers(int id);
    ArrayList<RecordsForMessages> getTakenReportByKeeperId(int keeper_id);
    ArrayList<Keepers> getAllKeepers();
    String whatsmyName(int id, int id_f);
    static void addKeeper(){};
}


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBManageData implements DataFromDataBase {


    public ArrayList<RecordsForMessages> getMessagesForKeepers (int user_type) {
        ArrayList<RecordsForMessages> msg = new ArrayList<>();
        String get = "SELECT Id, Text, Title, Data, Id_f FROM messages WHERE Id_a_to=? AND Stan=0";
            try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(get)) {
                ps.setInt(1,user_type);
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        int id_q = rs.getInt("Id");
                        String title = rs.getString("Title");
                        String msgs = rs.getString("Text");
                        String date = rs.getString("Data");
                        int id_f = rs.getInt("Id_f");
                        msg.add(new RecordsForMessages(id_q,title,msgs,date,id_f));
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        return msg;
    }
    public ArrayList<RecordsForMessages> getTakenReportByKeeperId(int keeper_id){

        ArrayList<RecordsForMessages> msg = new ArrayList<>();
        String keeperTakenReports = "SELECT Id, Title, Data, Id_f, Date_take FROM messages WHERE keeper_id=? AND Stan=1";
        try(Connection c = DBConnection.getConnection();
        PreparedStatement ps = c.prepareStatement(keeperTakenReports))
        {
            ps.setInt(1,keeper_id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                int id = rs.getInt("Id");
                String title = rs.getString("Title");
                String date = rs.getString("Data");
                int id_f = rs.getInt("Id_f");
                String take_date = rs.getString("Date_take");
                msg.add(new RecordsForMessages(id,title,date,id_f,take_date));
            }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public ArrayList<Keepers> getAllKeepers() {
        ArrayList<Keepers> msg = new ArrayList<>();
        String keeperTakenReports = "SELECT Id, Name, Surname, completed_tasks FROM keepers";
        String countPendingReports = "SELECT COUNT(Id) as pending_reports FROM messages WHERE Stan=1 AND keeper_id=?";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(keeperTakenReports))
        {
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    String surname = rs.getString("Surname");
                    int completed_tasks = rs.getInt("completed_tasks");
                    msg.add(new Keepers(id,name,surname,completed_tasks));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return msg;
    }
    }




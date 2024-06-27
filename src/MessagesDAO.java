import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MessagesDAO extends DBManageData{
    public ArrayList<RecordsForMessages> getMessagesForKeepers() {
        ArrayList<RecordsForMessages> msg = new ArrayList<>();
        String get = "SELECT Id, Text, Title, Data, Id_f FROM messages WHERE Id_a_to=2 AND Stan=0";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(get)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id_q = rs.getInt("Id");
                    String title = rs.getString("Title");
                    String msgs = rs.getString("Text");
                    String date = rs.getString("Data");
                    int id_f = rs.getInt("Id_f");
                    msg.add(new RecordsForMessages(id_q, title, msgs, date, id_f));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }
    public ArrayList<RecordsForMessages> getTakenReportByKeeperId(int keeper_id) {

        ArrayList<RecordsForMessages> msg = new ArrayList<>();
        String keeperTakenReports = "SELECT Id, Title, Data, Id_f, Date_take FROM messages WHERE keeper_id=? AND Stan=1";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(keeperTakenReports)) {
            ps.setInt(1, keeper_id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String title = rs.getString("Title");
                    String date = rs.getString("Data");
                    int id_f = rs.getInt("Id_f");
                    String take_date = rs.getString("Date_take");
                    msg.add(new RecordsForMessages(id, title, date, id_f, take_date));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }
    public static void sendReport(String flatName, int id_r, String message, String tittle, int id_f) {
        String sendReportSQL = "INSERT INTO messages (Title, Text, Data, Id_a_to, Id_a_from, Id_f, Stan) VALUES(?,?,?,?,?,?,?)";
        LocalDate date = LocalDate.now();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement sendM = c.prepareStatement(sendReportSQL))
        {
            sendM.setString(1, tittle);
            sendM.setString(2, message+"\n"+flatName);
            sendM.setDate(3, Date.valueOf(date));
            sendM.setInt(4, 2);
            sendM.setInt(5, id_r);
            sendM.setInt(6, id_f);
            sendM.setInt(7, 0);
            sendM.executeUpdate();
        } catch(SQLException a){
            a.printStackTrace();
        }
    }
    public static void endTask(int selectedTask, int id) {
        String endTaskSQL = "UPDATE messages SET Stan=3, Date_end=? WHERE Id=?";
        String countEndedTasks = "UPDATE keepers SET completed_tasks=completed_tasks+1 WHERE Id=?";
        LocalDate date = LocalDate.now();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(endTaskSQL);
             PreparedStatement countPs = c.prepareStatement(countEndedTasks)) {

            ps.setString(1, String.valueOf(date));
            ps.setInt(2, selectedTask);

            countPs.setInt(1, id);

            ps.executeUpdate();
            countPs.executeUpdate();

        } catch (SQLException a) {
            a.printStackTrace();
        }
    }

    public static void getTask(int keeper_id,int id) {
        String getTaskSQL = "UPDATE messages SET keeper_id=?, Stan=? , Date_take=? WHERE Id=?";
        LocalDate date = LocalDate.now();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(getTaskSQL)) {
            ps.setInt(1, keeper_id);
            ps.setInt(2, 1);
            ps.setDate(3, Date.valueOf(date));
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException a) {
            a.printStackTrace();
        }
    }
}

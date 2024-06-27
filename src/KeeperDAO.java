import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KeeperDAO  extends DBManageData{
    public void addKeeper(String name, String surname, int Password) {
        String newKeeper = "INSERT INTO keepers (Id, name, surname, completed_tasks) VALUES(?,?,?,0)";
        String keeperLogin = "INSERT INTO users (Login, Password, Access_lvl) VALUES(?,?,2)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(newKeeper);
             PreparedStatement kL = c.prepareStatement(keeperLogin)) {

            ps.setInt(1, lastID());
            ps.setString(2, name);
            ps.setString(3, surname);

            kL.setString(1, lastLogin());
            kL.setInt(2, Password);

            kL.executeUpdate();
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Keepers> getAllKeepers() {
        ArrayList<Keepers> msg = new ArrayList<>();
        String keeperTakenReports = "SELECT Id, Name, Surname, completed_tasks FROM keepers";
        String countPendingReports = "SELECT COUNT(Id) as pending_reports FROM messages WHERE Stan=1 AND keeper_id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(keeperTakenReports)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    String surname = rs.getString("Surname");
                    int completed_tasks = rs.getInt("completed_tasks");
                    msg.add(new Keepers(id, name, surname, completed_tasks));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }
    public static void deleteKeeper(int id){
        String deleteFromKeepers = "DELETE FROM keepers WHERE Id=?";
        String deleteFromUsers = "DELETE FROM users WHERE Id=?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement delKeepers = c.prepareStatement(deleteFromKeepers);
             PreparedStatement delUsers = c.prepareStatement(deleteFromUsers)) {

            delKeepers.setInt(1,id);
            delKeepers.executeUpdate();

            delUsers.setInt(1,id);
            delUsers.executeUpdate();
        } catch (SQLException a) {
            a.printStackTrace();
        }
    }
}

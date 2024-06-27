import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResidentDAO extends DBManageData{
    public void addResident(String name, String surname, int Password, int id_f,int id) {
        int lastID = lastID();
        String lastLog = lastLogin();
        String newResident = "INSERT INTO residents (Id, name, surname, Id_f) VALUES(?,?,?,?)";
        String residentLogin = "INSERT INTO users (Login, Password, Access_lvl,Id_f) VALUES(?,?,1,?)";
        String flatUpdate = "INSERT INTO flats (Id_f,Id_resident) VALUES (?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(newResident);
             PreparedStatement rL= c.prepareStatement(residentLogin);
             PreparedStatement fU = c.prepareStatement(flatUpdate)) {

            ps.setInt(1,lastID);
            ps.setString(2,name);
            ps.setString(3,surname);
            ps.setInt(4,id_f);
            ps.executeUpdate();

            rL.setString(1,lastLog);
            rL.setInt(2,Password);
            rL.setInt(3,id_f);
            rL.executeUpdate();

            fU.setInt(1,id_f);
            fU.setInt(2,lastID);
            fU.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteResident(int id) {
        String deleteFromResidents = "DELETE FROM residents WHERE Id=?";
        String deleteFromUsers = "DELETE FROM users WHERE Id=?";
        String deleteFromFlats = "DELETE FROM flats WHERE Id_resident=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement delResidents = c.prepareStatement(deleteFromResidents);
             PreparedStatement delUsers = c.prepareStatement(deleteFromUsers);
             PreparedStatement delFlats = c.prepareStatement(deleteFromFlats)) {

            delResidents.setInt(1,id);
            delResidents.executeUpdate();

            delUsers.setInt(1,id);
            delUsers.executeUpdate();

            delFlats.setInt(1,id);
            delFlats.executeUpdate();
        } catch (SQLException a) {
            a.printStackTrace();
        }

    }
    public ArrayList<Resident> getAllResidents() {
        ArrayList<Resident> msg = new ArrayList<>();
        String keeperTakenReports = "SELECT Id, Name, Surname, Id_f FROM residents";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(keeperTakenReports)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    String surname = rs.getString("Surname");
                    int id_f = rs.getInt("Id_f");
                    msg.add(new Resident(id, name, surname, id_f));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }
}

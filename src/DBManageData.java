import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class DBManageData implements DataFromDataBase {
    public int lastID() {
        String query = "SELECT Id FROM users ORDER BY Id DESC LIMIT 1";
        int lastId = 0;
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                lastId = rs.getInt("Id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId+1;
    }
    public String lastLogin() {
        String query = "SELECT Login FROM users ORDER BY Id DESC LIMIT 1";
        String lastLog = "";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                lastLog = rs.getString("Login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int l = Integer.parseInt(lastLog)+1;
        lastLog=String.format("%04d",l);
        return lastLog;
    }

    public String whatsmyName(int id, int id_f) {
        String yoursName = "SELECT Name, Surname FROM residents WHERE Id=? AND Id_f=?";
        String myName = "";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(yoursName)) {
            ps.setInt(1, id);
            ps.setInt(2, id_f);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("Name");
                    String surname = rs.getString("Surname");
                    myName = name + " " + surname;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myName;
    }
    public void updateAutoIncrementUsers(){


            String updateAutoIncrement = "ALTER TABLE users AUTO_INCREMENT="+String.valueOf(lastID());

            try (Connection c = DBConnection.getConnection();
                 PreparedStatement ps = c.prepareStatement(updateAutoIncrement)) {

                ps.executeUpdate();

            } catch (SQLException a) {
                a.printStackTrace();
            }
        }
    }
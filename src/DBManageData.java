import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBManageData implements DataFromDataBase {

    public ArrayList<RecordsForMessages> getMessagesForKeepers(int user_type) {
        ArrayList<RecordsForMessages> msg = new ArrayList<>();
        String get = "SELECT Id, Text, Title, Data, Id_f FROM messages WHERE Id_a_to=? AND Stan=0";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(get)) {
            ps.setInt(1, user_type);
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

    @Override
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

    public static void addKeeper(String name, String surname, int Password) {
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

    public static int lastID() {
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
    public static String lastLogin() {
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
    public static void updateAutoIncrementUsers(){

            String updateAutoIncrement = "ALTER TABLE users AUTO_INCREMENT="+String.valueOf(lastID());

            try (Connection c = DBConnection.getConnection();
                 PreparedStatement ps = c.prepareStatement(updateAutoIncrement)) {

                ps.executeUpdate();

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
    public static void addResident(String name, String surname, int Password, int id_f,int id) {
        int lastID = lastID();
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

            rL.setString(1,lastLogin());
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



    public static void deleteResident(int id) {
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
    }









import java.sql.*;
public class DBLogin {
    public Resident loginToDB(String login, String password) {
        String sql = "SELECT Id, Login, Access_lvl, Id_f, Password FROM users WHERE Login=? AND Password=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()) {
                    int id_f = rs.getInt("Id_f");
                    int accessLvl = rs.getInt("Access_lvl");
                    int id = rs.getInt("Id");
                    return new Resident(id_f,accessLvl,id);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
    }
}
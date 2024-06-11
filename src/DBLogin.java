
import java.sql.*;
public class DBLogin {
    public Resident loginToDB(String login, String password) {
        String sql = "SELECT Login, Id_a, Id_m, Password FROM users WHERE Login=? AND Password=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()) {
                    int id_m = rs.getInt("Id_m");
                    int id_a = rs.getInt("Id_a");
                    return new Resident(id_a,id_m);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
    }
}
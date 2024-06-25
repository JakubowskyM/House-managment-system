import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class reportsCounter {


    private static String query = "SELECT COUNT(*) AS count FROM messages WHERE Data >= ?";


    public static int countReportsFromlast30Dasys() {

        LocalDate last30days = LocalDate.now().minusDays(30);
        int count = 0;

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)){
            ps.setDate(1,java.sql.Date.valueOf(last30days));
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next())
                        count=rs.getInt("count");
                }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}

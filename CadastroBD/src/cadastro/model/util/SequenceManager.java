package cadastro.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    public static int getValue(Connection conn, String sequenceName) throws SQLException {
        String sql = "SELECT NEXT VALUE FOR " + sequenceName + " AS nextValue";
        PreparedStatement stmt = ConectorBD.getPrepared(conn, sql);
        ResultSet rs = ConectorBD.getSelect(stmt);
        if (rs.next()) {
            return rs.getInt("nextValue");
        }
        return -1;
    }
}






















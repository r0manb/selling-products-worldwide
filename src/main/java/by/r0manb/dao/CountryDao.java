package by.r0manb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CountryDao {
    public static long getIdOrCreate(Connection conn, String name, long regionId)
            throws SQLException {
        String select = "SELECT id FROM countries WHERE name = ?";
        try (PreparedStatement ps = conn.prepareStatement(select)){
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }

        String insert = "INSERT INTO countries(name, region_id) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(
                insert, PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            ps.setString(1, name);
            ps.setLong(2, regionId);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    return rs.getLong(1);
                }
            }
        }

        throw new SQLException();
    }
}

package br.com.oficina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkOrderDAO {
    public int save(int vehicleId, String complaint, String status)
            throws SQLException {

        String sql = "INSERT INTO workorder " +
                "(vehicle_id, complaint, status, created_at) " +
                "VALUES (?, ?, ?, CURRENT_TIMESTAMP) RETURNING id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vehicleId);
            statement.setString(2, complaint);
            statement.setString(3, status);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("id");
                }
            }
        }

        throw new SQLException("O banco não retornou o ID da ordem.");
    }
}
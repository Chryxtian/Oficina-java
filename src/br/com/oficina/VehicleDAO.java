package br.com.oficina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDAO {
    public int save(Vehicle vehicle, int customerId) throws SQLException {
        String sql = "INSERT INTO vehicle " +
                "(customer_id, license_plate, brand, model, year, created_at) " +
                "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP) RETURNING id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerId);
            statement.setString(2, vehicle.getLicensePlate());
            statement.setString(3, vehicle.getBrand());
            statement.setString(4, vehicle.getModel());
            statement.setInt(5, vehicle.getYear());

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("id");
                }
            }
        }

        throw new SQLException("O banco não retornou o ID do veículo.");
    }
}
//DATA ACESS OBJECT
//

package br.com.oficina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    public int save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (name, phone, create_at) " +
                "VALUES (?, ?, CURRENT_TIMESTAMP) RETURNING id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPhone());

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("id");
                }
            }
        }

        throw new SQLException("O banco não retornou o ID do cliente.");
    }
}

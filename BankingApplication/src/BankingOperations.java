import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankingOperations {
	
	
	 public static void createAccount(String accountNumber, String accountHolder) throws SQLException {
	        String query = "INSERT INTO accounts (account_number, account_holder, balance) VALUES (?, ?, 0.00)";
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, accountNumber);
	            stmt.setString(2, accountHolder);
	            stmt.executeUpdate();
	        }
	    }

    public static double checkBalance(String accountNumber) throws SQLException {
        String query = "SELECT balance FROM accounts WHERE account_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            } else {
                throw new SQLException("Account not found.");
            }
        }
    }

    public static void depositMoney(String accountNumber, double amount) throws SQLException {
        String query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, amount);
            stmt.setString(2, accountNumber);
            stmt.executeUpdate();
        }
    }

    public static void withdrawMoney(String accountNumber, double amount) throws SQLException {
        String query = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, amount);
            stmt.setString(2, accountNumber);
            stmt.executeUpdate();
        }
    }

    public static void transferMoney(String fromAccount, String toAccount, double amount) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            withdrawMoney(fromAccount, amount);
            depositMoney(toAccount, amount);

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}

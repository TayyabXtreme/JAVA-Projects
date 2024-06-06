# Simple Banking Application

This is a simple banking application that allows users to perform basic banking operations such as checking balance, depositing money, withdrawing money, and transferring money between accounts. The application is built using Java SE, JDBC, MySQL, and Eclipse.

## Features

- Create a new account
- Check account balance
- Deposit money into an account
- Withdraw money from an account
- Transfer money between accounts

## Technologies Used

- Java SE
- JDBC
- MySQL
- Eclipse IDE

## Setup Instructions

### Prerequisites

- JDK 8 or higher
- MySQL
- Eclipse IDE
- MySQL JDBC Driver

### Database Setup

1. **Install MySQL**: Ensure you have MySQL installed on your system.
2. **Create Database and Table**:
    ```sql
    CREATE DATABASE banking_app;
    USE banking_app;

    CREATE TABLE accounts (
        account_id INT AUTO_INCREMENT PRIMARY KEY,
        account_number VARCHAR(20) NOT NULL UNIQUE,
        account_holder VARCHAR(100) NOT NULL,
        balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00
    );
    ```

### Project Setup

1. **Open Eclipse**: Create a new Java project in Eclipse.
2. **Add MySQL JDBC Connector**:
    - Download the MySQL Connector/J from the [official MySQL website](https://dev.mysql.com/downloads/connector/j/).
    - Add the JAR file to your project build path:
      - Right-click on your project.
      - Select `Build Path` > `Add External Archives`.
      - Select the downloaded JAR file.

3. **Create Classes**:

#### DatabaseConnection Class

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/banking_app";
    private static final String USER = "root";  // replace with your MySQL username
    private static final String PASSWORD = "password";  // replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

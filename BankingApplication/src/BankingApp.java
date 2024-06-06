import java.util.Scanner;
import java.sql.SQLException;

public class BankingApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Banking App");

        while (true) {
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Create Account");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter account number: ");
                        String checkAccountNumber = scanner.next();
                        double balance = BankingOperations.checkBalance(checkAccountNumber);
                        System.out.println("Balance: " + balance);
                        break;
                    case 2:
                        System.out.print("Enter account number: ");
                        String depositAccountNumber = scanner.next();
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        BankingOperations.depositMoney(depositAccountNumber, depositAmount);
                        System.out.println("Deposit successful.");
                        break;
                    case 3:
                        System.out.print("Enter account number: ");
                        String withdrawAccountNumber = scanner.next();
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        BankingOperations.withdrawMoney(withdrawAccountNumber, withdrawAmount);
                        System.out.println("Withdrawal successful.");
                        break;
                    case 4:
                        System.out.print("Enter source account number: ");
                        String fromAccount = scanner.next();
                        System.out.print("Enter destination account number: ");
                        String toAccount = scanner.next();
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        BankingOperations.transferMoney(fromAccount, toAccount, transferAmount);
                        System.out.println("Transfer successful.");
                        break;
                    case 5:
                        System.out.print("Enter new account number: ");
                        String newAccountNumber = scanner.next();
                        System.out.print("Enter account holder name: ");
                        String newAccountHolder = scanner.next();
                        BankingOperations.createAccount(newAccountNumber, newAccountHolder);
                        System.out.println("Account created successfully.");
                        break;
                    case 6:
                        System.out.println("Thank you for using the Banking App.");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (SQLException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}

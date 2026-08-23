import enums.OperationTypes;
import service.ATMService;

public class Main {
    public static void main(String[] args) {
        ATMService atmSystem = ATMService.getInstance();

        // Perform Check Balance operation
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin(1234);
        atmSystem.selectOperation(OperationTypes.CHECK_BALANCE); // $1000

        // Perform Withdraw Cash operation
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin(1234);
        atmSystem.selectOperation(OperationTypes.WITHDRAW_CASH, 570);

        // Perform Deposit Cash operation
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin(1234);
        atmSystem.selectOperation(OperationTypes.DEPOSIT_CASH, 200);

        // Perform Check Balance operation
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin(1234);
        atmSystem.selectOperation(OperationTypes.CHECK_BALANCE); // $630

        // Perform Withdraw Cash more than balance
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin(1234);
        atmSystem.selectOperation(OperationTypes.WITHDRAW_CASH, 700); // Insufficient balance

        // Insert Incorrect PIN
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin(3425);
    }
}
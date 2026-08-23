package state;

import enums.OperationTypes;
import service.ATMService;

public class AuthenticatedState extends ATMState{

    public AuthenticatedState(ATMService service){
        super(service);
    }

    @Override
    public void insertCard(String cardNum) {
        System.out.println("Error: A card is already inserted and a session is active.");
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("Error: PIN has already been entered and authenticated.");
    }

    @Override
    public void selectOps(OperationTypes op,int ...args) {

        switch (op){
            case CHECK_BALANCE :
                atmService.checkBalance();
                break;

            case WITHDRAW_CASH:
                if (args.length == 0 || args[0] <= 0) {
                    System.out.println("Error: Invalid withdrawal amount specified.");
                    break;
                }
                int amountToWithdraw = args[0];

                double accountBalance = atmService.getBalance();

                if (amountToWithdraw > accountBalance) {
                    System.out.println("Error: Insufficient balance.");
                    break;
                }

                System.out.println("Processing withdrawal for $" + amountToWithdraw);
                // Delegate the complex withdrawal logic to the ATM's dedicated method
                atmService.withdraw(amountToWithdraw);
                break;

            case DEPOSIT_CASH:
                if (args.length == 0 || args[0] <= 0) {
                    System.out.println("Error: Invalid withdrawal amount specified.");
                    break;
                }
                int amountToDeposit = args[0];
                System.out.println("Processing deposit for $" + amountToDeposit);
                atmService.depositCash(amountToDeposit);
                break;

            default:
                System.out.println("Error: Invalid operation selected.");
                break;

        }

        System.out.println("Transaction complete.");
        ejectCard();

    }

    @Override
    public void ejectCard() {
        System.out.println("Ending session. Card has been ejected. Thank you for using our ATM.");
        atmService.setSelectedCard(null);
        atmService.setCurrentState(new IdleState(atmService));
    }
}

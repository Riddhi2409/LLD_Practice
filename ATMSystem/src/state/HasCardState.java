package state;

import enums.OperationTypes;
import service.ATMService;

public class HasCardState extends ATMState{

    public HasCardState(ATMService atmService){
        super(atmService);
    }

    @Override
    public void insertCard(String cardNum) {
        System.out.println("Error: A card is already inserted. Cannot insert another card.");
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("Authenticating PIN...");
        boolean isAuthenticated = atmService.authenticatePin(pin);;

        if (isAuthenticated) {
            System.out.println("Authentication successful.");
            atmService.setCurrentState(new AuthenticatedState(atmService));
        } else {
            System.out.println("Authentication failed: Incorrect PIN.");
            ejectCard();
        }
    }

    @Override
    public void selectOps(OperationTypes op, int ...args) {
        System.out.println("Error: Please enter your PIN first to select an operation.");
    }

    @Override
    public void ejectCard() {
        System.out.println("Card has been ejected. Thank you for using our ATM.");
        atmService.setSelectedCard(null);
        atmService.setCurrentState(new IdleState(atmService));
    }
}

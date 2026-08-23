package state;

import entity.Card;
import enums.OperationTypes;
import service.ATMService;

public class IdleState extends ATMState{

    public IdleState(ATMService service){
        super(service);
    }


    @Override
    public void insertCard(String cardNum) {
        System.out.println("\nCard has been inserted.");
        Card card=atmService.getCard(cardNum);
        if(card==null){
            ejectCard();
        }
        else{
            atmService.setSelectedCard(card);
            atmService.setCurrentState(new HasCardState(atmService));
        }
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("Error: Please insert a card first.");
    }

    @Override
    public void selectOps(OperationTypes op, int ...args) {
        System.out.println("Error: Please insert a card first.");
    }

    @Override
    public void ejectCard() {
        System.out.println("Error: Card not found.");
        atmService.setSelectedCard(null);
    }
}

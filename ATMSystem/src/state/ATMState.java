package state;

import entity.Card;
import enums.OperationTypes;
import service.ATMService;

public abstract class ATMState {
    public ATMService atmService;

    public ATMState(ATMService atmService) {
        this.atmService = atmService;
    }

    abstract public void insertCard(String cardNum);
    abstract public void enterPin(int pin);
    abstract public void selectOps(OperationTypes op, int ...args);
    abstract  public  void ejectCard();
}

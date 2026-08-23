package state;

import enums.Coin;
import service.VendingMachine;

public class HasMoneyState extends VendingMachineState{

    public HasMoneyState(VendingMachine machine){
        super(machine);
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Already received full amount.");
    }

    @Override
    public void selectItem(String code) {
        System.out.println("Item already selected.");

    }

    @Override
    public void dispense() {
        vendingMachine.setCurrentState(new DispenseState(vendingMachine));
        vendingMachine.dispenseItem();
    }

    @Override
    public void refund() {
        vendingMachine.refundBalance();
        vendingMachine.reset();
        vendingMachine.setCurrentState(new IdleState(vendingMachine));
    }
}

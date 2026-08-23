package state;

import enums.Coin;
import service.VendingMachine;

public class IdleState extends VendingMachineState{
    public IdleState(VendingMachine machine) {
        super(machine);
    }
    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please select item before inserting money");
    }

    @Override
    public void selectItem(String code) {
        if(vendingMachine.isAvailable(code)){
            vendingMachine.setSelectedCode(code);
            vendingMachine.setCurrentState(new ItemSelectedState(vendingMachine));
            System.out.println("Item Selected: " + code);
            return;
        }
        System.out.println("Item not available.");
    }

    @Override
    public void dispense() {
        System.out.println("No item Selected");
    }

    @Override
    public void refund() {
        System.out.println("No money to refund");
    }
}

package state;

import enums.Coin;
import service.VendingMachine;

public class ItemSelectedState extends VendingMachineState{

    public ItemSelectedState(VendingMachine machine){
        super(machine);
    }

    @Override
    public void insertCoin(Coin coin) {
        vendingMachine.addBalance(coin.getValue());
        int currVal=vendingMachine.getBalance();
        if(currVal>=vendingMachine.getSelectedItem().getPrice()){
            System.out.println("Sufficient money received.");
            vendingMachine.setCurrentState(new HasMoneyState(vendingMachine));
        }
    }

    @Override
    public void selectItem(String code) {
        System.out.println("Item already selected.");
    }

    @Override
    public void dispense() {
        System.out.println("Please insert sufficient money.");
    }

    @Override
    public void refund() {
        vendingMachine.reset();
        vendingMachine.setCurrentState(new IdleState(vendingMachine));
    }
}

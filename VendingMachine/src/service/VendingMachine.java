package service;

import entity.Inventory;
import entity.Item;
import enums.Coin;
import state.IdleState;
import state.VendingMachineState;

import javax.swing.plaf.PanelUI;

public class VendingMachine {
    private final static VendingMachine instance = new VendingMachine();
    private final Inventory inventory=new Inventory();
    private int balance=0;
    private String selectedCode;
    private VendingMachineState currentState;

    private VendingMachine() {
        this.currentState = new IdleState(this);
    }

    public static VendingMachine getInstance(){
        return instance;
    }


    public void setCurrentState(VendingMachineState currentState) {
        this.currentState = currentState;
    }

    public int getBalance() {
        return balance;
    }

    public Item getSelectedItem() {
        return inventory.getItem(selectedCode);
    }

    public boolean isAvailable(String code){
        return inventory.isAvailable(code);
    }


    public void setSelectedCode(String selectedCode) {
        this.selectedCode = selectedCode;
    }

    public void reset(){
        balance=0;
        selectedCode=null;
    }

    public void addBalance(int val){
        balance+=val;
    }

    public void refundBalance(){
        System.out.println("Refunding "+ balance);
        balance=0;
    }

    public void dispenseItem(){
        int price=getSelectedItem().getPrice();
        if(price<=balance){
            inventory.reduceItem(selectedCode);
            balance-=price;
            System.out.println("Dispensed Item: "+ getSelectedItem().getName());
            if (balance > 0) {
                System.out.println("Returning change: " + balance);
            }
            reset();
            setCurrentState(new IdleState(this));
        }
    }

    public void addItem(String code,String name,int price, int quantity){
        Item item = new Item(name,code,price);
        inventory.addItem(code,quantity,item);
    }

    public void insertCoin(Coin coin){
        currentState.insertCoin(coin);
    }

    public void selectItem(String code){
        currentState.selectItem(code);
    }

    public void dispense(){
        currentState.dispense();
    }

    public void refund(){
        currentState.refund();
    }

}

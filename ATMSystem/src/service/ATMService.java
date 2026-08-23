package service;

import chainOfResponsibility.*;
import entity.Card;
import enums.OperationTypes;
import state.ATMState;
import state.IdleState;

public class ATMService {
    private static ATMService instance= new ATMService();
    private final BankService bankService;
    private Card selectedCard;
    private ATMState currentState;
    private CashDispenser cashDispenser;

    public ATMService(){
        bankService=new BankService();
        currentState = new IdleState(this);

        DispenseChain c1=new NoteDispenser100(10);
        DispenseChain c2= new NoteDispenser50(50);
        DispenseChain c3= new NoteDispenser20(100);
        c1.setNextChain(c2);
        c2.setNextChain(c3);
        c3.setNextChain(null);
        cashDispenser = new CashDispenser(c1);
    }

    public static ATMService getInstance() {
        return instance;
    }

    public Card getCard(String cardNumber){
        Card card = bankService.getCard(cardNumber);
        return card;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public boolean authenticatePin(int pin){
        return bankService.authenticate(selectedCard,pin);
    }

    public void setCurrentState(ATMState currentState) {
        this.currentState = currentState;
    }

    public void checkBalance(){
        System.out.println( "Current Balance: " + getBankService().getBalance(selectedCard));
    }

    public double getBalance(){
        return bankService.getBalance(selectedCard);
    }

    public void depositCash(double amount){
        bankService.depositMoney(selectedCard,amount);
    }

    public void withdraw(int amount){
        if (!cashDispenser.canDispenseCash(amount)) {
            throw new IllegalStateException("Insufficient cash available in the ATM.");
        }

        bankService.withdrawMoney(selectedCard,amount);

        try {
            cashDispenser.dispenseCash(amount);
        } catch (Exception e) {
            bankService.depositMoney(selectedCard, amount); // Deposit back if dispensing fails
        }


    }

    public void insertCard(String cardNum){
        currentState.insertCard(cardNum);
    }

    public void enterPin(int pin){
        currentState.enterPin(pin);
    }

    public void selectOperation(OperationTypes op, int... args) {
        currentState.selectOps(op, args);
    }


    public BankService getBankService() {
        return bankService;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }
}

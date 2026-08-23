package service;

import entity.Account;
import entity.Card;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private final Map<String, Card> cards= new ConcurrentHashMap<>();
    private final Map<String, Account> accounts=new ConcurrentHashMap<>();
    private final Map<Card,Account> cardAccountMap=new ConcurrentHashMap<>();

    public BankService() {
        // Create sample accounts and cards
        Account account1 = createAccount("1234567890", 1000.0);
        Card card1 = createCard("1234-5678-9012-3456", 1234);
        linkCardToAccount(card1, account1);

        Account account2 = createAccount("9876543210", 500.0);
        Card card2 = createCard("9876-5432-1098-7654", 4321);
        linkCardToAccount(card2, account2);
    }

    public Account createAccount(String accountNum,double balance){

        Account a1= new Account(accountNum,balance);
        accounts.put(accountNum,a1);
        return a1;
    }

    public Card createCard(String cardNum,int pin){
        Card c1= new Card(cardNum,pin);
        cards.put(cardNum,c1);
        return c1;
    }

    public void linkCardToAccount(Card card, Account account) {
        account.getCards().put(card.getCardNum(), card);
        cardAccountMap.put(card, account);
    }

    public boolean authenticate(Card card,int pin){
        return card.getPin()==pin;
    }

    public Card getCard(String cardnum){

        return cards.getOrDefault(cardnum,null);

    }
    public double getBalance(Card card) {
        return cardAccountMap.get(card).getBalance();
    }

    public void withdrawMoney(Card card, double amount) {
        cardAccountMap.get(card).withdraw(amount);
    }

    public void depositMoney(Card card, double amount) {
        cardAccountMap.get(card).addBalance(amount);
    }
}

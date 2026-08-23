package entity;

import java.time.LocalDate;
import java.util.*;

public class Group {
    final private String groupId;
    final private String name;
    final private List<User> members;
    private final List<Expense> expenses = new ArrayList<>();
    private final Map<User,BalanceSheet> balanceSheets=new HashMap<>();
    final private LocalDate createdAt;

    public Group(String name,List<User> members) {
        this.groupId= UUID.randomUUID().toString();
        this.createdAt = LocalDate.now();
        this.name = name;
        this.members=members;
        for(User user: members){
            balanceSheets.putIfAbsent(user,new BalanceSheet());
        }
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public List<User> getMembers() {
        return members;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void addMember(User user){
        members.add(user);
        balanceSheets.putIfAbsent(user,new BalanceSheet());
    }

    public BalanceSheet getBalanceSheet(User user) {
        return balanceSheets.get(user);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public Map<User, BalanceSheet> getBalanceSheets() {
        return balanceSheets;
    }
}

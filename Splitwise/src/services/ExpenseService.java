package services;

import entity.Expense;
import entity.Group;
import entity.Split;
import entity.User;
import enums.SplitType;

import java.util.*;

public class ExpenseService {
    private final BalanceSheetService balanceSheetService;

    public ExpenseService(BalanceSheetService balanceSheetService) {
        this.balanceSheetService = balanceSheetService;
    }

    public void addExpense(Group group, String description, double amount, User paidBy,
                           List<User> participants, SplitType splitType, List<Double> splitValues){

        Expense expense= new  Expense.ExpenseBuilder().setId(UUID.randomUUID().toString())
                .setAmount(amount)
                .setDescription(description)
                .setPaidBy(paidBy)
                .setParticipants(participants)
                .setSplitValues(splitValues)
                .setSplitType(splitType)
                .build();
        group.addExpense(expense);

        balanceSheetService.updateBalances(group, paidBy, expense.getSplits());
    }
}

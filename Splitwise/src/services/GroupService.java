package services;

import entity.Group;
import entity.User;
import enums.SplitType;
import repo.GroupRepo;

import java.util.List;

public class GroupService {
    private final GroupRepo repo;
    private final ExpenseService expenseService;
    private final DebtSimplificationService simplifier;


    public GroupService(GroupRepo repo, ExpenseService expenseService, DebtSimplificationService simplifier) {
        this.repo = repo;
        this.expenseService = expenseService;
        this.simplifier = simplifier;
    }

    public String createGroup(String name, List<User> members){
        Group group=new Group(name,members);
        repo.save(group);
        return group.getGroupId();
    }

    public void addMember(String groupId, User user) {
        get(groupId).addMember(user);
    }

    private Group get(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + id));
    }

    public void addExpense(String groupId, String description, double amount, User paidBy,
                           List<User> participants, SplitType splitType, List<Double> splitValues){

        expenseService.addExpense(get(groupId),description,amount
                    ,paidBy,participants,splitType,splitValues);
    }

    public void printBalances(String groupId){
        Group g= get(groupId);
        g.getMembers().forEach(u->
                g.getBalanceSheet(u).print(u));
    }

    public void simplifyDebts(String groupId) {
        simplifier.simplifyDebts(get(groupId));
    }
}

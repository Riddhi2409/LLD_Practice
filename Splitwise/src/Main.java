import entity.User;
import enums.SplitType;
import repo.GroupRepo;
import services.BalanceSheetService;
import services.DebtSimplificationService;
import services.ExpenseService;
import services.GroupService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        User shubh = new User("Shubh", "Shubh");
        User bob   = new User("Bob", "Bob");
        User tom   = new User("Tom", "Tom");
        User jake  = new User("Jake", "Jake");

        GroupRepo repo=new GroupRepo();
        BalanceSheetService balanceSheetService = new BalanceSheetService();
        ExpenseService expenseService = new ExpenseService(balanceSheetService);
        DebtSimplificationService simplificationService = new DebtSimplificationService();

        GroupService groupService = new GroupService(repo, expenseService,simplificationService);

        /* ---------- create groups ---------- */
        String goaGroupId = groupService.createGroup("Goa Trip", List.of(shubh, bob, tom));
        String miscGroup  = groupService.createGroup("Non-Group Expenses", List.of(shubh, bob, tom, jake));

        /* ---------- add expenses ---------- */
        groupService.addExpense(goaGroupId,
                "Lunch Day-1", 100, shubh,
                List.of(shubh, bob), SplitType.EQUAL, null);

        groupService.addExpense(goaGroupId,
                "Lunch Day-2", 100, bob,
                List.of(bob, tom), SplitType.EQUAL, null);

        System.out.println("printBalance before simplification");

        groupService.printBalances(goaGroupId);

        System.out.println("printBalance after simplification");

        /* ---------- simplify & print ---------- */
        groupService.simplifyDebts(goaGroupId);
        groupService.printBalances(goaGroupId);
    }
    }

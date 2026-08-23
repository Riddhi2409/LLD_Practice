package entity;

import enums.SplitType;
import factory.SplitStrategyFactory;
import strategy.SplitStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Expense {
    private final String expenseId;
    private final String description;
    private final User paidBy;
    private final List<Split> splits;
    private final LocalDateTime createdAt;
    private final double amount;

    private Expense(ExpenseBuilder builder) {
        this.expenseId = builder.id;
        this.description = builder.description;
        this.amount = builder.amount;
        this.paidBy = builder.paidBy;
        this.createdAt = LocalDateTime.now();

        // Use the strategy to calculate splits
        SplitStrategy strategy =
                SplitStrategyFactory.getStrategy(builder.splitType);

        this.splits = strategy.calculateSplits(
                amount,
                builder.participants,
                builder.splitValues
        );
    }

        // Getters...
    public String getExpenseId() { return expenseId; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public User getPaidBy() { return paidBy; }
    public List<Split> getSplits() { return splits; }


    public static class ExpenseBuilder{
        private String id;
        private String description;
        private double amount;
        private User paidBy;
        private List<User> participants;
        private SplitType splitType;
        private List<Double> splitValues; // For EXACT and PERCENTAGE splits
        private Group group;

        public ExpenseBuilder setId(String id) { this.id = id; return this; }
        public ExpenseBuilder setDescription(String description) { this.description = description; return this; }
        public ExpenseBuilder setAmount(double amount) { this.amount = amount; return this; }
        public ExpenseBuilder setPaidBy(User paidBy) { this.paidBy = paidBy; return this; }
        public ExpenseBuilder setParticipants(List<User> participants) { this.participants = participants; return this; }
        public ExpenseBuilder setSplitType(SplitType splitType) { this.splitType = splitType; return this; }
        public ExpenseBuilder setSplitValues(List<Double> splitValues) { this.splitValues = splitValues; return this; }
        public ExpenseBuilder setGroup(Group group){this.group=group;return this;}
        public Expense build() {
            // Validations
            if (splitType == null) {
                throw new IllegalStateException("SplitType is required.");
            }
            return new Expense(this);
        }

    }

}

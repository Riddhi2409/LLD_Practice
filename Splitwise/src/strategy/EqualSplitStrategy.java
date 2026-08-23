package strategy;

import entity.Split;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy{

    @Override
    public List<Split> calculateSplits(double totalAmount, List<User> participants, List<Double> splitValues) {
        List<Split> splits=new ArrayList<>();
        double amountPerPerson = totalAmount / participants.size();
        for(User participant: participants){
            splits.add(new Split(participant,amountPerPerson));
        }
        return splits;
    }
}

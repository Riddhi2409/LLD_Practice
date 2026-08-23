package strategy;

import entity.Split;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class PercentageSplitStrategy implements SplitStrategy{
    @Override
    public List<Split> calculateSplits(double totalAmount, List<User> participants, List<Double> splitValues) {
        if(participants.size()!=splitValues.size()){
            throw new IllegalArgumentException("Number of participants and split values must match.");
        }
        if(Math.abs(splitValues.stream().mapToDouble(Double::doubleValue).sum()-100.0)>0.1){
            throw new IllegalArgumentException("Sum of percentages must be 100.");
        }
        List<Split> splits=new ArrayList<>();
        int n=participants.size();
        for(int i=0;i<n;i++){
            User participant=participants.get(i);
            double percent=splitValues.get(i);
            double amount= totalAmount * (percent/100);
            splits.add(new Split(participant,amount));
        }
        return splits;
    }
}

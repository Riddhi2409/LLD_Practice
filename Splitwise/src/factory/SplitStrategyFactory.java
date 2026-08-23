package factory;

import enums.SplitType;
import strategy.EqualSplitStrategy;
import strategy.ExactSplitStrategy;
import strategy.PercentageSplitStrategy;
import strategy.SplitStrategy;

public class SplitStrategyFactory {
    public static SplitStrategy getStrategy(SplitType splitType){
        if(splitType==SplitType.EQUAL){
            return new EqualSplitStrategy();
        }
        else if(splitType == SplitType.EXACT){
            return new ExactSplitStrategy();
        }
        else if(splitType == SplitType.PERCENTAGE){
            return new PercentageSplitStrategy();
        }
        throw new IllegalArgumentException("Invalid split type");
    }
}

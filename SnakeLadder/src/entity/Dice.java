package entity;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    int countDice;
    int min;
    int max;

    public Dice(int countDice, int min, int max) {
        this.countDice = countDice;
        this.min = min;
        this.max = max;
    }

    public int rollDice(){
       int sum=0;
       for(int i=0;i<countDice;i++){
           int randomNum= ThreadLocalRandom.current().nextInt(min,max+1);
           sum+=randomNum;
       }
       return sum;
    }
}

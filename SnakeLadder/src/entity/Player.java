package entity;

public class Player {
    String name;
    int currPos;

    public Player(String name) {
        this.name = name;
        this.currPos = 0;
    }

    public void setCurrPos(int currPos) {
        this.currPos = currPos;
    }

    public String getName() {
        return name;
    }

    public int getCurrPos() {
        return currPos;
    }
}

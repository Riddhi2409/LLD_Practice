package entity;

public class Cell {
    int pos;
    Jump jump;

    public Cell(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public Jump getJump() {
        return jump;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setJump(Jump jump) {
        this.jump = jump;
    }
}

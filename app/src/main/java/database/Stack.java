package database;

public class Stack {

    private int toteLevel = 0;
    private int containerLevel = 0;
    private boolean noodle = false;

    public int getToteLevel() {
        return toteLevel;
    }

    public void setToteLevel(int toteLevel) {
        this.toteLevel = toteLevel;
    }

    public int getContainerLevel() {
        return containerLevel;
    }

    public void setContainerLevel(int containerLevel) {
        this.containerLevel = containerLevel;
    }

    public boolean isNoodle() {
        return noodle;
    }

    public void setNoodle(boolean noodle) {
        this.noodle = noodle;
    }


}

package wordPuzzle;

public class Word {
    private String text;
    private int startX;
    private int startY;
    private Orientation orientation;
    private boolean isFound;

    public enum Orientation {
        HORIZONTALleft, HORIZONTALright,VERTICALup, VERTICALdown, DIAGONALleftdown, DIAGONALleftup, DIAGONALrightup, DIAGONALrightdown
    }

    public Word(String text, int startX, int startY, Orientation orientation) {
        this.text = text;
        this.startX = startX;
        this.startY = startY;
        this.orientation = orientation;
        this.isFound = false;
    }

    public String getText() {
        return text;
    }
    public int getStartX() {
        return startX;
    }
    public int getStartY() {
        return startY;
    }
    public Orientation getOrientation() {
        return orientation;
    }
    public boolean getFound() {
        return isFound;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
    public void setStartX(int startX) {
        this.startX = startX;
    }
    public void setStartY(int startY) {
        this.startY = startY;
    }
    public void setFound(boolean isFound) {
        this.isFound=isFound;
    }
}

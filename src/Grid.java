package wordPuzzle;

import java.util.Random;

public class Grid {
    private char[][] grid;
    private int width;
    private int height;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];
        initializeGrid();
    }
        
    public int getWidth() {
    return this.width;
    }
    
    public int getHeight() {
    	return this.height;
    }
      
    public void initializeGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = '_';
            }
        }
    }

    public void fillEmptySpaces() {
        Random rand = new Random();
    	for (int i =0; i < this.height; i++) {
    		for (int j=0; j< this.width; j++) {
    			if (this.grid[i][j] == '_') {
        			this.grid[i][j] = (char)(rand.nextInt(26) + 65);	
    			}		
    		}
    	}
    }

    public void rotateColumnUp(int colIndex){
        // Make sure colIndex is wwithin bounds
        if (colIndex < 0 || colIndex >= width){
            throw new IllegalArgumentException("Invalid column index");
        }

        // Perform rotation
        char top = grid[0][colIndex];
        for (int i = 0; i < height-1; i++){
            grid[i][colIndex]=grid[i+1][colIndex];
        }   
        grid[height-1][colIndex] = top;
    }

    public void rotateColumnDown(int colIndex){
        // Make sure colIndex is wwithin bounds
        if (colIndex < 0 || colIndex >= width){
            throw new IllegalArgumentException("Invalid column index");
        }

        // Perform rotation
        char bottom = grid[height-1][colIndex];
        for (int i = height-1; i > 0; i--){
            grid[i][colIndex]=grid[i-1][colIndex];
        }   
        grid[0][colIndex] = bottom;
    }

    public void rotateRowLeft(int rowIndex){
        // Make sure rowIndex is within bounds
        if (rowIndex < 0 || rowIndex >= height){
            throw new IllegalArgumentException("Invalid row index");
        }

        // Perform rotation
        char left = grid[rowIndex][0];
        for (int i = 0; i < width-1; i++){
            grid[rowIndex][i]=grid[rowIndex][i+1];
        }   
        grid[rowIndex][width-1] = left;
    }

    public void rotateRowRight(int rowIndex){
        // Make sure rowIndex is within bounds
        if (rowIndex < 0 || rowIndex >= height){
            throw new IllegalArgumentException("Invalid row index");
        }

        // Perform rotation
        char right = grid[rowIndex][width-1];
        for (int i = width-1; i > 0; i--){
            grid[rowIndex][i]=grid[rowIndex][i-1];
        }   
        grid[rowIndex][0] = right;
    }

    public void randomRotate() {
        Random randomNumbers = new Random();
        int x = randomNumbers.nextInt(4);
        int w = randomNumbers.nextInt(width);
        int h = randomNumbers.nextInt(height);
        switch (x) {
            case 0:
                rotateColumnDown(w);
                break;
            case 1:
                rotateColumnUp(w);
                break;
            case 2:
                rotateRowLeft(h);
                break;
            case 3:
                rotateRowRight(h);
                break;
        }
    }

    public boolean isPlacementValid(Word word) {
        String text=word.getText();
        int length=text.length();
        int x=word.getStartX();
        int y=word.getStartY();
        Word.Orientation orientation=word.getOrientation();
        IllegalArgumentException error = new IllegalArgumentException("Word writing goes out of bounds");
        switch (orientation) {
            case  HORIZONTALleft :
                if (y-(length-1) < 0) {
                    throw error;
                }
                for (int i=0; i < length; i++) {
                    if (grid[x][y-i] != '_') {
                        throw error;
                    }
                }
                break;
            case HORIZONTALright :
                if ( y + (length-1) >= width) {
                    throw error;
                }
                for (int i=0; i < length; i++) {
                    if (grid[x][y+i] != '_') {
                        throw error;
                    }
                }
                break;
            case  VERTICALup :
                if (x - (length-1) < 0) {
                    throw error;
                }
                for (int i=0; i < length; i++) {
                    if (grid[x-i][y] != '_') {
                        throw error;
                    }
                }
                break;
            case VERTICALdown :
                if (x + (length-1) >= height) {
                    throw error;
                }
                for (int i=0; i < length; i++) {
                    if (grid[x+i][y] != '_') {
                        throw error;
                    }
                }
                break;
            case DIAGONALleftdown :
                if (x + (length-1) >= height || y - (length-1) < 0 ) {
                    throw error;
                }
                for (int i=0; i<length; i++) {
                    if (grid[x+i][y-i] != '_') {
                        throw error;
                    }
                }
                break;
            case DIAGONALleftup :
                if (x - (length-1) < 0 || y - (length-1) < 0 ) {
                    throw error;
                }
                for (int i=0; i<length; i++) {
                    if (grid[x-i][y-i] != '_') {
                        throw error;
                    }
                }
                break;
            case DIAGONALrightup :
                if (x - (length-1) < 0 || y + (length-1) >= width ) {
                    throw error;
                }
                for (int i=0; i<length; i++) {
                    if (grid[x-i][y+i] != '_') {
                        throw error;
                    }
                }
                break;
            case DIAGONALrightdown :

                if (x + (length-1) >= height || y + (length-1) >= width ) {
                    throw error;
                }
                for (int i=0; i<length; i++) {
                    if (grid[x+i][y+i] != '_') {
                        throw error;
                    }
                }
                break;
        }
        return true;
    }
   
    public void placeWord(Word word) {
        Word.Orientation orientation = word.getOrientation();
        String text = word.getText();
        int x=word.getStartX();
        int y=word.getStartY();
        int length = text.length();
        switch (orientation) {
            case  HORIZONTALleft :
                for (int i=0; i<length; i++) {
                    grid[x][y-i]=text.charAt(i);
                }
                break;
            case HORIZONTALright :
                for (int i=0; i<length; i++) {
                    grid[x][y+i]=text.charAt(i);
                }
                break;
            case  VERTICALup :
                for (int i=0; i<length; i++) {
                    grid[x-i][y]=text.charAt(i);
                }
                break;
            case VERTICALdown :
                for (int i=0; i<length; i++) {
                    grid[x+i][y]=text.charAt(i);
                }
                break;
            case DIAGONALleftdown :
                for (int i=0; i<length; i++) {
                    grid[x+i][y-i]=text.charAt(i);
                }
                break;
            case DIAGONALleftup :
                for (int i=0; i<length; i++) {
                    grid[x-i][y-i]=text.charAt(i);
                }
                break;
            case DIAGONALrightup :
                for (int i=0; i<length; i++) {
                    grid[x-i][y+i]=text.charAt(i);
                }
                break;
            case DIAGONALrightdown :
                for (int i=0; i<length; i++) {
                    grid[x+i][y+i]=text.charAt(i);
                }
                break;
        }
    }
    
    public void changePlacement(Word word) {
        int x=word.getStartX();
        int y=word.getStartY();
        Word.Orientation orientation=word.getOrientation();
        switch (orientation) {
            case VERTICALdown :
                word.setOrientation(Word.Orientation.DIAGONALleftdown);
                break;
            case DIAGONALleftdown :
                word.setOrientation(Word.Orientation.HORIZONTALleft);
                break;
            case  HORIZONTALleft :
                word.setOrientation(Word.Orientation.DIAGONALleftup);
                break;
            case DIAGONALleftup :
                word.setOrientation(Word.Orientation.VERTICALup);
                break;
            case  VERTICALup :
                word.setOrientation(Word.Orientation.DIAGONALrightup);
                break;
            case DIAGONALrightup :
                word.setOrientation(Word.Orientation.HORIZONTALright);
                break;
            case HORIZONTALright :
                word.setOrientation(Word.Orientation.DIAGONALrightdown);
                break;     
            case DIAGONALrightdown :
                word.setOrientation(Word.Orientation.VERTICALdown);
                Random randomNumbers = new Random();
                y=randomNumbers.nextInt(width);
                x=randomNumbers.nextInt(height);
                word.setStartX(x);
                word.setStartY(y);
                break;
        }
    }

    public void brutForce(Word word) {
        boolean placed = false;
        int attempts = 0;
        int maxAttempts = 100; // arbitraire
    
        while (!placed && attempts < maxAttempts) {
            try {
                isPlacementValid(word);
                placeWord(word);
                placed = true;
            } catch (IllegalArgumentException error) {
                changePlacement(word);
                attempts++;
            }
        }
    
        if (!placed) {
            throw new RuntimeException("Could not place the word: " + word.getText());
        }
    }
    
    public char[][] getGrid() {
        return grid;
    }

}
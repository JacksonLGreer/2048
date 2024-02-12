import java.lang.Math;

public class board {
    
    cell cells[][] = new cell[4][4];

    public board() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] = new cell();
                cells[i][j].val = 0;
                cells[i][j].filled = false;
            }
        }
    } // board

    public int random() {
        return (int) ((Math.random() * (4-0)) + 0);
    } // random

    /**
     * Adds a 2 or 4 to a random cell that is currently empty.
     * @return 1 on success
     */
    public int addRandom() {
        
        int i1 = random();
        int i2 = random();

        int seed = random();

        if (cells[i1][i2].filled == false) {
            if (seed == 3) {
                cells[i1][i2].val = 4;
                cells[i1][i2].filled = true;
            } else {
                cells[i1][i2].val = 2;
                cells[i1][i2].filled = true;
            }
        } else {
            addRandom();
        }
        return 1;
    } // addRandom

    public void printBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(cells[i][j].val);
            }
            System.out.println();
        }
    } // printBoard

    public Boolean filled() {
        int check = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cells[i][j].val != 0) {
                    check++;
                }
            }
        }
        if (check == 16) {
            return true;
        } else {
            return false;
        }
    }

    public int shiftRight() {
        
        for (int i = 0; i < 4; i++) { //four rows
            
        }
        
        return 1;
    }
}

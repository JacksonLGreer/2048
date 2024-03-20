import java.lang.Math;

public class board {
    
    cell cells[][] = new cell[4][4];

    /**
     * Board constructor fills cells with 0
     */
    public board() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] = new cell();
                cells[i][j].val = 0;
                cells[i][j].filled = false;
            }
        }
    } // board

    /**
     * Generate random number 0 - 4
     * @return random num
     */
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

    /**
     * Prints the board in a 4x4 grid.
     */
    public void printBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(cells[i][j].val);
            }
            System.out.println();
        }
    } // printBoard

    /**
     * Checks if the board is completely full.
     * @return true or false if full or not
     */
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

    /**
     * Shift board right 
     * @return 1 on success
     */
    public int shiftRight() {
        int ret = 0;
        int farRight = 3;
        for (int i = 0; i < 4; i++) { //four rows
            if (this.rowFull(i) == -1) { // if row is empty nothing has to be done
                ret = 1;
            } else if (this.rowFull(i) == 1) { // row is filled
                
            } else { // row is partially filled

            }
        }
        
        return 1;
    }

    /**
     * Checks if the cell to the right of the xy coord is full
     * @param x - row
     * @param y - col
     * @return 0 if empty, 1 if full, 2 if on right edge
     */
    public int checkRight(int x, int y) {
        int ret = 0;
        if (y == 3) {
            ret = 2;
        } else if (this.cells[x][y+1].val > 0) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Checks if a row is full.
     * @param row - the row to be examined
     * @return -1 if empty, 0 if not full, 1 if full
     */
    public int rowFull(int row) {
        int ret = 0;
        if (cells[row][0].val == 0 && cells[row][1].val == 0 && cells[row][2].val == 0 && cells[row][3].val == 0) {
            ret = -1; // empty row
        } else if (cells[row][0].val > 0 && cells[row][1].val > 0 && cells[row][2].val > 0 && cells[row][3].val > 0) {
            ret = 1; // full row
        } else {
            ret = 0; // partially full
        }
        return ret;
    } // rowFull
}

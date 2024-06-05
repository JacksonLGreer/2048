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
     * Sets the 'locked' status of all cells to false.
     * @return 1
     */
    public int lockClear() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.cells[i][j].locked = false;
            }
        }
        return 1;
    }
    /**
     * Shift board right 
     * @return 1 on success
     */
    public int shiftRight() {
        int ret = 0;
        this.lockClear();
        for (int i = 0; i <= 3; i++) { // rows
            for (int j = 3; j >= 0; j--) { // cells
                if (checkRight(i,j) == 2) { // if 2 then on right edge, do nothing, no lock
                    ret = 1;
                } else if (checkRight(i,j) == 1) { // not on edge, but right is filled
                    if (this.cells[i][j+1].val == this.cells[i][j].val && this.cells[i][j+1].locked == false) { // two next to each other are equal
                        this.cells[i][j+1].val *= 2; // double right
                        this.cells[i][j].val = 0; // left equal 0
                        this.cells[i][j].filled = false; // left is empty
                        this.cells[i][j+1].locked = true; // right is locked
                    } else { // two are not equal, do nothing
                        ret = 1;
                    }
                } else if (checkRight(i,j) == 0) {
                    int t = 0; // counter for movement
                    while (checkRight(i,j+t) == 0) {
                        this.cells[i][j+1+t].val = this.cells[i][j+t].val; // set right to left
                        this.cells[i][j+1+t].filled = true; // set right to filled
                        this.cells[i][j+t].val = 0; // set left to 0 and empty
                        this.cells[i][j+t].filled = false;
                        t++;
                        if (checkRight(i,j+t) == 1) { // copy of right being filled, makes sure two numbers with space between can merge
                            if (this.cells[i][j+1+t].val == this.cells[i][j+t].val && this.cells[i][j+1+t].locked == false) { // two next to each other are equal
                                this.cells[i][j+1+t].val *= 2; // double right
                                this.cells[i][j+t].val = 0; // left equal 0
                                this.cells[i][j+t].filled = false; // left is empty
                                this.cells[i][j+1+t].locked = true; // right is locked
                            } else { // two are not equal, do nothing
                                ret = 1;
                            }
                        }
                    }
                }

            }
        }
        return ret;
    }



    /**
     * Shift board left 
     * @return 1 on success
     */
    public int shiftLeft() {
        int ret = 0;
        this.lockClear();
        for (int i = 3; i >= 0; i--) { // rows
            for (int j = 0; j <= 3; j++) { // cells
                if (checkLeft(i,j) == 2) { // if 2 then on left edge, do nothing, no lock
                    ret = 1;
                } else if (checkLeft(i,j) == 1) { // not on edge, but left is filled
                    if (this.cells[i][j-1].val == this.cells[i][j].val && this.cells[i][j-1].locked == false) { // two next to each other are equal
                        this.cells[i][j-1].val *= 2; // double right
                        this.cells[i][j].val = 0; // left equal 0
                        this.cells[i][j].filled = false; // left is empty
                        this.cells[i][j-1].locked = true; // right is locked
                    } else { // two are not equal, do nothing
                        ret = 1;
                    }
                } else if (checkLeft(i,j) == 0) {
                    int t = 0; // counter for movement
                    while (checkLeft(i,j-t) == 0) {
                        this.cells[i][j-1-t].val = this.cells[i][j-t].val; // set right to left
                        this.cells[i][j-1-t].filled = true; // set right to filled
                        this.cells[i][j-t].val = 0; // set left to 0 and empty
                        this.cells[i][j-t].filled = false;
                        t++;
                        if (checkLeft(i,j-t) == 1) { // copy of right being filled, makes sure two numbers with space between can merge
                            if (this.cells[i][j-1-t].val == this.cells[i][j-t].val && this.cells[i][j-1-t].locked == false) { // two next to each other are equal
                                this.cells[i][j-1-t].val *= 2; // double right
                                this.cells[i][j-t].val = 0; // left equal 0
                                this.cells[i][j-t].filled = false; // left is empty
                                this.cells[i][j-1-t].locked = true; // right is locked
                            } else { // two are not equal, do nothing
                                ret = 1;
                            }
                        }
                    }
                }

            }
        }
        return ret;
    }

        /**
     * Shift board down 
     * @return 1 on success
     */
    public int shiftDown() {
        int ret = 0;
        this.lockClear();
        for (int j = 0; j <= 3; j++) { // rows
            for (int i = 0; i <= 3; i++) { // cells
                if (checkDown(i,j) == 2) { // if 2 then on right edge, do nothing, no lock
                    ret = 1;
                } else if (checkDown(i,j) == 1) { // not on edge, but right is filled
                    if (this.cells[i+1][j].val == this.cells[i][j].val && this.cells[i+1][j].locked == false) { // two next to each other are equal
                        this.cells[i+1][j].val *= 2; // double right
                        this.cells[i][j].val = 0; // left equal 0
                        this.cells[i][j].filled = false; // left is empty
                        this.cells[i+1][j].locked = true; // right is locked
                    } else { // two are not equal, do nothing
                        ret = 1;
                    }
                } else if (checkDown(i,j) == 0) {
                    int t = 0; // counter for movement
                    while (checkDown(i+t,j) == 0) {
                        this.cells[i+1+t][j].val = this.cells[i+t][j].val; // set right to left
                        this.cells[i+1+t][j].filled = true; // set right to filled
                        this.cells[i+t][j].val = 0; // set left to 0 and empty
                        this.cells[i+t][j].filled = false;
                        t++;
                        if (checkDown(i+t,j) == 1) { // copy of right being filled, makes sure two numbers with space between can merge
                            if (this.cells[i+1+t][j].val == this.cells[i+t][j].val && this.cells[i+1+t][j].locked == false) { // two next to each other are equal
                                this.cells[i+1+t][j].val *= 2; // double right
                                this.cells[i+t][j].val = 0; // left equal 0
                                this.cells[i+t][j].filled = false; // left is empty
                                this.cells[i+1+t][j].locked = true; // right is locked
                            } else { // two are not equal, do nothing
                                ret = 1;
                            }
                        }
                    }
                }

            }
        }
        return ret;
    }

      /**
     * Shift board down 
     * @return 1 on success
     */
    public int shiftUp() {
        int ret = 0;
        this.lockClear();
        for (int j = 3; j >= 0; j--) { // rows
            for (int i = 3; i >= 0; i--) { // cells
                if (checkUp(i,j) == 2) { // if 2 then on right edge, do nothing, no lock
                    ret = 1;
                } else if (checkUp(i,j) == 1) { // not on edge, but right is filled
                    if (this.cells[i-1][j].val == this.cells[i][j].val && this.cells[i-1][j].locked == false) { // two next to each other are equal
                        this.cells[i-1][j].val *= 2; // double right
                        this.cells[i][j].val = 0; // left equal 0
                        this.cells[i][j].filled = false; // left is empty
                        this.cells[i-1][j].locked = true; // right is locked
                    } else { // two are not equal, do nothing
                        ret = 1;
                    }
                } else if (checkUp(i,j) == 0) {
                    int t = 0; // counter for movement
                    while (checkUp(i-t,j) == 0) {
                        this.cells[i-1-t][j].val = this.cells[i-t][j].val; // set right to left
                        this.cells[i-1-t][j].filled = true; // set right to filled
                        this.cells[i-t][j].val = 0; // set left to 0 and empty
                        this.cells[i-t][j].filled = false;
                        t++;
                        if (checkUp(i-t,j) == 1) { // copy of right being filled, makes sure two numbers with space between can merge
                            if (this.cells[i-1-t][j].val == this.cells[i-t][j].val && this.cells[i-1-t][j].locked == false) { // two next to each other are equal
                                this.cells[i-1-t][j].val *= 2; // double right
                                this.cells[i-t][j].val = 0; // left equal 0
                                this.cells[i-t][j].filled = false; // left is empty
                                this.cells[i-1-t][j].locked = true; // right is locked
                            } else { // two are not equal, do nothing
                                ret = 1;
                            }
                        }
                    }
                }

            }
        }
        return ret;
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
     * Checks if the cell to the left of the xy coord is full
     * @param x - row
     * @param y - col
     * @return 0 if empty, 1 if full, 2 if on left edge
     */
    public int checkLeft(int x, int y) {
        int ret = 0;
        if (y == 0) {
            ret = 2;
        } else if (this.cells[x][y-1].val > 0) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Checks if the cell to the up of the xy coord is full
     * @param x - row
     * @param y - col
     * @return 0 if empty, 1 if full, 2 if on top edge
     */
    public int checkUp(int x, int y) {
        int ret = 0;
        if (x == 0) {
            ret = 2;
        } else if (this.cells[x-1][y].val > 0) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Checks if the cell to the bottom of the xy coord is full
     * @param x - row
     * @param y - col
     * @return 0 if empty, 1 if full, 2 if on bottom edge
     */
    public int checkDown(int x, int y) {
        int ret = 0;
        if (x == 3) {
            ret = 2;
        } else if (this.cells[x+1][y].val > 0) {
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

public class driver {
    
    public static void main(String args[]) {
        board b1 = new board();
        b1.printBoard();
        Boolean loop = false;
        

        
        while (loop == false) {
            System.out.println();
            b1.addRandom();
            b1.printBoard();
            if (b1.filled() == true) {
                loop = true;
            }
        }
        
        

    }
}

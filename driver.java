import java.util.Scanner;

public class driver {
    
    public static void main(String args[]) {
        board b1 = new board();
        b1.printBoard();
        Boolean loop = false;
        

        
        while (loop == false) {
            System.out.println();
            b1.addRandom();
            b1.printBoard();
            int key = keypress();
            System.out.println(key);
            if (key == 1) {
                b1.shiftUp();
            } else if (key == 2) {
                b1.shiftLeft();
            } else if (key == 3) {
                b1.shiftDown();
            } else if (key == 4) {
                b1.shiftRight();
            } else {
                System.out.println("INVALID");
            }
            if (b1.filled() == true) {
                loop = true;
            }
        }
        
        

    }

    public static int keypress() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("WASD");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("w")) {
            return 1;
        } else if (input.equalsIgnoreCase("a")) {
            return 2;
        } else if (input.equalsIgnoreCase("s")) {
            return 3;
        } else if (input.equalsIgnoreCase("d")) {
            return 4;
        } else {
            return 0;
        }
    }
}

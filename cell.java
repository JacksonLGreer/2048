public class cell {
    
    int val;
    Boolean filled;
    Boolean locked;

    public cell() {
        val = 0;
        filled = false;
        locked = false;
    } // cell

    public Boolean fillStatus() {
        if (this.val != 0) {
            this.filled = true;
        } else {
            this.filled = false;
        }
        return filled;
    } // fillStatus

    

    
}

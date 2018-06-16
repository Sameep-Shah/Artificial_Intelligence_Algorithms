
/**
 *
 * @author sameepshah
 */
public class Queen {


    private int row;
    private int column;
    
    public Queen(int a, int b){
		row = a;
		column  = b;
	}

    public boolean canAttack(Queen q) {
        boolean c = false;

        if (row == q.getRow() || column == q.getColumn()) {
            c = true;
        }
        else if (Math.abs(column - q.getColumn()) == Math.abs(row - q.getRow())) {
            c = true;
        }

        return c;
    }
    // method for making queen move
    public void moveDown(int spaces){
		row = row+spaces;
		
		
		if(row>7 && row%7!=0){
			row=(row%7)-1;
		}
		else if(row>7 && row%7==0){
			row=7;
		}
	}

    public void setRow(int a) {
        this.row = a;
    }

    public void setColumn(int b) {
        this.column = b;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
     @Override
    public String toString() {
        return "Queen{" + "row=" + row + ", column=" + column + '}';
    }

}

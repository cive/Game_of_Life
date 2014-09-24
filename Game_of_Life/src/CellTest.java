import java.awt.Point;

public class CellTest {
	static Cells cells;
	public static void main(String args[]){
		Point field_size = new Point(2, 4);
		cells = new Cells(field_size);
		cells.setCell(0, 0, true);
		cells.setCell(0, 1, true);
		cells.setCell(0, 2, true);
		print_cell();
		cells.nextState();
		print_cell();
	}
	static void print_cell(){
		Point field_size = cells.getField_size();
		for(int y = 0; y < field_size.y; y++){	//printは配列を処理するので仕方ない…
			for(int x = 0; x < field_size.x; x++){
				if(cells.getCell(x, y))
					System.out.print("■");
				else
					System.out.print("□");
			}
			System.out.println();
		}
		System.out.println("----------");
	}
}

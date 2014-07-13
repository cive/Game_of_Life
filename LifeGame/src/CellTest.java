import java.awt.Point;

public class CellTest {
	static Cells cellss;
	public static void main(String args[]){
		Point p = new Point(2, 4);
		cellss = new Cells(p);
		cellss.setCell(0, 0, true);
		cellss.setCell(0, 1, true);
		cellss.setCell(0, 2, true);
		print_cell();
		cellss.nextState();
		print_cell();
	}
	static void print_cell(){
		Point p = cellss.getSize();
		for(int y = 0; y < p.y; y++){	//print‚È‚Ì‚ÅA‚±‚±‘‚«•û‚µ‚©‚È‚¢
			for(int x = 0; x < p.x; x++){
				if(cellss.getCell(x, y))
					System.out.print("¡");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println("----------");
	}
}

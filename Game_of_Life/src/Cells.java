import java.awt.Point;
/*
 * This class is the structure of the cells in the Game of Life.
 */
class Cells extends Thread{
	private boolean Cell[][];
	private Point field_size;
	private boolean auto_mode = false;
	private int wait_time = 1000;
	Cells(int x, int y){
		field_size = new Point(x, y);
		Cell = new boolean[y][x];
		// init of the cells
		clear();
	}
	Cells(){
		this(new Point(80, 80));
	}
	Cells(Point p){
		this(p.x, p.y);
	}
	public void run(){
		while(true){
			if(auto_mode)nextState();
			try{
				Thread.sleep(wait_time);
			} catch (InterruptedException ie){
				System.out.println("Cells class InterruptException");
			}
		}
	}
	void setMode(boolean bool){
		auto_mode = bool;
	}
	void setCell(int x, int y, boolean bool){
		Cell[y][x] = bool;
	}
	boolean getCell(int x, int y){
		return Cell[y][x];
	}
	boolean getCell(Point p){
		return getCell(p.x, p.y);
	}
	Point getField_size(){
		return field_size;
	}
	void setWait_time(int wait_time){
		this.wait_time = wait_time;
	}
	void clear(){
		for(int y = 0; y < field_size.y; y++)
			for(int x = 0; x < field_size.x; x++)
				Cell[y][x] = false;
	};
	void copyCellsStateFrom(Cells cells){
		for(int y = 0; y < Cell.length; y++)
			for(int x = 0; x < Cell[y].length; x++)
				Cell[y][x] = cells.getCell(x, y);
	}; // return boolean[][]
	void nextState(){
		Cells pre_Cells_State = new Cells(field_size.x, field_size.y);
		pre_Cells_State.copyCellsStateFrom(this);
		for(int x = 0; x < field_size.x; x++){for(int y = 0; y < field_size.x; y++){
			int neighboring_number = 0;
			int xLeft = ((x-1)% field_size.x < 0)? field_size.x-1:(x-1)% field_size.x;
			int yTop = ((y-1)% field_size.y < 0)? field_size.y-1:(y-1)% field_size.y;
			int xRight = (x+1)% field_size.x;
			int yBottom = (y+1)% field_size.y;
			Point moore_neighborhood[] = {new Point(xLeft, y),
				new Point(xLeft,yTop), new Point(x,yTop),
				new Point(xRight,yTop), new Point(xRight,y),
				new Point(xRight,yBottom), new Point(x, yBottom),
				new Point(xLeft,yBottom)
			};
			// セル近傍の判定
			for(int k = 0; k < 8; k++){
				if(pre_Cells_State.getCell(moore_neighborhood[k]))
					neighboring_number++;
			}
			if(pre_Cells_State.getCell(x,  y)){
				// CELL'S PRE-STATE : ALIVE
				if(neighboring_number < 2 || neighboring_number > 3)this.setCell(x, y, false);
				else this.setCell(x, y, true);
			} else {
				// CELL'S PRE-STATE : DEAD
				if(neighboring_number == 3)this.setCell(x, y, true);
				else this.setCell(x, y, false);
			}}
		}
	};
}
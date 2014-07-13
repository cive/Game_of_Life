import java.awt.Point;


class Cells extends Thread{
	private boolean Cell[][];
	private Point Size;
	private boolean auto_mode = false;
	private int wait_time = 1000;
	Cells(int x, int y){
		Size = new Point(x, y);
		Cell = new boolean[y][x];
		// セルの初期化
		clear();
	}
	Cells(){
		this(new Point(200, 200));
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
				System.out.println("test2 InterruptException");
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
	Point getSize(){
		return Size;
	}
	void setWait_time(int wait_time){
		this.wait_time = wait_time;
	}
	void clear(){
		for(int y = 0; y < Size.y; y++)
			for(int x = 0; x < Size.x; x++)
				Cell[y][x] = false;
	};
	void copy(Cells cells){
		for(int y = 0; y < Cell.length; y++)
			for(int x = 0; x < Cell[y].length; x++)
				Cell[y][x] = cells.getCell(x, y);
	}; // return boolean[][]
	void nextState(){
		Cells tmp_Cell = new Cells(Size.x, Size.y);
		tmp_Cell.copy(this);
		for(int x = 0; x < Size.x; x++){
			for(int y = 0; y < Size.x; y++){
				int neighbor = 0;
				// セル近傍の点を定義
				int xLeft = ((x-1)%Size.x < 0)?Size.x-1:(x-1)%Size.x;
				int yTop = ((y-1)%Size.y < 0)?Size.y-1:(y-1)%Size.y;
				int xRight = (x+1)%Size.x;
				int yBottom = (y+1)%Size.y;
				Point p[] = {new Point(xLeft, y),
							new Point(xLeft,yTop), new Point(x,yTop),
							new Point(xRight,yTop), new Point(xRight,y),
							new Point(xRight,yBottom), new Point(x, yBottom),
							new Point(xLeft,yBottom)
							};
				// セル近傍の判定
				for(int k = 0; k < 8; k++){
					if(tmp_Cell.getCell(p[k]))
						neighbor++;
				}
				if(tmp_Cell.getCell( x,  y)){ 
					// MINE : ALIVE
					if(neighbor < 2 || neighbor > 3)this.setCell(x, y, false);
					else this.setCell(x, y, true);
				} else {                    
					// MINE : DEAD
					if(neighbor == 3)this.setCell(x, y, true);
					else this.setCell(x, y, false);
				}
			}
		}
	};
}
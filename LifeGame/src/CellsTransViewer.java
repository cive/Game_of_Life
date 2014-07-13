import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class CellsTransViewer extends JPanel implements MouseListener, MouseMotionListener, WindowListener, Runnable{
	/**
	 * LifeGameの表示部
	 */
	private static final long serialVersionUID = 1L;
	//private BufferedImage axis; // 直交軸的な
	private BufferedImage cellsImage;
	private Graphics2D offImage;
	private Cells cells;
	private Point Size_of_CellAutomata; //セルオートマトンの大きさ
	private final static int view_width = 450;
	private final static int view_height = 450;
	private final static int cell_size = 5;
	public CellsTransViewer(){
		this.addMouseListener(this);
		Size_of_CellAutomata = new Point(80, 80);
		this.setBounds(new Rectangle(0, 0, view_width, view_height));
		cells = new Cells(Size_of_CellAutomata);
		cells.start();
		cellsImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
		offImage = cellsImage.createGraphics();
		Thread refresh = new Thread(this);
		refresh.start();
	}
	
	public void paint(Graphics g){
		offImage.setColor(Color.BLACK);
		offImage.fillRect(0, 0, 400, 400);
		offImage.setColor(Color.WHITE);
		for(int x = 0; x < Size_of_CellAutomata.x; x++){
			for(int y = 0; y < Size_of_CellAutomata.y; y++){
				if(cells.getCell(x, y))
					offImage.fillRect(x*cell_size, y*cell_size, cell_size, cell_size);
			}
		}
		g.drawImage(cellsImage, 50, 50, this);
	}
	void setMode(boolean bool){
		cells.setMode(bool);
	}
	void nextState(){
		cells.nextState();
	}
	void setWait_time(int wait_time){
		cells.setWait_time(wait_time);
	}
	@Override
	public void mouseClicked(MouseEvent me) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		int x = (me.getX()-50)/cell_size;
		int y = (me.getY()-50)/cell_size;
		if(0<=x && x<=Size_of_CellAutomata.x && 0<=y && y<=Size_of_CellAutomata.y){
			cells.setCell(x, y, !cells.getCell(x, y));
		}
		repaint();	
	}

	@Override
	public void mouseReleased(MouseEvent me) {
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		repaint();
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		while(true){
			try{
				Thread.sleep(20);
			}catch(InterruptedException ie){
				System.out.println("InterruptedException on transView");
			}
			repaint();
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent me) {
	}

	@Override
	public void mouseMoved(MouseEvent me) {
	}

}

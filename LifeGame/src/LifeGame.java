import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class LifeGame extends JFrame implements WindowListener, ActionListener, Runnable{

	/**
	 * Cell Automata (Life game)
	 * 
	 * TODO: Graphics, ClickEvent, WaitTimeConfigText
	 */
	private JButton Auto_button;
	private JButton Next_button;
	private JButton Stop_button;
	private JTextField Time_text;
	private JButton Enter_button;
	private CellsTransViewer transView;
	private static final long serialVersionUID = 1L;
	public static void main(String args[]){
		new LifeGame();
	}
	public LifeGame(){
		transView = new CellsTransViewer();
		this.add(transView);
		this.setBounds(new Rectangle(0, 0, 600, 600));
		this.setLayout(null);
		Auto_button = new JButton("é©ìÆçƒê∂");
		Next_button = new JButton("éüÇ÷");
		Stop_button = new JButton("àÍéûí‚é~");
		Time_text = new JTextField("1000");
		Enter_button = new JButton("åàíË");
		Auto_button.setBounds(470, 20, 96, 24);
		this.add(Auto_button);
		Auto_button.addActionListener(this);
		Next_button.setBounds(470, 50, 96, 24);
		this.add(Next_button);
		Next_button.addActionListener(this);
		Stop_button.setBounds(470, 80, 96, 24);
		this.add(Stop_button);
		Stop_button.addActionListener(this);
		Time_text.setBounds(470, 110, 96, 24);
		this.add(Time_text);
		Enter_button.setBounds(470, 140, 96, 24);
		this.add(Enter_button);
		Enter_button.addActionListener(this);
		this.setVisible(true);
		Thread refresh = new Thread(this);
		refresh.start();
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
	public void actionPerformed(ActionEvent ae) {
		repaint();
		if(ae.getSource() == Auto_button){
			transView.setMode(true);
		} else if(ae.getSource() == Next_button){
			transView.setMode(false);
			transView.nextState();
		} else if(ae.getSource() == Stop_button){
			transView.setMode(false);
		} else if(ae.getSource() == Enter_button){
			transView.setWait_time(Integer.parseInt(Time_text.getText()));
		}
		
	}
	@Override
	public void run() {
		while(true){
			try{
				Thread.sleep(20);
			}catch(InterruptedException ie){
				System.out.println("InterruptedException on CellAutomata");
			}
			repaint();
		}
	}
}

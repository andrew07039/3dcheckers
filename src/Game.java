import java.awt.*;
import java.awt.event.*;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.*;
import javax.swing.JFrame;

import java.nio.ByteBuffer;


public class Game extends JFrame implements Runnable, GLEventListener, KeyListener, MouseListener, MouseWheelListener, MouseMotionListener, ActionListener {
	private final GLCanvas canvas;
	public static final int WIDTH =1260, HEIGHT = 720;
	public Thread gameThread;
	public static boolean running = false;
	

	
/*	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}
	*/
	public Game(){
		canvas = new GLCanvas();
		canvas.addGLEventListener(this);
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseWheelListener(this);
		canvas.addMouseMotionListener(this);
		getContentPane().add(canvas);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		canvas.requestFocus();
	}
	
	public synchronized void start(){
		if(running)return;
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public synchronized void stop(){
		if(!running)return;
		running=false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}
	}
	

	public void run() {
		while(running){
			
		}
		stop();
	}


	public void actionPerformed(ActionEvent arg0) {

		
	}


	public void mouseDragged(MouseEvent arg0) {
	
		
	}

	
	public void mouseMoved(MouseEvent arg0) {
	
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
	
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

		
	}

	@Override
	public void init(GLAutoDrawable drawable) {

		
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {

	}

	@Override
	public void display(GLAutoDrawable drawable) {
	
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		
	}

}

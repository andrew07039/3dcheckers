
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class TitleScreen extends JPanel implements ActionListener{
	private String name ="3DCheckers";
	private Container c;
	private JFrame menuscreen;
	private JButton startbutton, optionsbutton, exitbutton;
	
	public TitleScreen(){
		menuscreen = new JFrame("Checkers Menu Screen");
		startbutton = new JButton("Start Game");
		startbutton.setPreferredSize(new Dimension(100,100));
		startbutton.setBounds(500, 300, 100, 100);
		startbutton.addActionListener(this);
		menuscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = menuscreen.getContentPane();
		this.setLayout(null);
		this.add(startbutton);
		c.add(this);
		this.setPreferredSize(new Dimension(1260,720));
		menuscreen.pack();
		menuscreen.setBackground(Color.black);
		menuscreen.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
			Object a = ae.getSource();
			if(a==startbutton){
				this.menuscreen.dispose();
				new GameScreen();
				
			}
	}
	
	public void paintComponent(Graphics g){
			g.setFont(new Font(name, 10, 18));
			g.setColor(Color.white);
			g.drawString(name, 500, 20 );
	}		
	
/*	public static void main(String[] args){
		new TitleScreen();
	}*/
}

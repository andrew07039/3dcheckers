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


public class GameScreen {
	private JFrame gamescreen;
	
	public GameScreen(){
		gamescreen = new JFrame("3DCheckers");
		gamescreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamescreen.setSize(1260,720);
		gamescreen.setVisible(true);
		System.out.println("start button works");
	}
}

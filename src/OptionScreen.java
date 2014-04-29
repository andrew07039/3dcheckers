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

public class OptionScreen {
	private JFrame optionscreen;
	
	public OptionScreen(){
		optionscreen = new JFrame("Options");
		optionscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		optionscreen.setSize(1260,720);
		optionscreen.setVisible(true);
	}
}

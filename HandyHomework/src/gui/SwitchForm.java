package gui;

import java.awt.Frame;
import java.awt.Window;

import javax.swing.JFrame;

public class SwitchForm extends JFrame{
	public void switchForm(JFrame frame) {
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
}

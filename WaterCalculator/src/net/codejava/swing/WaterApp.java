package net.codejava.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WaterApp extends JFrame {

	private JPanel contentPane;
	private JTextField fieldWeight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WaterApp frame = new WaterApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WaterApp() {
		setTitle("Water Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 254, 137);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblHowMuchWater = new JLabel("How much water should I drink?");
		contentPane.add(lblHowMuchWater);
		
		JLabel lblMyWeightkg = new JLabel("My weight (Kg)");
		contentPane.add(lblMyWeightkg);
		
		fieldWeight = new JTextField();
		contentPane.add(fieldWeight);
		fieldWeight.setColumns(10);
		
		JButton buttonTellMe = new JButton("Tell me");
		buttonTellMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				float weight = Float.parseFloat(fieldWeight.getText());
				float waterAmount = CalculateWater(weight);
				String message = "Buddy, you should be drinking %.2f L of water a day!";
				message = message.format(message, waterAmount);
				JOptionPane.showMessageDialog(WaterApp.this, message);
			}
		});
		buttonTellMe.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(buttonTellMe);
	}

	private float CalculateWater(float weight) {
		return weight / 10f * 0.4f;
	}
}

package gui;

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
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import java.awt.Dimension;

public class HandyHomeworkMainPage extends JFrame {

	private JLayeredPane contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HandyHomeworkMainPage frame = new HandyHomeworkMainPage();
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
	public HandyHomeworkMainPage() {
		setTitle("HandyHomework");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JLayeredPane();
		contentPane.setMinimumSize(new Dimension(139, 23));
		contentPane.setMaximumSize(new Dimension(139, 23));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblWelcome = new JLabel("Choose one of the following:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWelcome, 156, SpringLayout.WEST, contentPane);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblWelcome);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.setLayer(lblWelcome, 0);
		
		JButton buttonEnterQuestion = new JButton("Enter a question");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblWelcome, -23, SpringLayout.NORTH, buttonEnterQuestion);
		buttonEnterQuestion.setMaximumSize(new Dimension(139, 23));
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonEnterQuestion, 132, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonEnterQuestion, -166, SpringLayout.EAST, contentPane);
		buttonEnterQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HHFormFrame().setVisible(true);
			}
		});
		buttonEnterQuestion.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonEnterQuestion.setHorizontalAlignment(SwingConstants.RIGHT);
	
		buttonEnterQuestion.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(buttonEnterQuestion);
		
		JLabel lblWelcometoHH = new JLabel("Welcome to HandyHomework!");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWelcometoHH, 26, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWelcometoHH, 50, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblWelcometoHH, -40, SpringLayout.NORTH, lblWelcome);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblWelcometoHH, -51, SpringLayout.EAST, contentPane);
		lblWelcometoHH.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcometoHH.setHorizontalTextPosition(SwingConstants.CENTER);
		lblWelcometoHH.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblWelcometoHH.setFont(new Font("Georgia", Font.PLAIN, 25));
		contentPane.add(lblWelcometoHH);
		
		JButton savedQuestionsButton = new JButton("View Saved Questions");
		sl_contentPane.putConstraint(SpringLayout.NORTH, savedQuestionsButton, 24, SpringLayout.SOUTH, buttonEnterQuestion);
		sl_contentPane.putConstraint(SpringLayout.WEST, savedQuestionsButton, -23, SpringLayout.WEST, lblWelcome);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, savedQuestionsButton, -45, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, savedQuestionsButton, -121, SpringLayout.EAST, contentPane);
		savedQuestionsButton.setPreferredSize(new Dimension(200, 23));
		savedQuestionsButton.setMaximumSize(new Dimension(200, 23));
		savedQuestionsButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		savedQuestionsButton.setHorizontalTextPosition(SwingConstants.CENTER);
		contentPane.add(savedQuestionsButton);
		savedQuestionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent i) {
				dispose();
				new HHSavedQuestionsPage().setVisible(true);
			}
		});
	}
}
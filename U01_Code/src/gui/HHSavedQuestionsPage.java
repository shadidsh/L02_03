package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class HHSavedQuestionsPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHSavedQuestionsPage frame = new HHSavedQuestionsPage();
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
	public HHSavedQuestionsPage() {
		setTitle("HandyHomework");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSavedQuestions = new JLabel("Saved Questions");
		lblSavedQuestions.setMaximumSize(new Dimension(100, 30));
		lblSavedQuestions.setFont(new Font("Dialog", Font.ITALIC, 20));
		lblSavedQuestions.setBounds(232, 11, 175, 31);
		contentPane.add(lblSavedQuestions);
		
		// when we can access the data base, check if there is anything in the db, if there is, use jLabel.setText("new Value"); to modify display
		JLabel lblNewLabel = new JLabel("There are no saved questions");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(10, 53, 397, 152);
		contentPane.add(lblNewLabel);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(298, 216, 89, 23);
		contentPane.add(btnMainMenu);
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HandyHomeworkMainPage().setVisible(true);
			}
		});
	}
}

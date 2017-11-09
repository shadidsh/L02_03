package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import java.awt.Component;
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
		sl_contentPane.putConstraint(SpringLayout.EAST, lblWelcome, -147, SpringLayout.EAST, contentPane);
		lblWelcome.setFont(new Font("Lucida Grande", Font.ITALIC, 15));
		contentPane.add(lblWelcome);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.setLayer(lblWelcome, 0);
		
//		JButton buttonEnterQuestion = new JButton("Enter a question");
//		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonEnterQuestion, 8, SpringLayout.SOUTH, lblWelcome);
//		sl_contentPane.putConstraint(SpringLayout.SOUTH, buttonEnterQuestion, -128, SpringLayout.SOUTH, contentPane);
//		sl_contentPane.putConstraint(SpringLayout.EAST, buttonEnterQuestion, -121, SpringLayout.EAST, contentPane);
//		buttonEnterQuestion.setMaximumSize(new Dimension(139, 23));
//		buttonEnterQuestion.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//				new HHFormFrame().setVisible(true);
//			}
//		});
//	
//		buttonEnterQuestion.setFont(new Font("Tahoma", Font.BOLD, 13));
//		contentPane.add(buttonEnterQuestion);
		
		JLabel lblWelcometoHH = new JLabel("Welcome to HandyHomework!");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWelcometoHH, 21, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWelcometoHH, 52, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblWelcometoHH, -24, SpringLayout.NORTH, lblWelcome);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblWelcometoHH, -49, SpringLayout.EAST, contentPane);
		lblWelcometoHH.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcometoHH.setHorizontalTextPosition(SwingConstants.CENTER);
		lblWelcometoHH.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblWelcometoHH.setFont(new Font("Georgia", Font.PLAIN, 25));
		contentPane.add(lblWelcometoHH);
		
//		JButton savedQuestionsButton = new JButton("View Saved Questions");
//		savedQuestionsButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent i) {
//				dispose();
//				new HHSavedQuestionsPage().setVisible(true);
//			}
//		});

//		sl_contentPane.putConstraint(SpringLayout.NORTH, savedQuestionsButton, 6, SpringLayout.SOUTH, btnViewSavedAssessments);
//		sl_contentPane.putConstraint(SpringLayout.WEST, savedQuestionsButton, 0, SpringLayout.WEST, btnViewSavedAssessments);
//		sl_contentPane.putConstraint(SpringLayout.EAST, savedQuestionsButton, 0, SpringLayout.EAST, btnViewSavedAssessments);
//		sl_contentPane.putConstraint(SpringLayout.WEST, lblWelcome, 0, SpringLayout.WEST, savedQuestionsButton);
//		sl_contentPane.putConstraint(SpringLayout.SOUTH, savedQuestionsButton, -28, SpringLayout.SOUTH, contentPane);
//		savedQuestionsButton.setPreferredSize(new Dimension(200, 20));
//		savedQuestionsButton.setMaximumSize(new Dimension(200, 20));
//		savedQuestionsButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
//		savedQuestionsButton.setHorizontalTextPosition(SwingConstants.CENTER);
//		contentPane.add(savedQuestionsButton);
		
		JButton btnViewSavedAssessments = new JButton("View Saved Assessments");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnViewSavedAssessments, 178, SpringLayout.NORTH, contentPane);
		
		sl_contentPane.putConstraint(SpringLayout.WEST, btnViewSavedAssessments, 143, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnViewSavedAssessments, -39, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnViewSavedAssessments, 0, SpringLayout.EAST, lblWelcome);
		btnViewSavedAssessments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent i) {
				dispose();
				new HHSavedAssessments().setVisible(true);
			}
		});
		btnViewSavedAssessments.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		contentPane.add(btnViewSavedAssessments);
		
		JButton btnCreateAnAssessment = new JButton("Create an Assessment");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblWelcome, -23, SpringLayout.NORTH, btnCreateAnAssessment);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnCreateAnAssessment, 117, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCreateAnAssessment, 143, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnCreateAnAssessment, -20, SpringLayout.NORTH, btnViewSavedAssessments);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnCreateAnAssessment, 0, SpringLayout.EAST, lblWelcome);
		btnCreateAnAssessment.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
//		sl_contentPane.putConstraint(SpringLayout.EAST, btnCreateAnAssessment, 236, SpringLayout.WEST, buttonEnterQuestion);
		btnCreateAnAssessment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHCreateAssessmentFrame frame = new HHCreateAssessmentFrame();
				frame.setVisible(true);
				frame.setAlwaysOnTop(true);
				if (frame.isDisplayable()){
					dispose();
				}
				
			}
		});
		contentPane.add(btnCreateAnAssessment);
	}
}
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
					frame.setResizable(false);
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
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Choose one of the following:");
		lblWelcome.setBounds(140, 80, 208, 19);
		lblWelcome.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
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
		lblWelcometoHH.setBounds(57, 26, 389, 30);
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
		
		JButton btnViewAssessments = new JButton("View Assessments");
		btnViewAssessments.setBounds(148, 148, 187, 47);
		btnViewAssessments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent i) {
				HHSavedAssessments frame = new HHSavedAssessments();
				frame.setVisible(true);
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		btnViewAssessments.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		contentPane.add(btnViewAssessments);
		
		JButton btnCreateAnAssessment = new JButton("Create an Assessment");
		btnCreateAnAssessment.setBounds(150, 105, 187, 47);
		btnCreateAnAssessment.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
//		sl_contentPane.putConstraint(SpringLayout.EAST, btnCreateAnAssessment, 236, SpringLayout.WEST, buttonEnterQuestion);
		btnCreateAnAssessment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHCreateAssessmentFrame frame = new HHCreateAssessmentFrame();
				frame.setVisible(true);
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
				
			}
		});
		contentPane.add(btnCreateAnAssessment);
		
		JButton btnViewCourses = new JButton("View Courses");
		btnViewCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHViewCoursesPage frame = new HHViewCoursesPage();
				frame.setVisible(true);
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		btnViewCourses.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnViewCourses.setBounds(146, 195, 187, 47);
		contentPane.add(btnViewCourses);
	}
}
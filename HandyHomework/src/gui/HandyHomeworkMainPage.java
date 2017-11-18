package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import login.SelectedUser;

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
		setBounds(100, 100, 500, 339);
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
		
		JLabel lblWelcometoHH = new JLabel("Welcome to HandyHomework!");
		lblWelcometoHH.setBounds(57, 26, 389, 30);
		lblWelcometoHH.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcometoHH.setHorizontalTextPosition(SwingConstants.CENTER);
		lblWelcometoHH.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblWelcometoHH.setFont(new Font("Georgia", Font.PLAIN, 25));
		contentPane.add(lblWelcometoHH);
		
//		/// Is this section for profs?
//		JButton btnViewAssessments = new JButton("View Assessments");
//		btnViewAssessments.setEnabled(false);
//		btnViewAssessments.setBounds(150, 148, 187, 47);
//		//btnViewAssessments.setBounds(150, 111, 187, 47);
//		btnViewAssessments.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent i) {
//				HHSavedAssessments frame = new HHSavedAssessments();
//				frame.setVisible(true);
//				frame.setResizable(false);
//				if (frame.isShowing()){
//					dispose();
//				}
//			}
//		});
//		btnViewAssessments.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
//		contentPane.add(btnViewAssessments);
//		
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
		btnViewCourses.setBounds(150, 192, 187, 47);
		contentPane.add(btnViewCourses);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnLogOut.setBounds(150, 231, 187, 47);
		contentPane.add(btnLogOut);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectedUser.setUser(null);
				
				HHLogin frame = new HHLogin();
				frame.setVisible(true);
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
	}
}
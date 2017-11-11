package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HHViewCoursesPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHViewCoursesPage frame = new HHViewCoursesPage();
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
	public HHViewCoursesPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setBounds(161, 20, 101, 30);
		lblCourses.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		
		JList list = new JList();
		list.setBounds(66, 68, 304, 119);
		// TODO
		// populate list w/ courses created by this user or for now, courses in general from DB
		
		JButton btnSelectCourse = new JButton("Select Course");
		btnSelectCourse.setBounds(91, 205, 129, 29);
		btnSelectCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.isSelectionEmpty()){
					JOptionPane.showMessageDialog(HHViewCoursesPage.this, "Please select a course.");
				} else {
					HHSavedAssessments frame = new HHSavedAssessments();
					frame.setVisible(true);
					frame.setResizable(false);
					if (frame.isShowing()){
						dispose();
					}
				}
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(238, 205, 75, 29);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HandyHomeworkMainPage frame = new HandyHomeworkMainPage();
				frame.setVisible(true);
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnSelectCourse);
		contentPane.add(btnBack);
		contentPane.add(list);
		contentPane.add(lblCourses);
	}
}

package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assessment.Assessment;
import course.Course;
import course.SelectedCourse;
import db.DbConnection;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class HHViewCoursesPage extends JFrame {

	private Course selectedCourse;
	
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

		String res = "";		
		
		Connection conn = DbConnection.getConnection();
		DefaultListModel<String> lstCourses = new DefaultListModel<>();
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			PreparedStatement stat = conn.prepareStatement("SELECT * FROM public.courses;");
			ResultSet Rs = stat.executeQuery();	
			
			while (Rs.next()) { 
				Integer cId = Rs.getInt(1);
				String course = Rs.getString(2);
				String name = Rs.getString(3);
				String term = Rs.getString(4);
				Course cs = new Course(cId, course, name, term);				
				lstCourses.addElement(course + ":" + term);
				courses.add(cs);
				
				res =  cId + "," + course + "," +  name + "," + name + "," +  term;
				System.out.println(res);	
				
			}			
			Rs.close();
			conn.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(HHViewCoursesPage.this, "Could not access database - " 
					+ "\nplease check your connection and try again.");
		}		
		
		JList listCourses = new JList<>(lstCourses);
		listCourses.setBounds(66, 68, 304, 119);
		
		listCourses.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				JList list = (JList) e.getSource();
				Course as = courses.get(list.getSelectedIndex());
				
				selectedCourse = as;
			}
		});
		
		
		JButton btnSelectCourse = new JButton("Select Course");
		btnSelectCourse.setBounds(94, 229, 129, 29);
		btnSelectCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listCourses.isSelectionEmpty()){
					JOptionPane.showMessageDialog(HHViewCoursesPage.this, "Please select a course.");
				} else {
					
					if (selectedCourse == null) {
						JOptionPane.showMessageDialog(HHViewCoursesPage.this, "Please select a course.");
					} else {
						SelectedCourse.setCourse(selectedCourse);
						System.out.println(selectedCourse.getCourseCode());
						HHSavedAssessments frame = new HHSavedAssessments();
						frame.setVisible(true);
						frame.setResizable(false);
						if (frame.isShowing()){
							dispose();
						}
					}
				}
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(239, 229, 75, 29);
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
		contentPane.add(listCourses);
		contentPane.add(lblCourses);
		
		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHCreateCoursePage frame = new HHCreateCoursePage();
				frame.setVisible(true);
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		btnAddCourse.setBounds(157, 199, 117, 29);
		contentPane.add(btnAddCourse);
	}
}

package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import net.miginfocom.swing.MigLayout;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;

import dao.DbCourse;
import login.ProfessorLogin;
import login.SelectedUser;

//import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HHCreateCoursePage extends JFrame {

	private JPanel contentPane;
	private JTextField txtCourseCode;
	private JTextField txtCourseName;
	private JButton btnCreate;
	private JButton btnCancel;
	private JTextField txtCourseTerm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHCreateCoursePage frame = new HHCreateCoursePage();
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
	public HHCreateCoursePage() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCreateCourse = new JLabel("Create Course");
		lblCreateCourse.setBounds(133, 18, 189, 30);
		lblCreateCourse.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 24));
		
		JLabel lblCourseCode = new JLabel("Course Code:");
		lblCourseCode.setBounds(41, 103, 84, 16);
		
		JLabel lblCourseTitle = new JLabel("Course Name:");
		lblCourseTitle.setBounds(41, 141, 88, 16);
		
		txtCourseCode = new JTextField();
		txtCourseCode.setBounds(133, 98, 130, 26);
		txtCourseCode.setColumns(10);
		
		txtCourseName = new JTextField();
		txtCourseName.setBounds(133, 136, 130, 26);
		txtCourseName.setColumns(10);
		contentPane.setLayout(null);
		
		btnCreate = new JButton("Create");
		contentPane.getRootPane().setDefaultButton(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtCourseName.getText();
				String courseCode = txtCourseCode.getText();
				String courseTerm = txtCourseTerm.getText();
				
				if (name.isEmpty() || courseCode.isEmpty() || courseTerm.isEmpty()){
					JOptionPane.showMessageDialog(HHCreateCoursePage.this, "One or more fields are empty.");
				} else {
					ProfessorLogin prof = (ProfessorLogin) SelectedUser.getUser();
					int pid = prof.getId();					
					DbCourse dbCourse = new DbCourse();
					dbCourse.insertCourses(pid, courseCode, name, courseTerm);
					HHViewCoursesPage frame = new HHViewCoursesPage();
					frame.setVisible(true);
					frame.setResizable(false);
					if (frame.isShowing()){
						dispose();
					}
				}
			}
		});
		btnCreate.setBounds(133, 170, 96, 30);
		contentPane.add(btnCreate);
		contentPane.add(lblCourseCode);
		contentPane.add(txtCourseCode);
		contentPane.add(lblCourseTitle);
		contentPane.add(txtCourseName);
		contentPane.add(lblCreateCourse);
		
		btnCancel = new JButton("\u2190 Back");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHViewCoursesPage frame = new HHViewCoursesPage();
				frame.setVisible(true);
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		btnCancel.setBounds(30, 15, 90, 29);
		contentPane.add(btnCancel);
		
		txtCourseTerm = new JTextField();
		txtCourseTerm.setColumns(10);
		txtCourseTerm.setBounds(133, 60, 130, 26);
		contentPane.add(txtCourseTerm);
		
		JLabel lblCourseTerm = new JLabel("Course Term:");
		lblCourseTerm.setBounds(41, 65, 84, 16);
		contentPane.add(lblCourseTerm);
	}
}

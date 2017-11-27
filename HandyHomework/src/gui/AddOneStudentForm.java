package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import course.SelectedCourse;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import dao.DbCourse;
import dao.DbUser;
import login.UserLogin;

public class AddOneStudentForm extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddOneStudentForm frame = new AddOneStudentForm();
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
	public AddOneStudentForm() {
		SwitchForm sf = new SwitchForm();
		setTitle("HandyHomework - Add A Student");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 368, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentInformation = new JLabel("Student Information");
		lblStudentInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblStudentInformation.setBounds(70, 44, 242, 31);
		contentPane.add(lblStudentInformation);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblUsername.setBounds(54, 102, 73, 17);
		contentPane.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(139, 98, 130, 26);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		usernameField.setName("username");
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.setName("addStudent");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				
				if (username.isEmpty()){
					JOptionPane.showMessageDialog(AddOneStudentForm.this, "One or more fields are empty.");
				} else {
					DbUser user = new DbUser();
					if (!user.userExists(username)){
						JOptionPane.showMessageDialog(
								AddOneStudentForm.this, "Student " + username + " is not registered, could not enrol student to course");
					} else {
						UserLogin student = user.getUser(username);
						DbCourse course = new DbCourse();
						int id = student.getId();
						int cid = SelectedCourse.getCourse().getcID();
						if (!course.checkManagedCourse(id, cid)) {
							course.insertManagedCourses(id, cid, false);
							
							ViewStudentsPage frame = new ViewStudentsPage();
							sf.switchForm(frame);
							if (frame.isShowing()){
								dispose();
							}
						}
					}
				}
			}
		});
		btnAddStudent.setBounds(113, 153, 117, 29);
		contentPane.add(btnAddStudent);
	
		JButton btnBack = new JButton("\u2190 Back");
		btnBack.setBounds(10, 11, 100, 30);
		contentPane.add(btnBack);
		btnBack.setName("back");
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentsPage frame = new AddStudentsPage();
				sf.switchForm(frame);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
	}
}

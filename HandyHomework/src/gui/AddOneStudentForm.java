package gui;

import java.awt.BorderLayout;
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
import login.SelectedUser;
import login.StudentLogin;
import login.UserLogin;

public class AddOneStudentForm extends JFrame {

	private JPanel contentPane;
	private JTextField studentNumField;
	private JTextField firstName;
	private JTextField lastName;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentInformation = new JLabel("Student Information");
		lblStudentInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblStudentInformation.setBounds(70, 44, 242, 31);
		contentPane.add(lblStudentInformation);
		
		JLabel lblStudentNumber = new JLabel("Student Number:");
		lblStudentNumber.setEnabled(false);
		lblStudentNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblStudentNumber.setBounds(37, 90, 116, 17);
		contentPane.add(lblStudentNumber);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setEnabled(false);
		lblFirstName.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblFirstName.setBounds(37, 153, 77, 17);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setEnabled(false);
		lblLastName.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblLastName.setBounds(37, 182, 74, 17);
		contentPane.add(lblLastName);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblUsername.setBounds(37, 123, 73, 17);
		contentPane.add(lblUsername);
		
		studentNumField = new JTextField();
		studentNumField.setEnabled(false);
		studentNumField.setBounds(154, 86, 130, 26);
		contentPane.add(studentNumField);
		studentNumField.setColumns(10);
		studentNumField.setName("studentNum");
		
		firstName = new JTextField();
		firstName.setEnabled(false);
		firstName.setBounds(154, 150, 130, 26);
		contentPane.add(firstName);
		firstName.setColumns(10);
		firstName.setName("firstname");
		
		lastName = new JTextField();
		lastName.setEnabled(false);
		lastName.setBounds(154, 179, 130, 26);
		contentPane.add(lastName);
		lastName.setColumns(10);
		lastName.setName("lastname");
		
		usernameField = new JTextField();
		usernameField.setBounds(154, 118, 130, 26);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		usernameField.setName("username");
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.setName("addStudent");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studID = studentNumField.getText();
				String fName = firstName.getText();
				String lName = lastName.getText();
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
							frame.setVisible(true);
							frame.setResizable(false);
							if (frame.isShowing()){
								dispose();
							}
						}
					}
				}
			}
		});
		btnAddStudent.setBounds(167, 218, 117, 29);
		contentPane.add(btnAddStudent);
	
		JButton btnBack = new JButton("\u2190 Back");
		btnBack.setBounds(10, 11, 67, 30);
		contentPane.add(btnBack);
		btnBack.setName("back");
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentsPage frame = new AddStudentsPage();
				frame.setVisible(true);		
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
	}
}

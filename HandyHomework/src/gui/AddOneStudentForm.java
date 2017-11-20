package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddOneStudentForm extends JFrame {

	private JPanel contentPane;
	private JTextField studentNumField;
	private JTextField firstName;
	private JTextField lastName;

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
		setBounds(100, 100, 370, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentInformation = new JLabel("Student Information");
		lblStudentInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblStudentInformation.setBounds(37, 28, 242, 31);
		contentPane.add(lblStudentInformation);
		
		JLabel lblStudentNumber = new JLabel("Student Number:");
		lblStudentNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblStudentNumber.setBounds(37, 94, 116, 17);
		contentPane.add(lblStudentNumber);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblFirstName.setBounds(37, 127, 77, 17);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblLastName.setBounds(37, 160, 74, 17);
		contentPane.add(lblLastName);
		
		studentNumField = new JTextField();
		studentNumField.setBounds(161, 90, 130, 26);
		contentPane.add(studentNumField);
		studentNumField.setColumns(10);
		
		firstName = new JTextField();
		firstName.setBounds(125, 123, 130, 26);
		contentPane.add(firstName);
		firstName.setColumns(10);
		
		lastName = new JTextField();
		lastName.setBounds(125, 156, 130, 26);
		contentPane.add(lastName);
		lastName.setColumns(10);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studID = studentNumField.getText();
				String fName = firstName.getText();
				String lName = lastName.getText();
				if (studID.isEmpty() || fName.isEmpty() || lName.isEmpty()){
					JOptionPane.showMessageDialog(AddOneStudentForm.this, "One or more fields are empty.");
				} else {
					
					//db.DbConnection.insertCourses(courseCode, name, courseTerm);
					
					ViewStudentsPage frame = new ViewStudentsPage();
					frame.setVisible(true);
					frame.setResizable(false);
					if (frame.isShowing()){
						dispose();
					}
				}
			}
		});
		btnAddStudent.setBounds(195, 194, 117, 29);
		contentPane.add(btnAddStudent);
	}
}

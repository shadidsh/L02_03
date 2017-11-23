package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import course.SelectedCourse;
import dao.DbCourse;
import dao.DbUser;
import login.UserLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class AddStudentsPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentsPage frame = new AddStudentsPage();
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
	public AddStudentsPage() {
		this.setName("addStudents");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Students");
		lblNewLabel.setBounds(123, 18, 184, 38);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		contentPane.add(lblNewLabel);
		
		//final JFileChooser fc = new JFileChooser();
		
		JButton btnChoosecsvFile = new JButton("Upload a .csv file");
		btnChoosecsvFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//int returnVal = fc.showOpenDialog(contentPane);
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "CSV", "csv");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(contentPane);
			    if (returnVal == JFileChooser.APPROVE_OPTION){
			    	// Read the file into the table
			    	File f = chooser.getSelectedFile();
			    	try {
			    		BufferedReader brd = new BufferedReader(new FileReader(f));
		                while (brd.ready()) {
		                    String st = brd.readLine();
		                    String[] studInfo = st.split(",");
		                    
		                    DbUser user = new DbUser();
							if (!user.userExists(studInfo[0])){
								JOptionPane.showMessageDialog(
										AddStudentsPage.this, "Student " + studInfo[0] + " is not registered, could not enrol student to course");
							} else {
								UserLogin student = user.getUser(studInfo[0]);
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
		            } catch (Exception e1) {
		                String errmsg = e1.getMessage();
		                System.out.println("File not found:" + errmsg);
		            } // end of Catch
			    } else {
			    	chooser.cancelSelection();
			    }
			}
		});
		btnChoosecsvFile.setBounds(128, 109, 152, 48);
		contentPane.add(btnChoosecsvFile);
		
		JLabel lblChooseOneOf = new JLabel("Choose one of the following methods:");
		lblChooseOneOf.setBounds(94, 74, 241, 16);
		contentPane.add(lblChooseOneOf);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.setName("addStudent");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddOneStudentForm frame = new AddOneStudentForm();
				frame.setVisible(true);
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		btnAddStudent.setBounds(126, 169, 154, 48);
		contentPane.add(btnAddStudent);
		
		JButton btnBack = new JButton("\u2190 Back");
		btnBack.setBounds(10, 11, 164, 30);
		contentPane.add(btnBack);
		btnBack.setName("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewStudentsPage frame = new ViewStudentsPage();
				frame.setVisible(true);		
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
	}
}

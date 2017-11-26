package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import assessment.Assessment;
import course.Course;
import course.SelectedCourse;
import dao.DbCourse;
import dao.DbUser;
import login.StudentLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ViewStudentsPage extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStudentsPage frame = new ViewStudentsPage();
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
	public ViewStudentsPage() {
		setTitle("HandyHomework - Enrolled Students");
		this.setName("ViewStudentsPage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudents = new JLabel("Students");
		lblStudents.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		lblStudents.setBounds(163, 26, 112, 31);
		contentPane.add(lblStudents);
		
		JButton btnAddStudentsPage = new JButton("Add Students");
		btnAddStudentsPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentsPage frame = new AddStudentsPage();
				frame.setVisible(true);	
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				if (frame.isShowing()){
					dispose();
				} 
			}
		});
		btnAddStudentsPage.setBounds(231, 208, 142, 45);
		contentPane.add(btnAddStudentsPage);
		
		
		String[] columnNames = {"Student ID", "First Name", "Last Name"};
		
		
//		DbCourse course = new DbCourse();
		DbUser user = new DbUser();
//		Object data[][];
		String[] colHeadings = {"Student Number","First Name", "Last Name"};
		int numRows = 0;
		DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length) {
			@Override
		    public String getColumnName(int index) {
		        return columnNames[index];
		    }
		};
		
		if (SelectedCourse.isSelected()) {
			Course cs = SelectedCourse.getCourse();
			List<StudentLogin> students = user.getStudentsForCourse(cs.getcID());
			
			for (StudentLogin stud : students) {
				Object[] data = {stud.getId(), stud.getName(), stud.getPassword()};
				model.addRow(data);
				System.out.println(stud.getId());
			}
		}
		
		table = new JTable(model);
		model.setColumnIdentifiers(colHeadings);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(42, 75, 376, 121);
		contentPane.add(scrollPane);
		table.setName("studentTable");
		scrollPane.setName("studentTable");
		
		JButton btnBack = new JButton("\u2190 Back");
		btnBack.setBounds(17, 26, 90, 36);
		contentPane.add(btnBack);
		
		JButton btnRemoveStudent = new JButton("Remove Student");
		btnRemoveStudent.setName("btnRemoveStudent");
		btnRemoveStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1){
					JOptionPane.showMessageDialog(ViewStudentsPage.this, "No student has been selected.");
				} else {
					int row = table.getSelectedRow();
					DbUser stud = new DbUser();
					DbCourse course = new DbCourse();
					String user = (String) table.getValueAt(row, 1);
					Course cs = SelectedCourse.getCourse();
					
					int ret = JOptionPane.showConfirmDialog(ViewStudentsPage.this, "Are you sure you want to remove " + user + "?", "Remove Student?", JOptionPane.YES_NO_OPTION);
					if (ret == JOptionPane.YES_OPTION){
						course.removeManagedCourses(stud.getUser(user).getId(), cs.getcID());
						new ViewStudentsPage().setVisible(true);						
						dispose();
					}
				}
			}
		});
		btnRemoveStudent.setBounds(84, 208, 142, 45);
		contentPane.add(btnRemoveStudent);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHSavedAssessments frame = new HHSavedAssessments();
				frame.setVisible(true);		
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		
	}
}

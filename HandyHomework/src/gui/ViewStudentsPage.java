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
				if (frame.isShowing()){
					dispose();
				} 
			}
		});
		btnAddStudentsPage.setBounds(147, 208, 142, 45);
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
//		model.setColumnIdentifiers(colHeadings);
		
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
	

//		JPanel tablePanel = new JPanel(new BorderLayout());
//		tablePanel.add(table, BorderLayout.CENTER);
//		tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(42, 75, 376, 121);
		contentPane.add(scrollPane);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 79, 30);
		contentPane.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHSavedAssessments frame = new HHSavedAssessments();
				frame.setVisible(true);		
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		
		//scrollPane.setViewportView(table_1);
		
	}
}


package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import assessment.Assessment;
import assessment.SelectedAssessment;
import course.Course;
import course.SelectedCourse;
import dao.DbAssessment;
import db.DbConnection;
import login.SelectedUser;

import java.awt.SystemColor;

public class HHSavedAssessments extends JFrame {

	private JPanel contentPane;
	private JList<?> list;
	private String questAnswer;
	private Assessment selectedAs;
	private int selInd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHSavedAssessments frame = new HHSavedAssessments();
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
	public HHSavedAssessments() {
		setTitle("HandyHomework");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 221, 120, 20);
		scrollPane.setBorder(null);
		contentPane.add(scrollPane);
		JLabel lblPts = new JLabel("");
		lblPts.setHorizontalAlignment(SwingConstants.LEFT);
		lblPts.setVerticalAlignment(SwingConstants.TOP);
		lblPts.setVisible(true);
		scrollPane.setViewportView(lblPts);
		lblPts.setBorder(null);
		lblPts.setAutoscrolls(true);
		
		JLabel lblSavedAssessment = new JLabel("Saved Assessments");
		lblSavedAssessment.setBounds(273, 22, 245, 30);
		lblSavedAssessment.setMaximumSize(new Dimension(100, 30));
		lblSavedAssessment.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
		contentPane.add(lblSavedAssessment);
		
		JTextArea assessmentTitle = new JTextArea("");
		assessmentTitle.setBounds(311, 82, 194, 46);
		assessmentTitle.setBackground(SystemColor.window);
		assessmentTitle.setEditable(false);
		assessmentTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		assessmentTitle.setLineWrap(true);
		assessmentTitle.setWrapStyleWord(true);
		contentPane.add(assessmentTitle);
		
		String res = "";
		
		DefaultListModel<String> lstAssess = new DefaultListModel<>();
		ArrayList<Assessment> assess = new ArrayList<Assessment>();
		DbAssessment dbAssess = new DbAssessment();
		
		System.out.println(SelectedCourse.isSelected());
		if (SelectedCourse.isSelected()) {
			Course cs = SelectedCourse.getCourse();
			List<Assessment> as = dbAssess.getAssessmentForCourse(cs.getcID());
			
			for (Assessment ase : as) {
				lstAssess.addElement(ase.getName());
				assess.add(ase);			
			}
		} else {
			JOptionPane.showMessageDialog(HHSavedAssessments.this, 
					"No Courses have been selected, Logging out ");
			HHLogin frame = new HHLogin();
			frame.setVisible(true);
			frame.setResizable(false);
			if (frame.isShowing()){
				dispose();
			}
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(311, 138, 207, 30);
		scrollPane_1.setBorder(null);
		contentPane.add(scrollPane_1);
		
		JLabel lblAssessment = new JLabel("Select an Assessment");
		lblAssessment.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblAssessment.setBorder(null);
		scrollPane_1.setViewportView(lblAssessment);
		lblAssessment.setVerticalAlignment(SwingConstants.TOP);
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(12, 54, 120, 30);
		contentPane.add(btnMainMenu);
		
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HandyHomeworkMainPage frame = new HandyHomeworkMainPage();
				frame.setVisible(true);	
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}

			}
		});
		JButton btnView = new JButton("Select Assessment");
		btnView.setBounds(254, 309, 160, 30);
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedAs == null ) {
					JOptionPane.showMessageDialog(HHSavedAssessments.this, "Please select an assessment.");
				} else {
					SelectedAssessment.setAssess(selectedAs);
					HHSavedQuestionsPage frame = new HHSavedQuestionsPage();
					frame.setVisible(true);	
					frame.setResizable(false);
					if (frame.isShowing()){
						dispose();
					}
				}
				}
		});
		contentPane.add(btnView);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(30, 97, 215, 239);
		contentPane.add(scrollPane_2);
		JList<String> listAssessment = new JList<>(lstAssess);
		scrollPane_2.setViewportView(listAssessment);
		
		listAssessment.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String res;
				JList<?> list = (JList<?>) e.getSource();
				int index = list.getSelectedIndex();
				if (index != -1) {
					Assessment as = assess.get(list.getSelectedIndex());
					res =  String.valueOf(as.getWeight()); //"<html>This assessment is worth " + String.valueOf(as.getWeight())  + "%</html>";
					
					lblAssessment.setText(as.getName());
					lblPts.setText(res);
					assessmentTitle.setText(as.getTitle());
					selectedAs = as;
					selInd = index;
				}
			}
		});			
		
		listAssessment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnNewAssessment = new JButton("New Assessment");
		if (SelectedUser.getUser().isProf()) {
			btnNewAssessment.setBounds(254, 269, 160, 30);
		} else {
			btnNewAssessment.setBounds(254, 269, 275, 30);
		}
		btnNewAssessment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHCreateAssessmentFrame frame = new HHCreateAssessmentFrame();
				frame.setVisible(true);	
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		contentPane.add(btnNewAssessment);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(255, 90, 46, 14);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblTitle);
		
		JButton btnBack = new JButton("Back to Courses");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				HHViewCoursesPage frame = new HHViewCoursesPage();
				frame.setVisible(true);
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		btnBack.setBounds(12, 13, 140, 30);
		contentPane.add(btnBack);
		
		JButton btnRemove = new JButton("Remove ");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedAs == null || selInd < 0 ) {
					JOptionPane.showMessageDialog(HHSavedAssessments.this, "Please select an assessment to remove.");
				} else {
					dbAssess.removeAssessment(selectedAs.getAid());
					
					System.out.println(selInd);
					lstAssess.remove(selInd);
					//assess.remove(selInd);	
					
					// re-query this assessment - workaround to just load the page again
					/*
					HHSavedAssessments frame = new HHSavedAssessments();
					frame.setVisible(true);	
					frame.setResizable(false);
					if (frame.isShowing()){
						dispose();
					} 
					*/
				}
			} 
		}); 	
		
		btnRemove.setBounds(420, 309, 120, 30);
		contentPane.add(btnRemove);
		
		JLabel lblTotalMarks = new JLabel("Total Marks:");
		lblTotalMarks.setBounds(299, 221, 71, 20);
		contentPane.add(lblTotalMarks);
		
		if (SelectedUser.getUser().isProf()) {
			JButton btnAddStudents = new JButton("Add Students");
			btnAddStudents.setBounds(420, 269, 120, 30);
			contentPane.add(btnAddStudents);
		}
		
		
		
	}
}

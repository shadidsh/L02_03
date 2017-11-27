
package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import dao.DbCourse;
import dao.DbQuestions;
import login.SelectedUser;

import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;

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
		SwitchForm sf = new SwitchForm();
		setTitle("HandyHomework - Assessments");
		this.setName("SavedAssess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSavedAssessment = new JLabel("Assessments");
		lblSavedAssessment.setBounds(309, 29, 230, 30);
		lblSavedAssessment.setMaximumSize(new Dimension(100, 30));
		lblSavedAssessment.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 29));
		contentPane.add(lblSavedAssessment);
		
		JTextArea assessmentTitle = new JTextArea("");
		assessmentTitle.setBounds(283, 98, 222, 30);
		assessmentTitle.setBackground(SystemColor.window);
		assessmentTitle.setEditable(false);
		assessmentTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		assessmentTitle.setLineWrap(true);
		assessmentTitle.setWrapStyleWord(true);
		contentPane.add(assessmentTitle);
		
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
			sf.switchForm(frame);
			if (frame.isShowing()){
				dispose();
			}
		}
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(12, 54, 100, 30);
		contentPane.add(btnMainMenu);
		
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HandyHomeworkMainPage frame = new HandyHomeworkMainPage();
				sf.switchForm(frame);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		JButton btnView = new JButton("Select Assessment");
		btnView.setBounds(309, 294, 160, 30);
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedAs == null ) {
					JOptionPane.showMessageDialog(HHSavedAssessments.this, "Please select an assessment.");
				} else {
					SelectedAssessment.setAssess(selectedAs);
					if (SelectedUser.getUser().isProf()) {
						HHSavedQuestionsPage frame = new HHSavedQuestionsPage();
						sf.switchForm(frame);
						if (frame.isShowing()){
							dispose();
						}
					} else {
						
						//DUPLICATE COOODE from actionPerformed
						DbQuestions dbQ = new DbQuestions();
						int aid = SelectedAssessment.getAssess().getAid();
						if (dbQ.hasTextQuestions(aid)) {
							AnswerStudentQuestions frame = new AnswerStudentQuestions();
							sf.switchForm(frame);
							if (frame.isShowing()){
								dispose();
							}							
						} else if (dbQ.hasMultChoice(aid)){
							AnswerMultipleChoice frame = new AnswerMultipleChoice();
							sf.switchForm(frame);
							if (frame.isShowing()){
								dispose();
							}
						}
					}
				}
			}
		});
		contentPane.add(btnView);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(30, 97, 215, 239);
		contentPane.add(scrollPane_2);
		JList<String> listAssessment = new JList<>(lstAssess);
		listAssessment.setName("assess");
		scrollPane_2.setViewportView(listAssessment);
		JLabel lblPts = new JLabel("");
		lblPts.setBounds(283, 215, 203, 30);
		contentPane.add(lblPts);
		lblPts.setBackground(SystemColor.window);
		lblPts.setHorizontalAlignment(SwingConstants.LEFT);
		lblPts.setVerticalAlignment(SwingConstants.TOP);
		lblPts.setVisible(true);
		lblPts.setBorder(null);
		lblPts.setAutoscrolls(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(283, 140, 203, 63);
		contentPane.add(scrollPane);
		JLabel lblAssessment = new JLabel("Select an Assessment");
		scrollPane.setViewportView(lblAssessment);
		lblAssessment.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblAssessment.setBorder(null);
		lblAssessment.setVerticalAlignment(SwingConstants.TOP);
		
		
		listAssessment.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String res;
				JList<?> list = (JList<?>) e.getSource();
				int index = list.getSelectedIndex();
				if (index != -1) {
					Assessment as = assess.get(list.getSelectedIndex());
					res =  String.valueOf(as.getWeight()); //"<html>This assessment is worth " + String.valueOf(as.getWeight())  + "%</html>";
					
					lblAssessment.setText(as.getName());
					lblPts.setText("Total Marks: " + res);
					assessmentTitle.setText("Title: "+ as.getTitle());
					selectedAs = as;
					selInd = index;
				}
			}
		});
		
		listAssessment.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JList listAssessment = (JList)e.getSource();
				if (e.getClickCount() == 2) {
					SelectedAssessment.setAssess(selectedAs);
					if (SelectedUser.getUser().isProf()) {
						HHSavedQuestionsPage frame = new HHSavedQuestionsPage();
						sf.switchForm(frame);
						if (frame.isShowing()){
							dispose();
						}
					} else {
						
						//DUPLICATE COOODE from actionPerformed
						DbQuestions dbQ = new DbQuestions();
						int aid = SelectedAssessment.getAssess().getAid();
						if (dbQ.hasTextQuestions(aid)) {
							AnswerStudentQuestions frame = new AnswerStudentQuestions();
							sf.switchForm(frame);
							if (frame.isShowing()){
								dispose();
							}							
						} else if (dbQ.hasMultChoice(aid)){
							AnswerMultipleChoice frame = new AnswerMultipleChoice();
							sf.switchForm(frame);
							if (frame.isShowing()){
								dispose();
							}
						}
					}
				}
			}
		});
		
		listAssessment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnNewAssessment = new JButton("New Assessment");
		btnNewAssessment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHCreateAssessmentFrame frame = new HHCreateAssessmentFrame();
				sf.switchForm(frame);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		contentPane.add(btnNewAssessment);
		
		JButton btnBack = new JButton("\u2190 Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				HHViewCoursesPage frame = new HHViewCoursesPage();
				sf.switchForm(frame);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		btnBack.setBounds(12, 13, 100, 30);
		contentPane.add(btnBack);
		
		JButton btnRemove = new JButton("Remove Assessment");
		btnRemove.setName("removeAssess");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedAs == null || selInd < 0 ) {
					JOptionPane.showMessageDialog(HHSavedAssessments.this, "Please select an assessment to remove.");
				} else {
					
					dbAssess.removeAssessment(selectedAs.getAid());
					lstAssess.remove(selInd);
					assess.remove(selInd);	
				}
			} 
		}); 	
		
		
		if (SelectedUser.getUser().isProf()) {
			JButton btnViewStudents = new JButton("View Students");
			btnViewStudents.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewStudentsPage frame = new ViewStudentsPage();
					sf.switchForm(frame);
					if (frame.isShowing()){
						dispose();
					} 
				}
				
			});
			btnViewStudents.setBounds(420, 257, 120, 30);
			contentPane.add(btnViewStudents);
			btnRemove.setBounds(309, 326, 160, 30);
			contentPane.add(btnRemove);
			contentPane.add(btnNewAssessment);
			btnNewAssessment.setBounds(257, 257, 160, 30);
		} else if (!SelectedUser.getUser().isProf()) {
			/*
			JButton btnViewMarks = new JButton("View Grades");
			btnViewMarks.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// pop-up looking at the grades for the assessment, if one is selected 
					if (selectedAs == null ) {
						JOptionPane.showMessageDialog(HHSavedAssessments.this, "Please select an assessment.");
					} else {
						// use selectedAs to get the score from the db? selectedAs;
						JOptionPane.showMessageDialog(HHSavedAssessments.this, "This\nis\nwhere\nthe\nresults\ngo");
					}	
				}
				
			}
			);
			
			btnViewMarks.setBounds(309, 326, 160, 30);
			contentPane.add(btnViewMarks);*/
		}
	}
}

package gui;

import java.awt.BorderLayout;
import java.awt.Component;
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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import assessment.Assessment;
import db.DbConnection;
import question.TextQuestion;

public class HHSavedAssessments extends JFrame {

	private JPanel contentPane;
	private JList list;
	private String questAnswer;
	private Assessment selectedAs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHSavedAssessments frame = new HHSavedAssessments();
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
	public HHSavedAssessments() {
		setTitle("HandyHomework");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSavedAssessment = new JLabel("Saved Assessments");
		lblSavedAssessment.setMaximumSize(new Dimension(100, 30));
		lblSavedAssessment.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
		lblSavedAssessment.setBounds(294, 13, 245, 30);
		contentPane.add(lblSavedAssessment);
		JLabel lblPts = new JLabel("");
		lblPts.setAutoscrolls(true);
		
		JLabel assessmentTitle = new JLabel("");
		assessmentTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		assessmentTitle.setBounds(266, 55, 175, 33);
		contentPane.add(assessmentTitle);
		
		Connection conn = DbConnection.getConnection();
		String res = "";
		
		DefaultListModel<String> lstAssess = new DefaultListModel<>();
		ArrayList<Assessment> assess = new ArrayList<Assessment>();
		ArrayList<Integer> aids = new ArrayList<Integer>();
			try {
				PreparedStatement stat = conn.prepareStatement("SELECT * FROM public.assessments;");
				ResultSet Rs = stat.executeQuery();				
				
				while (Rs.next()) {
					
					Integer aid = Rs.getInt(1);
					String title = Rs.getString(2);
					String name = Rs.getString(3);
					Boolean isOpt = Rs.getBoolean(4);
					java.sql.Timestamp dueDate = Rs.getTimestamp(5);
					float weight = Rs.getFloat(6);
					Calendar due = Calendar.getInstance();
					
					if (dueDate != null) {
						due.setTimeInMillis(dueDate.getTime());
					}
					
					
					Assessment as = new Assessment(aid, title, name, isOpt, due, weight);
					
					lstAssess.addElement(name);
					assess.add(as);
					
					res =  aid + "," + title + "," +  name + "," + isOpt + dueDate + " VS " + due.getTime() +  " ," + weight +  ",";
					
					System.out.println(res);
					
				}
				
				Rs.close();
				conn.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(HHSavedAssessments.this, "Could not access database - " + "\nplease check your connection and try again.");
			} catch (NullPointerException e2){
				e2.printStackTrace();
				JOptionPane.showMessageDialog(HHSavedAssessments.this, "Could not access database - " + "\nplease check your connection and try again.");
			}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(266, 99, 201, 139);
		contentPane.add(scrollPane_1);
		
		JLabel lblAssessment = new JLabel("Select an Assessment");
		scrollPane_1.setViewportView(lblAssessment);
		lblAssessment.setVerticalAlignment(SwingConstants.TOP);
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(266, 306, 120, 30);
		contentPane.add(btnMainMenu);
		
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HandyHomeworkMainPage().setVisible(true);
			}
		});
		lblPts.setVerticalAlignment(SwingConstants.TOP);
		lblPts.setBounds(266, 127, 120, 72);
		contentPane.add(lblPts);
		JButton btnView = new JButton("Select");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedAs == null ) {
					JOptionPane.showMessageDialog(HHSavedAssessments.this, "Please select an assessment.");
				} else {
					SharedAssessment.setAssess(selectedAs);
					dispose();
					new HHSavedQuestionsPage().setVisible(true);
				}
//				////////////////////////////////// need to modify to pass info to view saved questions page
//				if (answer.isEmpty()) {
//					JOptionPane.showMessageDialog(HHSavedAssessments.this, "One or more fields are empty");
//				} else if (lblAssessment.getText().length() == 0)  {
//						JOptionPane.showMessageDialog(HHSavedAssessments.this, "One or more fields are empty");
//				} else if (questAnswer.compareTo(answer) == 0)  {
//					JOptionPane.showMessageDialog(HHSavedAssessments.this, "Correct!!");
//				} else {
//					JOptionPane.showMessageDialog(HHSavedAssessments.this, "Wrong!! Your answer is " + questAnswer + ", btw");
//				}
//					
				}
		});
		
		
		btnView.setBounds(419, 306, 120, 31);
		contentPane.add(btnView);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(30, 97, 196, 239);
		contentPane.add(scrollPane_2);
		JList listAssessment = new JList<>(lstAssess);
		scrollPane_2.setViewportView(listAssessment);
		
		listAssessment.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String res = "<html>This question<br> is worth</html>";
				
				
				/* needs to be modified when assessments class is added */
				JList list = (JList) e.getSource();
				Assessment as = assess.get(list.getSelectedIndex());
				
				lblAssessment.setText(as.getName());					
				res = "<html>This assessment's id:" + as.getAid() + ", its name is " + as.getName() 
					+  ", and it is weighted " + as.getWeight() + "</html>" ;
				
				lblPts.setText(res);
				assessmentTitle.setText(as.getTitle());
				selectedAs = as;				
			}
		});	
		
		
		listAssessment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnNewAssessment = new JButton("Create New Assessment");
		btnNewAssessment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HHCreateAssessmentFrame().setVisible(true);
			}
		});
		btnNewAssessment.setBounds(266, 265, 195, 29);
		contentPane.add(btnNewAssessment);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 97, 196, 240);
		contentPane.add(scrollPane);
		
		
		
		
		
		
	}
}

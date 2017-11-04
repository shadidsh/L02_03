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

import db.DbConnection;
import question.TextQuestion;

public class HHSavedAssessments extends JFrame {

	private JPanel contentPane;
	private JList list;
	private String questAnswer;

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
		lblSavedAssessment.setFont(new Font("Dialog", Font.ITALIC, 20));
		lblSavedAssessment.setBounds(151, 13, 215, 31);
		contentPane.add(lblSavedAssessment);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 97, 196, 240);
		contentPane.add(scrollPane);
		
		JLabel lblAssessment = new JLabel("Select an Assessment");
		JLabel lblPts = new JLabel("");
		lblPts.setAutoscrolls(true);
		
		JLabel assessmentTitle = new JLabel("");
		assessmentTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		assessmentTitle.setBounds(266, 55, 175, 33);
		contentPane.add(assessmentTitle);
		
		Connection conn = DbConnection.getConnection();
		String res = "";
		
		DefaultListModel<String> lstQuestion = new DefaultListModel<>();
		
		//res = "<html>name question Answer<br>";
		ArrayList<TextQuestion> questions = new ArrayList<TextQuestion>();
			try {
				PreparedStatement stat = conn.prepareStatement("SELECT * FROM sware.textquestions;");
				ResultSet Rs = stat.executeQuery();				
				
				while (Rs.next()) {
					
					String name = Rs.getString(2);
					String questionContent = Rs.getString(3);
					String answer = Rs.getString(4);
					Integer value = new Integer(Rs.getInt(5));
					TextQuestion question = new TextQuestion(name, questionContent, answer, value);
					
					lstQuestion.addElement(question.getName());
					questions.add(question);
					//res +=  Rs.getString(2) + "," +  Rs.getString(3) + "," 
					//		+ Rs.getString(4) + "," + Rs.getInt(5) +  "<br>";
				}
				
				Rs.close();
				conn.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		lblAssessment.setVerticalAlignment(SwingConstants.TOP);
		lblAssessment.setBounds(266, 99, 201, 139);
		contentPane.add(lblAssessment);		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(419, 307, 120, 30);
		contentPane.add(btnMainMenu);
		
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HandyHomeworkMainPage().setVisible(true);
			}
		});
		lblPts.setVerticalAlignment(SwingConstants.TOP);
		lblPts.setBounds(289, 265, 120, 72);
		contentPane.add(lblPts);
		JButton btnView = new JButton("Select");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				
//				String answer = String.valueOf(questionAnswerField.getText());
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
		btnView.setBounds(419, 265, 120, 31);
		contentPane.add(btnView);
		JList listAssessment = new JList<>(lstQuestion);
		contentPane.add(listAssessment);
		//res += "</html>";
		
		listAssessment.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String res = "<html>This question<br> is worth</html>";
				////////////////////// needs to be modified when assessments class is added
				JList list = (JList) e.getSource();
				///
				TextQuestion question = questions.get(list.getSelectedIndex());
				
				lblAssessment.setText(question.getQuestion());					
				res = "<html>This question<br> is worth <html>" + new Integer(question.getPoints()).toString() + "<html> marks</html>" ;
				lblPts.setText(res);
				assessmentTitle.setText(question.getName());
				questAnswer = question.getAnswer();
				// need to pass this question into submit button, then check for answer
				
			}
		});
		
		
		
		listAssessment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			
		
		listAssessment.setBounds(30, 97, 196, 239);
		
		
		
		
		
		
	}
}

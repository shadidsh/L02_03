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
	private JList listQuestion_1;
	private JTextField questionAnswerField;
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
		
		JLabel lblSavedQuestions = new JLabel("Saved Assessments");
		lblSavedQuestions.setMaximumSize(new Dimension(100, 30));
		lblSavedQuestions.setFont(new Font("Dialog", Font.ITALIC, 20));
		lblSavedQuestions.setBounds(151, 13, 215, 31);
		contentPane.add(lblSavedQuestions);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 97, 190, 114);
		contentPane.add(scrollPane);
		
		JLabel lblQuestion = new JLabel("Select an Assessment");
		JLabel lblPts = new JLabel("");
		lblPts.setAutoscrolls(true);
		
		JLabel assessmentTitle = new JLabel("");
		assessmentTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		assessmentTitle.setBounds(266, 55, 175, 33);
		contentPane.add(assessmentTitle);
		
		Connection conn = DbConnection.getConnection();
		String res = "";
		
		DefaultListModel<String> lstQuestion = new DefaultListModel<>();
		JList listQuestion = new JList<>(lstQuestion);
		
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
			//res += "</html>";
		
			listQuestion.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					String res = "<html>This question<br> is worth</html>";
					
					JList list = (JList) e.getSource();
					TextQuestion question = questions.get(list.getSelectedIndex());
					
					lblQuestion.setText(question.getQuestion());					
					res = "<html>This question<br> is worth <html>" + new Integer(question.getPoints()).toString() + "<html> marks</html>" ;
					lblPts.setText(res);
					assessmentTitle.setText(question.getName());
					questAnswer = question.getAnswer();
					// need to pass this question into submit button, then check for answer
					
				}
			});
			
			
			
		listQuestion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			
		lblQuestion.setVerticalAlignment(SwingConstants.TOP);
		lblQuestion.setBounds(266, 99, 201, 49);
		contentPane.add(lblQuestion);		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(419, 307, 120, 30);
		contentPane.add(btnMainMenu);
		
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HandyHomeworkMainPage().setVisible(true);
			}
		});
		
		listQuestion.setBounds(12, 84, 100, 100);
		scrollPane.setViewportView(listQuestion);
		lblPts.setVerticalAlignment(SwingConstants.TOP);
		lblPts.setBounds(10, 265, 120, 72);
		contentPane.add(lblPts);
		JButton btnView = new JButton("Select");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String answer = String.valueOf(questionAnswerField.getText());
				
				if (answer.isEmpty()) {
					JOptionPane.showMessageDialog(HHSavedAssessments.this, "One or more fields are empty");
				} else if (lblQuestion.getText().length() == 0)  {
						JOptionPane.showMessageDialog(HHSavedAssessments.this, "One or more fields are empty");
				} else if (questAnswer.compareTo(answer) == 0)  {
					JOptionPane.showMessageDialog(HHSavedAssessments.this, "Correct!!");
				} else {
					JOptionPane.showMessageDialog(HHSavedAssessments.this, "Wrong!! Your answer is " + questAnswer + ", btw");
				}
					
					
					
					
				}
		});
		btnView.setBounds(419, 265, 120, 31);
		contentPane.add(btnView);		
		questionAnswerField = new JTextField();
		questionAnswerField.setAlignmentY(Component.TOP_ALIGNMENT);
		questionAnswerField.setColumns(10);
		questionAnswerField.setBounds(150, 258, 247, 79);
		contentPane.add(questionAnswerField);
		
		
		
		
		
		
	}
}

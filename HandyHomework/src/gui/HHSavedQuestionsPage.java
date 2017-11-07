package gui;
import db.DbConnection;
import question.TextQuestion;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;

import answer.TextAnswer;
import assessment.Assessment;

import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import java.awt.Component;
import javax.swing.JScrollPane;

public class HHSavedQuestionsPage extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JList listQuestion_1;
	private JTextField questionAnswerField;
	private TextAnswer questAnswer;
	
	private TextQuestion selQuestion;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHSavedQuestionsPage frame = new HHSavedQuestionsPage();
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
	public HHSavedQuestionsPage() {
		setTitle("HandyHomework");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSavedQuestions = new JLabel("Saved Questions");
		lblSavedQuestions.setMaximumSize(new Dimension(100, 30));
		lblSavedQuestions.setFont(new Font("Dialog", Font.ITALIC, 20));
		lblSavedQuestions.setBounds(150, 13, 175, 31);
		contentPane.add(lblSavedQuestions);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(-133, -37, 190, 114);
		contentPane.add(scrollPane);
		
		JLabel lblQuestion = new JLabel("Select a Question");
		JLabel lblPts = new JLabel("");
		lblPts.setAutoscrolls(true);
		
		JLabel questionTitle = new JLabel("");
		questionTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		questionTitle.setBounds(277, 100, 175, 33);
		contentPane.add(questionTitle);
		
		Connection conn = DbConnection.getConnection();
		String res = "";
		
		DefaultListModel<String> lstQuestion = new DefaultListModel<>();
		
		ArrayList<TextQuestion> questions = new ArrayList<TextQuestion>();

		
		try {
			PreparedStatement stat;
			int aid;
			
			if (SharedAssessment.isSelected()) {				
				//JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "Assessment is selected");
				stat = conn.prepareStatement("SELECT * FROM "	
						+ constants.Constants.DataConstants.QUESTIONS + " where aid = ?;");
				Assessment as = SharedAssessment.getAssess();
				stat.setInt(1, as.getAid());
				aid = as.getAid();
			} else {
				//JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "No Assessments have been selected, displaying question for assessment 3");
				stat = conn.prepareStatement("SELECT * FROM "	
						+ constants.Constants.DataConstants.QUESTIONS + ";");
				aid = 3;
			}
				System.out.println(stat);
				ResultSet Rs = stat.executeQuery();				
				
				while (Rs.next()) {
					
					int qid = Rs.getInt(1);
					String name = Rs.getString(4);
					String questionContent = Rs.getString(5);
					Integer points = new Integer(Rs.getInt(6));
					
					TextQuestion question = new TextQuestion(aid, name, questionContent, points);
					
					ArrayList<TextAnswer> ans = db.DbConnection.answers_for_question(qid);
					question.addList(ans);
					
					
					lstQuestion.addElement(question.getName());
					questions.add(question);
					
			}
				
			Rs.close();
			conn.close();
				
		} catch (SQLException e1) {
				e1.printStackTrace();
		}
		lblQuestion.setVerticalAlignment(SwingConstants.TOP);
		lblQuestion.setBounds(327, 65, 201, 49);
		contentPane.add(lblQuestion);		
		JButton btnback = new JButton("Assessments");
		btnback.setBounds(10, 307, 120, 30);
		contentPane.add(btnback);
		
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HHSavedAssessments().setVisible(true);
			}
		});
		lblPts.setVerticalAlignment(SwingConstants.TOP);
		lblPts.setBounds(10, 265, 120, 72);
		contentPane.add(lblPts);
		JButton btnView = new JButton("Submit");

		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String answer = String.valueOf(questionAnswerField.getText());
				
				if (answer.isEmpty()) {
					JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "One or more fields are empty");
				} else if (selQuestion == null) {
					JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "No questions selected");
				} else {
					questAnswer = selQuestion.getCorrectAnswer();
					if (questAnswer == null) {
						JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "Question doesn't have a corresponding answer");
					} else {
						if (questAnswer.isCorrect(answer.toString())) {
							JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "Correct!!");
						} else {
							JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "incorrect!!");
						}
						
					}
				}				
			}
		});
		btnView.setBounds(419, 307, 120, 31);
		contentPane.add(btnView);		
		questionAnswerField = new JTextField();
		questionAnswerField.setAlignmentY(Component.TOP_ALIGNMENT);
		questionAnswerField.setColumns(10);
		questionAnswerField.setBounds(150, 280, 247, 57);
		contentPane.add(questionAnswerField);
		JList listQuestion = new JList<>(lstQuestion);
		contentPane.add(listQuestion);
		
		listQuestion.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String res = "<html>This question<br> is worth</html>";
				//System.out.println(res);
					
				JList list = (JList) e.getSource();
				TextQuestion question = questions.get(list.getSelectedIndex());
					
				lblQuestion.setText(question.getQuestion());					
				res = "<html>This question<br> is worth <html>" + new Integer(question.getPoints()).toString() + "<html> marks</html>" ;
				lblPts.setText(res);
				questionTitle.setText(question.getName());
				
				selQuestion = question;
				//System.out.println(selQuestion.getAssessID());
				//questAnswer = question.getCorrectAnswer();
				/*questAnswer = question.getAnswer(); */
					// need to pass this question into submit button, then check for answer
					
			}
		});
		
		
		listQuestion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listQuestion.setBounds(29, 84, 188, 112);
		
		JButton btnAdd = new JButton("Add Question");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//SharedQuestion.setQuestion(selQuestion);
				dispose();
				new HHFormFrame().setVisible(true);
			}
		});
		btnAdd.setMaximumSize(new Dimension(139, 23));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdd.setBounds(403, 239, 136, 31);
		contentPane.add(btnAdd);
		
		
		
		
		
		
	}
}

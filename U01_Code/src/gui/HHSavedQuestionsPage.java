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
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;

public class HHSavedQuestionsPage extends JFrame {

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
		
		JLabel lblQuestion = new JLabel("There are no saved questions");
		JLabel lblPts = new JLabel("");
		
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
					String res = "";
					
					JList list = (JList) e.getSource();
					TextQuestion question = questions.get(list.getSelectedIndex());
					
					lblQuestion.setText(question.getQuestion());					
					res = new Integer(question.getPoints()).toString();
					lblPts.setText(res);
					lblSavedQuestions.setText(question.getName());
					questAnswer = question.getAnswer();
					// need to pass this question into submit button, then check for answer
					
				}
			});
			
			
			
		listQuestion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			
		lblQuestion.setVerticalAlignment(SwingConstants.TOP);
		lblQuestion.setBounds(150, 73, 201, 49);
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
		contentPane.add(listQuestion);		
		lblPts.setVerticalAlignment(SwingConstants.TOP);
		lblPts.setBounds(277, 162, 190, 49);
		contentPane.add(lblPts);
		JButton btnView = new JButton("submit");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String answer = String.valueOf(questionAnswerField.getText());
				
				if (answer.isEmpty()) {
					JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "One or more fields are empty");
				} else if (lblQuestion.getText().length() == 0)  {
						JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "One or more fields are empty");
				} else if (questAnswer.compareTo(answer) == 0)  {
					JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "Correct!!");
				} else {
					JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "Wrong!! Your answer is " + questAnswer + ", btw");
				}
					
					
					
					
				}
		});
		btnView.setBounds(36, 257, 73, 25);
		contentPane.add(btnView);		
		questionAnswerField = new JTextField();
		questionAnswerField.setColumns(10);
		questionAnswerField.setBounds(163, 258, 234, 49);
		contentPane.add(questionAnswerField);
		
		
		
	}
}

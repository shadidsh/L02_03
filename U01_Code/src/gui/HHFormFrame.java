package gui;

import java.awt.EventQueue;
import question.TextQuestion;
import db.DbConnection;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class HHFormFrame extends JFrame {

	private JPanel contentPane;
	private JTextField questionNameField;
	private JTextField questionAnswerField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHFormFrame frame = new HHFormFrame();
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
	public HHFormFrame() {
		setTitle("HandyHomework");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblQuestionForm = new JLabel("Question Form  ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblQuestionForm, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblQuestionForm, 79, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblQuestionForm, -23, SpringLayout.EAST, contentPane);
		lblQuestionForm.setFont(new Font("Menlo", Font.ITALIC, 20));
		lblQuestionForm.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblQuestionForm);
		
		JLabel lblQuestionName = new JLabel("Question Name:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblQuestionName, 21, SpringLayout.SOUTH, lblQuestionForm);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblQuestionName, 10, SpringLayout.WEST, contentPane);
		lblQuestionName.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblQuestionName);
		
		questionNameField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, questionNameField, -5, SpringLayout.NORTH, lblQuestionName);
		sl_contentPane.putConstraint(SpringLayout.WEST, questionNameField, 6, SpringLayout.EAST, lblQuestionName);
		sl_contentPane.putConstraint(SpringLayout.EAST, questionNameField, 124, SpringLayout.EAST, lblQuestionName);
		contentPane.add(questionNameField);
		questionNameField.setColumns(10);
		
		JLabel lblEnterYourQuestion = new JLabel("Enter your question here: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblEnterYourQuestion, 26, SpringLayout.SOUTH, lblQuestionName);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblEnterYourQuestion, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblEnterYourQuestion);
		
		JTextArea questionContentField = new JTextArea();
		questionContentField.setWrapStyleWord(true);
		questionContentField.setLineWrap(true);
		sl_contentPane.putConstraint(SpringLayout.NORTH, questionContentField, 7, SpringLayout.SOUTH, lblEnterYourQuestion);
		sl_contentPane.putConstraint(SpringLayout.WEST, questionContentField, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, questionContentField, 88, SpringLayout.SOUTH, lblEnterYourQuestion);
		contentPane.add(questionContentField);
		
		JLabel lblFinalAnswer = new JLabel("Final Answer: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblFinalAnswer, 12, SpringLayout.SOUTH, questionContentField);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblFinalAnswer, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblFinalAnswer);
		
		questionAnswerField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, questionAnswerField, -5, SpringLayout.NORTH, lblFinalAnswer);
		sl_contentPane.putConstraint(SpringLayout.WEST, questionAnswerField, 6, SpringLayout.EAST, lblFinalAnswer);
		sl_contentPane.putConstraint(SpringLayout.EAST, questionAnswerField, 81, SpringLayout.EAST, lblFinalAnswer);
		questionAnswerField.setColumns(10);
		contentPane.add(questionAnswerField);
		
		JLabel lblNumberOfMarks = new JLabel("Number of Marks Awarded: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfMarks, 26, SpringLayout.SOUTH, questionAnswerField);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfMarks, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNumberOfMarks);
		
		JSpinner spinner = new JSpinner();
		sl_contentPane.putConstraint(SpringLayout.NORTH, spinner, -5, SpringLayout.NORTH, lblNumberOfMarks);
		sl_contentPane.putConstraint(SpringLayout.WEST, spinner, 6, SpringLayout.EAST, lblNumberOfMarks);
		contentPane.add(spinner);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setValueIsAdjusting(true);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollBar, 6, SpringLayout.EAST, lblQuestionForm);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollBar, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollBar, 241, SpringLayout.SOUTH, contentPane);
		contentPane.add(scrollBar);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = String.valueOf(questionNameField.getText());
				String questionContent = String.valueOf(questionContentField.getText());
				String answer = String.valueOf(questionAnswerField.getText());
				int value = (int) (spinner.getValue());
				System.out.println("question is :" + questionContent);
				if (name.isEmpty() || questionContent.isEmpty() || answer.isEmpty()) {
					JOptionPane.showMessageDialog(HHFormFrame.this, "One or more fields are empty");
				}
				
				else {	
					TextQuestion question = new TextQuestion(name, questionContent, answer, value);
					// add the question to database and produce successful/unsuccessful msg box
					
					Connection conn = DbConnection.getConnection();
					
					String insert = "INSERT INTO sware.textquestions " 
					+ " VALUES(?, ?, ?, ?);";
					try {
						PreparedStatement seq = conn.prepareStatement("SELECT nextval('question_id') as bigint;");
						ResultSet Rs = seq.executeQuery();
						Rs.next();
						Integer qid = Rs.getInt(1);
						
						PreparedStatement prepInsert = conn.prepareStatement(insert);
						prepInsert.setInt(1, qid);
						prepInsert.setString(2, name);
						prepInsert.setString(3, questionContent);
						prepInsert.setString(4, answer);
						System.out.println(prepInsert.toString());
						prepInsert.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					// confirm that the question was made
					String message = question.getName() + "\nQuestion is: " + question.getQuestion();
					message += "\nAnswer is: " + question.getAnswer() + "\nQuestion is worth " + question.getPoints() + " points";
					JOptionPane.showMessageDialog(HHFormFrame.this, message);
				}
			}
		});
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Main Menu");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HandyHomeworkMainPage().setVisible(true);;
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnCancel, 114, SpringLayout.SOUTH, questionContentField);
		sl_contentPane.putConstraint(SpringLayout.EAST, questionContentField, 0, SpringLayout.EAST, btnCancel);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCancel, 386, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSubmit, 0, SpringLayout.NORTH, btnCancel);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnSubmit, -6, SpringLayout.WEST, btnCancel);
		contentPane.add(btnCancel);
	}
}

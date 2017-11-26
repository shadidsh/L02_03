package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assessment.Assessment;
import assessment.SelectedAssessment;
import dao.DbQuestions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HHCreateTextQuestion extends JFrame {

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
					HHCreateTextQuestion frame = new HHCreateTextQuestion();
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
	public HHCreateTextQuestion() {
		setTitle("HandyHomework - Create Text Question");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 325);
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuestionForm = new JLabel("Question Form  ");
		lblQuestionForm.setBounds(223, 22, 219, 24);
		lblQuestionForm.setFont(new Font("Menlo", Font.ITALIC, 23));
		lblQuestionForm.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblQuestionForm);
		
		JLabel lblQuestionName = new JLabel("Question Name:");
		lblQuestionName.setBounds(20, 75, 101, 16);
		lblQuestionName.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblQuestionName);
		
		questionNameField = new JTextField();
		questionNameField.setBounds(122, 73, 118, 20);
		contentPane.add(questionNameField);
		questionNameField.setColumns(10);
		questionNameField.setName("questionName");
		
		JLabel lblEnterYourQuestion = new JLabel("Enter your question here: ");
		lblEnterYourQuestion.setBounds(20, 102, 162, 16);
		contentPane.add(lblEnterYourQuestion);
		
		JTextArea questionContentField = new JTextArea();
		questionContentField.setBounds(20, 123, 426, 81);
		questionContentField.setWrapStyleWord(true);
		questionContentField.setLineWrap(true);
		contentPane.add(questionContentField);
		questionContentField.setName("content");
		
		JLabel lblFinalAnswer = new JLabel("Final Answer: ");
		lblFinalAnswer.setBounds(20, 225, 90, 14);
		contentPane.add(lblFinalAnswer);
		
		questionAnswerField = new JTextField();
		questionAnswerField.setBounds(109, 222, 150, 20);
		questionAnswerField.setColumns(10);
		contentPane.add(questionAnswerField);
		questionAnswerField.setName("questionAnswer");
		
		JLabel lblNumberOfMarks = new JLabel("Number of Marks Awarded: ");
		lblNumberOfMarks.setBounds(20, 257, 180, 14);
		contentPane.add(lblNumberOfMarks);
		
		JSpinner spinMarks = new JSpinner();
		spinMarks.setBounds(192, 254, 90, 20);
		contentPane.add(spinMarks);
		spinMarks.setName("spin");
		
		JComponent field = ((JSpinner.DefaultEditor) spinMarks.getEditor());
	    Dimension prefSize = field.getPreferredSize();
	    prefSize = new Dimension(40, prefSize.height);
	    field.setPreferredSize(prefSize);
		
		JButton btnSubmit = new JButton("Submit");
		contentPane.getRootPane().setDefaultButton(btnSubmit);
		btnSubmit.setName("submit");
		btnSubmit.setBounds(315, 225, 100, 50);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = String.valueOf(questionNameField.getText());
				String questionContent = String.valueOf(questionContentField.getText());
				String answer = String.valueOf(questionAnswerField.getText());
				int value = (int) (spinMarks.getValue());
				System.out.println("question is :" + questionContent);
				if (name.isEmpty() || questionContent.isEmpty() || answer.isEmpty()) {
					JOptionPane.showMessageDialog(HHCreateTextQuestion.this, "One or more fields are empty.");
				} else if (!SelectedAssessment.isSelected()) {
					JOptionPane.showMessageDialog(HHCreateTextQuestion.this, "No assessment selected.");
				}				
				else {	
					try {
						Assessment as = SelectedAssessment.getAssess();
						DbQuestions dbQuest = new DbQuestions();
						
						int qid = dbQuest.insertQuestions(as.getAid(), name, questionContent, value, false);
						dbQuest.insertAnswers(qid,  true,  answer);
						
						//String message = name + "\nQuestion is: " + questionContent + "\nSuccessfully added.";
						//JOptionPane.showMessageDialog(HHFormFrame.this, message);
						questionContentField.setText("");
						
						questionNameField.setText("");
						questionAnswerField.setText("");
						spinMarks.setValue(0);						
						
					} catch (NullPointerException e1){
						System.out.println("Could not insert question into database."); 
						e1.printStackTrace();
						JOptionPane.showMessageDialog(HHCreateTextQuestion.this, "Could not save question - please check your connection and try again.");
					}
				}
			}
		});
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("\u2190 Back");
		btnCancel.setName("back");
		btnCancel.setBounds(20, 26, 100, 25);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHSavedQuestionsPage frame = new HHSavedQuestionsPage();
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		contentPane.add(btnCancel);
	}
}

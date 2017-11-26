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
import javax.swing.JCheckBox;

public class AddMultipleChoice extends JFrame {

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
					AddMultipleChoice frame = new AddMultipleChoice();
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
	public AddMultipleChoice() {
		setTitle("questionCreate");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 375);
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
		
		JLabel lblFinalAnswer = new JLabel("Answer: ");
		lblFinalAnswer.setBounds(20, 215, 90, 14);
		contentPane.add(lblFinalAnswer);
		
		questionAnswerField = new JTextField();
		questionAnswerField.setBounds(20, 240, 131, 37);
		questionAnswerField.setColumns(10);
		contentPane.add(questionAnswerField);
		questionAnswerField.setName("questionAnswer");
		
		JLabel lblNumberOfMarks = new JLabel("Number of Marks Awarded: ");
		lblNumberOfMarks.setBounds(20, 300, 180, 14);
		contentPane.add(lblNumberOfMarks);
		
		JSpinner spinMarks = new JSpinner();
		spinMarks.setBounds(192, 297, 90, 20);
		contentPane.add(spinMarks);
		spinMarks.setName("spin");
		
		JComponent field = ((JSpinner.DefaultEditor) spinMarks.getEditor());
	    Dimension prefSize = field.getPreferredSize();
	    prefSize = new Dimension(40, prefSize.height);
	    field.setPreferredSize(prefSize);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(315, 275, 100, 50);
		btnSubmit.setName("submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = String.valueOf(questionNameField.getText());
				String questionContent = String.valueOf(questionContentField.getText());
				String answer = String.valueOf(questionAnswerField.getText());
				int value = (int) (spinMarks.getValue());
				System.out.println("question is :" + questionContent);
				if (name.isEmpty() || questionContent.isEmpty() || answer.isEmpty()) {
					JOptionPane.showMessageDialog(AddMultipleChoice.this, "One or more fields are empty.");
				} else if (!SelectedAssessment.isSelected()) {
					JOptionPane.showMessageDialog(AddMultipleChoice.this, "No assessment selected.");
				}				
				else {	
					try {
						Assessment as = SelectedAssessment.getAssess();
						DbQuestions dbQuest = new DbQuestions();
						
						int qid = dbQuest.insertQuestions(as.getAid(), name, questionContent, value);
						dbQuest.insertAnswers(qid,  true,  answer);
						
						questionContentField.setText("");
						
						questionNameField.setText("");
						questionAnswerField.setText("");
						spinMarks.setValue(0);						
						
					} catch (NullPointerException e1){
						System.out.println("Could not insert question into database."); 
						e1.printStackTrace();
						JOptionPane.showMessageDialog(AddMultipleChoice.this, "Could not save question - please check your connection and try again.");
					}
				}
			}
		});
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("\u2190 Back");
		btnCancel.setBounds(20, 26, 100, 25);
		btnCancel.setName("back");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHSavedQuestionsPage frame = new HHSavedQuestionsPage();
				frame.setVisible(true);
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		contentPane.add(btnCancel);
		
		JCheckBox chckbxCorrectAnswer = new JCheckBox("Correct Answer");
		chckbxCorrectAnswer.setBounds(161, 211, 180, 23);
		contentPane.add(chckbxCorrectAnswer);
		
		JButton btnAddAnswer = new JButton("Add Answer");
		contentPane.getRootPane().setDefaultButton(btnAddAnswer);
		btnAddAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chckbxCorrectAnswer.isSelected();
				questionAnswerField.setText("");
			}
		});
		btnAddAnswer.setBounds(161, 235, 125, 50);
		contentPane.add(btnAddAnswer);
		
	}
}

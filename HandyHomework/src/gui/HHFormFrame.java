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
	public HHFormFrame() {
		setTitle("HandyHomework");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 325);
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuestionForm = new JLabel("Question Form  ");
		lblQuestionForm.setBounds(10, 40, 150, 26);
		lblQuestionForm.setFont(new Font("Menlo", Font.ITALIC, 20));
		lblQuestionForm.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblQuestionForm);
		
		JLabel lblQuestionName = new JLabel("Question Name:");
		lblQuestionName.setBounds(20, 75, 90, 14);
		lblQuestionName.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblQuestionName);
		
		questionNameField = new JTextField();
		questionNameField.setBounds(115, 72, 118, 20);
		contentPane.add(questionNameField);
		questionNameField.setColumns(10);
		
		JLabel lblEnterYourQuestion = new JLabel("Enter your question here: ");
		lblEnterYourQuestion.setBounds(20, 102, 150, 14);
		contentPane.add(lblEnterYourQuestion);
		
		JTextArea questionContentField = new JTextArea();
		questionContentField.setBounds(20, 123, 426, 81);
		questionContentField.setWrapStyleWord(true);
		questionContentField.setLineWrap(true);
		contentPane.add(questionContentField);
		
		JLabel lblFinalAnswer = new JLabel("Final Answer: ");
		lblFinalAnswer.setBounds(20, 225, 90, 14);
		contentPane.add(lblFinalAnswer);
		
		questionAnswerField = new JTextField();
		questionAnswerField.setBounds(100, 222, 150, 20);
		questionAnswerField.setColumns(10);
		contentPane.add(questionAnswerField);
		
		JLabel lblNumberOfMarks = new JLabel("Number of Marks Awarded: ");
		lblNumberOfMarks.setBounds(20, 257, 180, 14);
		contentPane.add(lblNumberOfMarks);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(180, 252, 110, 20);
		contentPane.add(spinner);
		
		JComponent field = ((JSpinner.DefaultEditor) spinner.getEditor());
	    Dimension prefSize = field.getPreferredSize();
	    prefSize = new Dimension(40, prefSize.height);
	    field.setPreferredSize(prefSize);
		
		JButton btnSubmit = new JButton("Submit");
		contentPane.getRootPane().setDefaultButton(btnSubmit);
		btnSubmit.setBounds(325, 220, 100, 50);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = String.valueOf(questionNameField.getText());
				String questionContent = String.valueOf(questionContentField.getText());
				String answer = String.valueOf(questionAnswerField.getText());
				int value = (int) (spinner.getValue());
				System.out.println("question is :" + questionContent);
				if (name.isEmpty() || questionContent.isEmpty() || answer.isEmpty()) {
					JOptionPane.showMessageDialog(HHFormFrame.this, "One or more fields are empty.");
				} else if (!SelectedAssessment.isSelected()) {
					JOptionPane.showMessageDialog(HHFormFrame.this, "No assessment selected.");
				}				
				else {	
					try {
						Assessment as = SelectedAssessment.getAssess();
						DbQuestions dbQuest = new DbQuestions();
						
						int qid = dbQuest.insertQuestions(as.getAid(), name, questionContent, value);
						dbQuest.insertAnswers(qid,  true,  answer);
						
						String message = name + "\nQuestion is: " + questionContent + "\nSuccessfully added.";
						JOptionPane.showMessageDialog(HHFormFrame.this, message);
						questionContentField.setText("");
						questionNameField.setText("");
						questionAnswerField.setText("");
						spinner.setValue(0);
						
					} catch (NullPointerException e1){
						System.out.println("Could not insert question into database."); 
						e1.printStackTrace();
						JOptionPane.showMessageDialog(HHFormFrame.this, "Could not save question - please check your connection and try again.");
					}
				}
			}
		});
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("\u2190 Back");
		btnCancel.setBounds(15, 10, 100, 25);
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
	}
}

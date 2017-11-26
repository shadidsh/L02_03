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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		setBounds(100, 100, 734, 501);
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuestionName = new JLabel("Question Name:");
		lblQuestionName.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestionName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuestionName.setBounds(224, 25, 262, 28);
		lblQuestionName.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblQuestionName);
		
		questionNameField = new JTextField();
		questionNameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		questionNameField.setBounds(207, 66, 304, 28);
		contentPane.add(questionNameField);
		questionNameField.setColumns(10);
		questionNameField.setName("questionName");
		
		JLabel lblEnterYourQuestion = new JLabel("Enter your question here: ");
		lblEnterYourQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterYourQuestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnterYourQuestion.setBounds(20, 129, 466, 27);
		contentPane.add(lblEnterYourQuestion);
		
		JTextArea questionContentField = new JTextArea();
		questionContentField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		questionContentField.setBounds(20, 169, 466, 107);
		questionContentField.setWrapStyleWord(true);
		questionContentField.setLineWrap(true);
		contentPane.add(questionContentField);
		questionContentField.setName("content");
		
		JLabel lblFinalAnswer = new JLabel("Final Answer: ");
		lblFinalAnswer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFinalAnswer.setBounds(228, 307, 170, 14);
		contentPane.add(lblFinalAnswer);
		
		questionAnswerField = new JTextField();
		questionAnswerField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		questionAnswerField.setBounds(20, 334, 668, 57);
		questionAnswerField.setColumns(10);
		contentPane.add(questionAnswerField);
		questionAnswerField.setName("questionAnswer");
		
		JLabel lblNumberOfMarks = new JLabel("Number of Marks Awarded: ");
		lblNumberOfMarks.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNumberOfMarks.setBounds(12, 428, 228, 14);
		contentPane.add(lblNumberOfMarks);
		
		JSpinner spinMarks = new JSpinner();
		spinMarks.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spinMarks.setBounds(241, 428, 101, 20);
		contentPane.add(spinMarks);
		spinMarks.setName("spin");
		
		JComponent field = ((JSpinner.DefaultEditor) spinMarks.getEditor());
	    Dimension prefSize = field.getPreferredSize();
	    prefSize = new Dimension(40, prefSize.height);
	    field.setPreferredSize(prefSize);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.getRootPane().setDefaultButton(btnSubmit);
		btnSubmit.setName("submit");
		btnSubmit.setBounds(548, 408, 156, 38);
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
						
						int qid = dbQuest.insertQuestions(as.getAid(), name, questionContent, value, false, false);
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
		
		JButton btnCancel = new JButton("Back");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancel.setName("back");
		btnCancel.setBounds(20, 26, 123, 38);
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
		
		JButton btnPrev = new JButton("Preview");
		btnPrev.setVisible(false);
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPrev.setName("submit");
		btnPrev.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPrev.setBounds(517, 238, 156, 38);
		contentPane.add(btnPrev);
		
		JCheckBox chckbxLatex = new JCheckBox("Is latex");
		chckbxLatex.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (chckbxLatex.isSelected()) {
					btnPrev.setVisible(true);
				} else {
					btnPrev.setVisible(false);
				}
			}
		});
		chckbxLatex.setFont(new Font("Tahoma", Font.PLAIN, 18));
		chckbxLatex.setBounds(548, 170, 113, 25);
		contentPane.add(chckbxLatex);
	}
}

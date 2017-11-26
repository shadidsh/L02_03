package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import answer.TextAnswer;
import assessment.Assessment;
import assessment.SelectedAssessment;
import dao.DbQuestions;
import login.StudentLogin;
import question.MultQuestion;
import question.TextQuestion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.Component;

public class AddMultipleChoice extends JFrame {

	private JPanel contentPane;
	private JTextField questionNameField;
	private JTextField questionAnswerField;
	private JTable table;
	private MultQuestion textQ;
	private int ansCount = 0;
	private int ansInd;
	private List<String> answers;
	
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
		setBounds(100, 100, 778, 531);
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuestionName = new JLabel("Question Name:");
		lblQuestionName.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestionName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuestionName.setBounds(219, 133, 315, 24);
		lblQuestionName.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblQuestionName);
		
		questionNameField = new JTextField();
		questionNameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		questionNameField.setBounds(36, 170, 698, 41);
		contentPane.add(questionNameField);
		questionNameField.setColumns(10);
		questionNameField.setName("questionName");
		
		JLabel lblEnterYourQuestion = new JLabel("Enter your question here: ");
		lblEnterYourQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterYourQuestion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterYourQuestion.setBounds(302, 210, 213, 49);
		contentPane.add(lblEnterYourQuestion);
		
		JTextArea questionContentField = new JTextArea();
		questionContentField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		questionContentField.setBounds(36, 273, 698, 88);
		questionContentField.setWrapStyleWord(true);
		questionContentField.setLineWrap(true);
		contentPane.add(questionContentField);
		questionContentField.setName("content");
		
		JLabel lblFinalAnswer = new JLabel("Enter your answer here");
		lblFinalAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinalAnswer.setBounds(86, 302, 648, 37);
		contentPane.add(lblFinalAnswer);
		lblFinalAnswer.setVisible(false);
		
		questionAnswerField = new JTextField();
		questionAnswerField.setBounds(86, 341, 648, 37);
		questionAnswerField.setColumns(10);
		contentPane.add(questionAnswerField);
		questionAnswerField.setName("questionAnswer");
		questionAnswerField.setVisible(false);
		
		JLabel lblNumberOfMarks = new JLabel("Number of Marks Awarded: ");
		lblNumberOfMarks.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNumberOfMarks.setBounds(20, 403, 222, 35);
		contentPane.add(lblNumberOfMarks);
		
		JSpinner spinMarks = new JSpinner();
		spinMarks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinMarks.setBounds(242, 405, 116, 35);
		contentPane.add(spinMarks);
		spinMarks.setName("spin");
		
		JComponent field = ((JSpinner.DefaultEditor) spinMarks.getEditor());
	    Dimension prefSize = field.getPreferredSize();
	    prefSize = new Dimension(40, prefSize.height);
	    field.setPreferredSize(prefSize);
	    
		String[] columnNames = {"Answer"};
		String[] colHeadings = {"Answer"};
		int numRows = 0;
		
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length) {
			@Override
		    public String getColumnName(int index) {
		        return columnNames[index];
		    }
		};		
		JButton btnCancel = new JButton("\u2190 Back");
		btnCancel.setBounds(20, 13, 132, 41);
		btnCancel.setName("back");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textQ = null;
				
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
		btnAddAnswer.setVisible(false);
		
		contentPane.getRootPane().setDefaultButton(btnAddAnswer);
		btnAddAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String answer = String.valueOf(questionAnswerField.getText());				
				
				if (answer.isEmpty() ) {
					JOptionPane.showMessageDialog(AddMultipleChoice.this, 
							"Answer field is empty.");
				} else if (ansCount > 5) {
					JOptionPane.showMessageDialog(AddMultipleChoice.this, 
							"Maximum number of answers for a question is 6.");
				} else if (alreadyExists(table, answer)) {
					JOptionPane.showMessageDialog(AddMultipleChoice.this, 
							"Duplicate answer entered.");					
				} else {
					
					Object[] data = {answer};
					model.addRow(data);
					ansCount++;
					questionAnswerField.setText("");
	
				}
			}
		});
		btnAddAnswer.setBounds(334, 406, 125, 50);
		contentPane.add(btnAddAnswer);
		
		table = new JTable(model);
		model.setColumnIdentifiers(colHeadings);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVisible(false);

		scrollPane.setBounds(77, 134, 657, 155);
		contentPane.add(scrollPane);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblCorAns = new JLabel("Please select one correct answer from the list");
		lblCorAns.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCorAns.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorAns.setToolTipText("");
		lblCorAns.setBounds(213, 100, 336, 57);
		contentPane.add(lblCorAns);
		lblCorAns.setVisible(false);
		
		JButton btnRemoveAnswer = new JButton("Remove Answer");
		btnRemoveAnswer.setVisible(false);
		btnRemoveAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(AddMultipleChoice.this, 
							"Please select an answer to remove.");
				} else {
					DefaultTableModel dm=(DefaultTableModel)table.getModel();
					dm.removeRow(row);
					ansCount--;
				}
			}
		});
		btnRemoveAnswer.setBounds(596, 406, 140, 50);
		contentPane.add(btnRemoveAnswer);
		
		JLabel lblQuestion = new JLabel("Question Name:");
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setVerticalAlignment(SwingConstants.TOP);
		lblQuestion.setBounds(147, 77, 419, 24);
		contentPane.add(lblQuestion);
		lblQuestion.setVisible(false);
		
		JLabel lblSelectedQn = new JLabel("");
		lblSelectedQn.setBounds(77, 188, 213, 109);
		contentPane.add(lblSelectedQn);
		
		JButton btnAddQuestion = new JButton("Add Question");
		btnAddQuestion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnAbortQuestion = new JButton("Discard Question");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(86, 406, 125, 50);
		btnSubmit.setName("Submit");
		btnSubmit.setVisible(false);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				if (ansCount < 2) {
					JOptionPane.showMessageDialog(AddMultipleChoice.this, 
							"Atleast 2 answers are required for multiple choice.");
				} else if (row < 0 || table.getSelectedRowCount() > 1) {
					JOptionPane.showMessageDialog(AddMultipleChoice.this, 
							"Please select one correct answer for this question.");
				} else {
					DefaultTableModel dm=(DefaultTableModel)table.getModel();
					DbQuestions dbQ = new DbQuestions();
					for (int ansInd = 0; ansInd < dm.getRowCount();ansInd++) {
						String ans = (String) dm.getValueAt(ansInd, 0);
						dbQ.insertAnswers(textQ.getQid(), ansInd == row, ans);
					}
					dm.setRowCount(0);
					
					JOptionPane.showMessageDialog(AddMultipleChoice.this, 
							"Successfully added.");
					
					//RESET
					ansCount = 0;
					textQ = null;
					lblQuestionName.setVisible(true);
					questionNameField.setVisible(true);
					lblEnterYourQuestion.setVisible(true);
					questionContentField.setVisible(true);					
					lblNumberOfMarks.setVisible(true);
					spinMarks.setVisible(true);
					btnAddQuestion.setVisible(true);
					
					lblQuestion.setVisible(false);
					scrollPane.setVisible(false);
					lblFinalAnswer.setVisible(false);
					questionAnswerField.setVisible(false);					
					btnAddAnswer.setVisible(false);
					btnRemoveAnswer.setVisible(false);
					btnSubmit.setVisible(false);
					
					questionContentField.setText("");
					questionNameField.setText("");
					lblSelectedQn.setText("");
					spinMarks.setValue(0);
				}
	
			}
		});
		contentPane.add(btnSubmit);
		
		
		btnAddQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textQ == null) {
					String name = String.valueOf(questionNameField.getText());
					String questionContent = String.valueOf(questionContentField.getText());					
					int value = (int) (spinMarks.getValue());
					
					System.out.println("question is :" + questionContent);
					if (name.isEmpty() || questionContent.isEmpty()) {
						JOptionPane.showMessageDialog(AddMultipleChoice.this, 
								"One or more fields for questions are empty.");
					} else if (!SelectedAssessment.isSelected()) {
						JOptionPane.showMessageDialog(AddMultipleChoice.this, "No assessment selected.");
					} else {
						Assessment as = SelectedAssessment.getAssess();
						DbQuestions dbQuest = new DbQuestions();
						
						int qid = dbQuest.insertQuestions(as.getAid(), name, questionContent, value);
						MultQuestion mc = new MultQuestion(qid, questionContent, questionContent, qid);
						
						lblSelectedQn.setText(mc.getQuestion());
						textQ = mc;
						
						scrollPane.setName("Answers for " +  mc.name);
						
						lblQuestionName.setVisible(false);
						questionNameField.setVisible(false);
						lblEnterYourQuestion.setVisible(false);
						questionContentField.setVisible(false);
						lblNumberOfMarks.setVisible(false);
						spinMarks.setVisible(false);
						btnAddQuestion.setVisible(false);						
						
						scrollPane.setVisible(true);
						lblFinalAnswer.setVisible(true);
						questionAnswerField.setVisible(true);		
						btnAddAnswer.setVisible(true);
						btnRemoveAnswer.setVisible(true);
						btnSubmit.setVisible(true);
						btnAbortQuestion.setVisible(true);
						
						ansCount = 0;
					}
				}
			}
		});
		btnAddQuestion.setName("AddQuestion");
		btnAddQuestion.setBounds(459, 406, 125, 50);
		contentPane.add(btnAddQuestion);
		
		btnAbortQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DbQuestions dbQuest = new DbQuestions();
				dbQuest.removeQuestion(textQ.qid);
				
				//RESET
				ansCount = 0;
				textQ = null;
				lblQuestionName.setVisible(true);
				questionNameField.setVisible(true);
				lblEnterYourQuestion.setVisible(true);
				questionContentField.setVisible(true);					
				lblNumberOfMarks.setVisible(true);
				spinMarks.setVisible(true);
				btnAddQuestion.setVisible(true);
				
				lblQuestion.setVisible(false);
				scrollPane.setVisible(false);
				lblFinalAnswer.setVisible(false);
				questionAnswerField.setVisible(false);					
				btnAddAnswer.setVisible(false);
				btnRemoveAnswer.setVisible(false);
				btnSubmit.setVisible(false);
				btnAbortQuestion.setVisible(false);
				
				questionContentField.setText("");
				questionNameField.setText("");
				lblSelectedQn.setText("");
				spinMarks.setValue(0);
				
			}
		});
		btnAbortQuestion.setBounds(533, 15, 201, 39);
		contentPane.add(btnAbortQuestion);
		btnAbortQuestion.setVisible(false);
	}
	
	public boolean alreadyExists(JTable table, String answer) {
		DefaultTableModel dm=(DefaultTableModel)table.getModel();
		for (int row = 0;row < dm.getRowCount();row++) {
			String ans = (String) dm.getValueAt(row, 0);
			if (ans.equals(answer)) {
				return true;
			}
		}
		return false;
	}
}

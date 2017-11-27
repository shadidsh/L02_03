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
import java.awt.Frame;

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
		SwitchForm sf = new SwitchForm();
		setTitle("HandyHomework - Create Multiple Choice Question");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 500);
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuestionName = new JLabel("Question Name:");
		lblQuestionName.setBounds(39, 102, 107, 19);
		lblQuestionName.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestionName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuestionName.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblQuestionName);
		
		questionNameField = new JTextField();
		questionNameField.setBounds(30, 123, 513, 41);
		questionNameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(questionNameField);
		questionNameField.setColumns(10);
		questionNameField.setName("questionName");
		
		JLabel lblEnterYourQuestion = new JLabel("Enter your question here: ");
		lblEnterYourQuestion.setBounds(39, 195, 171, 19);
		lblEnterYourQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterYourQuestion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblEnterYourQuestion);
		
		JTextArea questionContentField = new JTextArea();
		questionContentField.setBounds(30, 226, 513, 88);
		questionContentField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		questionContentField.setWrapStyleWord(true);
		questionContentField.setLineWrap(true);
		contentPane.add(questionContentField);
		questionContentField.setName("content");
		
		JLabel lblFinalAnswer = new JLabel("Enter your answer here");
		lblFinalAnswer.setBounds(82, 275, 143, 16);
		lblFinalAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblFinalAnswer);
		lblFinalAnswer.setVisible(false);
		
		questionAnswerField = new JTextField();
		questionAnswerField.setBounds(80, 294, 463, 37);
		questionAnswerField.setColumns(10);
		contentPane.add(questionAnswerField);
		questionAnswerField.setName("questionAnswer");
		questionAnswerField.setVisible(false);
		
		JLabel lblNumberOfMarks = new JLabel("Number of Marks Awarded: ");
		lblNumberOfMarks.setBounds(39, 358, 186, 19);
		lblNumberOfMarks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNumberOfMarks);
		
		JSpinner spinMarks = new JSpinner();
		spinMarks.setBounds(229, 350, 116, 35);
		spinMarks.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
		
		JButton btnAddAnswer = new JButton("Add Answer");
		btnAddAnswer.setBounds(237, 359, 125, 50);
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
		contentPane.add(btnAddAnswer);
		table = new JTable(model);
		model.setColumnIdentifiers(colHeadings);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(71, 87, 470, 155);
		scrollPane.setVisible(false);
		contentPane.add(scrollPane);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblCorAns = new JLabel("Please select one correct answer from the list");
		lblCorAns.setBounds(117, 54, 336, 57);
		lblCorAns.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCorAns.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorAns.setToolTipText("");
		contentPane.add(lblCorAns);
		lblCorAns.setVisible(false);
		
		JButton btnRemoveAnswer = new JButton("Remove Answer");
		btnRemoveAnswer.setBounds(390, 359, 140, 50);
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
		contentPane.add(btnRemoveAnswer);
		
		JLabel lblQuestion = new JLabel("Q: ");
		lblQuestion.setBounds(39, 33, 543, 29);
		lblQuestion.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblQuestion);
		lblQuestion.setVisible(false);
		
		JLabel lblSelectedQn = new JLabel("");
		lblSelectedQn.setBounds(71, 141, 213, 109);
		contentPane.add(lblSelectedQn);
		
		JButton btnCancel = new JButton("\u2190 Back");
		btnCancel.setBounds(20, 13, 132, 41);
		btnCancel.setName("back");
		
		JButton btnAddQuestion = new JButton("Add Question");
		btnAddQuestion.setBounds(388, 406, 125, 50);
		btnAddQuestion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnAbortQuestion = new JButton("Discard Question");
		btnAbortQuestion.setBounds(202, 413, 201, 39);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(80, 359, 125, 50);
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
						dm.removeRow(ansInd);
					}
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
						
						int qid = dbQuest.insertQuestions(as.getAid(), name, questionContent, value, true, false);
						MultQuestion mc = new MultQuestion(qid, questionContent, questionContent, qid);
						
						lblSelectedQn.setText(mc.getQuestion());
						textQ = mc;
						
						scrollPane.setName("Answers");
						
						lblQuestionName.setVisible(false);
						questionNameField.setVisible(false);
						lblEnterYourQuestion.setVisible(false);
						questionContentField.setVisible(false);
						lblNumberOfMarks.setVisible(false);
						spinMarks.setVisible(false);
						btnAddQuestion.setVisible(false);			
						btnCancel.setVisible(false);
						
						lblQuestion.setText("Question: " + mc.name);
						lblQuestion.setVisible(true);
						lblQuestion.setPreferredSize(lblQuestion.getPreferredSize());
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
				btnCancel.setVisible(true);
				
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
		contentPane.add(btnAbortQuestion);
		btnAbortQuestion.setVisible(false);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textQ = null;
				HHSavedQuestionsPage frame = new HHSavedQuestionsPage();
				sf.switchForm(frame);
				if (frame.isShowing()){
					dispose();
				}			
				
			}
		});
		contentPane.add(btnCancel);
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

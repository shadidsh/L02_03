package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
		setBounds(100, 100, 835, 533);
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuestionForm = new JLabel("Question Form  ");
		lblQuestionForm.setBounds(166, 22, 219, 24);
		lblQuestionForm.setFont(new Font("Menlo", Font.ITALIC, 23));
		lblQuestionForm.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblQuestionForm);
		
		JLabel lblQuestionName = new JLabel("Question Name:");
		lblQuestionName.setBounds(138, 94, 101, 16);
		lblQuestionName.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblQuestionName);
		
		questionNameField = new JTextField();
		questionNameField.setBounds(20, 123, 359, 20);
		contentPane.add(questionNameField);
		questionNameField.setColumns(10);
		questionNameField.setName("questionName");
		
		JLabel lblEnterYourQuestion = new JLabel("Enter your question here: ");
		lblEnterYourQuestion.setBounds(106, 156, 162, 16);
		contentPane.add(lblEnterYourQuestion);
		
		JTextArea questionContentField = new JTextArea();
		questionContentField.setBounds(20, 185, 359, 37);
		questionContentField.setWrapStyleWord(true);
		questionContentField.setLineWrap(true);
		contentPane.add(questionContentField);
		questionContentField.setName("content");
		
		JLabel lblFinalAnswer = new JLabel("Answer: ");
		lblFinalAnswer.setBounds(138, 257, 90, 37);
		contentPane.add(lblFinalAnswer);
		lblFinalAnswer.setVisible(false);
		
		questionAnswerField = new JTextField();
		questionAnswerField.setBounds(20, 307, 299, 37);
		questionAnswerField.setColumns(10);
		contentPane.add(questionAnswerField);
		questionAnswerField.setName("questionAnswer");
		questionAnswerField.setVisible(false);
		
		JLabel lblNumberOfMarks = new JLabel("Number of Marks Awarded: ");
		lblNumberOfMarks.setBounds(20, 424, 180, 14);
		contentPane.add(lblNumberOfMarks);
		
		JSpinner spinMarks = new JSpinner();
		spinMarks.setBounds(192, 421, 90, 20);
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
		
		JButton btnAddAnswer = new JButton("Add Answer");
		btnAddAnswer.setVisible(false);
		
		contentPane.getRootPane().setDefaultButton(btnAddAnswer);
		btnAddAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String answer = String.valueOf(questionAnswerField.getText());				
				
				if (answer.isEmpty() ) {
					JOptionPane.showMessageDialog(AddMultipleChoice.this, 
							"Answer field is empty.");
				} else if (ansCount > 6) {
					JOptionPane.showMessageDialog(AddMultipleChoice.this, 
							"Maximum number of answers for a question is 6.");
				} else if (alreadyExists(table, answer)) {
					JOptionPane.showMessageDialog(AddMultipleChoice.this, 
							"Duplicate answer entered.");					
				} else {
					
					Object[] data = {answer};
					model.addRow(data);
					questionAnswerField.setText("");
	
				}
			}
		});
		btnAddAnswer.setBounds(532, 406, 125, 50);
		contentPane.add(btnAddAnswer);

		

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVisible(false);
		scrollPane.setName("studentTable");
		scrollPane.setBounds(401, 136, 404, 240);
		contentPane.add(scrollPane);
		
		JLabel lblPleaseSelectThe = new JLabel("Please select the correct answer");
		lblPleaseSelectThe.setBounds(518, 84, 236, 37);
		contentPane.add(lblPleaseSelectThe);
		
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
				}
			}
		});
		btnRemoveAnswer.setBounds(669, 406, 125, 50);
		contentPane.add(btnRemoveAnswer);
		
		JLabel lblQuestion = new JLabel("Question Name:");
		lblQuestion.setVerticalAlignment(SwingConstants.TOP);
		lblQuestion.setBounds(434, 55, 101, 16);
		contentPane.add(lblQuestion);
		
		JLabel lblSelectedQn = new JLabel("Selected Qn");
		lblSelectedQn.setVerticalAlignment(SwingConstants.TOP);
		lblSelectedQn.setBounds(572, 55, 101, 16);
		contentPane.add(lblSelectedQn);
		
		JButton button = new JButton("Add Question");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(410, 406, 125, 50);
		btnSubmit.setName("Submit");
		btnSubmit.setVisible(false);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (ansCount < 2) {
					JOptionPane.showMessageDialog(AddMultipleChoice.this, 
							"Atleast 2 answers are required for multiple choice.");
				} else if (row < 0) {
					JOptionPane.showMessageDialog(AddMultipleChoice.this, 
							"Please select the correct answer for this question.");
				} else {
					DefaultTableModel dm=(DefaultTableModel)table.getModel();
					DbQuestions dbQ = new DbQuestions();
					for (int ansInd = 0; ansInd < dm.getRowCount();ansInd++) {
						String ans = (String) dm.getValueAt(ansInd, 0);
						dbQ.insertAnswers(textQ.getQid(), ansInd == row, ans);
						
						dm.removeRow(ansInd);
					}
					textQ = null;
					
					JOptionPane.showMessageDialog(AddMultipleChoice.this, 
							"Successfully added.");
					
					lblQuestionName.setVisible(true);
					questionNameField.setVisible(true);
					lblEnterYourQuestion.setVisible(true);
					questionContentField.setVisible(true);					
					lblNumberOfMarks.setVisible(true);
					spinMarks.setVisible(true);
					button.setVisible(true);
					
					
					scrollPane.setVisible(false);
					lblFinalAnswer.setVisible(false);
					questionAnswerField.setVisible(false);					
					btnAddAnswer.setVisible(false);
					btnRemoveAnswer.setVisible(false);
					btnSubmit.setVisible(false);
					
				}
	
			}
		});
		contentPane.add(btnSubmit);
		
		
		button.addActionListener(new ActionListener() {
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
						
						lblQuestionName.setVisible(false);
						questionNameField.setVisible(false);
						lblEnterYourQuestion.setVisible(false);
						questionContentField.setVisible(false);
						lblNumberOfMarks.setVisible(false);
						spinMarks.setVisible(false);
						button.setVisible(false);
						
						
						scrollPane.setVisible(true);
						lblFinalAnswer.setVisible(true);
						questionAnswerField.setVisible(true);		
						btnAddAnswer.setVisible(true);
						btnRemoveAnswer.setVisible(true);
						btnSubmit.setVisible(true);
						
						ansCount = 0;
						
						
					}
				}
			}
		});
		button.setName("AddQuestion");
		button.setBounds(315, 406, 125, 50);
		contentPane.add(button);
		
		table = new JTable(model);
		model.setColumnIdentifiers(colHeadings);
		
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
	
	public void reset(javax.swing.JComponent c) {
		c.setVisible(!c.isVisible());
		
		
	}
/*	public void hideQuestionInput() {
		lblQuestionName.setVisible(false);
		questionNameField.setVisible(false);
		lblEnterYourQuestion.setVisible(false);
		questionContentField.setVisible(false);
		lblFinalAnswer.setVisible(false);
		questionAnswerField.setVisible(false);
		lblNumberOfMarks.setVisible(false);
		spinMarks.setVisible(false);
		button.setVisible(false);
	}
	*/
}

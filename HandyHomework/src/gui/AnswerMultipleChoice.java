package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import answer.TextAnswer;
import dao.DbQuestions;
import question.MultQuestion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnswerMultipleChoice extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// id is the test MCQ that has 4 ans
					AnswerMultipleChoice frame = new AnswerMultipleChoice(36);
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
	public AnswerMultipleChoice(Integer qid) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuestionName = new JLabel("Question Name");
		lblQuestionName.setBounds(141, 18, 150, 33);
		lblQuestionName.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		contentPane.add(lblQuestionName);
		
		JLabel lblquestion = new JLabel("Question: ...");
		lblquestion.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblquestion.setBounds(48, 74, 354, 71);
		contentPane.add(lblquestion);
		
		// Optional part:
		DbQuestions MCQ = new DbQuestions();
		List<TextAnswer> ans = MCQ.multAnswerQuestion(qid);
		
		// Have to put in if statements to check if ans.get(index) exists
		
		JRadioButton rdbtnA1 = new JRadioButton(ans.get(0).getAnswer());
		rdbtnA1.setBounds(80, 157, 312, 23);
		contentPane.add(rdbtnA1);
		
		JRadioButton rdbtnA2 = new JRadioButton(ans.get(1).getAnswer());
		rdbtnA2.setBounds(80, 192, 312, 23);
		contentPane.add(rdbtnA2);
		
		JRadioButton rdbtnA3 = new JRadioButton(ans.get(2).getAnswer());
		rdbtnA3.setBounds(80, 227, 312, 23);
		contentPane.add(rdbtnA3);
		
		JRadioButton rdbtnA4 = new JRadioButton(ans.get(3).getAnswer());
		rdbtnA4.setBounds(80, 262, 312, 23);
		contentPane.add(rdbtnA4);
		
		JRadioButton rdbtnA5 = new JRadioButton(ans.get(4).getAnswer());
		rdbtnA5.setBounds(80, 297, 312, 23);
		contentPane.add(rdbtnA5);
		
		JRadioButton rdbtnA6 = new JRadioButton(ans.get(5).getAnswer());
		rdbtnA6.setBounds(80, 338, 312, 23);
		contentPane.add(rdbtnA6);
		
		// button group to make sure only one can be selected at a time
		ButtonGroup buttons = new ButtonGroup();
		buttons.add(rdbtnA1);
		buttons.add(rdbtnA2);
		buttons.add(rdbtnA3);
		buttons.add(rdbtnA4);
		buttons.add(rdbtnA5);
		buttons.add(rdbtnA6);
		
		
//		for (TextAnswer t : ans){
//			System.out.println(t.getAnswer());
//			JRadioButton button = new JRadioButton(t.getAnswer());
//			buttons.add(button);
//			button.setBounds(80, 157 + (35 * ans.indexOf(t)), 312, 23);
//			contentPane.add(button);
//		}
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (buttons.getSelection() != null){
					if (ans != null){ // filler if
						JOptionPane.showMessageDialog(AnswerMultipleChoice.this, "Correct!");
					} else {
						JOptionPane.showMessageDialog(AnswerMultipleChoice.this, "Incorrect!");
					}
				}

				JOptionPane.showMessageDialog(AnswerMultipleChoice.this, "No answer selected.");
			}
		});
		btnSubmit.setBounds(285, 382, 117, 29);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(153, 382, 117, 29);
		contentPane.add(btnCancel);
		
		
	}
}

package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import answer.TextAnswer;
import assessment.SelectedAssessment;
import dao.DbQuestions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.util.List;
import java.util.ListIterator;

import net.miginfocom.swing.MigLayout;
import question.TextQuestion;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AnswerStudentQuestions extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private TextQuestion tq;
	private int totalPts = 0;
	private boolean nextQ = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnswerStudentQuestions window = new AnswerStudentQuestions();
					window.setVisible(true);
					window.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Initialize the contents of the frame.
	 */
	public AnswerStudentQuestions() {
		//frame = new JFrame();
	    setBounds(300, 300, 853, 564);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JLabel lblQuestions = new JLabel("");
		lblQuestions.setName("");
		lblQuestions.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestions.setBounds(12, 74, 835, 144);
		lblQuestions.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblQuestions);
		//lblQuestions.setText(null);
		
		JTextArea txtAns = new JTextArea();
		txtAns.setName("Ans");
		txtAns.setLineWrap(true);
		txtAns.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAns.setBounds(12, 271, 811, 170);
		getContentPane().add(txtAns);
		
		JButton btnback = new JButton(" Back");
		btnback.setName("back");
		btnback.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnback.setName("back");
		btnback.setBounds(12, 454, 141, 49);
		getContentPane().add(btnback);
		

		
		JLabel lblPointsWorth = new JLabel("");
		lblPointsWorth.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointsWorth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPointsWorth.setBounds(547, 0, 238, 72);
		getContentPane().add(lblPointsWorth);
	//	lblPointsWorth.setName(null);
		
		JLabel lblQuestName = new JLabel("");
		lblQuestName.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuestName.setBounds(0, 0, 238, 72);
		getContentPane().add(lblQuestName);
		//lblQuestName.setText(null);
		
		if (!SelectedAssessment.isSelected()) {
			JOptionPane.showMessageDialog(AnswerStudentQuestions.this, "Asssessment not selected");
			HHLogin frame = new HHLogin();
			frame.setVisible(true);
			frame.setResizable(false);
			if (frame.isShowing()){
				dispose();
			}
		}
		int aid = SelectedAssessment.getAssess().getAid();
		DbQuestions dbQuest = new DbQuestions();
		List<TextQuestion> textQuests = dbQuest.questions_for_assessments(aid);
		ListIterator<TextQuestion>  textQ = textQuests.listIterator();
		
		if (!textQ.hasNext()) {
			JOptionPane.showMessageDialog(AnswerStudentQuestions.this, 
					"There are no questions for this assessment");
			HHSavedAssessments frame = new HHSavedAssessments();
			frame.setVisible(true);
			frame.setResizable(false);
			if (frame.isShowing()){
				dispose();
			}			

		}
		
		tq = textQ.next();
		lblQuestions.setText("Questions: " + tq.getQuestion());
		lblQuestName.setText("Name: " + tq.getName());
		lblPointsWorth.setText("Points: " + new Integer(tq.getPoints()).toString() );	
					
		
		JButton btnSubmit = new JButton("Submit Answer");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String answer = String.valueOf(txtAns.getText());
				
				// AnswerStudentQuestions
				if (answer.isEmpty()) {
					JOptionPane.showMessageDialog(AnswerStudentQuestions.this, "Answer field is empty.");
				} else if (tq == null) {
					JOptionPane.showMessageDialog(AnswerStudentQuestions.this, ".");
				} else {
					if (!tq.hasAnswer()) {
						JOptionPane.showMessageDialog(AnswerStudentQuestions.this, 
								"ERROR in the Database - There are no answers for this question");
						HHSavedAssessments frame = new HHSavedAssessments();
						frame.setVisible(true);
						frame.setResizable(false);
						if (frame.isShowing()){
							dispose();
						}
					}
					
					TextAnswer ta = tq.getCorrectAnswer();
					System.out.println(answer.toString() + ": vs. :" + ta.getAnswer());
					if (ta.isCorrect(answer.toString())) {
						totalPts += tq.getPoints();
					}
					System.out.print(textQ.hasNext());
					System.out.println("gets here");
					System.out.println(textQ.nextIndex());
					System.out.println(textQ.toString());
					if (!textQ.hasNext()) {
						JOptionPane.showMessageDialog(
								AnswerStudentQuestions.this, "Finished assessment, points earned : " + totalPts);
						HHSavedAssessments frame = new HHSavedAssessments();
						frame.setVisible(true);
						frame.setResizable(false);
						if (frame.isShowing()){
							dispose();
						}
					} else {
						tq = textQ.next();
						lblQuestions.setText("Questions: " + tq.getQuestion());
						lblQuestName.setText("Name: " + tq.getName());
						lblPointsWorth.setText("Points: " + new Integer(tq.getPoints()).toString() );	
					}
				}
			}
		});
		
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setName("Submit");
		btnSubmit.setBounds(652, 454, 171, 52);
		contentPane.add(btnSubmit);
		
	}
	
}

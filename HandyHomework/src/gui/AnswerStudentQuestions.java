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

import assessment.SelectedAssessment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class AnswerStudentQuestions extends JFrame {

	private JFrame frame;

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
	 * Create the application.
	 */
	public AnswerStudentQuestions() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 300, 853, 564);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblQuestions = new JLabel("Display your questions here?");
		lblQuestions.setName("dispQuestion");
		lblQuestions.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestions.setBounds(0, 75, 835, 144);
		lblQuestions.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblQuestions);
		
		JTextArea txtAns = new JTextArea();
		txtAns.setName("Ans");
		txtAns.setLineWrap(true);
		txtAns.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAns.setText("testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesSDDDDDDDDDDDDDttesttesttesttest");
		txtAns.setBounds(12, 271, 811, 170);
		frame.getContentPane().add(txtAns);
		
		JButton btnback = new JButton(" Back");
		btnback.setName("back");
		btnback.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnback.setName("back");
		btnback.setBounds(12, 454, 141, 49);
		frame.getContentPane().add(btnback);
		
		JButton btnSubmit = new JButton("Submit Answer");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setName("Submit");
		btnSubmit.setBounds(652, 454, 171, 52);
		frame.getContentPane().add(btnSubmit);
		
		JLabel lblPointsWorth = new JLabel("Points worth: ");
		lblPointsWorth.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointsWorth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPointsWorth.setBounds(547, 0, 238, 72);
		frame.getContentPane().add(lblPointsWorth);
		
		JLabel lblQuestName = new JLabel("Question Name:");
		lblQuestName.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuestName.setBounds(0, 0, 238, 72);
		frame.getContentPane().add(lblQuestName);
		
		if (!SelectedAssessment.isSelected()) {
			JOptionPane.showMessageDialog(AnswerStudentQuestions.this, "No user logged in");
			HHLogin frame = new HHLogin();
			frame.setVisible(true);
			frame.setResizable(false);
			if (frame.isShowing()){
				dispose();
			}
		} else {
			int aid = SelectedAssessment.getAssess().getAid();
			
			
		}
		
		
		
		
	}
}

package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import answer.TextAnswer;
import assessment.SelectedAssessment;
import dao.DbQuestions;
import question.MultQuestion;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AnswerMultipleChoice extends JFrame {

	private JPanel contentPane;
	private MultQuestion mq;
	private int totalPts;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnswerMultipleChoice frame = new AnswerMultipleChoice();
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
	public AnswerMultipleChoice() {
		SwitchForm sf = new SwitchForm();
		setTitle("HandyHomework - Multiple Choice Question");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuestionName = new JLabel("Question Name");
		lblQuestionName.setBounds(166, 11, 150, 33);
		lblQuestionName.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		contentPane.add(lblQuestionName);
		
		JLabel lblquestion = new JLabel("Question: ...");
		lblquestion.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblquestion.setBounds(20, 62, 430, 71);
		contentPane.add(lblquestion);
		
		JRadioButton rdbtnA1 = new JRadioButton();
		rdbtnA1.setName("rdbtnA1");
		rdbtnA1.setBounds(166, 153, 312, 23);
		contentPane.add(rdbtnA1);
		rdbtnA1.setVisible(false);
		
		JRadioButton rdbtnA2 = new JRadioButton();
		rdbtnA2.setName("rdbtnA2");
		rdbtnA2.setBounds(166, 189, 312, 23);
		contentPane.add(rdbtnA2);
		rdbtnA2.setVisible(false);
		
		JRadioButton rdbtnA3 = new JRadioButton();
		rdbtnA3.setName("rdbtnA3");
		rdbtnA3.setBounds(166, 229, 312, 23);
		contentPane.add(rdbtnA3);
		rdbtnA3.setVisible(false);
		
		JRadioButton rdbtnA4 = new JRadioButton();
		rdbtnA4.setName("rdbtnA4");
		rdbtnA4.setBounds(166, 265, 312, 23);
		contentPane.add(rdbtnA4);
		rdbtnA4.setVisible(false);
		
		JRadioButton rdbtnA5 = new JRadioButton();
		rdbtnA5.setName("rdbtnA5");
		rdbtnA5.setBounds(166, 297, 312, 23);
		contentPane.add(rdbtnA5);
		rdbtnA5.setVisible(false);
		
		JRadioButton rdbtnA6 = new JRadioButton();
		rdbtnA6.setName("rdbtnA6");
		rdbtnA6.setBounds(166, 323, 312, 23);
		contentPane.add(rdbtnA6);
		rdbtnA6.setVisible(false);
		
		// button group to make sure only one can be selected at a time
		ButtonGroup buttons = new ButtonGroup();
		buttons.add(rdbtnA1);
		buttons.add(rdbtnA2);
		buttons.add(rdbtnA3);
		buttons.add(rdbtnA4);
		buttons.add(rdbtnA5);
		buttons.add(rdbtnA6);
		
		if (!SelectedAssessment.isSelected()) {
			JOptionPane.showMessageDialog(AnswerMultipleChoice.this, 
					"Asssessment not selected");
			HHLogin frame = new HHLogin();
			sf.switchForm(frame);
			if (frame.isShowing()){
				dispose();
			}
		}
		
		int aid = SelectedAssessment.getAssess().getAid();
		DbQuestions MCQ = new DbQuestions();
		List<MultQuestion> qns = MCQ.multChoiceQuestions(aid);
		ListIterator<MultQuestion>  multQ = qns.listIterator();
		
		if (!multQ.hasNext()) { 
			JOptionPane.showMessageDialog(AnswerMultipleChoice.this, 
					"There are no questions for this assessment");
			HHSavedAssessments frame = new HHSavedAssessments();
			sf.switchForm(frame);
			if (frame.isShowing()){
				dispose();
			}			
		} else {
			mq = multQ.next();
			lblquestion.setText("Questions: " + mq.getQuestion());
			List<TextAnswer> mqAnswers = mq.getAnswers();
			setButtonText(buttons, mqAnswers);
		}		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setName("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sel =  getSelectedText(buttons);
				if (sel == null) {
					JOptionPane.showMessageDialog(AnswerMultipleChoice.this, "No answer selected.");
				} else {
					if (!mq.hasAnswer()) {
						JOptionPane.showMessageDialog(AnswerMultipleChoice.this, 
								"ERROR in the Database - There are no answers for this question");
						HHSavedAssessments frame = new HHSavedAssessments();
						sf.switchForm(frame);
						if (frame.isShowing()){
							dispose();
						}
					}
					
					if  (mq.isCorrectAnswer(sel)) {
						totalPts += mq.getPoints();
					}
					
					if (!multQ.hasNext()) {
						System.out.println(totalPts);
						JOptionPane.showMessageDialog(AnswerMultipleChoice.this, 
								"Finished assessment, points earned: " + totalPts);
						HHSavedAssessments frame = new HHSavedAssessments();
						sf.switchForm(frame);
						if (frame.isShowing()){
							dispose();
						}			
					} else {
						mq = multQ.next();
						lblquestion.setText("Questions: " + mq.getQuestion());
						List<TextAnswer> mqAnswers = mq.getAnswers();
						setButtonText(buttons, mqAnswers);
					}
				}
				
				
			}
		});	
    	
		btnSubmit.setBounds(285, 370, 117, 29);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHSavedAssessments frame = new HHSavedAssessments();
				sf.switchForm(frame);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		btnCancel.setBounds(156, 370, 117, 29);
		contentPane.add(btnCancel);
	}
	
    public String setButtonText(ButtonGroup buttonGroup, List<TextAnswer> mqAnswers) {
    	int i = 0;
    	int size = mqAnswers.size();
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (i < mqAnswers.size()) {
                button.setText(mqAnswers.get(i).getAnswer());
                button.setVisible(true);
                i++;
            } else {
            	button.setVisible(false);
            }
        }
        return null;
    }
    
    public String getSelectedText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
            	return button.getText();
            }
        }
        return null;
    }
}

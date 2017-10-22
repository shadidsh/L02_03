package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HHFormFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHFormFrame frame = new HHFormFrame();
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
	public HHFormFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblQuestionForm = new JLabel("Question Form  ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblQuestionForm, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblQuestionForm, 79, SpringLayout.WEST, contentPane);
		lblQuestionForm.setFont(new Font("Menlo", Font.ITALIC, 20));
		lblQuestionForm.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblQuestionForm);
		
		JLabel lblQuestionName = new JLabel("Question Name:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblQuestionName, 21, SpringLayout.SOUTH, lblQuestionForm);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblQuestionName, 10, SpringLayout.WEST, contentPane);
		lblQuestionName.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblQuestionName);
		
		textField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, -5, SpringLayout.NORTH, lblQuestionName);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblQuestionName);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, 124, SpringLayout.EAST, lblQuestionName);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterYourQuestion = new JLabel("Enter your question here: *");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblEnterYourQuestion, 26, SpringLayout.SOUTH, lblQuestionName);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblEnterYourQuestion, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblEnterYourQuestion);
		
		JTextArea textArea = new JTextArea();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textArea, 7, SpringLayout.SOUTH, lblEnterYourQuestion);
		sl_contentPane.putConstraint(SpringLayout.WEST, textArea, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textArea, 88, SpringLayout.SOUTH, lblEnterYourQuestion);
		contentPane.add(textArea);
		
		JLabel lblFinalAnswer = new JLabel("Final Answer: *");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblFinalAnswer, 12, SpringLayout.SOUTH, textArea);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblFinalAnswer, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblFinalAnswer);
		
		textField_1 = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_1, -5, SpringLayout.NORTH, lblFinalAnswer);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, lblFinalAnswer);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_1, 81, SpringLayout.EAST, lblFinalAnswer);
		textField_1.setColumns(10);
		contentPane.add(textField_1);
		
		JLabel lblNumberOfMarks = new JLabel("Number of Marks Awarded: *");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfMarks, 26, SpringLayout.SOUTH, textField_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfMarks, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNumberOfMarks);
		
		JSpinner spinner = new JSpinner();
		sl_contentPane.putConstraint(SpringLayout.NORTH, spinner, -5, SpringLayout.NORTH, lblNumberOfMarks);
		sl_contentPane.putConstraint(SpringLayout.WEST, spinner, 6, SpringLayout.EAST, lblNumberOfMarks);
		contentPane.add(spinner);
		
		JScrollBar scrollBar = new JScrollBar();
		sl_contentPane.putConstraint(SpringLayout.EAST, lblQuestionForm, -6, SpringLayout.WEST, scrollBar);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollBar, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollBar, 241, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollBar, 0, SpringLayout.EAST, contentPane);
		contentPane.add(scrollBar);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// add the question to database and produce successful/unsuccessful msg box
			}
		});
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HandyHomeworkMainPage().setVisible(true);;
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnCancel, 114, SpringLayout.SOUTH, textArea);
		sl_contentPane.putConstraint(SpringLayout.EAST, textArea, 0, SpringLayout.EAST, btnCancel);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCancel, 386, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSubmit, 0, SpringLayout.NORTH, btnCancel);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnSubmit, -6, SpringLayout.WEST, btnCancel);
		contentPane.add(btnCancel);
	}
}

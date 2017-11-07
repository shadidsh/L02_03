package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;

//import net.miginfocom.swing.MigLayout;

import question.QuestionAbstract;

import javax.swing.JLabel;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
//import com.toedter.calendar.JDateChooser;
//import com.toedter.components.JSpinField;

import assessment.Assessment;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;

public class HHCreateAssessmentForm extends JFrame  {

	private JFrame frame;
	private JTextField assessmentNameField;
	private JTextField titleField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//HHCreateAssessmentForm window = new HHCreateAssessmentForm();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HHCreateAssessmentForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblCreateAssessmentForm = new JLabel("Create Assessment Form");
		lblCreateAssessmentForm.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblAssessmentName = new JLabel("Assessment Name:");
		
		assessmentNameField = new JTextField();
		assessmentNameField.setColumns(10);
		
		JLabel lblDueDate = new JLabel("Due Date:");
		
		JLabel lblTotalPointsAwarded = new JLabel("Total Points Awarded:");		
		
		JLabel lblAssessmentTitle = new JLabel("Assessment Title:");
		
		titleField = new JTextField();
		titleField.setColumns(10);
		
		//JDateChooser dateChooser = new JDateChooser();
		
		JSpinner spinner = new JSpinner();
		
		JCheckBox chckbxContainsMCQ = new JCheckBox("Contains multiple choice");

		JCheckBox chckbxOptionalAssessment = new JCheckBox("Optional Assessment");
		
		JSplitPane splitPane = new JSplitPane();

		JButton btnAddQuestion = new JButton("Add Question");
		btnAddQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.setVisible(false);
				HHFormFrame newQuestion = new HHFormFrame();
				newQuestion.setVisible(true);
				newQuestion.setAlwaysOnTop(true);
			}
		});
		
		// JList for questions
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			QuestionAbstract[] values = new QuestionAbstract[] {};
			public int getSize() {
				return values.length;
			}
			public QuestionAbstract getElementAt(int index) {
				return values[index];
			}
			public void addQuestion(QuestionAbstract q){
				values[values.length] = q;
			}
		});
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		splitPane.setLeftComponent(list);
		
		JLabel lblSelectedQuestion = new JLabel("No questions in assessment");
		
		if (list.getModel().getSize() != 0 && list.isSelectionEmpty()){
			lblSelectedQuestion = new JLabel("No questions selected");
		} else if (! list.isSelectionEmpty()) {
			lblSelectedQuestion = new JLabel(list.getSelectedValue().toString());
		}
		
		splitPane.setRightComponent(lblSelectedQuestion);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new HandyHomeworkMainPage().setVisible(true);
			}
		});
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the fields
				String name = String.valueOf(assessmentNameField.getText()); 
				String title = String.valueOf(titleField.getText());
				
				// get date thing? TODO
				// Date due = dateChooser.getDate();
				//Calendar c = new GregorianCalendar();
				//c.setTime(due);
				// points
				
				int totalPoints = (int) (spinner.getValue());
				// booleans
				
				boolean mult = chckbxContainsMCQ.isSelected();
				boolean opt = chckbxOptionalAssessment.isSelected();
				
				System.out.println("Assessment name is :" + name);
				if (name.isEmpty() || title.isEmpty()) {
					JOptionPane.showInputDialog(HHCreateAssessmentForm.this, "One or more required fields are empty");
				} else {
					if (totalPoints == 0){
						//Assessment a1 = new Assessment(name, title, mult, opt);
					} else{
						//Assessment a1 = new Assessment(name, title, mult, opt, c, totalPoints);
					}
				// TODO
				// add assessment to DB and return success/fail msg
				}
				// Upon confirmation, open the saved assessments!!
				frame.dispose();
				new HHSavedAssessments().setVisible(true);
			}
		});		
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(62)
							.addComponent(lblCreateAssessmentForm, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblAssessmentTitle)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(titleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblAssessmentName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(assessmentNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDueDate)
									.addGap(18)
									//.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblTotalPointsAwarded)
									.addGap(18)
									.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(chckbxContainsMCQ)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(chckbxOptionalAssessment))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(23)
									.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnCancel, Alignment.TRAILING)
								.addComponent(btnAddQuestion))
							.addGap(28)))
					.addGap(137))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(192)
					.addComponent(btnCreate)
					.addContainerGap(445, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCreateAssessmentForm)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(assessmentNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAssessmentName))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAssessmentTitle)
						.addComponent(titleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDueDate)
						//.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalPointsAwarded)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddQuestion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxContainsMCQ)
						.addComponent(chckbxOptionalAssessment))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreate)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		
		
		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date()); // will only show the current time
		
//		JList<QuestionAbstract> list = new JList();
//		splitPane.setLeftComponent(list);
//		// LIST MUST ADD QUESTIONS AS THEY ARE CREATED
//		list.setModel(new AbstractListModel() {
//			public int getSize() {
//				return values.length;
//			}
//			public Object getElementAt(int index) {
//				return values[index];
//			}
//		});
//		
//		JLabel lblselectedQuestion = new JLabel("No question selected"); 
//		if (){
//			JLabel lblselectedQuestion = new JLabel("No questions in this assessment");
//		} else if (! list.isSelectionEmpty()){
//			lblselectedQuestion = new JLabel(list.getSelectedValue().toString());
//		}
//		splitPane.setRightComponent(lblselectedQuestion);
		
		frame.getContentPane().setLayout(groupLayout);
	}
}

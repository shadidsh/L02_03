package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
//import net.miginfocom.swing.MigLayout;
import question.QuestionAbstract;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class HHCreateAssessmentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField assessmentNameField;
	private JTextField titleField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHCreateAssessmentFrame frame = new HHCreateAssessmentFrame();
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
	public HHCreateAssessmentFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCreateAssessmentForm = new JLabel("Create Assessment Form");
		lblCreateAssessmentForm.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblAssessmentName = new JLabel("* Assessment Name:");
		
		assessmentNameField = new JTextField();
		assessmentNameField.setColumns(10);
		
		JLabel lblAssessmentTitle = new JLabel("* Assessment Title:");
		
		titleField = new JTextField();
		titleField.setColumns(10);
		
		JLabel lblDueDate = new JLabel("Due Date:");
		
		JLabel lblTotalPointsAwarded = new JLabel("Total Points Awarded:");
		
		JSpinner spinner = new JSpinner();
		
		JSplitPane splitPane = new JSplitPane();
		
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
		
		JButton btnAddQuestion = new JButton("Add Question");
		btnAddQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHFormFrame newQuestion = new HHFormFrame();
				newQuestion.setVisible(true);
				newQuestion.setAlwaysOnTop(true);
			}
		});
		
		JCheckBox chckbxContainsMCQ = new JCheckBox("Contains multiple choice");
		
		JCheckBox chckbxOptionalAssessment = new JCheckBox("Optional Assessment");
		
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
					// msg box to say mand fields are empty
					JOptionPane.showMessageDialog(HHCreateAssessmentFrame.this, "One or more mandatory fields are empty.");
				} else {
					Calendar due = Calendar.getInstance();
					 due.set(2017, 9, 25, 10, 05, 30);	
						if (totalPoints > 100){
							JOptionPane.showMessageDialog(HHCreateAssessmentFrame.this, "Weight of an assessment can only be 100%");
							//Assessment a1 = new Assessment(name, title, mult, opt);
						} else{
							db.DbConnection.insertAssessment(title, name, due, false,  ((float) totalPoints/100));
							//Assessment a1 = new Assessment(name, title, mult, opt, c, totalPoints);
						}
					 													// Weight of assessment not points and shud be less than 1 (0 to 1)
					
					
					

				// TODO
				// add assessment to DB and return success/fail msg
				}
				// Upon confirmation, open the saved assessments!!
				new HHSavedAssessments().setVisible(true);
				dispose();
			}
		});		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HandyHomeworkMainPage().setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCreateAssessmentForm))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(25)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
								.addComponent(btnAddQuestion, Alignment.LEADING)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblTotalPointsAwarded)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblDueDate, Alignment.LEADING)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblAssessmentTitle)
									.addGap(18)
									.addComponent(titleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblAssessmentName)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(assessmentNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxOptionalAssessment)
						.addComponent(chckbxContainsMCQ))
					.addContainerGap(218, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(210, Short.MAX_VALUE)
					.addComponent(btnCreate)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancel)
					.addGap(48))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addComponent(lblCreateAssessmentForm)
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAssessmentName)
						.addComponent(assessmentNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAssessmentTitle)
						.addComponent(titleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(lblDueDate)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalPointsAwarded)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddQuestion)
					.addGap(18)
					.addComponent(chckbxContainsMCQ)
					.addGap(18)
					.addComponent(chckbxOptionalAssessment)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnCreate))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		
		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date()); // will only show the current time
	}
}

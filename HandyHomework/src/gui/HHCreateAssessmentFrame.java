package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import course.SelectedCourse;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JOptionPane;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;

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
	public HHCreateAssessmentFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCreateAssessmentForm = new JLabel("Create Assessment Form");
		lblCreateAssessmentForm.setBounds(10, 40, 225, 26);
		lblCreateAssessmentForm.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblAssessmentName = new JLabel("* Assessment Name:");
		lblAssessmentName.setBounds(30, 85, 150, 14);
		
		assessmentNameField = new JTextField();
		assessmentNameField.setBounds(160, 80, 150, 20);
		assessmentNameField.setColumns(10);
		
		JLabel lblAssessmentTitle = new JLabel("* Assessment Title:");
		lblAssessmentTitle.setBounds(30, 120, 150, 14);
		
		titleField = new JTextField();
		titleField.setBounds(160, 115, 150, 20);
		titleField.setColumns(10);
		
		JLabel lblTotalPointsAwarded = new JLabel("Total Points Awarded:");
		lblTotalPointsAwarded.setBounds(30, 154, 150, 14);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(160, 150, 150, 20);
		
		JComponent field = ((JSpinner.DefaultEditor) spinner.getEditor());
	    Dimension prefSize = field.getPreferredSize();
	    prefSize = new Dimension(40, prefSize.height);
	    field.setPreferredSize(prefSize);
	    
		JCheckBox chckbxContainsMCQ = new JCheckBox("Contains multiple choice");
		chckbxContainsMCQ.setBounds(30, 195, 190, 23);
		
		JCheckBox chckbxOptionalAssessment = new JCheckBox("Optional Assessment");
		chckbxOptionalAssessment.setBounds(30, 230, 160, 23);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(250, 205, 125, 40);
		contentPane.getRootPane().setDefaultButton(btnCreate);
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
							JOptionPane.showMessageDialog(HHCreateAssessmentFrame.this, "Maximum assessment weight is 100.");
							//Assessment a1 = new Assessment(name, title, mult, opt);
						} else if (!SelectedCourse.isSelected()) {
							JOptionPane.showMessageDialog(HHCreateAssessmentFrame.this, 
									"Attempting to insert Assessment without a course being selected");
						}	else {
							try {
								
								db.DbConnection.insertAssessment(title,
										SelectedCourse.getCourse().getcID(), name, due, false,  ((float) totalPoints/100));
								
								// Upon confirmation, open the saved assessments!!
								HHSavedAssessments frame = new HHSavedAssessments();
								frame.setVisible(true);
								frame.setResizable(false);
								frame.setResizable(false);
								if (frame.isShowing()){
									dispose();
								}
							} catch (NullPointerException e1){
								System.out.println("Could not access database."); 
								e1.printStackTrace();
								JOptionPane.showMessageDialog(HHCreateAssessmentFrame.this, "Could not access the database -" + "\nplease check your connection and try again.");
							}
						}
				}
				
			}
		});		
		
		JButton btnCancel = new JButton("\u2190Back");
		btnCancel.setBounds(10, 10, 75, 25);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHSavedAssessments frame = new HHSavedAssessments();
				frame.setVisible(true);
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lblCreateAssessmentForm);
		contentPane.add(lblTotalPointsAwarded);
		contentPane.add(spinner);
		contentPane.add(lblAssessmentTitle);
		contentPane.add(titleField);
		contentPane.add(lblAssessmentName);
		contentPane.add(assessmentNameField);
		contentPane.add(chckbxOptionalAssessment);
		contentPane.add(chckbxContainsMCQ);
		contentPane.add(btnCreate);
		contentPane.add(btnCancel);		
		
		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date()); // will only show the current time
	}
}

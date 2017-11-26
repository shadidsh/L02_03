package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import course.SelectedCourse;
import dao.DbAssessment;

import javax.swing.JLabel;
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
		setTitle("HandyHomework - Create Assessment");
		this.setName("SavedAssess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCreateAssessmentForm = new JLabel("Assessment Form");
		lblCreateAssessmentForm.setBounds(170, 22, 230, 28);
		lblCreateAssessmentForm.setFont(new Font("Menlo", Font.BOLD | Font.ITALIC, 23));
		
		JLabel lblAssessmentName = new JLabel("* Assessment Name:");
		lblAssessmentName.setBounds(30, 85, 150, 14);
		
		assessmentNameField = new JTextField();
		assessmentNameField.setBounds(160, 80, 150, 20);
		assessmentNameField.setColumns(10);
		assessmentNameField.setName("assess");
		
		JLabel lblAssessmentTitle = new JLabel("* Assessment Title:");
		lblAssessmentTitle.setBounds(30, 120, 150, 14);
		
		titleField = new JTextField();
		titleField.setBounds(160, 115, 150, 20);
		titleField.setColumns(10);
		titleField.setName("title");
		
		JLabel lblTotalPointsAwarded = new JLabel("Total Points Awarded:");
		lblTotalPointsAwarded.setBounds(30, 154, 150, 14);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(170, 151, 150, 20);
		spinner.setName("spin");
		
		JComponent field = ((JSpinner.DefaultEditor) spinner.getEditor());
	    Dimension prefSize = field.getPreferredSize();
	    prefSize = new Dimension(40, prefSize.height);
	    field.setPreferredSize(prefSize);
		
		JButton btnCreate = new JButton("Create Assessment");
		btnCreate.setBounds(205, 273, 150, 40);
		btnCreate.setName("create");
		contentPane.getRootPane().setDefaultButton(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the fields
				String name = String.valueOf(assessmentNameField.getText()); 
				String title = String.valueOf(titleField.getText());
				
				int totalPoints = (int) (spinner.getValue());
				
				System.out.println("Assessment name is :" + name);
				if (name.isEmpty() || title.isEmpty()) {
					JOptionPane.showMessageDialog(HHCreateAssessmentFrame.this, "One or more mandatory fields are empty.");
				} else {
					Calendar due = Calendar.getInstance();
					 due.set(2017, 9, 25, 10, 05, 30);	
						if (totalPoints > 100){
							JOptionPane.showMessageDialog(HHCreateAssessmentFrame.this, "Maximum assessment weight is 100.");
						} else if (!SelectedCourse.isSelected()) {
							JOptionPane.showMessageDialog(HHCreateAssessmentFrame.this, 
									"Attempting to insert Assessment without a course being selected");
						}	else {
							try {
								DbAssessment dbAssess = new DbAssessment();
								dbAssess.insertAssessment(title, SelectedCourse.getCourse().getcID(),
										name, due, false,  ((float) totalPoints/100));
								
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
		
		JButton btnCancel = new JButton("\u2190 Back");
		btnCancel.setName("back");
		btnCancel.setBounds(6, 26, 86, 28);
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
		contentPane.add(btnCreate);
		contentPane.add(btnCancel);		
		
		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date()); // will only show the current time
	}
}

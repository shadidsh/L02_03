package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HHCreateCoursePage extends JFrame {

	private JPanel contentPane;
	private JTextField courseCodeField;
	private JTextField courseTitleField;
	private JButton btnCreate;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHCreateCoursePage frame = new HHCreateCoursePage();
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
	public HHCreateCoursePage() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCreateCourse = new JLabel("Create Course");
		lblCreateCourse.setBounds(138, 23, 189, 30);
		lblCreateCourse.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 24));
		
		JLabel lblCourseCode = new JLabel("Course Code:");
		lblCourseCode.setBounds(31, 87, 84, 16);
		
		JLabel lblCourseTitle = new JLabel("Course Title: ");
		lblCourseTitle.setBounds(31, 131, 84, 16);
		
		courseCodeField = new JTextField();
		courseCodeField.setBounds(120, 82, 130, 26);
		courseCodeField.setColumns(10);
		
		courseTitleField = new JTextField();
		courseTitleField.setBounds(120, 126, 130, 26);
		courseTitleField.setColumns(10);
		contentPane.setLayout(null);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (courseCodeField.getText().isEmpty() || courseTitleField.getText().isEmpty()){
					JOptionPane.showMessageDialog(HHCreateCoursePage.this, "One or more fields are empty.");
				} else {
					// TODO
					// insert course into DB here
					
					HHViewCoursesPage frame = new HHViewCoursesPage();
					frame.setVisible(true);
					frame.setResizable(false);
					if (frame.isShowing()){
						dispose();
					}
				}
			}
		});
		btnCreate.setBounds(133, 174, 96, 29);
		contentPane.add(btnCreate);
		contentPane.add(lblCourseCode);
		contentPane.add(courseCodeField);
		contentPane.add(lblCourseTitle);
		contentPane.add(courseTitleField);
		contentPane.add(lblCreateCourse);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHViewCoursesPage frame = new HHViewCoursesPage();
				frame.setVisible(true);
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		btnCancel.setBounds(229, 174, 103, 29);
		contentPane.add(btnCancel);
	}
}

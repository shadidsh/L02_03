package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStudentsPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentsPage frame = new AddStudentsPage();
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
	public AddStudentsPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Students");
		lblNewLabel.setBounds(123, 18, 184, 38);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		contentPane.add(lblNewLabel);
		
		//final JFileChooser fc = new JFileChooser();
		
		JButton btnChoosecsvFile = new JButton("Upload a .csv file");
		btnChoosecsvFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//int returnVal = fc.showOpenDialog(contentPane);
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "CSV", "csv");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(contentPane);
			}
		});
		btnChoosecsvFile.setBounds(128, 109, 152, 48);
		contentPane.add(btnChoosecsvFile);
		
		JLabel lblChooseOneOf = new JLabel("Choose one of the following methods:");
		lblChooseOneOf.setBounds(94, 74, 241, 16);
		contentPane.add(lblChooseOneOf);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddOneStudentForm frame = new AddOneStudentForm();
				frame.setVisible(true);
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		btnAddStudent.setBounds(126, 169, 154, 48);
		contentPane.add(btnAddStudent);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 72, 30);
		contentPane.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewStudentsPage frame = new ViewStudentsPage();
				frame.setVisible(true);		
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
	}
}

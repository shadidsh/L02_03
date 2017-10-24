package gui;
import db.DbConnection;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class HHSavedQuestionsPage extends JFrame {

	private JPanel contentPane;
	private JList listQuestion_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHSavedQuestionsPage frame = new HHSavedQuestionsPage();
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
	public HHSavedQuestionsPage() {
		setTitle("HandyHomework");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSavedQuestions = new JLabel("Saved Questions");
		lblSavedQuestions.setMaximumSize(new Dimension(100, 30));
		lblSavedQuestions.setFont(new Font("Dialog", Font.ITALIC, 20));
		lblSavedQuestions.setBounds(150, 13, 175, 31);
		contentPane.add(lblSavedQuestions);
		
		JLabel lblNewLabel = new JLabel("There are no saved questions");
		
		Connection conn = DbConnection.getConnection();
		String res = "";
		DefaultListModel<String> lstQuestion = new DefaultListModel<>();
		
		JList listQuestion = new JList<>(lstQuestion);

		
		res = "<html>name question Answer<br>";
		ArrayList questions = new ArrayList();
			try {
				PreparedStatement stat = conn.prepareStatement("SELECT * FROM sware.textquestions;");
				ResultSet Rs = stat.executeQuery();				
				
				while (Rs.next()) {
					
					String name = Rs.getString(2);
					String questionContent = Rs.getString(3);
					String answer = Rs.getString(4);
					String value = Rs.getString(5);
					
					lstQuestion.addElement(name);
					questions.add(name);
					res +=  Rs.getString(2) + "," +  Rs.getString(3) + "," 
							+ Rs.getString(4) + "," + Rs.getInt(5) +  "<br>";
				}
				System.out.println(lstQuestion);
				System.out.println(res);
				
				Rs.close();
				conn.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			res += "</html>";
		
			listQuestion.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					int ind = e.getFirstIndex();
					System.out.println(questions.get(ind));
					
					
				}
			});
			listQuestion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	

			
			
		lblNewLabel.setText("");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(181, 110, 397, 152);
		contentPane.add(lblNewLabel);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(419, 307, 120, 30);
		contentPane.add(btnMainMenu);
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HandyHomeworkMainPage().setVisible(true);
			}
		});
		
		listQuestion.setBounds(12, 84, 100, 100);
		contentPane.add(listQuestion);
		
		
		
	}
}
